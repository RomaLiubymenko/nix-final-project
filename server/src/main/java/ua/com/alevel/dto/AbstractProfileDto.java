package ua.com.alevel.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractProfileDto {

    private UUID uuid;
    private LocalDateTime created;
    private LocalDateTime updated;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProfileDto entity = (AbstractProfileDto) o;
        return Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.created, entity.created) &&
                Objects.equals(this.updated, entity.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, created, updated);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "created = " + created + ", " +
                "updated = " + updated + ")";
    }
}
