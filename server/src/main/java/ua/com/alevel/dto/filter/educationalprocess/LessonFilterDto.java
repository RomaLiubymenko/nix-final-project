package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.LessonStatus;
import ua.com.alevel.enumeration.LessonType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class LessonFilterDto extends AbstractFilterDto {

    private String name;
    private String description;
    private LocalDateTime date;
    private String length;
    private LessonType lessonType;
    private LessonStatus lessonStatus;
    private UUID subjectUuid;
    private UUID roomUuid;

    public UUID getSubjectUuid() {
        return subjectUuid;
    }

    public void setSubjectUuid(UUID subjectUuid) {
        this.subjectUuid = subjectUuid;
    }

    public UUID getRoomUuid() {
        return roomUuid;
    }

    public void setRoomUuid(UUID roomUuid) {
        this.roomUuid = roomUuid;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
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
        LessonFilterDto entity = (LessonFilterDto) o;
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
}
