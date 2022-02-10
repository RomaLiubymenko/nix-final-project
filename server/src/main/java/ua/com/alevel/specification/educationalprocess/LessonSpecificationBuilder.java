package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.LessonFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Service
public class LessonSpecificationBuilder implements SpecificationBuilder<Lesson, LessonFilterDto> {

    @Override
    public Specification<Lesson> build(String query, LessonFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Lesson> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Lesson> build(LessonFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Lesson>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.equalsChain("length", filterDto.getLength()))
                .and(SpecificationUtil.equalsChain("lessonType", filterDto.getLessonType()))
                .and(SpecificationUtil.equalsChain("lessonStatus", filterDto.getLessonStatus()))
                .and(SpecificationUtil.<Lesson, Subject>inChainUuid("subject", filterDto.getSubjectUuid(), JoinType.LEFT))
                .and(SpecificationUtil.<Lesson, Room>inChainUuid("room", filterDto.getRoomUuid(), JoinType.LEFT));
    }

    private Specification<Lesson> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Lesson, Subject> subjectJoin = root.join("subject", JoinType.LEFT);
                Join<Lesson, Room> roomJoin = root.join("room", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("length").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lessonType").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lessonStatus").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(subjectJoin.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(roomJoin.get("name")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
