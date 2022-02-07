package ua.com.alevel.persistence.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role", indexes = {
        @Index(name = "idx_role_uuid", columnList = "uuid")
})
public class Role extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence")
    @SequenceGenerator(name = "role_id_sequence", sequenceName = "role_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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
