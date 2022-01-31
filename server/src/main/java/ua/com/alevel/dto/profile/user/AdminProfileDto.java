package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminProfileDto extends UserProfileDto {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AdminProfileDto that = (AdminProfileDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", getUuid())
                .append("username", getUsername())
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("email", getEmail())
                .append("gender", getGender())
                .append("birthDay", getBirthDay())
                .append("activated", getActivated())
                .append("account", getAccount())
                .toString();
    }
}
