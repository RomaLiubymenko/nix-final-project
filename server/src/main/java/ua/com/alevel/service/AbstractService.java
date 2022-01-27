package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractService<
        ENTITY extends AbstractEntity,
        REPOSITORY extends CommonRepository<ENTITY>> implements CommonService<ENTITY> {

    private final REPOSITORY repository;
    private final CrudRepositoryHelper<ENTITY, REPOSITORY> crudRepositoryHelper;

    protected AbstractService(REPOSITORY repository, CrudRepositoryHelper<ENTITY, REPOSITORY> crudRepositoryHelper) {
        this.repository = repository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ENTITY create(ENTITY entity) {
        return crudRepositoryHelper.create(repository, entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<ENTITY> update(ENTITY entity, UUID uuid) {
        return crudRepositoryHelper.update(repository, entity, uuid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteByUuid(UUID uuid) {
        crudRepositoryHelper.deleteByUuid(repository, uuid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteByUuids(Set<UUID> uuids) {
        crudRepositoryHelper.deleteByUuids(repository, uuids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ENTITY> findByUuid(UUID uuid) {
        return crudRepositoryHelper.findByUuid(repository, uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public ENTITY getByUuid(UUID uuid) {
        return crudRepositoryHelper.getByUuid(repository, uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ENTITY> findAll(Specification<ENTITY> specification, Pageable pageable) {
        return crudRepositoryHelper.findAll(repository, specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ENTITY> findAll() {
        return crudRepositoryHelper.findAll(repository);
    }
}
