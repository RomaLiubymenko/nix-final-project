package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface AdminRepository extends CommonRepository<Admin> {
}
