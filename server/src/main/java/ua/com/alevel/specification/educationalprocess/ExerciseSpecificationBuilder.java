package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.ExerciseFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Exercise;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class ExerciseSpecificationBuilder implements SpecificationBuilder<Exercise, ExerciseFilterDto> {

    @Override
    public Specification<Exercise> build(String query, ExerciseFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Exercise> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Exercise> build(ExerciseFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Exercise>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.equalsChain("content", filterDto.getContent()))
                .and(SpecificationUtil.<Exercise, Subject>inChainUuid("subject", List.of(filterDto.getSubjectUuid()), JoinType.LEFT))
                .and(SpecificationUtil.<Exercise, Tutor>inChainUuid("tutor", List.of(filterDto.getTutorUuid()), JoinType.LEFT));
    }

    private Specification<Exercise> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Exercise, Subject> subjectJoin = root.join("subject", JoinType.LEFT);
                Join<Exercise, Tutor> tutorJoin = root.join("tutor", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("content").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(subjectJoin.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
