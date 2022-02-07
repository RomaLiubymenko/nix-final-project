package ua.com.alevel.dto.profile.finance;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.educationalprocess.LessonProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.enumeration.AttendanceStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class AttendanceProfileDto extends AbstractProfileDto {

    private AttendanceStatus status;
    private String description;
    private BigDecimal paymentAmount;

    @NotNull
    private LessonProfileDto lesson;

    @NotNull
    private StudentProfileDto student;

    public LessonProfileDto getLesson() {
        return lesson;
    }

    public void setLesson(LessonProfileDto lesson) {
        this.lesson = lesson;
    }

    public StudentProfileDto getStudent() {
        return student;
    }

    public void setStudent(StudentProfileDto student) {
        this.student = student;
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
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceProfileDto entity = (AttendanceProfileDto) o;
        return Objects.equals(this.status, entity.status) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.paymentAmount, entity.paymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, description, paymentAmount);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "status = " + status + ", " +
                "description = " + description + ", " +
                "paymentAmount = " + paymentAmount + ")";
    }
}
