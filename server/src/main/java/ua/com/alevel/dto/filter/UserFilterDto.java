package ua.com.alevel.dto.filter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.GenderType;

import java.io.Serializable;
import java.time.LocalDate;

public class UserFilterDto extends AbstractFilterDto implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private GenderType gender;
    private LocalDate birthDay;
    private String activated;

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

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserFilterDto that = (UserFilterDto) o;

        return new EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(email, that.email)
                .append(gender, that.gender)
                .append(birthDay, that.birthDay)
                .append(activated, that.activated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(gender)
                .append(birthDay)
                .append(activated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("gender", gender)
                .append("birthDay", birthDay)
                .append("activated", activated)
                .toString();
    }
}
