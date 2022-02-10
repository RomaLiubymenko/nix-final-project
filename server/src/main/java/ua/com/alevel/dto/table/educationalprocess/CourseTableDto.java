package ua.com.alevel.dto.table.educationalprocess;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.alevel.dto.AbstractTableDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CourseTableDto extends AbstractTableDto {

    private String name;
    private String description;
    private String languageOfInstruction;
    private String knowledgeLevelOfCourse;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private Boolean isCompleted;

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

    public String getLanguageOfInstruction() {
        return languageOfInstruction;
    }

    public void setLanguageOfInstruction(String languageOfInstruction) {
        this.languageOfInstruction = languageOfInstruction;
    }

    public String getKnowledgeLevelOfCourse() {
        return knowledgeLevelOfCourse;
    }

    public void setKnowledgeLevelOfCourse(String knowledgeLevelOfCourse) {
        this.knowledgeLevelOfCourse = knowledgeLevelOfCourse;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTableDto that = (CourseTableDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getDescription(), that.getDescription())
                .append(getLanguageOfInstruction(), that.getLanguageOfInstruction())
                .append(getKnowledgeLevelOfCourse(), that.getKnowledgeLevelOfCourse())
                .append(getStartDate(), that.getStartDate())
                .append(getEndDate(), that.getEndDate())
                .append(getIsCompleted(), that.getIsCompleted())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getLanguageOfInstruction())
                .append(getKnowledgeLevelOfCourse())
                .append(getStartDate())
                .append(getEndDate())
                .append(getIsCompleted())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "languageOfInstruction = " + languageOfInstruction + ", " +
                "knowledgeLevelOfCourse = " + knowledgeLevelOfCourse + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ", " +
                "isCompleted = " + isCompleted + ")";
    }
}
