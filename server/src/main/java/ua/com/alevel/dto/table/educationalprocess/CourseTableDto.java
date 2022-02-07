package ua.com.alevel.dto.table.educationalprocess;

import ua.com.alevel.dto.AbstractTableDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
        CourseTableDto entity = (CourseTableDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.languageOfInstruction, entity.languageOfInstruction) &&
                Objects.equals(this.knowledgeLevelOfCourse, entity.knowledgeLevelOfCourse) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.isCompleted, entity.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, languageOfInstruction, knowledgeLevelOfCourse, startDate, endDate, isCompleted);
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
