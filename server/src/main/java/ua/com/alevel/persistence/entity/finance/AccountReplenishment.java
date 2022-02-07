package ua.com.alevel.persistence.entity.finance;

import org.hibernate.Hibernate;
import ua.com.alevel.enumeration.ReplenishmentMethod;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Student;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "account_replenishment", indexes = {
        @Index(name = "idx_account_replenishment_uuid", columnList = "uuid")
})
public class AccountReplenishment extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_replenishment_id_sequence")
    @SequenceGenerator(name = "account_replenishment_id_sequence", sequenceName = "account_replenishment_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "amount", precision = 9, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "payer")
    private String payer;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "replenishment_method")
    private ReplenishmentMethod replenishmentMethod;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public ReplenishmentMethod getReplenishmentMethod() {
        return replenishmentMethod;
    }

    public void setReplenishmentMethod(ReplenishmentMethod replenishmentMethod) {
        this.replenishmentMethod = replenishmentMethod;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountReplenishment that = (AccountReplenishment) o;
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
                "date = " + getDate() + ", " +
                "amount = " + getAmount() + ", " +
                "payer = " + getPayer() + ", " +
                "purpose = " + getPurpose() + ", " +
                "comment = " + getComment() + ", " +
                "replenishmentMethod = " + getReplenishmentMethod() + ")";
    }
}
