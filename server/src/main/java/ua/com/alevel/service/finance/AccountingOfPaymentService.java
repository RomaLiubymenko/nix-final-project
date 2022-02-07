package ua.com.alevel.service.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;
import ua.com.alevel.persistence.repository.finance.AccountingOfPaymentRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class AccountingOfPaymentService extends AbstractService<AccountingOfPayment, AccountingOfPaymentRepository> {

    private final AccountingOfPaymentRepository accountingOfPaymentRepository;
    private final CrudRepositoryHelper<AccountingOfPayment, AccountingOfPaymentRepository> crudRepositoryHelper;

    public AccountingOfPaymentService(AccountingOfPaymentRepository accountingOfPaymentRepository,
                                      CrudRepositoryHelper<AccountingOfPayment, AccountingOfPaymentRepository> crudRepositoryHelper) {
        super(accountingOfPaymentRepository, crudRepositoryHelper);
        this.accountingOfPaymentRepository = accountingOfPaymentRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
