package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractProfileDto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RoleDto extends AbstractProfileDto {

    private UUID uuid;

    @NotNull
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuid, roleDto.uuid)
                .append(name, roleDto.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(uuid)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("name", name)
                .toString();
    }
}
