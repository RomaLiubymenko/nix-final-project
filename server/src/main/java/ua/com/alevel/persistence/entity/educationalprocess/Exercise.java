package ua.com.alevel.persistence.entity.educationalprocess;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Student;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "exercise", indexes = {
        @Index(name = "idx_exercise_uuid", columnList = "uuid")
})
public class Exercise extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_id")
    @SequenceGenerator(name = "exercise_id", sequenceName = "exercise_id", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(name = "exercise_lms_files",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "lms_file_id"))
    private Set<LmsFile> lmsFiles = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "exercise_students",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<LmsFile> getLmsFiles() {
        return lmsFiles;
    }

    public void setLmsFiles(Set<LmsFile> lmsFiles) {
        this.lmsFiles = lmsFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exercise exercise = (Exercise) o;
        return getUuid() != null && Objects.equals(getUuid(), exercise.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "uuid = " + getUuid() + ", " +
                "created = " + getCreated() + ", " +
                "updated = " + getUpdated() + ", " +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "content = " + content + ")";
    }
}
