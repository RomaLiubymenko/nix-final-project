package ua.com.alevel.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.TutorRepository;
import ua.com.alevel.service.AbstractService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TutorService extends AbstractService<Tutor, TutorRepository> {

    private final TutorRepository repository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final CrudRepositoryHelper<Tutor, TutorRepository> crudRepositoryHelper;

    public TutorService(
            TutorRepository repository,
            RoleRepository roleRepository,
            AccountRepository accountRepository,
            CrudRepositoryHelper<Tutor, TutorRepository> crudRepositoryHelper) {
        super(repository, crudRepositoryHelper);
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Tutor create(Tutor tutor) {
        Account account = createAccountForTutor(tutor);
        tutor.setAccount(account);
        tutor.setRole(roleRepository.findByName("Tutor"));
        return crudRepositoryHelper.create(repository, tutor);
    }

    private Account createAccountForTutor(Tutor tutor) {
        Account account = new Account();
        account.setName("Tutor account");
        account.setDescription("Account " + tutor.getFirstName() + " " + tutor.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }
}
