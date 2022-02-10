package ua.com.alevel.specification.finance;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountingOfPaymentFilterDto;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
public class AccountingOfPaymentSpecificationBuilder implements SpecificationBuilder<AccountingOfPayment, AccountingOfPaymentFilterDto> {

    @Override
    public Specification<AccountingOfPayment> build(String query, AccountingOfPaymentFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<AccountingOfPayment> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<AccountingOfPayment> build(AccountingOfPaymentFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<AccountingOfPayment>equalsChain("date", filterDto.getDate()))
                .and(SpecificationUtil.equalsChain("amount", filterDto.getAmount()))
                .and(SpecificationUtil.equalsChain("purpose", filterDto.getPurpose()))
                .and(SpecificationUtil.equalsChain("comment", filterDto.getComment()))
                .and(SpecificationUtil.<AccountingOfPayment, Tutor>inChainUuid("tutor", filterDto.getTutorUuid(), JoinType.LEFT));
    }

    private Specification<AccountingOfPayment> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<AccountingOfPayment, Tutor> tutorJoin = root.join("tutor", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("date").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("amount").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("purpose")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("comment")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(tutorJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
