package ua.com.alevel.mapper;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.persistence.entity.AbstractEntity;

public interface CommonMapper<ENTITY extends AbstractEntity, TABLE_DTO extends AbstractTableDto, PROFILE_DTO extends AbstractProfileDto> {

    ENTITY toEntity(PROFILE_DTO profileDto);

    TABLE_DTO toTableDto(ENTITY entity);

    PROFILE_DTO toProfileDto(ENTITY entity);
}
