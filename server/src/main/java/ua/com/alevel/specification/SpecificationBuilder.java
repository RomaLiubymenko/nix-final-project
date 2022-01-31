package ua.com.alevel.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.persistence.entity.AbstractEntity;

public interface SpecificationBuilder<ENTITY extends AbstractEntity, FILTER_DTO extends AbstractFilterDto> {

    Specification<ENTITY> build(String query);

    Specification<ENTITY> build(FILTER_DTO filterDto);

    Specification<ENTITY> build(String query, FILTER_DTO filterDto);
}
