package ua.com.alevel.controller.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.finance.AccountFilterDto;
import ua.com.alevel.dto.profile.finance.AccountProfileDto;
import ua.com.alevel.dto.table.finance.AccountTableDto;
import ua.com.alevel.facade.finance.AccountFacade;


@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends AbstractController<AccountTableDto, AccountProfileDto, AccountFilterDto> {

    private static final String ENTITY_NAME = "account";
    private static final String URL = "/api/v1/accounts";
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade) {
        super(URL, ENTITY_NAME, logger, accountFacade);
        this.accountFacade = accountFacade;
    }
}
