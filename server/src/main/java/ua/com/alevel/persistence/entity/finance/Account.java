package ua.com.alevel.persistence.entity.finance;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.enumeration.AccountOwnershipType;
import ua.com.alevel.enumeration.AccountType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "account", indexes = {
        @Index(name = "idx_account_uuid", columnList = "uuid")
})
public class Account extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_sequence")
    @SequenceGenerator(name = "account_id_sequence", sequenceName = "account_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "changed_date", columnDefinition = "timestamp without time zone")
    private LocalDateTime accountChangedDate;

    @Column(name = "balance", precision = 9, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccountType type = AccountType.STANDARD;

    @Enumerated(EnumType.STRING)
    @Column(name = "ownership_type", nullable = false)
    private AccountOwnershipType ownershipType = AccountOwnershipType.STUDENT;

    @OneToMany(mappedBy = "account")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAccountChangedDate() {
        return accountChangedDate;
    }

    public void setAccountChangedDate(LocalDateTime accountChangedDate) {
        this.accountChangedDate = accountChangedDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountOwnershipType getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(AccountOwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return getUuid() != null && Objects.equals(getUuid(), account.getUuid());
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
                "name = " + getName() + ", " +
                "description = " + getDescription() + ", " +
                "accountChangedDate = " + getAccountChangedDate() + ", " +
                "balance = " + getBalance() + ", " +
                "isBlocked = " + isBlocked + ", " +
                "type = " + getType() + ", " +
                "ownershipType = " + getOwnershipType() + ")";
    }
}
