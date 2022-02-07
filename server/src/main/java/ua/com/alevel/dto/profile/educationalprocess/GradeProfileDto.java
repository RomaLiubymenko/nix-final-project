package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class GradeProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String value;

    @NotNull
    private Integer weight;
    private LocalDateTime date;
    private ReportProfileDto report;
    private CourseProfileDto course;

    public ReportProfileDto getReport() {
        return report;
    }

    public void setReport(ReportProfileDto report) {
        this.report = report;
    }

    public CourseProfileDto getCourse() {
        return course;
    }

    public void setCourse(CourseProfileDto course) {
        this.course = course;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeProfileDto entity = (GradeProfileDto) o;
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
