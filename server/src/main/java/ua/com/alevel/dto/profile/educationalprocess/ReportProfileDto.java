package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ReportProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String content;
    private LocalDateTime date;
    private String comment;

    @NotNull
    private TutorProfileDto tutor;

    @NotNull
    private StudentProfileDto student;
    private GradeProfileDto grade;
    private Set<ExerciseProfileDto> exercises = new HashSet<>();

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

    public StudentProfileDto getStudent() {
        return student;
    }

    public void setStudent(StudentProfileDto student) {
        this.student = student;
    }

    public GradeProfileDto getGrade() {
        return grade;
    }

    public void setGrade(GradeProfileDto grade) {
        this.grade = grade;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportProfileDto entity = (ReportProfileDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.comment, entity.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, content, comment);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "content = " + content + ", " +
                "comment = " + comment + ")";
    }
}
