package ua.com.alevel.service.auth;

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

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
@Transactional
public class KeycloakService {

    private static final Logger log = LoggerFactory.getLogger(KeycloakService.class);
    private final String realm;
    private final String authServerUrl;
    private final String keycloakAdminUsername;
    private final String keycloakAdminPassword;
    private final String clientSecret;
    private final String clientId;

    private Keycloak keycloak = null;

    public KeycloakService(
            @Value("${keycloak.auth-server-url}") String authServerUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak-admin-username}") String keycloakAdminUsername,
            @Value("${keycloak-admin-password}") String keycloakAdminPassword,
            @Value("${keycloak.credentials.secret}") String clientSecret,
            @Value("${keycloak.resource}") String clientId
    ) {
        this.keycloakAdminPassword = keycloakAdminPassword;
        this.clientSecret = clientSecret;
        this.clientId = clientId;
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.keycloakAdminUsername = keycloakAdminUsername;
    }

    @PostConstruct
    public void initConfiguration() {
        keycloak = KeycloakBuilder.builder()
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .serverUrl(authServerUrl)
                .realm(realm)
                .username(keycloakAdminUsername)
                .password(keycloakAdminPassword)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
                .build();
    }

    public Keycloak getInstance() {
        return keycloak;
    }

    public UserResource getUser(String uuid) {
        return this.keycloak.realm(realm).users().get(uuid);
    }

    public Response createUser(User user) {
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
        attributes.put("gender", Collections.singletonList(user.getGender() != null ? user.getGender().name() : null));
        attributes.put("birth_day", Collections.singletonList(user.getBirthDay() != null ? user.getBirthDay().toString() : null));

        kcUser.setAttributes(attributes);
        return usersResource.create(kcUser);
    }

    public void updateUser(User user) {
        UserResource userResource = this.getUser(user.getUuid().toString());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender", Collections.singletonList(user.getGender() != null ? user.getGender().name() : null));
        attributes.put("birth_day", Collections.singletonList(user.getBirthDay() != null ? user.getBirthDay().toString() : null));
        kcUser.setAttributes(attributes);
        userResource.update(kcUser);
    }

    public void deleteUserByUuid(UUID uuid) {
        UserResource userResource = this.getUser(uuid.toString());
        userResource.remove();
    }

    public void deleteUserByUuids(Set<UUID> uuids) {
        for (UUID uuid : uuids) {
            UserResource userResource = this.getUser(uuid.toString());
            userResource.remove();
        }
    }

    public CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public List<String> getAllRealmRoles() {
        return keycloak.realm(realm)
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

        List<String> serverRoles = existingRoles.stream()
                .map(RoleRepresentation::getName)
                .toList();

        List<RoleRepresentation> resultRoles = new ArrayList<>();
        for (String role : roles) {
            int index = serverRoles.indexOf(role);
            if (index != -1) {
                resultRoles.add(existingRoles.get(index));
            } else {
                log.error("Role doesn't exist");
            }
        }
        return resultRoles;
    }
}
