package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.profile.educationalprocess.*;
import ua.com.alevel.dto.profile.finance.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.profile.finance.AttendanceProfileDto;

import java.util.HashSet;
import java.util.Set;

public class StudentProfileDto extends UserProfileDto {

    private Set<AccountReplenishmentProfileDto> accountReplenishments = new HashSet<>();
    private Set<AttendanceProfileDto> attendances = new HashSet<>();
    private Set<LessonProfileDto> lessons = new HashSet<>();
    private Set<StudentGroupProfileDto> studentGroups = new HashSet<>();
    private Set<ExerciseProfileDto> exercises = new HashSet<>();
    private Set<CourseProfileDto> courses = new HashSet<>();
    private Set<ReportProfileDto> reports = new HashSet<>();

    public Set<AttendanceProfileDto> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<AttendanceProfileDto> attendances) {
        this.attendances = attendances;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public Set<ExerciseProfileDto> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseProfileDto> exercises) {
        this.exercises = exercises;
    }

    public Set<ReportProfileDto> getReports() {
        return reports;
    }

    public void setReports(Set<ReportProfileDto> reports) {
        this.reports = reports;
    }

    public Set<AccountReplenishmentProfileDto> getAccountReplenishments() {
        return accountReplenishments;
    }

    public void setAccountReplenishments(Set<AccountReplenishmentProfileDto> accountReplenishments) {
        this.accountReplenishments = accountReplenishments;
    }

    public Set<StudentGroupProfileDto> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroupProfileDto> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set<CourseProfileDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseProfileDto> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        StudentProfileDto that = (StudentProfileDto) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(accountReplenishments, that.accountReplenishments)
                .append(studentGroups, that.studentGroups)
                .append(courses, that.courses)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(accountReplenishments)
                .append(studentGroups)
                .append(courses)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", getUuid())
                .append("username", getUsername())
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("email", getEmail())
                .append("gender", getGender())
                .append("birthDay", getBirthDay())
                .append("activated", getActivated())
                .append("account", getAccount())
                .append("accountReplenishments", accountReplenishments)
                .append("studentGroups", studentGroups)
                .append("courses", courses)
                .toString();
    }
}
