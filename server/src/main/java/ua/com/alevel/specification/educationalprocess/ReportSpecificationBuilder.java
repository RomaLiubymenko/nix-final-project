package ua.com.alevel.specification.educationalprocess;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.ReportFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Report;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class ReportSpecificationBuilder implements SpecificationBuilder<Report, ReportFilterDto> {

    @Override
    public Specification<Report> build(String query, ReportFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Report> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Report> build(ReportFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Report>equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.equalsChain("content", filterDto.getContent()))
                .and(SpecificationUtil.equalsChain("comment", filterDto.getComment()))
                .and(SpecificationUtil.<Report, Student>inChainUuid("student", List.of(filterDto.getStudentUuid()), JoinType.LEFT))
                .and(SpecificationUtil.<Report, Tutor>inChainUuid("tutor", List.of(filterDto.getTutorUuid()), JoinType.LEFT));
    }

    private Specification<Report> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Report, Student> studentJoin = root.join("student", JoinType.LEFT);
                Join<Report, Tutor> tutorJoin = root.join("tutor", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("comment")), queryFilter),
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
