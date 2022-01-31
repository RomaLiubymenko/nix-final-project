package ua.com.alevel.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak.server")
public class KeycloakServerProperties {

    private KeycloakServerProperties() {
        this.adminUser = new AdminUser();
    }

    private AdminUser adminUser;

    @Value("keycloak.server.contextPath")
    private String contextPath;

    @Value("keycloak.server.realmImportFile")
    private String realmImportFile;

    public AdminUser getAdminUser() {
        if (adminUser == null) {
            adminUser = new AdminUser();
        }
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRealmImportFile() {
        return realmImportFile;
    }

    public void setRealmImportFile(String realmImportFile) {
        this.realmImportFile = realmImportFile;
    }

    public static class AdminUser {

        @Value("keycloak.server.adminUser.username")
        private String username;

        @Value("keycloak.server.adminUser.password")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
