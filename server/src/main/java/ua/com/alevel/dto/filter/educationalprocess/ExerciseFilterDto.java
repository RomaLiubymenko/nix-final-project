package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ExerciseFilterDto extends AbstractFilterDto {

    private String name;
    private LocalDateTime date;
    private String content;
    private UUID tutorUuid;
    private UUID subjectUuid;

    public UUID getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(UUID tutorUuid) {
        this.tutorUuid = tutorUuid;
    }

    public UUID getSubjectUuid() {
        return subjectUuid;
    }

    public void setSubjectUuid(UUID subjectUuid) {
        this.subjectUuid = subjectUuid;
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
        ExerciseFilterDto entity = (ExerciseFilterDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.content, entity.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, content);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "content = " + content + ")";
    }
}
