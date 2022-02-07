package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.finance.AttendanceProfileDto;
import ua.com.alevel.dto.profile.location.RoomProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.enumeration.LessonStatus;
import ua.com.alevel.enumeration.LessonType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LessonProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String description;
    private LocalDateTime date;
    private Double length;
    private LessonType lessonType;
    private LessonStatus lessonStatus;
    private Set<AttendanceProfileDto> attendances = new HashSet<>();
    private Set<StudentProfileDto> students = new HashSet<>();
    private SubjectProfileDto subject;
    private Set<TopicProfileDto> topics = new HashSet<>();
    private CourseProfileDto course;
    private RoomProfileDto room;
    private Set<TutorProfileDto> tutors = new HashSet<>();
    private Set<StudentGroupProfileDto> studentGroups = new HashSet<>();

    public Set<AttendanceProfileDto> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<AttendanceProfileDto> attendances) {
        this.attendances = attendances;
    }

    public Set<StudentProfileDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentProfileDto> students) {
        this.students = students;
    }

    public SubjectProfileDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectProfileDto subject) {
        this.subject = subject;
    }

    public Set<TopicProfileDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicProfileDto> topics) {
        this.topics = topics;
    }

    public CourseProfileDto getCourse() {
        return course;
    }

    public void setCourse(CourseProfileDto course) {
        this.course = course;
    }

    public RoomProfileDto getRoom() {
        return room;
    }

    public void setRoom(RoomProfileDto room) {
        this.room = room;
    }

    public Set<TutorProfileDto> getTutors() {
        return tutors;
    }

    public void setTutors(Set<TutorProfileDto> tutors) {
        this.tutors = tutors;
    }

    public Set<StudentGroupProfileDto> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroupProfileDto> studentGroups) {
        this.studentGroups = studentGroups;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public LessonStatus getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(LessonStatus lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonProfileDto entity = (LessonProfileDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.lessonType, entity.lessonType) &&
                Objects.equals(this.lessonStatus, entity.lessonStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, length, lessonType, lessonStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "date = " + date + ", " +
                "length = " + length + ", " +
                "lessonType = " + lessonType + ", " +
                "lessonStatus = " + lessonStatus + ")";
    }
}
