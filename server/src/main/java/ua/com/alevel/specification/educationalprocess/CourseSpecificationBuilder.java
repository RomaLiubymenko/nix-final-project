package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.CourseFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Course;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

@Service
public class CourseSpecificationBuilder implements SpecificationBuilder<Course, CourseFilterDto> {

    @Override
    public Specification<Course> build(String query, CourseFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Course> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Course> build(CourseFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Course>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("languageOfInstruction", filterDto.getLanguageOfInstruction()))
                .and(SpecificationUtil.equalsChain("knowledgeLevelOfCourse", filterDto.getKnowledgeLevelOfCourse()))
                .and(SpecificationUtil.equalsChain("startDate", filterDto.getStartDate()))
                .and(SpecificationUtil.equalsChain("endDate", filterDto.getEndDate()))
                .and(SpecificationUtil.equalsChain("isCompleted", filterDto.getIsCompleted()));
    }

    private Specification<Course> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("languageOfInstruction")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("knowledgeLevelOfCourse")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("startDate").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("endDate").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("isCompleted").as(String.class)), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
