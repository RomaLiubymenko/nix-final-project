package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.util.Objects;
import java.util.UUID;

public class SubjectFilterDto extends AbstractFilterDto {

    private String name;
    private String description;
    private UUID tutorUuid;

    public UUID getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(UUID tutorUuid) {
        this.tutorUuid = tutorUuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectFilterDto entity = (SubjectFilterDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ")";
    }
}
