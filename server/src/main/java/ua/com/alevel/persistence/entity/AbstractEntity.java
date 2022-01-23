package ua.com.alevel.persistence.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "created", columnDefinition = "timestamp without time zone")
    private LocalDateTime created;

    @Column(name = "updated", columnDefinition = "timestamp without time zone")
    private LocalDateTime updated;

    protected AbstractEntity() {
        uuid = UUID.randomUUID();
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "uuid=" + uuid +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
