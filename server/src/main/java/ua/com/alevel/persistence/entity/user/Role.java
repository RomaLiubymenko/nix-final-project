package ua.com.alevel.persistence.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "role", indexes = {
        @Index(name = "idx_role_uuid", columnList = "uuid")
})
public class Role extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id")
    @SequenceGenerator(name = "role_id", sequenceName = "role_id", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Collection<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getUuid() != null && Objects.equals(getUuid(), role.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "uuid = " + getUuid() + ", " +
                "created = " + getCreated() + ", " +
                "updated = " + getUpdated() + ", " +
                "name = " + name + ")";
    }
}
