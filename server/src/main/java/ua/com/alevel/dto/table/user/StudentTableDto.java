package ua.com.alevel.dto.table.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class StudentTableDto extends UserTableDto {

    private Set<StudentGroupAddInfoDto> studentGroups = new LinkedHashSet<>();

    public Set<StudentGroupAddInfoDto> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroupAddInfoDto> studentGroups) {
        this.studentGroups = studentGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTableDto entity = (StudentTableDto) o;
        return super.equals(entity) && Objects.equals(this.studentGroups, entity.studentGroups);
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
                getActivated(),
                studentGroups);
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
                .append("studentGroups", getStudentGroups())
                .toString();
    }

    public static class StudentGroupAddInfoDto {

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
            StudentGroupAddInfoDto entity = (StudentGroupAddInfoDto) o;
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
