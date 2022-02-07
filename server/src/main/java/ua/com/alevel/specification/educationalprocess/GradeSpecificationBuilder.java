package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.GradeFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Grade;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class GradeSpecificationBuilder implements SpecificationBuilder<Grade, GradeFilterDto> {

    @Override
    public Specification<Grade> build(String query, GradeFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Grade> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Grade> build(GradeFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Grade>equalsChain("value", filterDto.getValue()))
                .and(SpecificationUtil.equalsChain("weight", filterDto.getWeight()))
                .and(SpecificationUtil.equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.<Grade, Student>inChainUuid("student", List.of(filterDto.getStudentUuid()), JoinType.LEFT))
                .and(SpecificationUtil.<Grade, Tutor>inChainUuid("tutor", List.of(filterDto.getTutorUuid()), JoinType.LEFT));
    }

    private Specification<Grade> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Grade, Student> studentJoin = root.join("student", JoinType.LEFT);
                Join<Grade, Tutor> tutorJoin = root.join("tutor", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("value").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("weight").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("lastName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
