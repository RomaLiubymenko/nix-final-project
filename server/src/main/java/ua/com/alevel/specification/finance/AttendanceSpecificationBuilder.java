package ua.com.alevel.specification.finance;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AttendanceFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class AttendanceSpecificationBuilder implements SpecificationBuilder<Attendance, AttendanceFilterDto> {

    @Override
    public Specification<Attendance> build(String query, AttendanceFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Attendance> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Attendance> build(AttendanceFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Attendance>equalsChain("status", filterDto.getStatus()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("paymentAmount", filterDto.getPaymentAmount()))
                .and(SpecificationUtil.<Attendance, Student>inChainUuid("student", filterDto.getStudentUuid(), JoinType.LEFT))
                .and(SpecificationUtil.<Attendance, Lesson>inChainUuid("lesson", filterDto.getLessonUuid(), JoinType.LEFT));
    }

    private Specification<Attendance> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Attendance, Student> studentJoin = root.join("student", JoinType.LEFT);
                Join<Attendance, Lesson> lessonJoin = root.join("lesson", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("paymentAmount").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("lastName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(lessonJoin.get("name")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
