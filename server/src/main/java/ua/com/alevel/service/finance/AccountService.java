package ua.com.alevel.service.finance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.AbstractService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService extends AbstractService<Account, AccountRepository> {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CrudRepositoryHelper<Account, AccountRepository> crudRepositoryHelper;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository,
                          CrudRepositoryHelper<Account, AccountRepository> crudRepositoryHelper) {
        super(accountRepository, crudRepositoryHelper);
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Account create(Account account) {
        Set<User> users = prepareUserToSave(account);
        for (User user : users) {
            user.setAccount(account);
        }
        Account savedAccount = accountRepository.save(account);
        userRepository.saveAll(users);
        return savedAccount;
    }

    private Set<User> prepareUserToSave(Account account) {
        return account.getUsers().stream()
                .map(user -> userRepository.getByUuid(user.getUuid()))
                .collect(Collectors.toSet());
    }
}
