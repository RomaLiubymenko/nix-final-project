package ua.com.alevel.specification.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.StudentFilterDto;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Service
public class StudentSpecificationBuilder implements SpecificationBuilder<Student, StudentFilterDto> {

    @Override
    public Specification<Student> build(String query, StudentFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Student> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Student> build(StudentFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Student>equalsChain("firstName", filterDto.getFirstName()))
                .and(SpecificationUtil.equalsChain("lastName", filterDto.getLastName()))
                .and(SpecificationUtil.equalsChain("email", filterDto.getEmail()))
                .and(SpecificationUtil.equalsChain("gender", filterDto.getGender()))
                .and(SpecificationUtil.equalsChain("birthDay", filterDto.getBirthDay()))
                .and(SpecificationUtil.equalsChain("activated", filterDto.getActivated()))
                .and(SpecificationUtil.<Student, StudentGroup>inChainUuid("studentGroups", filterDto.getStudentGroupUuids(), JoinType.LEFT));
    }

    private Specification<Student> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Student, StudentGroup> studentGroupJoin = root.join("studentGroups", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("gender").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("activated").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("birthDay").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentGroupJoin.get("name")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
