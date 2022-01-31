package ua.com.alevel.controller;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.config.LmsProperties;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.facade.user.AdminFacade;
import ua.com.alevel.facade.user.UserFacade;
import ua.com.alevel.mapper.user.UserMapper;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.KeycloakAdminService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LmsProperties lmsProperties;
    private final KeycloakAdminService keycloakAdminService;
    private final UserFacade userFacade;
    private final AdminFacade adminFacade;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private Response result = null;
    private UserProfileDto savedUser = null;

    public AuthController(
            LmsProperties lmsProperties,
            KeycloakAdminService keycloakAdminService,
            UserFacade userFacade,
            AdminFacade adminFacade,
            RoleRepository roleRepository,
            UserRepository userRepository,
            UserMapper userMapper) {
        this.lmsProperties = lmsProperties;
        this.keycloakAdminService = keycloakAdminService;
        this.userFacade = userFacade;
        this.adminFacade = adminFacade;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @GetMapping("/keycloak-server-info")
    public ResponseEntity<LmsProperties.KeyCloakProps> getKeycloakServerInfo() {
        return ResponseEntity.ok(lmsProperties.getKeyCloakProps());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserProfileDto> registerNewUser(@RequestBody UserProfileDto user, @RequestParam("user-type") String userType) {
        registerUser(user, userType);
        if (result == null || savedUser == null || result.getStatus() != 201) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().build();
    }

    private void registerUser(UserProfileDto user, String userType) {
        String uuid;
        List<String> keycloakRoles = keycloakAdminService.getAllRealmRoles();
        if (userType != null && keycloakRoles.contains(userType.toUpperCase())) {
            try {
                switch (userType.toUpperCase()) {
                    case "ADMIN" -> {
                        AdminProfileDto adminDTO = new AdminProfileDto();
                        transferValues(user, adminDTO);
                        result = keycloakAdminService.createUser(userMapper.toEntity(user), userType);
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakAdminService.assignRoles(uuid, List.of("ADMIN"));
                        adminDTO.setUuid(UUID.fromString(uuid));
                        savedUser = adminFacade.create(adminDTO);
                    }
                    default -> {
                        AdminProfileDto adminDTO = new AdminProfileDto();
                        transferValues(user, adminDTO);
                        result = keycloakAdminService.createUser(userMapper.toEntity(user), userType);
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakAdminService.assignRoles(uuid, List.of("ADMIN"));
                        adminDTO.setUuid(UUID.fromString(uuid));
                        savedUser = adminFacade.create(adminDTO);
                    }
                }
            } catch (WebApplicationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void transferValues(UserProfileDto user, UserProfileDto dto) {
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setBirthDay(user.getBirthDay());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
    }
}
