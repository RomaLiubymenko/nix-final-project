package ua.com.alevel.dto.profile;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.enumeration.AccountOwnershipType;
import ua.com.alevel.enumeration.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AccountProfileDto extends AbstractProfileDto {

    private String name;
    private String description;
    private LocalDateTime accountChangedDate;
    private BigDecimal balance;
    private Boolean isBlocked;
    private AccountType type;
    private AccountOwnershipType ownershipType;
    private Set<StudentProfileDto> users = new HashSet<>();

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

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
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

    public Set<StudentProfileDto> getUsers() {
        return users;
    }

    public void setUsers(Set<StudentProfileDto> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountProfileDto entity = (AccountProfileDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.accountChangedDate, entity.accountChangedDate) &&
                Objects.equals(this.balance, entity.balance) &&
                Objects.equals(this.isBlocked, entity.isBlocked) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.ownershipType, entity.ownershipType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, accountChangedDate, balance, isBlocked, type, ownershipType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "accountChangedDate = " + accountChangedDate + ", " +
                "balance = " + balance + ", " +
                "isBlocked = " + isBlocked + ", " +
                "type = " + type + ", " +
                "ownershipType = " + ownershipType + ")";
    }
}
