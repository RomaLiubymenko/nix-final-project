package ua.com.alevel.dto.table.finance;

import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.enumeration.AccountOwnershipType;
import ua.com.alevel.enumeration.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class AccountTableDto extends AbstractTableDto {

    private String name;
    private String description;
    private LocalDateTime accountChangedDate;
    private BigDecimal balance;
    private Boolean isBlocked;
    private AccountType type;
    private AccountOwnershipType ownershipType;
    private Set<UserAddInfoDto> users = new HashSet<>();

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

    public Set<UserAddInfoDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserAddInfoDto> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTableDto entity = (AccountTableDto) o;
        return Objects.equals(this.getUuid(), entity.getUuid()) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.accountChangedDate, entity.accountChangedDate) &&
                Objects.equals(this.balance, entity.balance) &&
                Objects.equals(this.isBlocked, entity.isBlocked) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.ownershipType, entity.ownershipType) &&
                Objects.equals(this.users, entity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), name, description, accountChangedDate, balance, isBlocked, type, ownershipType, users);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + getUuid() + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "accountChangedDate = " + accountChangedDate + ", " +
                "balance = " + balance + ", " +
                "isBlocked = " + isBlocked + ", " +
                "type = " + type + ", " +
                "ownershipType = " + ownershipType + ", " +
                "users = " + users + ")";
    }

    public static class UserAddInfoDto {

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
            UserAddInfoDto entity = (UserAddInfoDto) o;
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
}
