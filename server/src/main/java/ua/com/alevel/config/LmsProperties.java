package ua.com.alevel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
@ConfigurationProperties(prefix = "lms")
public class LmsProperties {

    private final CorsConfiguration cors = new CorsConfiguration();
    private final KeyCloakProps keyCloakProps = new KeyCloakProps();
    private final Client client = new Client();

    public CorsConfiguration getCors() {
        return cors;
    }

    public KeyCloakProps getKeyCloakProps() {
        return keyCloakProps;
    }

    public Client getClient() {
        return client;
    }

    public class KeyCloakProps {

        private String authServerUrl;
        private String realm;
        private String clientId;
        private String grantType;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getAuthServerUrl() {
            return authServerUrl;
        }

        public void setAuthServerUrl(String authServerUrl) {
            this.authServerUrl = authServerUrl;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getRealm() {
            return realm;
        }

        public void setRealm(String realm) {
            this.realm = realm;
        }
    }

    public class Client {

        private String admin;
        private String tutor;
        private String student;

        public String getTutor() {
            return tutor;
        }

        public void setTutor(String tutor) {
            this.tutor = tutor;
        }

        public String getStudent() {
            return student;
        }

        public void setStudent(String student) {
            this.student = student;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }
    }
}
