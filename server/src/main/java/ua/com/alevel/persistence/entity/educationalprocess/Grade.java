package ua.com.alevel.persistence.entity.educationalprocess;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "grade", indexes = {
        @Index(name = "idx_grade_uuid", columnList = "uuid")
})
public class Grade extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_id")
    @SequenceGenerator(name = "grade_id", sequenceName = "grade_id", allocationSize = 1)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Grade grade = (Grade) o;
        return getUuid() != null && Objects.equals(getUuid(), grade.getUuid());
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
                "value = " + getValue() + ", " +
                "weight = " + getWeight() + ", " +
                "date = " + getDate() + ")";
    }
}
