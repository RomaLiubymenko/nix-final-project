package ua.com.alevel.dto.table.educationalprocess;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractTableDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ExerciseTableDto extends AbstractTableDto {

    private String name;
    private LocalDateTime date;
    private String content;
    private TutorAddInfoDto tutor;
    private SubjectAddInfo subject;

    public SubjectAddInfo getSubject() {
        return subject;
    }

    public void setSubject(SubjectAddInfo subject) {
        this.subject = subject;
    }

    public TutorAddInfoDto getTutor() {
        return tutor;
    }

    public void setTutor(TutorAddInfoDto tutor) {
        this.tutor = tutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseTableDto that = (ExerciseTableDto) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getDate(), that.getDate())
                .append(getContent(), that.getContent())
                .append(getTutor(), that.getTutor())
                .append(getSubject(), that.getSubject())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDate())
                .append(getContent())
                .append(getTutor())
                .append(getSubject())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "content = " + content + ")";
    }

    public static class TutorAddInfoDto {

        private UUID uuid;
        private String username;
        private String firstName;
        private String lastName;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ExerciseTableDto.TutorAddInfoDto entity = (ExerciseTableDto.TutorAddInfoDto) o;
            return Objects.equals(this.uuid, entity.uuid) &&
                    Objects.equals(this.username, entity.username) &&
                    Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uuid, username, firstName, lastName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "uuid = " + uuid + ", " +
                    "username = " + username + ", " +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ")";
        }
    }

    public static class SubjectAddInfo {

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
            ExerciseTableDto.SubjectAddInfo that = (ExerciseTableDto.SubjectAddInfo) o;
            return new EqualsBuilder()
                    .append(getUuid(), that.getUuid())
                    .append(getName(), that.getName())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getUuid())
                    .append(getName())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("uuid", uuid)
                    .append("name", name)
                    .toString();
        }
    }
}
