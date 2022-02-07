package ua.com.alevel.dto.table.location;

import ua.com.alevel.dto.AbstractTableDto;

import java.util.Objects;

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
        RoomTableDto entity = (RoomTableDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.number, entity.number) &&
                Objects.equals(this.capacity, entity.capacity) &&
                Objects.equals(this.isAvailabilityToUse, entity.isAvailabilityToUse) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, capacity, isAvailabilityToUse, description);
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
