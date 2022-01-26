package ua.com.alevel.persistence.repository.finance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface AccountingOfPaymentRepository extends CommonRepository<AccountingOfPayment> {
}
