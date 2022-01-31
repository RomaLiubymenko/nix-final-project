package ua.com.alevel.dto.filter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class StudentFilterDto extends UserFilterDto implements Serializable {

    private List<UUID> studentGroupUuids;

    public List<UUID> getStudentGroupUuids() {
        return studentGroupUuids;
    }

    public void setStudentGroupUuids(List<UUID> studentGroupUuids) {
        this.studentGroupUuids = studentGroupUuids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        StudentFilterDto that = (StudentFilterDto) o;

        return new EqualsBuilder()
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getEmail(), that.getEmail())
                .append(getGender(), that.getGender())
                .append(getBirthDay(), that.getBirthDay())
                .append(getActivated(), that.getActivated())
                .append(studentGroupUuids, that.studentGroupUuids)
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
                .append(studentGroupUuids)
                .toHashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
