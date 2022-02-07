package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SubjectProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;
    private String description;
    private Set<CourseProfileDto> courses = new HashSet<>();
    private Set<LessonProfileDto> lessons = new HashSet<>();
    private Set<TopicProfileDto> topics = new HashSet<>();
    private Set<ExerciseProfileDto> exercises = new HashSet<>();

    @NotNull
    private TutorProfileDto tutor;

    public Set<CourseProfileDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseProfileDto> courses) {
        this.courses = courses;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public Set<TopicProfileDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicProfileDto> topics) {
        this.topics = topics;
    }

    public Set<ExerciseProfileDto> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseProfileDto> exercises) {
        this.exercises = exercises;
    }

    public TutorProfileDto getTutor() {
        return tutor;
    }

    public void setTutor(TutorProfileDto tutor) {
        this.tutor = tutor;
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
        SubjectProfileDto entity = (SubjectProfileDto) o;
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
