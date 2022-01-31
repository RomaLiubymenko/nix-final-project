package ua.com.alevel.dto.table.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class AdminTableDto extends UserTableDto {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminTableDto entity = (AdminTableDto) o;
        return super.equals(entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getUuid(),
                getFirstName(),
                getLastName(),
                getEmail(),
                getGender(),
                getBirthDay(),
                getActivated());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", getUuid())
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("email", getEmail())
                .append("gender", getGender())
                .append("birthDay", getBirthDay())
                .append("activated", getActivated())
                .toString();
    }
}
