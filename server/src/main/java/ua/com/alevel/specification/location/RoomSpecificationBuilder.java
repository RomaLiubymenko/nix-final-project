package ua.com.alevel.specification.location;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.location.RoomFilterDto;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

@Service
public class RoomSpecificationBuilder implements SpecificationBuilder<Room, RoomFilterDto> {

    @Override
    public Specification<Room> build(String query, RoomFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Room> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Room> build(RoomFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Room>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("number", filterDto.getNumber()))
                .and(SpecificationUtil.equalsChain("capacity", filterDto.getCapacity()))
                .and(SpecificationUtil.equalsChain("isAvailabilityToUse", filterDto.getIsAvailabilityToUse()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()));
    }

    private Specification<Room> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("number")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("capacity").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("isAvailabilityToUse").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
