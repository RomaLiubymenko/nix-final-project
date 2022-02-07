package ua.com.alevel.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.user.AdminFilterDto;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.table.user.AdminTableDto;
import ua.com.alevel.facade.user.AdminFacade;

@RestController
@RequestMapping(value = "/api/v1/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends AbstractController<AdminTableDto, AdminProfileDto, AdminFilterDto> {

    private static final String ENTITY_NAME = "admin";
    private static final String URL = "/api/v1/admins";
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminFacade adminFacade;

    public AdminController(AdminFacade adminFacade) {
        super(URL, ENTITY_NAME, logger, adminFacade);
        this.adminFacade = adminFacade;
    }
}
