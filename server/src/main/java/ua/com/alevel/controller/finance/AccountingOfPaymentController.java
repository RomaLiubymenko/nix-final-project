package ua.com.alevel.controller.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.finance.AccountingOfPaymentFilterDto;
import ua.com.alevel.dto.profile.finance.AccountingOfPaymentProfileDto;
import ua.com.alevel.dto.table.finance.AccountingOfPaymentTableDto;
import ua.com.alevel.facade.finance.AccountingOfPaymentFacade;


@RestController
@RequestMapping(value = "/api/v1/accounting-of-payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountingOfPaymentController extends AbstractController<AccountingOfPaymentTableDto, AccountingOfPaymentProfileDto, AccountingOfPaymentFilterDto> {

    private static final String ENTITY_NAME = "accounting-of-payment";
    private static final String URL = "/api/v1/accounting-of-payments";
    private static final Logger logger = LoggerFactory.getLogger(AccountingOfPaymentController.class);
    private final AccountingOfPaymentFacade accountingOfPaymentFacade;

    public AccountingOfPaymentController(AccountingOfPaymentFacade accountingOfPaymentFacade) {
        super(URL, ENTITY_NAME, logger, accountingOfPaymentFacade);
        this.accountingOfPaymentFacade = accountingOfPaymentFacade;
    }
}
