package ua.com.alevel.facade.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountReplenishmentFilterDto;
import ua.com.alevel.dto.profile.finance.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.table.finance.AccountReplenishmentTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.finance.AccountReplenishmentMapper;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;
import ua.com.alevel.service.finance.AccountReplenishmentService;
import ua.com.alevel.specification.finance.AccountReplenishmentSpecificationBuilder;

@Service
public class AccountReplenishmentFacade extends AbstractFacade<AccountReplenishment, AccountReplenishmentFilterDto, AccountReplenishmentTableDto, AccountReplenishmentProfileDto> {

    private final AccountReplenishmentService accountReplenishmentService;
    private final AccountReplenishmentMapper accountReplenishmentMapper;
    private final AccountReplenishmentSpecificationBuilder accountReplenishmentSpecificationBuilder;

    public AccountReplenishmentFacade(AccountReplenishmentService accountReplenishmentService,
                                      AccountReplenishmentMapper accountReplenishmentMapper,
                                      AccountReplenishmentSpecificationBuilder accountReplenishmentSpecificationBuilder) {
        super(accountReplenishmentService, accountReplenishmentMapper, accountReplenishmentSpecificationBuilder);
        this.accountReplenishmentService = accountReplenishmentService;
        this.accountReplenishmentMapper = accountReplenishmentMapper;
        this.accountReplenishmentSpecificationBuilder = accountReplenishmentSpecificationBuilder;
    }
}
