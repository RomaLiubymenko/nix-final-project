package ua.com.alevel.dto.filter.finance;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.AccountOwnershipType;
import ua.com.alevel.enumeration.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AccountFilterDto extends AbstractFilterDto {

    private String name;
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime accountChangedDate;
    private BigDecimal balance;
    private String isBlocked;
    private AccountType type;
    private AccountOwnershipType ownershipType;
    private List<UUID> userUuids;

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

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
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

    public List<UUID> getUserUuids() {
        return userUuids;
    }

    public void setUserUuids(List<UUID> userUuids) {
        this.userUuids = userUuids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AccountFilterDto that = (AccountFilterDto) o;

        return new EqualsBuilder()
                .append(getName(), that.getName())
                .append(getDescription(), that.getDescription())
                .append(getAccountChangedDate(), that.getAccountChangedDate())
                .append(getBalance(), that.getBalance())
                .append(getIsBlocked(), that.getIsBlocked())
                .append(getType(), that.getType())
                .append(getOwnershipType(), that.getOwnershipType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getName())
                .append(getDescription())
                .append(getAccountChangedDate())
                .append(getBalance())
                .append(getIsBlocked())
                .append(getType())
                .append(getOwnershipType())
                .toHashCode();
    }
}
