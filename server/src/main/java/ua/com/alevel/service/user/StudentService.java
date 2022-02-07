package ua.com.alevel.service.user;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.educationalprocess.StudentGroupRepository;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.AbstractService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService extends AbstractService<Student, StudentRepository> {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;

    public StudentService(
            StudentRepository studentRepository,
            RoleRepository roleRepository,
            AccountRepository accountRepository,
            StudentGroupRepository studentGroupRepository,
            CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper) {
        super(studentRepository, crudRepositoryHelper);
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.studentGroupRepository = studentGroupRepository;
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
        return crudRepositoryHelper.create(studentRepository, student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<Student> update(Student student, UUID uuid) {
        boolean isExistStudent = studentRepository.existsByUuid(uuid);
        if (isExistStudent) {
            Student studentReturned = studentRepository.findByUuid(uuid).get();
            Set<StudentGroup> groupForDelete;
            if (CollectionUtils.isNotEmpty(student.getStudentGroups())) {
                List<UUID> groupUuids = student.getStudentGroups().stream()
                        .map(StudentGroup::getUuid)
                        .toList();
                groupForDelete = studentReturned.getStudentGroups().stream()
                        .filter(group -> !groupUuids.contains(group.getUuid()))
                        .collect(Collectors.toSet());
            } else {
                groupForDelete = studentReturned.getStudentGroups();
            }
            for (StudentGroup studentGroup : groupForDelete) {
                studentGroup.removeStudent(studentReturned);
                studentGroupRepository.save(studentGroup);
            }
            Set<StudentGroup> groups = prepareStudentGroupForSave(student);
            for (StudentGroup group : groups) {
                group.addStudent(studentReturned);
            }
            Student savedStudent = studentRepository.save(studentReturned);
            return Optional.of(savedStudent);
        }
        return Optional.empty();
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
}
