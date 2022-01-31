package ua.com.alevel.persistence.crudhelper.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CrudRepositoryHelperImpl<
        ENTITY extends AbstractEntity,
        REPOSITORY extends CommonRepository<ENTITY>> implements CrudRepositoryHelper<ENTITY, REPOSITORY> {

    @Override
    public ENTITY create(REPOSITORY repository, ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ENTITY> update(REPOSITORY repository, ENTITY entity, UUID uuid) {
        boolean isExistEntity = repository.existsByUuid(uuid);
        if (isExistEntity) {
            return Optional.of(repository.save(entity));
        }
        return Optional.empty();
    }

    @Override
    public void deleteByUuid(REPOSITORY repository, UUID uuid) {
        repository.deleteByUuid(uuid);
    }

    @Override
    public void deleteByUuids(REPOSITORY repository, Set<UUID> uuids) {
        repository.deleteByUuidIn(uuids);
    }

    @Override
    public Optional<ENTITY> findByUuid(REPOSITORY repository, UUID uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    public ENTITY getByUuid(REPOSITORY repository, UUID uuid) {
        return repository.getByUuid(uuid);
    }

    @Override
    public List<ENTITY> findAll(REPOSITORY repository) {
        return repository.findAll();
    }

    @Override
    public Page<ENTITY> findAll(REPOSITORY repository, Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<ENTITY> findAll(REPOSITORY repository, Specification<ENTITY> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }
}
