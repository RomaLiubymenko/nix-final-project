package ua.com.alevel.persistence.entity.location;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room", indexes = {
        @Index(name = "idx_room_uuid", columnList = "uuid")
})
public class Room extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id")
    @SequenceGenerator(name = "room_id", sequenceName = "room_id", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "is_availability_to_use", nullable = false)
    private Boolean isAvailabilityToUse = false;

    @Column(name = "description", length = 510)
    private String description;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Lesson> lessons = new LinkedHashSet<>();

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Boolean getAvailabilityToUse() {
        return isAvailabilityToUse;
    }

    public void setAvailabilityToUse(Boolean availabilityToUse) {
        isAvailabilityToUse = availabilityToUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Room room = (Room) o;
        return getUuid() != null && Objects.equals(getUuid(), room.getUuid());
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
                "number = " + getNumber() + ", " +
                "capacity = " + getCapacity() + ", " +
                "isAvailabilityToUse = " + isAvailabilityToUse + ", " +
                "description = " + getDescription() + ")";
    }
}
