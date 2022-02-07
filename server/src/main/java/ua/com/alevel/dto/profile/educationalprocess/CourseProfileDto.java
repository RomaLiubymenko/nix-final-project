package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CourseProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;
    private String description;

    @NotBlank
    @NotEmpty
    private String languageOfInstruction;

    @NotBlank
    @NotEmpty
    private String knowledgeLevelOfCourse;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private Boolean isCompleted;
    private Set<GradeProfileDto> grades = new HashSet<>();
    private Set<ExerciseProfileDto> exercises = new HashSet<>();
    private Set<SubjectProfileDto> subjects = new HashSet<>();
    private Set<TopicProfileDto> topics = new HashSet<>();
    private Set<LessonProfileDto> lessons = new HashSet<>();
    private StudentGroupProfileDto studentGroup;
    private Set<StudentProfileDto> students = new HashSet<>();
    private Set<TutorProfileDto> tutors = new HashSet<>();

    public Set<GradeProfileDto> getGrades() {
        return grades;
    }

    public void setGrades(Set<GradeProfileDto> grades) {
        this.grades = grades;
    }

    public Set<ExerciseProfileDto> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseProfileDto> exercises) {
        this.exercises = exercises;
    }

    public Set<SubjectProfileDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectProfileDto> subjects) {
        this.subjects = subjects;
    }

    public Set<TopicProfileDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicProfileDto> topics) {
        this.topics = topics;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public StudentGroupProfileDto getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroupProfileDto studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Set<StudentProfileDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentProfileDto> students) {
        this.students = students;
    }

    public Set<TutorProfileDto> getTutors() {
        return tutors;
    }

    public void setTutors(Set<TutorProfileDto> tutors) {
        this.tutors = tutors;
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

    public String getLanguageOfInstruction() {
        return languageOfInstruction;
    }

    public void setLanguageOfInstruction(String languageOfInstruction) {
        this.languageOfInstruction = languageOfInstruction;
    }

    public String getKnowledgeLevelOfCourse() {
        return knowledgeLevelOfCourse;
    }

    public void setKnowledgeLevelOfCourse(String knowledgeLevelOfCourse) {
        this.knowledgeLevelOfCourse = knowledgeLevelOfCourse;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseProfileDto entity = (CourseProfileDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.languageOfInstruction, entity.languageOfInstruction) &&
                Objects.equals(this.knowledgeLevelOfCourse, entity.knowledgeLevelOfCourse) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.isCompleted, entity.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, languageOfInstruction, knowledgeLevelOfCourse, startDate, endDate, isCompleted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "languageOfInstruction = " + languageOfInstruction + ", " +
                "knowledgeLevelOfCourse = " + knowledgeLevelOfCourse + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ", " +
                "isCompleted = " + isCompleted + ")";
    }
}
