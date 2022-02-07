package ua.com.alevel.dto.filter.finance;

import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.AttendanceStatus;

import java.util.Objects;
import java.util.UUID;

public class AttendanceFilterDto extends AbstractFilterDto {

    private AttendanceStatus status;
    private String description;
    private String paymentAmount;
    private UUID studentUuid;
    private UUID lessonUuid;

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(UUID studentUuid) {
        this.studentUuid = studentUuid;
    }

    public UUID getLessonUuid() {
        return lessonUuid;
    }

    public void setLessonUuid(UUID lessonUuid) {
        this.lessonUuid = lessonUuid;
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

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceFilterDto entity = (AttendanceFilterDto) o;
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
