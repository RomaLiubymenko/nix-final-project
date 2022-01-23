package ua.com.alevel.persistence.entity.user;

import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Admin admin = (Admin) o;
        return getUuid() != null && Objects.equals(getUuid(), admin.getUuid());
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
                "username = " + getUsername() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "email = " + getEmail() + ", " +
                "gender = " + getGender() + ", " +
                "birthDay = " + getBirthDay() + ")";
    }
}
