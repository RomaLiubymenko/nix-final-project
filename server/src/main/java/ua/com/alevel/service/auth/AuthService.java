package ua.com.alevel.service.auth;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.facade.user.AdminFacade;
import ua.com.alevel.facade.user.StudentFacade;
import ua.com.alevel.facade.user.TutorFacade;
import ua.com.alevel.mapper.user.UserMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final AdminFacade adminFacade;
    private final StudentFacade studentFacade;
    private final TutorFacade tutorFacade;
    private final UserMapper userMapper;
    private final KeycloakService keycloakService;
    private final RegisterData registerData;

    public AuthService(KeycloakService keycloakService,
                       AdminFacade adminFacade,
                       StudentFacade studentFacade,
                       TutorFacade tutorFacade,
                       UserMapper userMapper) {
        this.keycloakService = keycloakService;
        this.adminFacade = adminFacade;
        this.studentFacade = studentFacade;
        this.tutorFacade = tutorFacade;
        this.userMapper = userMapper;
        registerData = new RegisterData();
        registerData.setResult(null);
        registerData.setSavedUser(null);
    }

    public RegisterData registerUser(UserProfileDto user, String userType) {
        String uuid;
        List<String> keycloakRoles = keycloakService.getAllRealmRoles();
        if (userType != null && keycloakRoles.contains(userType.toUpperCase())) {
            try {
                switch (userType.toUpperCase()) {
                    case "ADMIN" -> {
                        AdminProfileDto adminDTO = new AdminProfileDto();
                        transferValues(user, adminDTO);
                        Response result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("ADMIN"));
                        adminDTO.setUuid(UUID.fromString(uuid));
                        AdminProfileDto savedUser = adminFacade.create(adminDTO);
                        registerData.setResult(result);
                        registerData.setSavedUser(savedUser);
                    }
                    case "STUDENT" -> {
                        StudentProfileDto studentProfileDto = new StudentProfileDto();
                        transferValues(user, studentProfileDto);
                        Response result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("STUDENT"));
                        studentProfileDto.setUuid(UUID.fromString(uuid));
                        StudentProfileDto savedUser = studentFacade.create(studentProfileDto);
                        registerData.setResult(result);
                        registerData.setSavedUser(savedUser);
                    }
                    case "TUTOR" -> {
                        TutorProfileDto tutorProfileDto = new TutorProfileDto();
                        transferValues(user, tutorProfileDto);
                        Response result = keycloakService.createUser(userMapper.toEntity(user));
                        uuid = CreatedResponseUtil.getCreatedId(result);
                        keycloakService.assignRoles(uuid, List.of("TUTOR"));
                        tutorProfileDto.setUuid(UUID.fromString(uuid));
                        TutorProfileDto savedUser = tutorFacade.create(tutorProfileDto);
                        registerData.setResult(result);
                        registerData.setSavedUser(savedUser);
                    }
                }
                return registerData;
            } catch (WebApplicationException e) {
                throw new RuntimeException(e);
            }
        }
        return registerData;
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

    public static class RegisterData {

        private Response result;
        private UserProfileDto savedUser;

        public Response getResult() {
            return result;
        }

        public void setResult(Response result) {
            this.result = result;
        }

        public UserProfileDto getSavedUser() {
            return savedUser;
        }

        public void setSavedUser(UserProfileDto savedUser) {
            this.savedUser = savedUser;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RegisterData that = (RegisterData) o;
            return new EqualsBuilder()
                    .append(getResult(), that.getResult())
                    .append(getSavedUser(), that.getSavedUser())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getResult())
                    .append(getSavedUser())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("result", result)
                    .append("savedUser", savedUser)
                    .toString();
        }
    }
}
