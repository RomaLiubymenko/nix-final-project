package ua.com.alevel.persistence.crudhelper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudRepositoryHelper<ENTITY extends AbstractEntity, REPOSITORY extends AbstractRepository<ENTITY>> {

    ENTITY save(REPOSITORY repository, ENTITY entity);

    Optional<ENTITY> update(REPOSITORY repository, ENTITY entity);

    void deleteByUuid(REPOSITORY repository, UUID uuid);

    Optional<ENTITY> findByUuid(REPOSITORY repository, UUID uuid);

    ENTITY getByUuid(REPOSITORY repository, UUID uuid);

    Page<ENTITY> findAll(REPOSITORY repository, Specification<ENTITY> specification, Pageable pageable);

    List<ENTITY> findAll(REPOSITORY repository);
}
