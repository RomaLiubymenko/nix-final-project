package ua.com.alevel.persistence.crudhelper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CrudRepositoryHelper<
        ENTITY extends AbstractEntity,
        REPOSITORY extends CommonRepository<ENTITY>> {

    ENTITY create(REPOSITORY repository, ENTITY entity);

    Optional<ENTITY> update(REPOSITORY repository, ENTITY entity, UUID uuid);

    void deleteByUuid(REPOSITORY repository, UUID uuid);

    void deleteByUuids(REPOSITORY repository, Set<UUID> uuids);

    Optional<ENTITY> findByUuid(REPOSITORY repository, UUID uuid);

    ENTITY getByUuid(REPOSITORY repository, UUID uuid);

    List<ENTITY> findAll(REPOSITORY repository);

    Page<ENTITY> findAll(REPOSITORY repository, Pageable pageable);

    Page<ENTITY> findAll(REPOSITORY repository, Specification<ENTITY> specification, Pageable pageable);
}
