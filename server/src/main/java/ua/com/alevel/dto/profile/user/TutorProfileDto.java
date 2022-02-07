package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.profile.educationalprocess.*;
import ua.com.alevel.dto.profile.finance.AccountingOfPaymentProfileDto;
import ua.com.alevel.enumeration.TutorStatus;

import java.util.HashSet;
import java.util.Set;

public class TutorProfileDto extends UserProfileDto {

    private TutorStatus status;
    private Set<AccountingOfPaymentProfileDto> accountingOfPayments = new HashSet<>();
    private Set<ReportProfileDto> reports = new HashSet<>();
    private Set<CourseProfileDto> courses = new HashSet<>();
    private Set<LessonProfileDto> lessons = new HashSet<>();
    private Set<SubjectProfileDto> subjects = new HashSet<>();
    private Set<ExerciseProfileDto> exercises = new HashSet<>();

    public Set<AccountingOfPaymentProfileDto> getAccountingOfPayments() {
        return accountingOfPayments;
    }

    public void setAccountingOfPayments(Set<AccountingOfPaymentProfileDto> accountingOfPayments) {
        this.accountingOfPayments = accountingOfPayments;
    }

    public Set<ReportProfileDto> getReports() {
        return reports;
    }

    public void setReports(Set<ReportProfileDto> reports) {
        this.reports = reports;
    }

    public Set<CourseProfileDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseProfileDto> courses) {
        this.courses = courses;
    }

    public Set<LessonProfileDto> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProfileDto> lessons) {
        this.lessons = lessons;
    }

    public Set<SubjectProfileDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectProfileDto> subjects) {
        this.subjects = subjects;
    }

    public Set<ExerciseProfileDto> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseProfileDto> exercises) {
        this.exercises = exercises;
    }

    public TutorStatus getStatus() {
        return status;
    }

    public void setStatus(TutorStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TutorProfileDto that = (TutorProfileDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
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
                .toString();
    }
}
