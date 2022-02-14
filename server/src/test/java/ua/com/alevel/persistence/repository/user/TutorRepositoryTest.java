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
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.finance.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TutorRepositoryTest {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role role;
    private Account account;
    private Tutor tutor;

    @BeforeEach
    void setUp() {
        tutor = new Tutor();
        tutor.setUuid(UUID.fromString("5da6789c-f0c4-4e1e-a741-ff5e0464c3e7"));
        tutor.setUsername("Tutor");
        tutor.setFirstName("Tutor");
        tutor.setLastName("Tutor");
        tutor.setEmail("tutor@sd.sd");
        tutor.setGender(GenderType.MALE);
        tutor.setBirthDay(LocalDate.now());
        tutor.setActivated(true);

        account = new Account();
        account.setName("Tutor account");
        account.setDescription("Account " + tutor.getFirstName() + " " + tutor.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);

        role = new Role();
        role.setName("TUTOR");
        role.setUsers(Set.of(tutor));

        tutor.setAccount(account);
        tutor.setRole(role);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    void shouldCreateTutor() {
        roleRepository.save(role);
        accountRepository.save(account);
        Tutor actual = tutorRepository.save(tutor);
        MatcherAssert.assertThat(actual.getId(), Matchers.notNullValue());
    }

    @Test
    @Order(2)
    void shouldFindByUuidTutor() {
        Optional<Tutor> actual = tutorRepository.findByUuid(tutor.getUuid());
        MatcherAssert.assertThat(actual, Matchers.notNullValue());
    }

    @Test
    @Order(3)
    void shouldBeExistByUuidTutor() {
        Boolean actual = tutorRepository.existsByUuid(tutor.getUuid());
        MatcherAssert.assertThat(actual, Matchers.is(true));
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void shouldUpdateByUuidTutor() {
        Tutor tutorReturned = tutorRepository.findByUuid(tutor.getUuid()).get();
        tutorReturned.setFirstName("first");
        tutorReturned.setLastName("last");
        tutorReturned.setUsername("tut");
        tutorReturned.setActivated(false);
        tutorReturned.setEmail("tutor11@sa.sss");
        Tutor actual = tutorRepository.save(tutorReturned);
        MatcherAssert.assertThat(actual, Matchers.equalTo(tutorReturned));
    }

    @Test
    @Order(5)
    void shouldFindAllTutorsWithPageable() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("created").ascending());
        Page<Tutor> actual = tutorRepository.findAll(pageable);
        MatcherAssert.assertThat(actual.getTotalElements(), Matchers.greaterThanOrEqualTo(1L));
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    void shouldDeleteByUuidsTutors() {
        tutorRepository.deleteByUuidIn(Set.of(tutor.getUuid()));
        Tutor maybeTutor = tutorRepository.getByUuid(tutor.getUuid());
        MatcherAssert.assertThat(maybeTutor, Matchers.nullValue());
    }
}
