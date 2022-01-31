package ua.com.alevel.dto.profile;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.enumeration.ReplenishmentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountReplenishmentProfileDto extends AbstractProfileDto {

    private LocalDateTime date;
    private BigDecimal amount;
    private String payer;
    private String purpose;
    private String comment;
    private ReplenishmentMethod replenishmentMethod;
    private StudentProfileDto student;

    public ReplenishmentMethod getReplenishmentMethod() {
        return replenishmentMethod;
    }

    public void setReplenishmentMethod(ReplenishmentMethod replenishmentMethod) {
        this.replenishmentMethod = replenishmentMethod;
    }

    public StudentProfileDto getStudent() {
        return student;
    }

    public void setStudent(StudentProfileDto student) {
        this.student = student;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

        AccountReplenishmentProfileDto that = (AccountReplenishmentProfileDto) o;

        return new EqualsBuilder()
                .append(date, that.date)
                .append(amount, that.amount)
                .append(payer, that.payer)
                .append(purpose, that.purpose)
                .append(comment, that.comment)
                .append(replenishmentMethod, that.replenishmentMethod)
                .append(student, that.student)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(date)
                .append(amount)
                .append(payer)
                .append(purpose)
                .append(comment)
                .append(replenishmentMethod)
                .append(student)
                .toHashCode();
    }
}
