package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.util.Objects;
import java.util.UUID;

public class TopicFilterDto extends AbstractFilterDto {

    private String name;
    private String description;
    private String length;
    private UUID subjectUuid;
    private UUID courseUuid;

    public UUID getSubjectUuid() {
        return subjectUuid;
    }

    public void setSubjectUuid(UUID subjectUuid) {
        this.subjectUuid = subjectUuid;
    }

    public UUID getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(UUID courseUuid) {
        this.courseUuid = courseUuid;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicFilterDto entity = (TopicFilterDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.length, entity.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, length);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "length = " + length + ")";
    }
}
