package ua.com.alevel.controller.auth;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.config.LmsProperties;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.facade.user.AdminFacade;
import ua.com.alevel.facade.user.StudentFacade;
import ua.com.alevel.facade.user.TutorFacade;
import ua.com.alevel.mapper.user.UserMapper;
import ua.com.alevel.service.auth.KeycloakService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LmsProperties lmsProperties;
    private final KeycloakService keycloakService;
    private final AdminFacade adminFacade;
    private final StudentFacade studentFacade;
    private final TutorFacade tutorFacade;
    private final UserMapper userMapper;

    private Response result = null;
    private UserProfileDto savedUser = null;

    public AuthController(LmsProperties lmsProperties,
                          KeycloakService keycloakService,
                          AdminFacade adminFacade,
                          StudentFacade studentFacade,
                          TutorFacade tutorFacade,
                          UserMapper userMapper) {
        this.lmsProperties = lmsProperties;
        this.keycloakService = keycloakService;
        this.adminFacade = adminFacade;
        this.studentFacade = studentFacade;
        this.tutorFacade = tutorFacade;
        this.userMapper = userMapper;
    }


    @GetMapping("/keycloak-info")
    public ResponseEntity<LmsProperties.KeyCloakProps> getKeycloakServerInfo() {
        return ResponseEntity.ok(lmsProperties.getKeyCloakProps());
    }

    @PostMapping("/signup")
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public ResponseEntity<UserProfileDto> registerNewUser(@RequestBody UserProfileDto user, @RequestParam("user-type") String userType) {
        registerUser(user, userType);
        if (result == null || savedUser == null || result.getStatus() != 201) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().build();
    }

    private void registerUser(UserProfileDto user, String userType) {
        String uuid;
        List<String> keycloakRoles = keycloakService.getAllRealmRoles();
        if (userType != null && keycloakRoles.contains(userType.toUpperCase())) {
            try {
                switch (userType.toUpperCase()) {
                    case "ADMIN" -> {
                        AdminProfileDto adminDTO = new AdminProfileDto();
                        transferValues(user, adminDTO);
                        result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("ADMIN"));
                        adminDTO.setUuid(UUID.fromString(uuid));
                        savedUser = adminFacade.create(adminDTO);
                    }
                    case "STUDENT" -> {
                        StudentProfileDto studentProfileDto = new StudentProfileDto();
                        transferValues(user, studentProfileDto);
                        result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("STUDENT"));
                        studentProfileDto.setUuid(UUID.fromString(uuid));
                        savedUser = studentFacade.create(studentProfileDto);
                    }
                    case "TUTOR" -> {
                        TutorProfileDto tutorProfileDto = new TutorProfileDto();
                        transferValues(user, tutorProfileDto);
                        result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("TUTOR"));
                        tutorProfileDto.setUuid(UUID.fromString(uuid));
                        savedUser = tutorFacade.create(tutorProfileDto);
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
