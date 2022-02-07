package ua.com.alevel.dto.filter.location;

import ua.com.alevel.dto.AbstractFilterDto;

import java.util.Objects;

public class RoomFilterDto extends AbstractFilterDto {

    private String name;
    private String number;
    private String capacity;
    private String isAvailabilityToUse;
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getIsAvailabilityToUse() {
        return isAvailabilityToUse;
    }

    public void setIsAvailabilityToUse(String isAvailabilityToUse) {
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
        RoomFilterDto entity = (RoomFilterDto) o;
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
