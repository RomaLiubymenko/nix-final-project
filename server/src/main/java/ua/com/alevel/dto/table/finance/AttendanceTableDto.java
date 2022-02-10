package ua.com.alevel.dto.table.finance;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.enumeration.AttendanceStatus;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class AttendanceTableDto extends AbstractTableDto {

    private AttendanceStatus status;
    private String description;
    private BigDecimal paymentAmount;
    private StudentAddInfoDto student;
    private LessonAddInfoDto lesson;

    public StudentAddInfoDto getStudent() {
        return student;
    }

    public void setStudent(StudentAddInfoDto student) {
        this.student = student;
    }

    public LessonAddInfoDto getLesson() {
        return lesson;
    }

    public void setLesson(LessonAddInfoDto lesson) {
        this.lesson = lesson;
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
        AttendanceTableDto that = (AttendanceTableDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getStatus(), that.getStatus())
                .append(getDescription(), that.getDescription())
                .append(getPaymentAmount(), that.getPaymentAmount())
                .append(getStudent(), that.getStudent())
                .append(getLesson(), that.getLesson())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getStatus())
                .append(getDescription())
                .append(getPaymentAmount())
                .append(getStudent())
                .append(getLesson())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "status = " + status + ", " +
                "description = " + description + ", " +
                "paymentAmount = " + paymentAmount + ")";
    }

    public static class StudentAddInfoDto {

        private UUID uuid;
        private String username;
        private String firstName;
        private String lastName;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttendanceTableDto.StudentAddInfoDto entity = (AttendanceTableDto.StudentAddInfoDto) o;
            return Objects.equals(this.uuid, entity.uuid) &&
                    Objects.equals(this.username, entity.username) &&
                    Objects.equals(this.firstName, entity.firstName) &&
                    Objects.equals(this.lastName, entity.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uuid, username, firstName, lastName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "uuid = " + uuid + ", " +
                    "username = " + username + ", " +
                    "firstName = " + firstName + ", " +
                    "lastName = " + lastName + ")";
        }
    }

    public static class LessonAddInfoDto {

        private UUID uuid;
        private String name;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LessonAddInfoDto that = (LessonAddInfoDto) o;
            return new EqualsBuilder()
                    .append(getUuid(), that.getUuid())
                    .append(getName(), that.getName())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getUuid())
                    .append(getName())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("uuid", uuid)
                    .append("name", name)
                    .toString();
        }
    }
}
