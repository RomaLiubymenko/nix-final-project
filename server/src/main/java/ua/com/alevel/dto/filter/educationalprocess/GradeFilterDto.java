package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class GradeFilterDto extends AbstractFilterDto {

    private String value;
    private String weight;
    private LocalDateTime date;
    private UUID studentUuid;
    private UUID tutorUuid;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(UUID studentUuid) {
        this.studentUuid = studentUuid;
    }

    public UUID getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(UUID tutorUuid) {
        this.tutorUuid = tutorUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeFilterDto entity = (GradeFilterDto) o;
        return Objects.equals(this.value, entity.value) &&
                Objects.equals(this.weight, entity.weight) &&
                Objects.equals(this.date, entity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, weight, date);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "value = " + value + ", " +
                "weight = " + weight + ", " +
                "date = " + date + ")";
    }
}
