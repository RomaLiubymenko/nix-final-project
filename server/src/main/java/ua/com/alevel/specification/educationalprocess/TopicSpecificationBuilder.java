package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.TopicFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Course;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.educationalprocess.Topic;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class TopicSpecificationBuilder implements SpecificationBuilder<Topic, TopicFilterDto> {

    @Override
    public Specification<Topic> build(String query, TopicFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Topic> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Topic> build(TopicFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Topic>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("length", filterDto.getLength()))
                .and(SpecificationUtil.<Topic, Subject>inChainUuid("subject", filterDto.getSubjectUuid(), JoinType.LEFT))
                .and(SpecificationUtil.<Topic, Course>inChainUuid("course", filterDto.getCourseUuid(), JoinType.LEFT));
    }

    private Specification<Topic> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Topic, Subject> subjectJoin = root.join("subject", JoinType.LEFT);
                Join<Topic, Course> courseJoin = root.join("course", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("length").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(subjectJoin.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(courseJoin.get("name")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
