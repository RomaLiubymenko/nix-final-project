package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.StudentGroupFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Service
public class StudentGroupSpecificationBuilder implements SpecificationBuilder<StudentGroup, StudentGroupFilterDto> {

    @Override
    public Specification<StudentGroup> build(String query, StudentGroupFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<StudentGroup> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<StudentGroup> build(StudentGroupFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<StudentGroup>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("groupType", filterDto.getGroupType()))
                .and(SpecificationUtil.equalsChain("startDate", filterDto.getStartDate()))
                .and(SpecificationUtil.equalsChain("endDate", filterDto.getEndDate()))
                .and(SpecificationUtil.equalsChain("isFormed", filterDto.getIsFormed()))
                .and(SpecificationUtil.<StudentGroup, Student>inChainUuid("students", filterDto.getStudentUuids(), JoinType.LEFT));
    }

    private Specification<StudentGroup> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<StudentGroup, Student> studentJoin = root.join("students", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("groupType").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("startDate").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("endDate").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("isFormed").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
