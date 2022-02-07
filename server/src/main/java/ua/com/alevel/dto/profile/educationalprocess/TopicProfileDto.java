package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TopicProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;
    private String description;
    private Double length;
    private SubjectProfileDto subject;
    private CourseProfileDto course;
    private Set<LessonProfileDto> lessons = new HashSet<>();
    private Set<ExerciseProfileDto> exercises = new HashSet<>();

    public Set<ExerciseProfileDto> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseProfileDto> exercises) {
        this.exercises = exercises;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public SubjectProfileDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectProfileDto subject) {
        this.subject = subject;
    }

    public CourseProfileDto getCourse() {
        return course;
    }

    public void setCourse(CourseProfileDto course) {
        this.course = course;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicProfileDto entity = (TopicProfileDto) o;
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
