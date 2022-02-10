package ua.com.alevel.persistence.repository.finance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends CommonRepository<Account> {

    @Query(
            value = "SELECT account FROM Account account LEFT JOIN FETCH account.users",
            countQuery = "SELECT COUNT(account) FROM Account account"
    )
    List<Account> findAll();

    @Query(
            value = "SELECT account FROM Account account LEFT JOIN FETCH account.users",
            countQuery = "SELECT COUNT(account) FROM Account account"
    )
    Page<Account> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"users"})
    Page<Account> findAll(Specification<Account> specification, Pageable pageable);

    @EntityGraph(attributePaths = {
            "users",
            "users.role",
            "users.account"
    })
    Optional<Account> findByUuid(UUID uuid);
}
