package ua.com.alevel.specification.finance;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountFilterDto;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.specification.SpecificationBuilder;
import ua.com.alevel.util.SpecificationUtil;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Service
public class AccountSpecificationBuilder implements SpecificationBuilder<Account, AccountFilterDto> {

    @Override
    public Specification<Account> build(String query, AccountFilterDto filterDto) {
        return build(filterDto).and(byQuery(query));
    }

    @Override
    public Specification<Account> build(String query) {
        return Specification.where(byQuery(query));
    }

    @Override
    public Specification<Account> build(AccountFilterDto filterDto) {
        return Specification.where(SpecificationUtil.<Account>equalsChain("name", filterDto.getName()))
                .and(SpecificationUtil.equalsChain("description", filterDto.getDescription()))
                .and(SpecificationUtil.equalsChain("accountChangedDate", filterDto.getAccountChangedDate()))
                .and(SpecificationUtil.equalsChain("balance", filterDto.getBalance()))
                .and(SpecificationUtil.equalsChain("isBlocked", filterDto.getIsBlocked()))
                .and(SpecificationUtil.equalsChain("type", filterDto.getType()))
                .and(SpecificationUtil.equalsChain("ownershipType", filterDto.getOwnershipType()))
                .and(SpecificationUtil.<Account, User>inChainUuid("users", filterDto.getUserUuids(), JoinType.LEFT));
    }

    private Specification<Account> byQuery(String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            final String queryString = query.strip();
            if (StringUtils.isNotBlank(queryString)) {
                String queryFilter = "%" + queryString.toLowerCase() + "%";
                Join<Account, User> userJoin = root.join("users", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("accountChangedDate").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("balance").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("isBlocked").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("type").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("ownershipType").as(String.class)), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("firstName")), queryFilter),
                        criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("lastName")), queryFilter)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}
