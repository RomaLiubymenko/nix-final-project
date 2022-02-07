package ua.com.alevel.dto.filter.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.alevel.enumeration.TutorStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TutorFilterDto extends UserFilterDto {

    private TutorStatus status;
    private List<UUID> subjectUuids = new ArrayList<>();

    public List<UUID> getSubjectUuids() {
        return subjectUuids;
    }

    public void setSubjectUuids(List<UUID> subjectUuids) {
        this.subjectUuids = subjectUuids;
    }

    public TutorStatus getStatus() {
        return status;
    }

    public void setStatus(TutorStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TutorFilterDto that = (TutorFilterDto) o;

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
