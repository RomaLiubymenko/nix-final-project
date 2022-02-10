package ua.com.alevel.service.user;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Exercise;
import ua.com.alevel.persistence.entity.educationalprocess.Report;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.educationalprocess.SubjectRepository;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.TutorRepository;
import ua.com.alevel.service.AbstractService;
import ua.com.alevel.service.auth.KeycloakService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TutorService extends AbstractService<Tutor, TutorRepository> {

    private final String mockPassword;
    private final KeycloakService keycloakService;
    private final TutorRepository tutorRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final SubjectRepository subjectRepository;
    private final CrudRepositoryHelper<Tutor, TutorRepository> crudRepositoryHelper;

    public TutorService(
            @Value("${lms.mock.password}") String mockPassword,
            KeycloakService keycloakService, TutorRepository tutorRepository,
            RoleRepository roleRepository,
            AccountRepository accountRepository,
            SubjectRepository subjectRepository, CrudRepositoryHelper<Tutor, TutorRepository> crudRepositoryHelper) {
        super(tutorRepository, crudRepositoryHelper);
        this.mockPassword = mockPassword;
        this.keycloakService = keycloakService;
        this.tutorRepository = tutorRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.subjectRepository = subjectRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Tutor create(Tutor tutor) {
        Account account = createAccountForTutor(tutor);
        tutor.setAccount(account);
        tutor.setRole(roleRepository.findByName("TUTOR"));
        Set<Subject> subjects = prepareSubjectToSave(tutor);
        tutor.getSubjects().clear();
        for (Subject subject : subjects) {
            tutor.addSubject(subject);
        }
        UUID uuid = createKeycloakTutor(tutor);
        tutor.setUuid(uuid);
        return tutorRepository.save(tutor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<Tutor> update(Tutor tutor, UUID uuid) {
        boolean isExistTutor = tutorRepository.existsByUuid(uuid);
        if (isExistTutor) {
            Tutor tutorReturned = tutorRepository.findByUuid(uuid).get();
            tutor.setId(tutorReturned.getId());
            tutor.setAccount(tutorReturned.getAccount());
            tutor.setRole(tutorReturned.getRole());
            tutor.setAccountingOfPayments(tutorReturned.getAccountingOfPayments());
            tutor.setReports(tutorReturned.getReports());
            tutor.setCourses(tutorReturned.getCourses());
            tutor.setLessons(tutorReturned.getLessons());
            tutor.setExercises(tutorReturned.getExercises());
            Set<Subject> subjects = prepareSubjectToSave(tutor);
            for (Subject subject : new HashSet<>(tutorReturned.getSubjects())) {
                tutorReturned.removeSubject(subject);
            }
            tutorRepository.save(tutorReturned);
            for (Subject subject : subjects) {
                tutor.addSubject(subject);
            }
            Tutor savedTutor = tutorRepository.save(tutor);
            keycloakService.updateUser(savedTutor);
            return Optional.of(savedTutor);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteByUuids(Set<UUID> uuids) {
        Set<Tutor> tutors = tutorRepository.findByUuidIn(uuids);
        for (Tutor tutor : tutors) {
            for (Subject subject : new HashSet<>(tutor.getSubjects())) {
                tutor.removeSubject(subject);
            }
            for (Exercise exercise : new HashSet<>(tutor.getExercises())) {
                tutor.removeExercise(exercise);
            }
            for (Report report : new HashSet<>(tutor.getReports())) {
                tutor.removeReport(report);
            }
        }
        tutorRepository.deleteByUuidIn(uuids);
        keycloakService.deleteUserByUuids(uuids);
    }

    private Account createAccountForTutor(Tutor tutor) {
        Account account = new Account();
        account.setName("Tutor account");
        account.setDescription("Account " + tutor.getFirstName() + " " + tutor.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    private Set<Subject> prepareSubjectToSave(Tutor tutor) {
        return tutor.getSubjects().stream()
                .map(subject -> subjectRepository.getByUuid(subject.getUuid()))
                .collect(Collectors.toSet());
    }

    private UUID createKeycloakTutor(Tutor tutor) {
        tutor.setPassword(mockPassword);
        Response result = keycloakService.createUser(tutor);
        String uuid = CreatedResponseUtil.getCreatedId(result);
        keycloakService.assignRoles(uuid, List.of("TUTOR"));
        return UUID.fromString(uuid);
    }
}
