package ua.com.alevel.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.config.LmsProperties;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.service.auth.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LmsProperties lmsProperties;
    private final AuthService authService;

    public AuthController(LmsProperties lmsProperties, AuthService authService) {
        this.lmsProperties = lmsProperties;
        this.authService = authService;
    }

    @GetMapping("/keycloak-info")
    public ResponseEntity<LmsProperties.KeyCloakProps> getKeycloakServerInfo() {
        return ResponseEntity.ok(lmsProperties.getKeyCloakProps());
    }

    @PostMapping("/signup")
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public ResponseEntity<UserProfileDto> registerNewUser(@RequestBody UserProfileDto user, @RequestParam("user-type") String userType) {
        AuthService.RegisterData registerData = authService.registerUser(user, userType);
        if (registerData.getResult() == null ||
                registerData.getSavedUser() == null ||
                registerData.getResult().getStatus() != 201) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().build();
    }
}
