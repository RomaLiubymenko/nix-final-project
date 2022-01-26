package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository<ENTITY extends AbstractEntity> extends JpaRepository<ENTITY, Long>, JpaSpecificationExecutor<ENTITY> {

    Optional<ENTITY> findByUuid(UUID uuid);

    ENTITY getByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    void deleteByUuidIn(Set<UUID> uuids);

    boolean existsByUuid(UUID uuid);
}
