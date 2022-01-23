package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface AdminRepository extends AbstractRepository<Admin> {
}
