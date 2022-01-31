package ua.com.alevel.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.*;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
@Transactional
public class KeycloakAdminService {
    private final Logger log = LoggerFactory.getLogger(KeycloakAdminService.class);

    private Keycloak keycloak = null;
    private List<String> lmsRoles = null;

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    @Value("${keycloak.auth-server-url}")
    public String serverUrl = "http://localhost:8081/auth";

    @Value("${keycloak.realm}")
    public String realm = "altronica-lms";

    @Value("${keycloak-admin-username}")
    public String adminUsername = "admin";

    @Value("${keycloak-admin-password}")
    public String adminPassword = "password";

    @Value("${keycloak.credentials.secret}")
    public String clientSecret = "secret";

    @Value("${keycloak.resource}")
    public String clientId = "lms-base";

    public KeycloakAdminService(
            UserRepository userRepository,
            StudentRepository studentRepository,
            TutorRepository tutorRepository,
            AdminRepository adminRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initConfiguration() {
        keycloak = KeycloakBuilder.builder()
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .serverUrl(serverUrl)
                .realm(realm)
                .username(adminUsername)
                .password(adminPassword)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    }

    public Keycloak getInstance() {
        return keycloak;
    }

    public UserResource getUser(String uuid) {
        return this.keycloak.realm(realm).users().get(uuid);
    }

    public Response createUser(User user, String userType) {
        UsersResource usersResource = this.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);

        kcUser.setEmailVerified(false);

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender", Collections.singletonList(user.getGender().name()));
        attributes.put("birth_day", Collections.singletonList(user.getBirthDay() != null ? user.getBirthDay().toString() : null));

        kcUser.setAttributes(attributes);
        return usersResource.create(kcUser);
    }

    public UserResource updateUser(User user) {
        UserResource userResource = this.getUser(user.getUuid().toString());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        userResource.update(kcUser);
        return userResource;
    }

    public CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public List<String> getAllRealmRoles() {
        return keycloak
                .realm(realm)
                .roles()
                .list()
                .stream()
                .map(RoleRepresentation::getName)
                .toList();
    }

    public void assignRoles(String userId, List<String> roles) {
        List<RoleRepresentation> roleList = rolesToRealmRoleRepresentation(roles);
        keycloak.realm(realm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(roleList);

    }

    private List<RoleRepresentation> rolesToRealmRoleRepresentation(List<String> roles) {
        List<RoleRepresentation> existingRoles = keycloak.realm(realm)
                .roles()
                .list();

        List<String> serverRoles = existingRoles
                .stream()
                .map(RoleRepresentation::getName)
                .toList();

        List<RoleRepresentation> resultRoles = new ArrayList<>();
        for (String role : roles) {
            int index = serverRoles.indexOf(role);
            if (index != -1) {
                resultRoles.add(existingRoles.get(index));
            } else {
                log.info("Role doesn't exist");
            }
        }
        return resultRoles;
    }

    private void transferValues(User castUser, User currentUser) {
        currentUser.setUuid(castUser.getUuid());
        currentUser.setUsername(castUser.getUsername());
        currentUser.setFirstName(castUser.getFirstName());
        currentUser.setLastName(castUser.getLastName());
        currentUser.setEmail(castUser.getEmail());
        currentUser.setActivated(castUser.getActivated());
        currentUser.setBirthDay(castUser.getBirthDay());
        currentUser.setGender(castUser.getGender());
        currentUser.setRole(castUser.getRole());
    }
}
