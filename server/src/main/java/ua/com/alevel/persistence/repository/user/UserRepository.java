package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User> {

    Optional<User> findByUsername(String username);
}
