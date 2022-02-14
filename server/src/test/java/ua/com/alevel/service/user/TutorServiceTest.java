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
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.TutorRepository;
import ua.com.alevel.service.auth.KeycloakService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TutorServiceTest.class);

    @Mock
    TutorRepository tutorRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    KeycloakService keycloakService;

    @Mock
    CrudRepositoryHelper<Tutor, TutorRepository> crudRepositoryHelper;

    @InjectMocks
    TutorService tutorService;

    private Role role;
    private UUID tutorUuid;
    private UUID keycloakUuid;
    private Account account;
    private Tutor tutor;

    @BeforeAll
    public static void setUp() {
        LOG.info("Start test: {}", TutorService.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOG.info("Complete testing: {}", TutorService.class.getSimpleName());
    }

    @BeforeEach
    void init() {
        tutorUuid = UUID.fromString("3315439c-66d0-4e59-b88a-8bdef03bc07c");
        keycloakUuid = UUID.fromString("0700dd5e-3c8f-4551-af17-ead9595b4db0");

        tutor = new Tutor();
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
    }

    @Test
    void shouldCreateTutor() {
        try (MockedStatic<CreatedResponseUtil> utilities = Mockito.mockStatic(CreatedResponseUtil.class)) {
            Mockito.when(accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(account);
            Mockito.when(roleRepository.findByName(ArgumentMatchers.any(String.class))).thenReturn(role);
            Mockito.when(keycloakService.createUser(ArgumentMatchers.any(Tutor.class))).thenReturn(null);
            utilities.when(() -> CreatedResponseUtil.getCreatedId(ArgumentMatchers.any())).thenReturn(String.valueOf(keycloakUuid));
            Mockito.doNothing().when(keycloakService).assignRoles(ArgumentMatchers.any(String.class), ArgumentMatchers.any(List.class));
            Mockito.when(tutorRepository.save(ArgumentMatchers.any(Tutor.class))).thenReturn(tutor);

            Tutor actual = tutorService.create(tutor);

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            ArgumentCaptor<Tutor> keycloakTutorCaptor = ArgumentCaptor.forClass(Tutor.class);
            ArgumentCaptor<String> keycloakUuidCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<List<String>> roleListCaptor = ArgumentCaptor.forClass(List.class);
            ArgumentCaptor<Tutor> tutorCaptor = ArgumentCaptor.forClass(Tutor.class);

            Mockito.verify(accountRepository, Mockito.times(1)).save(accountCaptor.capture());
            Mockito.verify(keycloakService, Mockito.times(1)).createUser(keycloakTutorCaptor.capture());
            Mockito.verify(keycloakService, Mockito.times(1)).assignRoles(keycloakUuidCaptor.capture(), roleListCaptor.capture());
            Mockito.verify(tutorRepository, Mockito.times(1)).save(tutorCaptor.capture());

            MatcherAssert.assertThat(account.getName(), Matchers.equalTo(accountCaptor.getValue().getName()));
            MatcherAssert.assertThat(account.getDescription(), Matchers.equalTo(accountCaptor.getValue().getDescription()));
            MatcherAssert.assertThat(account.getBalance(), Matchers.equalTo(accountCaptor.getValue().getBalance()));
            MatcherAssert.assertThat(tutor, Matchers.equalTo(keycloakTutorCaptor.getValue()));
            MatcherAssert.assertThat(String.valueOf(keycloakUuid), Matchers.equalTo(keycloakUuidCaptor.getValue()));
            MatcherAssert.assertThat(List.of("TUTOR"), Matchers.equalTo(roleListCaptor.getValue()));
            MatcherAssert.assertThat(tutor, Matchers.equalTo(tutorCaptor.getValue()));
            MatcherAssert.assertThat(actual, Matchers.equalTo(tutor));
        }
    }

    @Test
    void shouldUpdateTutor() {
        Mockito.when(tutorRepository.existsByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(true);
        Mockito.when(tutorRepository.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(tutor));
        Mockito.when(tutorRepository.save(ArgumentMatchers.any(Tutor.class))).thenReturn(tutor);
        Mockito.doNothing().when(keycloakService).updateUser(ArgumentMatchers.any(Tutor.class));

        Optional<Tutor> actual = tutorService.update(tutor, tutorUuid);

        ArgumentCaptor<UUID> existUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Tutor> tutorCaptor = ArgumentCaptor.forClass(Tutor.class);
        ArgumentCaptor<Tutor> keycloakTutorCaptor = ArgumentCaptor.forClass(Tutor.class);

        Mockito.verify(tutorRepository, Mockito.times(1)).existsByUuid(existUuidCaptor.capture());
        Mockito.verify(tutorRepository, Mockito.times(1)).findByUuid(findUuidCaptor.capture());
        Mockito.verify(tutorRepository, Mockito.times(2)).save(tutorCaptor.capture());
        Mockito.verify(keycloakService, Mockito.times(1)).updateUser(keycloakTutorCaptor.capture());

        MatcherAssert.assertThat(tutorUuid, Matchers.equalTo(existUuidCaptor.getValue()));
        MatcherAssert.assertThat(tutorUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(tutor, Matchers.equalTo(tutorCaptor.getValue()));
        MatcherAssert.assertThat(tutor, Matchers.equalTo(keycloakTutorCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(tutor)));
    }

    @Test
    void shouldDeleteTutorByUuids() {
        Mockito.when(tutorRepository.findByUuidIn(ArgumentMatchers.anySet())).thenReturn(Set.of(tutor));
        Mockito.doNothing().when(keycloakService).deleteUserByUuids(ArgumentMatchers.anySet());
        Mockito.doNothing().when(tutorRepository).deleteByUuidIn(ArgumentMatchers.anySet());

        tutorService.deleteByUuids(Set.of(tutorUuid));

        ArgumentCaptor<Set<UUID>> findUuidCaptor = ArgumentCaptor.forClass(Set.class);
        ArgumentCaptor<Set<UUID>> keycloakUuidCaptor = ArgumentCaptor.forClass(Set.class);
        ArgumentCaptor<Set<UUID>> tutorUuidCaptor = ArgumentCaptor.forClass(Set.class);

        Mockito.verify(tutorRepository, Mockito.times(1)).findByUuidIn(findUuidCaptor.capture());
        Mockito.verify(keycloakService, Mockito.times(1)).deleteUserByUuids(keycloakUuidCaptor.capture());
        Mockito.verify(tutorRepository, Mockito.times(1)).deleteByUuidIn(tutorUuidCaptor.capture());

        MatcherAssert.assertThat(Set.of(tutorUuid), Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(Set.of(tutorUuid), Matchers.equalTo(keycloakUuidCaptor.getValue()));
        MatcherAssert.assertThat(Set.of(tutorUuid), Matchers.equalTo(tutorUuidCaptor.getValue()));
    }

    @Test
    void shouldFindByUuidTutor() {
        Mockito.when(crudRepositoryHelper.findByUuid(
                        ArgumentMatchers.any(TutorRepository.class),
                        ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(tutor));

        Optional<Tutor> actual = tutorService.findByUuid(tutorUuid);

        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<TutorRepository> repositoryCaptor = ArgumentCaptor.forClass(TutorRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findByUuid(repositoryCaptor.capture(), findUuidCaptor.capture());

        MatcherAssert.assertThat(tutorUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(tutorRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(Optional.of(tutor), Matchers.equalTo(actual));
    }

    @Test
    void shouldGetByUuidTutor() {
        Mockito.when(crudRepositoryHelper.getByUuid(
                        ArgumentMatchers.any(TutorRepository.class),
                        ArgumentMatchers.any(UUID.class)))
                .thenReturn(tutor);

        Tutor actual = tutorService.getByUuid(tutorUuid);

        ArgumentCaptor<UUID> findUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<TutorRepository> repositoryCaptor = ArgumentCaptor.forClass(TutorRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).getByUuid(repositoryCaptor.capture(), findUuidCaptor.capture());

        MatcherAssert.assertThat(tutorUuid, Matchers.equalTo(findUuidCaptor.getValue()));
        MatcherAssert.assertThat(tutorRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(tutor, Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllTutors() {
        Mockito.when(crudRepositoryHelper.findAll(ArgumentMatchers.any(TutorRepository.class))).thenReturn(List.of(tutor));

        List<Tutor> actual = tutorService.findAll();

        ArgumentCaptor<TutorRepository> repositoryCaptor = ArgumentCaptor.forClass(TutorRepository.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findAll(repositoryCaptor.capture());

        MatcherAssert.assertThat(tutorRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(List.of(tutor), Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllTutorsWithPageable() {
        Page<Tutor> page = new PageImpl<>(List.of(tutor));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(crudRepositoryHelper.findAll(
                ArgumentMatchers.any(TutorRepository.class),
                ArgumentMatchers.any(Pageable.class)
        )).thenReturn(page);

        Page<Tutor> actual = tutorService.findAll(pageable);

        ArgumentCaptor<TutorRepository> repositoryCaptor = ArgumentCaptor.forClass(TutorRepository.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).findAll(repositoryCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(tutorRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(page, Matchers.equalTo(actual));
    }

    @Test
    void shouldFindAllTutorsWithPageableAndSpecification() {
        Page<Tutor> page = new PageImpl<>(List.of(tutor));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(crudRepositoryHelper.findAll(
                ArgumentMatchers.any(TutorRepository.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(Pageable.class)
        )).thenReturn(page);

        Page<Tutor> actual = tutorService.findAll(null, pageable);

        ArgumentCaptor<TutorRepository> repositoryCaptor = ArgumentCaptor.forClass(TutorRepository.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1))
                .findAll(repositoryCaptor.capture(), specificationCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(tutorRepository, Matchers.equalTo(repositoryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(page, Matchers.equalTo(actual));
    }
}
