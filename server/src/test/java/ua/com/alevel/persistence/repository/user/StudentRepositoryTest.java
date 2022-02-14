package ua.com.alevel.persistence.repository.user;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Role;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.finance.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role role;
    private Account account;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setUuid(UUID.fromString("3315439c-66d0-4e59-b88a-8bdef03bc07c"));
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

        student.setAccount(account);
        student.setRole(role);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    void shouldCreateStudent() {
        roleRepository.save(role);
        accountRepository.save(account);
        Student actual = studentRepository.save(student);
        MatcherAssert.assertThat(actual.getId(),  Matchers.notNullValue());
    }

    @Test
    @Order(2)
    void shouldFindByUuidStudent() {
        Optional<Student> actual = studentRepository.findByUuid(student.getUuid());
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
    }

    @Test
    @Order(3)
    void shouldBeExistByUuidStudent() {
        Boolean actual = studentRepository.existsByUuid(student.getUuid());
        MatcherAssert.assertThat(actual, Matchers.is(true));
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void shouldUpdateByUuidStudent() {
        Student studentReturned = studentRepository.findByUuid(student.getUuid()).get();
        studentReturned.setFirstName("first");
        studentReturned.setLastName("last");
        studentReturned.setUsername("users");
        studentReturned.setActivated(false);
        studentReturned.setEmail("student11@sa.sss");
        Student actual = studentRepository.save(studentReturned);
        MatcherAssert.assertThat(actual, Matchers.equalTo(studentReturned));
    }

    @Test
    @Order(5)
    void shouldFindAllStudentsWithPageable() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("created").ascending());
        Page<Student> actual = studentRepository.findAll(pageable);
        MatcherAssert.assertThat(actual.getTotalElements(), Matchers.greaterThanOrEqualTo(1L));
    }

//  Problem with h2 namespace *user table*

//    @Test
//    @Order(6)
//    @Rollback(value = false)
//    void shouldDeleteByUuidsStudents() {
//        studentRepository.deleteByUuidIn(Set.of(student.getUuid()));
//        Student maybeStudent = studentRepository.getByUuid(student.getUuid());
//        MatcherAssert.assertThat(maybeStudent, Matchers.nullValue());
//    }
}
