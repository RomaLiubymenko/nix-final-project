package ua.com.alevel.specification.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.TutorFilterDto;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Service
public class TutorSpecificationBuilder implements SpecificationBuilder<Tutor, TutorFilterDto> {

    @Override
    public Specification<Tutor> build(String query, TutorFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Tutor> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Tutor> build(TutorFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Tutor>equalsChain("firstName", filterDto.getFirstName()))
                .and(SpecificationUtil.equalsChain("lastName", filterDto.getLastName()))
                .and(SpecificationUtil.equalsChain("email", filterDto.getEmail()))
                .and(SpecificationUtil.equalsChain("gender", filterDto.getGender()))
                .and(SpecificationUtil.equalsChain("birthDay", filterDto.getBirthDay()))
                .and(SpecificationUtil.equalsChain("activated", filterDto.getActivated()))
                .and(SpecificationUtil.equalsChain("status", filterDto.getStatus()))
                .and(SpecificationUtil.<Tutor, Subject>inChainUuid("subjects", filterDto.getSubjectUuids(), JoinType.LEFT));
    }

    private Specification<Tutor> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Tutor, Subject> subjectJoin = root.join("subjects", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("gender").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("activated").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("birthDay").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(subjectJoin.get("name")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }


}
