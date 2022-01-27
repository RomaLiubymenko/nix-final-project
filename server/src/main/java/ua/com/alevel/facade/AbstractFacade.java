package ua.com.alevel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.service.CommonService;
import ua.com.alevel.specification.SpecificationBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractFacade<
        ENTITY extends AbstractEntity,
        FILTER_DTO extends AbstractFilterDto,
        TABLE_DTO extends AbstractTableDto,
        PROFILE_DTO extends AbstractProfileDto> implements CommonFacade<FILTER_DTO, TABLE_DTO, PROFILE_DTO> {

    private final CommonService<ENTITY> service;
    private final CommonMapper<ENTITY, TABLE_DTO, PROFILE_DTO> mapper;
    private final SpecificationBuilder<ENTITY, FILTER_DTO> specificationBuilder;

    protected AbstractFacade(
            CommonService<ENTITY> service,
            CommonMapper<ENTITY, TABLE_DTO, PROFILE_DTO> mapper,
            SpecificationBuilder<ENTITY, FILTER_DTO> specificationBuilder) {
        this.service = service;
        this.mapper = mapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public PROFILE_DTO create(PROFILE_DTO profileDto) {
        ENTITY entity = mapper.toEntity(profileDto);
        ENTITY savedEntity = service.create(entity);
        return mapper.toProfileDto(savedEntity);
    }

    @Override
    public Optional<PROFILE_DTO> update(PROFILE_DTO profileDto, UUID uuid) {
        ENTITY entity = mapper.toEntity(profileDto);
        return service.update(entity, uuid)
                .map(mapper::toProfileDto);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        service.deleteByUuid(uuid);
    }

    @Override
    public void deleteByUuids(Set<UUID> uuids) {
        service.deleteByUuids(uuids);
    }

    @Override
    public Optional<PROFILE_DTO> findByUuid(UUID uuid) {
        return service.findByUuid(uuid)
                .map(mapper::toProfileDto);
    }

    @Override
    public Page<TABLE_DTO> findAll(FILTER_DTO filterDto, Pageable pageable) {
        Specification<ENTITY> specification = specificationBuilder.build(filterDto);
        return service.findAll(specification, pageable)
                .map(mapper::toTableDto);
    }

    @Override
    public Page<TABLE_DTO> findAll(String query, FILTER_DTO filterDto, Pageable pageable) {
        Specification<ENTITY> specification = specificationBuilder.build(query, filterDto);
        return service.findAll(specification, pageable)
                .map(mapper::toTableDto);
    }

    @Override
    public Page<TABLE_DTO> findAll(Pageable pageable) {
        return service.findAll(null, pageable)
                .map(mapper::toTableDto);
    }

    @Override
    public List<TABLE_DTO> findAll() {
        return service.findAll().stream()
                .map(mapper::toTableDto)
                .toList();
    }
}
