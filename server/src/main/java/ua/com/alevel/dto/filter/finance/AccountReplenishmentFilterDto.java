package ua.com.alevel.dto.filter.finance;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.ReplenishmentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountReplenishmentFilterDto extends AbstractFilterDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private BigDecimal amount;
    private String payer;
    private String purpose;
    private String comment;
    private ReplenishmentMethod replenishmentMethod;
    private UUID studentUuid;

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(UUID studentUuid) {
        this.studentUuid = studentUuid;
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

    public ReplenishmentMethod getReplenishmentMethod() {
        return replenishmentMethod;
    }

    public void setReplenishmentMethod(ReplenishmentMethod replenishmentMethod) {
        this.replenishmentMethod = replenishmentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountReplenishmentFilterDto entity = (AccountReplenishmentFilterDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.amount, entity.amount) &&
                Objects.equals(this.payer, entity.payer) &&
                Objects.equals(this.purpose, entity.purpose) &&
                Objects.equals(this.comment, entity.comment) &&
                Objects.equals(this.replenishmentMethod, entity.replenishmentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, payer, purpose, comment, replenishmentMethod);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "amount = " + amount + ", " +
                "payer = " + payer + ", " +
                "purpose = " + purpose + ", " +
                "comment = " + comment + ", " +
                "replenishmentMethod = " + replenishmentMethod + ")";
    }
}
