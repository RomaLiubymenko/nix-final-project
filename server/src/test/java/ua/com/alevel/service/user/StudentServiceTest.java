package ua.com.alevel.service.user;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Role;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.finance.AttendanceRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.auth.KeycloakService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceTest.class);

    @Mock
    StudentRepository studentRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AttendanceRepository attendanceRepository;

    @Mock
    KeycloakService keycloakService;

    @Mock
    CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;

    @InjectMocks
    StudentService studentService;

    private Role role;
    private UUID studentUuid;
    private UUID keycloakUuid;
    private Account account;
    private Student student;

    @BeforeAll
    public static void setUp() {
        LOG.info("Start test: {}", StudentService.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOG.info("Complete testing: {}", StudentService.class.getSimpleName());
    }

    @BeforeEach
    void init() {
        studentUuid = UUID.fromString("3315439c-66d0-4e59-b88a-8bdef03bc07c");
        keycloakUuid = UUID.fromString("0700dd5e-3c8f-4551-af17-ead9595b4db0");

        student = new Student();
        student.setUsername("Student");
        student.setFirstName("Student");
        student.setLastName("Student");
        student.setEmail("student@sd.sd");
        student.setGender(GenderType.MALE);
        student.setBirthDay(LocalDate.now());
        student.setActivated(true);

        account = new Account();
        account.setName("Student account");
        account.setDescription("Account " + student.getFirstName() + " " + student.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);

        role = new Role();
        role.setName("STUDENT");
        role.setUsers(Set.of(student));
    }

    @Test
    void shouldCreateStudent() {
        try (MockedStatic<CreatedResponseUtil> utilities = Mockito.mockStatic(CreatedResponseUtil.class)) {
            Mockito.when(accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(account);
            Mockito.when(roleRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(role);
            Mockito.when(keycloakService.createUser(ArgumentMatchers.any(Student.class))).thenReturn(null);
            utilities.when(() -> CreatedResponseUtil.getCreatedId(ArgumentMatchers.any())).thenReturn(String.valueOf(keycloakUuid));
            Mockito.doNothing().when(keycloakService).assignRoles(ArgumentMatchers.any(String.class), ArgumentMatchers.any(List.class));
            Mockito.when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);

            Student actual = studentService.create(student);

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            ArgumentCaptor<Student> keycloakStudentCaptor = ArgumentCaptor.forClass(Student.class);
            ArgumentCaptor<String> keycloakUuidCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<List<String>> roleListCaptor = ArgumentCaptor.forClass(List.class);
            ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

            Mockito.verify(accountRepository, Mockito.times(1)).save(accountCaptor.capture());
            Mockito.verify(keycloakService, Mockito.times(1)).createUser(keycloakStudentCaptor.capture());
            Mockito.verify(keycloakService, Mockito.times(1)).assignRoles(keycloakUuidCaptor.capture(), roleListCaptor.capture());
            Mockito.verify(studentRepository, Mockito.times(1)).save(studentCaptor.capture());

            MatcherAssert.assertThat(account.getName(), Matchers.equalTo(accountCaptor.getValue().getName()));
            MatcherAssert.assertThat(account.getDescription(), Matchers.equalTo(accountCaptor.getValue().getDescription()));
            MatcherAssert.assertThat(account.getBalance(), Matchers.equalTo(accountCaptor.getValue().getBalance()));
            MatcherAssert.assertThat(student, Matchers.equalTo(keycloakStudentCaptor.getValue()));
            MatcherAssert.assertThat(String.valueOf(keycloakUuid), Matchers.equalTo(keycloakUuidCaptor.getValue()));
            MatcherAssert.assertThat(List.of("STUDENT"), Matchers.equalTo(roleListCaptor.getValue()));
            MatcherAssert.assertThat(student, Matchers.equalTo(studentCaptor.getValue()));
            MatcherAssert.assertThat(actual, Matchers.equalTo(student));
        }
    }

    @Test
    void shouldUpdateStudent() {
        Mockito.when(studentRepository.existsByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(true);
        Mockito.when(studentRepository.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        Mockito.doNothing().when(keycloakService).updateUser(ArgumentMatchers.any(Student.class));

        Optional<Student> actual = studentService.update(student, studentUuid);

        ArgumentCaptor<UUID> existUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        ArgumentCaptor<Student> keycloakStudentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentRepository, Mockito.times(1)).existsByUuid(existUuidCaptor.capture());
        Mockito.verify(studentRepository, Mockito.times(1)).findByUuid(findUuidCaptor.capture());
        Mockito.verify(studentRepository, Mockito.times(2)).save(studentCaptor.capture());
        Mockito.verify(keycloakService, Mockito.times(1)).updateUser(keycloakStudentCaptor.capture());

        MatcherAssert.assertThat(studentUuid, Matchers.equalTo(existUuidCaptor.getValue()));
        MatcherAssert.assertThat(studentUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(student, Matchers.equalTo(studentCaptor.getValue()));
        MatcherAssert.assertThat(student, Matchers.equalTo(keycloakStudentCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(student)));
    }

    @Test
    void shouldDeleteStudentByUuids() {
        Mockito.when(studentRepository.findByUuidIn(ArgumentMatchers.anySet())).thenReturn(Set.of(student));
        Mockito.doNothing().when(attendanceRepository).deleteByStudent_UuidIn(ArgumentMatchers.anySet());
        Mockito.doNothing().when(keycloakService).deleteUserByUuids(ArgumentMatchers.anySet());
        Mockito.doNothing().when(studentRepository).deleteByUuidIn(ArgumentMatchers.anySet());

        studentService.deleteByUuids(Set.of(studentUuid));

        ArgumentCaptor<Set<UUID>> findUuidCaptor = ArgumentCaptor.forClass(Set.class);
        ArgumentCaptor<Set<UUID>> attendanceUuidCaptor = ArgumentCaptor.forClass(Set.class);
        ArgumentCaptor<Set<UUID>> keycloakUuidCaptor = ArgumentCaptor.forClass(Set.class);
        ArgumentCaptor<Set<UUID>> studentUuidCaptor = ArgumentCaptor.forClass(Set.class);

        Mockito.verify(studentRepository, Mockito.times(1)).findByUuidIn(findUuidCaptor.capture());
        Mockito.verify(attendanceRepository, Mockito.times(1)).deleteByStudent_UuidIn(attendanceUuidCaptor.capture());
        Mockito.verify(keycloakService, Mockito.times(1)).deleteUserByUuids(keycloakUuidCaptor.capture());
        Mockito.verify(studentRepository, Mockito.times(1)).deleteByUuidIn(studentUuidCaptor.capture());

        MatcherAssert.assertThat(Set.of(studentUuid), Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(Set.of(studentUuid), Matchers.equalTo(attendanceUuidCaptor.getValue()));
        MatcherAssert.assertThat(Set.of(studentUuid), Matchers.equalTo(keycloakUuidCaptor.getValue()));
        MatcherAssert.assertThat(Set.of(studentUuid), Matchers.equalTo(studentUuidCaptor.getValue()));
    }

    @Test
    void shouldFindByUuidStudent() {
        Mockito.when(crudRepositoryHelper.findByUuid(
                        ArgumentMatchers.any(StudentRepository.class),
                        ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(student));

        Optional<Student> actual = studentService.findByUuid(studentUuid);

        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<StudentRepository> repositoryCaptor = ArgumentCaptor.forClass(StudentRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findByUuid(repositoryCaptor.capture(), findUuidCaptor.capture());

        MatcherAssert.assertThat(studentUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(studentRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(Optional.of(student), Matchers.equalTo(actual));
    }

    @Test
    void shouldGetByUuidStudent() {
        Mockito.when(crudRepositoryHelper.getByUuid(
                        ArgumentMatchers.any(StudentRepository.class),
                        ArgumentMatchers.any(UUID.class)))
                .thenReturn(student);

        Student actual = studentService.getByUuid(studentUuid);

        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<StudentRepository> repositoryCaptor = ArgumentCaptor.forClass(StudentRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).getByUuid(repositoryCaptor.capture(), findUuidCaptor.capture());

        MatcherAssert.assertThat(studentUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(studentRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(student, Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllStudents() {
        Mockito.when(crudRepositoryHelper.findAll(ArgumentMatchers.any(StudentRepository.class))).thenReturn(List.of(student));

        List<Student> actual = studentService.findAll();

        ArgumentCaptor<StudentRepository> repositoryCaptor = ArgumentCaptor.forClass(StudentRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findAll(repositoryCaptor.capture());

        MatcherAssert.assertThat(studentRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(List.of(student), Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllStudentsWithPageable() {
        Page<Student> page = new PageImpl<>(List.of(student));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(crudRepositoryHelper.findAll(
                ArgumentMatchers.any(StudentRepository.class),
                ArgumentMatchers.any(Pageable.class)
        )).thenReturn(page);

        Page<Student> actual = studentService.findAll(pageable);

        ArgumentCaptor<StudentRepository> repositoryCaptor = ArgumentCaptor.forClass(StudentRepository.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findAll(repositoryCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(studentRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(page, Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllStudentsWithPageableAndSpecification() {
        Page<Student> page = new PageImpl<>(List.of(student));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(crudRepositoryHelper.findAll(
                ArgumentMatchers.any(StudentRepository.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(Pageable.class)
        )).thenReturn(page);

        Page<Student> actual = studentService.findAll(null, pageable);

        ArgumentCaptor<StudentRepository> repositoryCaptor = ArgumentCaptor.forClass(StudentRepository.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1))
                .findAll(repositoryCaptor.capture(), specificationCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(studentRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(page, Matchers.equalTo(actual));
    }
}
