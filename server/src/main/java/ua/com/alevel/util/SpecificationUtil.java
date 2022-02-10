package ua.com.alevel.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.math.BigDecimal;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.UUID;

public class SpecificationUtil {

    private SpecificationUtil() {
    }

    public static <ENTITY extends AbstractEntity> Specification<ENTITY> equalsChain(String fieldName, String fieldValue) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(fieldValue)) {
                String fieldFilter = "%" + fieldValue.toLowerCase().strip() + "%";
                Expression<String> fieldExpression = criteriaBuilder.lower(root.get(fieldName)).as(String.class);
                return criteriaBuilder.like(fieldExpression, fieldFilter);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static <ENTITY extends AbstractEntity> Specification<ENTITY> equalsChain(String fieldName, Enum<?> fieldValue) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (fieldValue != null) {
                return criteriaBuilder.equal(root.get(fieldName), fieldValue);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static <ENTITY extends AbstractEntity> Specification<ENTITY> equalsChain(String fieldName, BigDecimal fieldValue) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (fieldValue != null) {
                return criteriaBuilder.equal(root.get(fieldName), fieldValue);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static <ENTITY extends AbstractEntity> Specification<ENTITY> equalsChain(String fieldName, TemporalAccessor fieldValue) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (fieldValue != null) {
                return criteriaBuilder.equal(root.get(fieldName), fieldValue);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static <ENTITY extends AbstractEntity, JOINED_ENTITY extends AbstractEntity> Specification<ENTITY> inChainUuid(
            String fieldName,
            List<UUID> relatedEntityUuids,
            JoinType joinType) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (CollectionUtils.isNotEmpty(relatedEntityUuids)) {
                Join<ENTITY, JOINED_ENTITY> relatedEntityJoin = root.join(fieldName, joinType);
                CriteriaBuilder.In<UUID> inClause = criteriaBuilder.in(relatedEntityJoin.get("uuid"));
                for (UUID uuid : relatedEntityUuids) {
                    inClause = inClause.value(uuid);
                }
                return inClause;
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static <ENTITY extends AbstractEntity, JOINED_ENTITY extends AbstractEntity> Specification<ENTITY> inChainUuid(
            String fieldName,
            UUID uuid,
            JoinType joinType) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (uuid != null) {
                Join<ENTITY, JOINED_ENTITY> relatedEntityJoin = root.join(fieldName, joinType);
                CriteriaBuilder.In<UUID> inClause = criteriaBuilder.in(relatedEntityJoin.get("uuid"));
                inClause = inClause.value(uuid);
                return inClause;
            }
            return criteriaBuilder.conjunction();
        };
    }
}

