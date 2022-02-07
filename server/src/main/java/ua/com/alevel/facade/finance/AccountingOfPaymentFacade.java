package ua.com.alevel.facade.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AccountingOfPaymentFilterDto;
import ua.com.alevel.dto.profile.finance.AccountingOfPaymentProfileDto;
import ua.com.alevel.dto.table.finance.AccountingOfPaymentTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.finance.AccountingOfPaymentMapper;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;
import ua.com.alevel.service.finance.AccountingOfPaymentService;
import ua.com.alevel.specification.finance.AccountingOfPaymentSpecificationBuilder;

@Service
public class AccountingOfPaymentFacade extends AbstractFacade<AccountingOfPayment, AccountingOfPaymentFilterDto, AccountingOfPaymentTableDto, AccountingOfPaymentProfileDto> {

    private final AccountingOfPaymentService accountingOfPaymentService;
    private final AccountingOfPaymentMapper accountingOfPaymentMapper;
    private final AccountingOfPaymentSpecificationBuilder accountingOfPaymentSpecificationBuilder;

    public AccountingOfPaymentFacade(AccountingOfPaymentService accountingOfPaymentService,
                                     AccountingOfPaymentMapper accountingOfPaymentMapper,
                                     AccountingOfPaymentSpecificationBuilder accountingOfPaymentSpecificationBuilder) {
        super(accountingOfPaymentService, accountingOfPaymentMapper, accountingOfPaymentSpecificationBuilder);
        this.accountingOfPaymentService = accountingOfPaymentService;
        this.accountingOfPaymentMapper = accountingOfPaymentMapper;
        this.accountingOfPaymentSpecificationBuilder = accountingOfPaymentSpecificationBuilder;
    }
}
