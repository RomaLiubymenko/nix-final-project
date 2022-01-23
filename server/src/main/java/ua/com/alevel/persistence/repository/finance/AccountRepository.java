package ua.com.alevel.persistence.repository.finance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface AccountRepository extends AbstractRepository<Account> {
}
