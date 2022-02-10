package ua.com.alevel.dto.table.location;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.alevel.dto.AbstractTableDto;

public class RoomTableDto extends AbstractTableDto {

    private String name;
    private String number;
    private Long capacity;
    private Boolean isAvailabilityToUse;
    private String description;

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

    public Boolean getIsAvailabilityToUse() {
        return isAvailabilityToUse;
    }

    public void setIsAvailabilityToUse(Boolean isAvailabilityToUse) {
        this.isAvailabilityToUse = isAvailabilityToUse;
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
        if (o == null || getClass() != o.getClass()) return false;
        RoomTableDto that = (RoomTableDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getNumber(), that.getNumber())
                .append(getCapacity(), that.getCapacity())
                .append(getIsAvailabilityToUse(), that.getIsAvailabilityToUse())
                .append(getDescription(), that.getDescription())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getNumber())
                .append(getCapacity())
                .append(getIsAvailabilityToUse())
                .append(getDescription())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "number = " + number + ", " +
                "capacity = " + capacity + ", " +
                "isAvailabilityToUse = " + isAvailabilityToUse + ", " +
                "description = " + description + ")";
    }
}
