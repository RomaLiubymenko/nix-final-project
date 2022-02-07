package ua.com.alevel.facade.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountFilterDto;
import ua.com.alevel.dto.profile.finance.AccountProfileDto;
import ua.com.alevel.dto.table.finance.AccountTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.finance.AccountMapper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.service.finance.AccountService;
import ua.com.alevel.specification.finance.AccountSpecificationBuilder;

@Service
public class AccountFacade extends AbstractFacade<Account, AccountFilterDto, AccountTableDto, AccountProfileDto> {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final AccountSpecificationBuilder accountSpecificationBuilder;

    public AccountFacade(AccountService accountService,
                         AccountMapper accountMapper,
                         AccountSpecificationBuilder accountSpecificationBuilder) {
        super(accountService, accountMapper, accountSpecificationBuilder);
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.accountSpecificationBuilder = accountSpecificationBuilder;
    }
}
