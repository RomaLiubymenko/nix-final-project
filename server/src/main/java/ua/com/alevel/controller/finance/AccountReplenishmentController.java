package ua.com.alevel.controller.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.finance.AccountReplenishmentFilterDto;
import ua.com.alevel.dto.profile.finance.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.table.finance.AccountReplenishmentTableDto;
import ua.com.alevel.facade.finance.AccountReplenishmentFacade;


@RestController
@RequestMapping(value = "/api/v1/account-replenishments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountReplenishmentController extends AbstractController<AccountReplenishmentTableDto, AccountReplenishmentProfileDto, AccountReplenishmentFilterDto> {

    private static final String ENTITY_NAME = "account-replenishment";
    private static final String URL = "/api/v1/account-replenishments";
    private static final Logger logger = LoggerFactory.getLogger(AccountReplenishmentController.class);
    private final AccountReplenishmentFacade accountReplenishmentFacade;

    public AccountReplenishmentController(AccountReplenishmentFacade accountReplenishmentFacade) {
        super(URL, ENTITY_NAME, logger, accountReplenishmentFacade);
        this.accountReplenishmentFacade = accountReplenishmentFacade;
    }
}
