package ua.com.alevel.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.user.UserFilterDto;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.dto.table.user.UserTableDto;
import ua.com.alevel.facade.user.UserFacade;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController<UserTableDto, UserProfileDto, UserFilterDto> {

    private static final String ENTITY_NAME = "user";
    private static final String URL = "/api/v1/users";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        super(URL, ENTITY_NAME, logger, userFacade);
        this.userFacade = userFacade;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserProfileDto> getUserByUsername(@PathVariable("username") String username) {
        logger.info("Request to get User by username : {}", username);
        Optional<UserProfileDto> userDto = userFacade.findByUsername(username);
        if (userDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto.get());

    }
}
