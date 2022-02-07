package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Role;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface RoleRepository extends CommonRepository<Role> {

    Role findByName(String name);
}
