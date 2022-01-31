package ua.com.alevel.dto.filter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class AdminFilterDto extends UserFilterDto implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AdminFilterDto that = (AdminFilterDto) o;

        return new EqualsBuilder()
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getEmail(), that.getEmail())
                .append(getGender(), that.getGender())
                .append(getBirthDay(), that.getBirthDay())
                .append(getActivated(), that.getActivated())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getFirstName())
                .append(getLastName())
                .append(getEmail())
                .append(getGender())
                .append(getBirthDay())
                .append(getActivated())
                .toHashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
