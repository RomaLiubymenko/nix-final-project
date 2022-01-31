package ua.com.alevel.persistence.entity.user;

import org.hibernate.Hibernate;
import org.hibernate.annotations.DiscriminatorOptions;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.finance.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "\"user\"", indexes = {
        @Index(name = "idx_user_uuid", columnList = "uuid")
})
@DiscriminatorValue("user")
@DiscriminatorColumn(name = "type")
@DiscriminatorOptions(force = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    @SequenceGenerator(name = "user_id", sequenceName = "user_id", allocationSize = 1)
    private Long id;

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 25)
    private GenderType gender;

    @Column(name = "birth_day", columnDefinition = "date")
    private LocalDate birthDay;

    @Column(name = "activated", nullable = false)
    private Boolean activated = true;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getUuid() != null && Objects.equals(getUuid(), user.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "uuid = " + getUuid() + ", " +
                "created = " + getCreated() + ", " +
                "updated = " + getUpdated() + ", " +
                "username = " + getUsername() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "email = " + getEmail() + ", " +
                "gender = " + getGender() + ", " +
                "birthDay = " + getBirthDay() + ", " +
                "activated = " + getActivated() + ")";
    }
}
