package ua.com.alevel.service.user;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Course;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.educationalprocess.StudentGroupRepository;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.finance.AttendanceRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.AbstractService;
import ua.com.alevel.service.auth.KeycloakService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService extends AbstractService<Student, StudentRepository> {

    private final String mockPassword;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final AttendanceRepository attendanceRepository;
    private final KeycloakService keycloakService;
    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;

    public StudentService(
            @Value("${lms.mock.password}") String mockPassword,
            StudentRepository studentRepository,
            RoleRepository roleRepository,
            AccountRepository accountRepository,
            StudentGroupRepository studentGroupRepository,
            AttendanceRepository attendanceRepository,
            KeycloakService keycloakService,
            CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper) {
        super(studentRepository, crudRepositoryHelper);
        this.mockPassword = mockPassword;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.attendanceRepository = attendanceRepository;
        this.keycloakService = keycloakService;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Student create(Student student) {
        Account account = createAccountForStudent(student);
        student.setAccount(account);
        student.setRole(roleRepository.findByName("STUDENT"));
        Set<StudentGroup> groups = prepareStudentGroupForSave(student);
        for (StudentGroup group : groups) {
            group.addStudent(student);
        }
        UUID uuid = createKeycloakStudent(student);
        student.setUuid(uuid);
        return crudRepositoryHelper.create(studentRepository, student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<Student> update(Student student, UUID uuid) {
        boolean isExistStudent = studentRepository.existsByUuid(uuid);
        if (isExistStudent) {
            Student studentReturned = studentRepository.findByUuid(uuid).get();
            for (StudentGroup group : new HashSet<>(studentReturned.getStudentGroups())) {
                studentReturned.removeStudentGroup(group);
            }
            studentRepository.save(studentReturned);
            student.setId(studentReturned.getId());
            student.setAccount(studentReturned.getAccount());
            student.setRole(studentReturned.getRole());
            student.setCourses(studentReturned.getCourses());
            student.setLessons(studentReturned.getLessons());
            Set<StudentGroup> groups = prepareStudentGroupForSave(student);
            student.setStudentGroups(groups);
            for (StudentGroup group : groups) {
                group.addStudent(student);
            }
            Student savedStudent = studentRepository.save(student);
            keycloakService.updateUser(savedStudent);
            return Optional.of(savedStudent);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteByUuids(Set<UUID> uuids) {
        Set<Student> students = studentRepository.findByUuidIn(uuids);
        for (Student student : students) {
            for (StudentGroup studentGroup : new HashSet<>(student.getStudentGroups())) {
                student.removeStudentGroup(studentGroup);
            }
            for (Course course : new HashSet<>(student.getCourses())) {
                student.removeCourse(course);
            }
        }
        attendanceRepository.deleteByStudent_UuidIn(uuids);
        studentRepository.deleteByUuidIn(uuids);
        keycloakService.deleteUserByUuids(uuids);
    }

    private Account createAccountForStudent(Student student) {
        Account account = new Account();
        account.setName("Student account");
        account.setDescription("Account " + student.getFirstName() + " " + student.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    private Set<StudentGroup> prepareStudentGroupForSave(Student student) {
        return student.getStudentGroups().stream()
                .filter(Objects::nonNull)
                .map(group -> studentGroupRepository.getByUuid(group.getUuid()))
                .collect(Collectors.toSet());
    }

    private UUID createKeycloakStudent(Student student) {
        student.setPassword(mockPassword);
        Response result = keycloakService.createUser(student);
        String uuid = CreatedResponseUtil.getCreatedId(result);
        keycloakService.assignRoles(uuid, List.of("STUDENT"));
        return UUID.fromString(uuid);
    }
}
