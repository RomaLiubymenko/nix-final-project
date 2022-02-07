package ua.com.alevel.dto.profile.educationalprocess;

import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.enumeration.GroupType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StudentGroupProfileDto extends AbstractProfileDto {

    @NotBlank
    @NotEmpty
    private String name;
    private String description;
    private GroupType groupType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isFormed;
    private Set<StudentProfileDto> students = new HashSet<>();
    private CourseProfileDto course;
    private Set<LessonProfileDto> lessons = new HashSet<>();

    public CourseProfileDto getCourse() {
        return course;
    }

    public void setCourse(CourseProfileDto course) {
        this.course = course;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public Set<StudentProfileDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentProfileDto> students) {
        this.students = students;
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

    public Boolean getIsFormed() {
        return isFormed;
    }

    public void setIsFormed(Boolean isFormed) {
        this.isFormed = isFormed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroupProfileDto entity = (StudentGroupProfileDto) o;
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
