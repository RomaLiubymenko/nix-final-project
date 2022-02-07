package ua.com.alevel.service.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class AccountService extends AbstractService<Account, AccountRepository> {

    private final AccountRepository accountRepository;
    private final CrudRepositoryHelper<Account, AccountRepository> crudRepositoryHelper;

    public AccountService(AccountRepository accountRepository, CrudRepositoryHelper<Account, AccountRepository> crudRepositoryHelper) {
        super(accountRepository, crudRepositoryHelper);
        this.accountRepository = accountRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
