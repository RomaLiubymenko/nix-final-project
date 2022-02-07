package ua.com.alevel.dto.filter.finance;

import ua.com.alevel.dto.AbstractFilterDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountingOfPaymentFilterDto extends AbstractFilterDto {

    private LocalDateTime date;
    private String amount;
    private String purpose;
    private String comment;
    private UUID tutorUuid;

    public UUID getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(UUID tutorUuid) {
        this.tutorUuid = tutorUuid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
        AccountingOfPaymentFilterDto entity = (AccountingOfPaymentFilterDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.amount, entity.amount) &&
                Objects.equals(this.purpose, entity.purpose) &&
                Objects.equals(this.comment, entity.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, purpose, comment);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "amount = " + amount + ", " +
                "purpose = " + purpose + ", " +
                "comment = " + comment + ")";
    }
}
