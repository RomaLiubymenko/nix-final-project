package ua.com.alevel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.AbstractTableDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CommonFacade<FILTER_DTO extends AbstractFilterDto, TABLE_DTO extends AbstractTableDto, PROFILE_DTO extends AbstractProfileDto> {

    PROFILE_DTO create(PROFILE_DTO profileDto);

    Optional<PROFILE_DTO> update(PROFILE_DTO profileDto, UUID uuid);

    void deleteByUuid(UUID uuid);

    void deleteByUuids(Set<UUID> uuids);

    Optional<PROFILE_DTO> findByUuid(UUID uuid);

    Set<TABLE_DTO> findAll();

    Page<TABLE_DTO> findAll(Pageable pageable);

    Page<TABLE_DTO> findAll(String query, Pageable pageable);

    Page<TABLE_DTO> findAll(FILTER_DTO filterDto, Pageable pageable);

    Page<TABLE_DTO> findAll(String query, FILTER_DTO filterDto, Pageable pageable);
}
