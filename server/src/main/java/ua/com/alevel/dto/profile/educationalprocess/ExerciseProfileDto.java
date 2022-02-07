package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ExerciseProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;
    private LocalDateTime date;
    private String content;
    private Set<StudentProfileDto> students = new HashSet<>();
    private ReportProfileDto report;
    private TopicProfileDto topic;
    private CourseProfileDto course;
    private SubjectProfileDto subject;
    private TutorProfileDto tutor;

    public Set<StudentProfileDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentProfileDto> students) {
        this.students = students;
    }

    public ReportProfileDto getReport() {
        return report;
    }

    public void setReport(ReportProfileDto report) {
        this.report = report;
    }

    public TopicProfileDto getTopic() {
        return topic;
    }

    public void setTopic(TopicProfileDto topic) {
        this.topic = topic;
    }

    public CourseProfileDto getCourse() {
        return course;
    }

    public void setCourse(CourseProfileDto course) {
        this.course = course;
    }

    public SubjectProfileDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectProfileDto subject) {
        this.subject = subject;
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
        ExerciseProfileDto entity = (ExerciseProfileDto) o;
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
