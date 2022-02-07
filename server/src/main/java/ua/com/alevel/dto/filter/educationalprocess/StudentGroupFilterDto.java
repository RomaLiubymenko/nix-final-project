package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.enumeration.GroupType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class StudentGroupFilterDto extends AbstractFilterDto {

    private String name;
    private String description;
    private GroupType groupType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String isFormed;
    private List<UUID> studentUuids = new ArrayList<>();

    public List<UUID> getStudentUuids() {
        return studentUuids;
    }

    public void setStudentUuids(List<UUID> studentUuids) {
        this.studentUuids = studentUuids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getIsFormed() {
        return isFormed;
    }

    public void setIsFormed(String isFormed) {
        this.isFormed = isFormed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroupFilterDto entity = (StudentGroupFilterDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.groupType, entity.groupType) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.isFormed, entity.isFormed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, groupType, startDate, endDate, isFormed);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "groupType = " + groupType + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ", " +
                "isFormed = " + isFormed + ")";
    }
}
