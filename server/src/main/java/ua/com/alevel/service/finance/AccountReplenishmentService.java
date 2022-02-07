package ua.com.alevel.service.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;
import ua.com.alevel.persistence.repository.finance.AccountReplenishmentRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class AccountReplenishmentService extends AbstractService<AccountReplenishment, AccountReplenishmentRepository> {

    private final AccountReplenishmentRepository accountReplenishmentRepository;
    private final CrudRepositoryHelper<AccountReplenishment, AccountReplenishmentRepository> crudRepositoryHelper;

    public AccountReplenishmentService(AccountReplenishmentRepository accountReplenishmentRepository,
                                       CrudRepositoryHelper<AccountReplenishment, AccountReplenishmentRepository> crudRepositoryHelper) {
        super(accountReplenishmentRepository, crudRepositoryHelper);
        this.accountReplenishmentRepository = accountReplenishmentRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
