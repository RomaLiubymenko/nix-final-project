package ua.com.alevel.dto.profile.finance;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class AccountingOfPaymentProfileDto extends AbstractProfileDto {

    private LocalDateTime date;
    private BigDecimal amount;
    private String purpose;
    private String comment;
    private TutorProfileDto tutor;

    public TutorProfileDto getTutor() {
        return tutor;
    }

    public void setTutor(TutorProfileDto tutor) {
        this.tutor = tutor;
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
        AccountingOfPaymentProfileDto entity = (AccountingOfPaymentProfileDto) o;
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
