package ua.com.alevel.persistence.entity.user;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.finance.Attendance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

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
