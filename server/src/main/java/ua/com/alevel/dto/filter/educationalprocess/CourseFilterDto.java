package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CourseFilterDto extends AbstractFilterDto {

    private String name;
    private String description;
    private String languageOfInstruction;
    private String knowledgeLevelOfCourse;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private String isCompleted;

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

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFilterDto entity = (CourseFilterDto) o;
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
