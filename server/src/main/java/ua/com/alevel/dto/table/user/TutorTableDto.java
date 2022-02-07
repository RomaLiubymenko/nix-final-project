package ua.com.alevel.dto.table.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.enumeration.TutorStatus;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class TutorTableDto extends UserTableDto {

    private TutorStatus status;

    private Set<SubjectAddInfoDto> subjects = new HashSet<>();

    public Set<SubjectAddInfoDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectAddInfoDto> subjects) {
        this.subjects = subjects;
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
        TutorTableDto entity = (TutorTableDto) o;
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

    public static class SubjectAddInfoDto {

        private UUID uuid;
        private String name;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TutorTableDto.SubjectAddInfoDto entity = (TutorTableDto.SubjectAddInfoDto) o;
            return Objects.equals(this.uuid, entity.uuid) &&
                    Objects.equals(this.name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uuid, name);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "uuid = " + uuid + ", " +
                    "name = " + name + ")";
        }
    }
}
