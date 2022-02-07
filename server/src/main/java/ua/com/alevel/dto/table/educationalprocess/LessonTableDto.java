package ua.com.alevel.dto.table.educationalprocess;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.enumeration.LessonStatus;
import ua.com.alevel.enumeration.LessonType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class LessonTableDto extends AbstractTableDto {

    private String name;
    private String description;
    private LocalDateTime date;
    private Double length;
    private LessonType lessonType;
    private LessonStatus lessonStatus;
    private SubjectAddInfo subject;
    private RoomAddInfo room;

    public RoomAddInfo getRoom() {
        return room;
    }

    public void setRoom(RoomAddInfo room) {
        this.room = room;
    }

    public SubjectAddInfo getSubject() {
        return subject;
    }

    public void setSubject(SubjectAddInfo subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public LessonStatus getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(LessonStatus lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTableDto entity = (LessonTableDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.lessonType, entity.lessonType) &&
                Objects.equals(this.lessonStatus, entity.lessonStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, length, lessonType, lessonStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "date = " + date + ", " +
                "length = " + length + ", " +
                "lessonType = " + lessonType + ", " +
                "lessonStatus = " + lessonStatus + ")";
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
            LessonTableDto.SubjectAddInfo that = (LessonTableDto.SubjectAddInfo) o;
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

    public static class RoomAddInfo {

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
            LessonTableDto.RoomAddInfo that = (LessonTableDto.RoomAddInfo) o;
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
