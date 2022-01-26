package ua.com.alevel.persistence.repository.finance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface AccountReplenishmentRepository extends CommonRepository<AccountReplenishment> {
}
