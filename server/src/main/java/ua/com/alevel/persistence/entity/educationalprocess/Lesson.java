package ua.com.alevel.persistence.entity.educationalprocess;

import org.hibernate.Hibernate;
import ua.com.alevel.enumeration.LessonStatus;
import ua.com.alevel.enumeration.LessonType;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.entity.user.Tutor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lesson", indexes = {
        @Index(name = "idx_lesson_uuid", columnList = "uuid")
})
public class Lesson extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_id_sequence")
    @SequenceGenerator(name = "lesson_id_sequence", sequenceName = "lesson_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 510)
    private String description;

    @Column(name = "date", columnDefinition = "timestamp without time zone")
    private LocalDateTime date;

    @Column(name = "length")
    private Double length;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LessonType lessonType = LessonType.LESSON;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LessonStatus lessonStatus = LessonStatus.NEW;

    @OneToMany(mappedBy = "lesson", orphanRemoval = true)
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lessons")
    private Set<Student> students = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToMany(mappedBy = "lessons")
    private Set<Topic> topics = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToMany(mappedBy = "lessons")
    private Set<Tutor> tutors = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lessons")
    private Set<StudentGroup> studentGroups = new LinkedHashSet<>();

    public void addStudentGroup(StudentGroup studentGroup) {
        if (!studentGroups.contains(studentGroup)) {
            studentGroups.add(studentGroup);
            studentGroup.addLesson(this);
        }
    }

    public void removeStudentGroup(StudentGroup studentGroup) {
        if (studentGroups.contains(studentGroup)) {
            studentGroups.remove(studentGroup);
            studentGroup.removeLesson(this);
        }
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.addLesson(this);
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.removeLesson(this);
        }
    }

    public void addTopic(Topic topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
            topic.addLesson(this);
        }
    }

    public void removeTopic(Topic topic) {
        if (topics.contains(topic)) {
            topics.remove(topic);
            topic.removeLesson(this);
        }
    }

    public void addTutor(Tutor tutor) {
        if (!tutors.contains(tutor)) {
            tutors.add(tutor);
            tutor.addLesson(this);
        }
    }

    public void removeTutor(Tutor tutor) {
        if (tutors.contains(tutor)) {
            tutors.remove(tutor);
            tutor.removeLesson(this);
        }
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

    public Set<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
        this.tutors = tutors;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lesson that = (Lesson) o;
        return getUuid() != null && Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "uuid = " + getUuid() + ", " +
                "created = " + getCreated() + ", " +
                "updated = " + getUpdated() + ", " +
                "name = " + getName() + ", " +
                "description = " + getDescription() + ", " +
                "date = " + getDate() + ", " +
                "length = " + getLength() + ", " +
                "type = " + getLessonType() + ", " +
                "status = " + getLessonStatus() + ")";
    }
}
