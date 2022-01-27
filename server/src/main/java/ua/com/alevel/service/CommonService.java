package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface CommonService<ENTITY extends AbstractEntity> {

    ENTITY create(ENTITY entity);

    Optional<ENTITY> update(ENTITY entity, UUID uuid);

    void deleteByUuid(UUID uuid);

    void deleteByUuids(Set<UUID> uuids);

    Optional<ENTITY> findByUuid(UUID uuid);

    ENTITY getByUuid(UUID uuid);

    Page<ENTITY> findAll(Specification<ENTITY> specification, Pageable pageable);

    List<ENTITY> findAll();
}
