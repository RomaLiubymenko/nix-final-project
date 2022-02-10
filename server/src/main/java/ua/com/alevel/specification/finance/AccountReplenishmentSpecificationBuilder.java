package ua.com.alevel.specification.finance;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountReplenishmentFilterDto;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class AccountReplenishmentSpecificationBuilder implements SpecificationBuilder<AccountReplenishment, AccountReplenishmentFilterDto> {

    @Override
    public Specification<AccountReplenishment> build(String query, AccountReplenishmentFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<AccountReplenishment> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<AccountReplenishment> build(AccountReplenishmentFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<AccountReplenishment>equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.equalsChain("amount", filterDto.getAmount()))
                .and(SpecificationUtil.equalsChain("payer", filterDto.getPayer()))
                .and(SpecificationUtil.equalsChain("purpose", filterDto.getPurpose()))
                .and(SpecificationUtil.equalsChain("comment", filterDto.getComment()))
                .and(SpecificationUtil.equalsChain("replenishmentMethod", filterDto.getReplenishmentMethod()))
                .and(SpecificationUtil.<AccountReplenishment, Student>inChainUuid("student", filterDto.getStudentUuid(), JoinType.LEFT));
    }

    private Specification<AccountReplenishment> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<AccountReplenishment, Student> studentJoin = root.join("student", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("amount").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("payer")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("purpose")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("comment")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("replenishmentMethod").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
