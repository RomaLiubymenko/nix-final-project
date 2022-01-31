package ua.com.alevel.dto.profile.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.profile.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.profile.CourseProfileDto;
import ua.com.alevel.dto.profile.StudentGroupProfileDto;

import java.util.HashSet;
import java.util.Set;

public class StudentProfileDto extends UserProfileDto {

    private Set<AccountReplenishmentProfileDto> accountReplenishments = new HashSet<>();
    private Set<StudentGroupProfileDto> studentGroups = new HashSet<>();
    private Set<CourseProfileDto> courses = new HashSet<>();

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
