package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.SubjectFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class SubjectSpecificationBuilder implements SpecificationBuilder<Subject, SubjectFilterDto> {

    @Override
    public Specification<Subject> build(String query, SubjectFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Subject> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Subject> build(SubjectFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Subject>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.<Subject, Tutor>inChainUuid("tutor", List.of(filterDto.getTutorUuid()), JoinType.LEFT));
    }

    private Specification<Subject> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Subject, Tutor> tutorJoin = root.join("tutor", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
