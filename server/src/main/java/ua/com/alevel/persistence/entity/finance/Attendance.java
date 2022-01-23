package ua.com.alevel.persistence.entity.finance;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.enumeration.AttendanceStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "attendance", indexes = {
        @Index(name = "idx_attendance_uuid", columnList = "uuid")
})
public class Attendance extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id")
    @SequenceGenerator(name = "attendance_id", sequenceName = "attendance_id", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AttendanceStatus status = AttendanceStatus.PENDING;

    @Column(name = "description", length = 510)
    private String description;

    @Column(name = "payment_amount", precision = 9, scale = 2, nullable = false)
    private BigDecimal paymentAmount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attendance that = (Attendance) o;
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
                "status = " + getStatus() + ", " +
                "description = " + getDescription() + ", " +
                "paymentAmount = " + getPaymentAmount() + ")";
    }
}
