package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends CommonRepository<Admin> {

    @EntityGraph(attributePaths = {"account", "role"})
    Optional<Admin> findByUuid(UUID uuid);
}
