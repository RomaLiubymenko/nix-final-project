package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.AccountProfileDto;
import ua.com.alevel.enumeration.GenderType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class UserProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String username;

    private String firstName;
    private String lastName;
    private String password;

    @Email
    @NotEmpty
    private String email;

    private GenderType gender;
    private LocalDate birthDay;
    private Boolean activated;
    private AccountProfileDto account;
    private RoleDto role;

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountProfileDto getAccount() {
        return account;
    }

    public void setAccount(AccountProfileDto account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserProfileDto userDto = (UserProfileDto) o;

        return new EqualsBuilder()
                .append(getUuid(), userDto.getUuid())
                .append(username, userDto.username)
                .append(firstName, userDto.firstName)
                .append(lastName, userDto.lastName)
                .append(email, userDto.email)
                .append(gender, userDto.gender)
                .append(birthDay, userDto.birthDay)
                .append(activated, userDto.activated)
                .append(account, userDto.account)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUuid())
                .append(username)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(gender)
                .append(birthDay)
                .append(activated)
                .append(account)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", getUuid())
                .append("username", username)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("gender", gender)
                .append("birthDay", birthDay)
                .append("activated", activated)
                .append("account", account)
                .toString();
    }
}
