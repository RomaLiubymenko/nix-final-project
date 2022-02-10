package ua.com.alevel.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.facade.CommonFacade;
import ua.com.alevel.util.FilterDtoUtil;
import ua.com.alevel.util.PaginationUtil;

import java.util.*;

import static ua.com.alevel.enumeration.ControllerLogEnum.*;

public abstract class AbstractController<
        TABLE_DTO extends AbstractTableDto,
        PROFILE_DTO extends AbstractProfileDto,
        FILTER_DTO extends AbstractFilterDto> implements CommonController<FILTER_DTO, TABLE_DTO, PROFILE_DTO> {

    private final String url;
    private final String entityName;
    private final Logger logger;
    private final CommonFacade<FILTER_DTO, TABLE_DTO, PROFILE_DTO> facade;

    protected AbstractController(String url, String entityName, Logger logger, CommonFacade<FILTER_DTO, TABLE_DTO, PROFILE_DTO> facade) {
        this.entityName = entityName;
        this.logger = logger;
        this.facade = facade;
        this.url = url;
    }

    @Override
    public ResponseEntity<PROFILE_DTO> create(PROFILE_DTO profileDto) {
        logger.info(NEW_LOG.getLogInfo(), entityName, profileDto.toString());
        PROFILE_DTO savedEntity = facade.create(profileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @Override
    public ResponseEntity<PROFILE_DTO> update(PROFILE_DTO profileDto, UUID uuid) {
        logger.info(UPDATED_BY_UUID_LOG.getLogInfo(), entityName, uuid, profileDto.toString());
        Optional<PROFILE_DTO> savedEntity = facade.update(profileDto, uuid);
        if (savedEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedEntity.get());
    }

    @Override
    public ResponseEntity<Void> delete(UUID uuid) {
        logger.info(DELETED_BY_UUID_LOG.getLogInfo(), entityName, uuid);
        facade.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteRoomsByUuids(Set<UUID> uuids) {
        logger.info(DELETED_BY_UUIDS_LOG.getLogInfo(), entityName, uuids);
        facade.deleteByUuids(uuids);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PROFILE_DTO> findByUuid(UUID uuid) {
        logger.info(GET_BY_UUID_LOG.getLogInfo(), entityName, uuid);
        Optional<PROFILE_DTO> profileDto = facade.findByUuid(uuid);
        if (profileDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileDto.get());
    }


    @Override
    public ResponseEntity<List<TABLE_DTO>> findAll(String query, FILTER_DTO filterDto, Pageable pageable) {
        boolean isExistFilter = FilterDtoUtil.isExistFields(filterDto);
        // quick fix
        if (pageable == null || pageable.getSort().isUnsorted()) {
            logger.info(GET_ALL_LOG.getLogInfo(), entityName);
            return ResponseEntity.ok(new ArrayList<>(facade.findAll()));
        } else {
            HttpHeaders headers;
            Page<TABLE_DTO> page;
            if (StringUtils.isNotBlank(query) && isExistFilter) {
                logger.info(GET_ALL_PAGE_FOR_FILTER_QUERY_LOG.getLogInfo(), entityName, pageable, filterDto, query);
                page = facade.findAll(query, filterDto, pageable);
                headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, url);
            } else if (isExistFilter) {
                logger.info(GET_ALL_PAGE_FOR_FILTER_LOG.getLogInfo(), entityName, pageable, filterDto);
                page = facade.findAll(filterDto, pageable);
                headers = PaginationUtil.generatePaginationHttpHeaders(page, url);
            } else if (StringUtils.isNotBlank(query)) {
                logger.info(GET_ALL_PAGE_FOR_QUERY_LOG.getLogInfo(), entityName, pageable, query);
                page = facade.findAll(query, pageable);
                headers = PaginationUtil.generatePaginationHttpHeaders(page, url);
            } else {
                logger.info(GET_ALL_PAGE_LOG.getLogInfo(), entityName, pageable);
                page = facade.findAll(pageable);
                headers = PaginationUtil.generatePaginationHttpHeaders(page, url);
            }
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
    }
}
