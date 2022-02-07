package ua.com.alevel.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.AbstractService;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    private final UserRepository userRepository;
    private final CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper;

    public UserService(UserRepository userRepository, CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper) {
        super(userRepository, crudRepositoryHelper);
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
