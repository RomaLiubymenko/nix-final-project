package ua.com.alevel.persistence.entity.user;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.educationalprocess.*;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;
import ua.com.alevel.persistence.entity.finance.Attendance;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("student")
public class Student extends User {

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private Set<AccountReplenishment> accountReplenishments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<Lesson> lessons = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<StudentGroup> studentGroups = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<Exercise> exercises = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private Set<Report> reports = new LinkedHashSet<>();

    public void addStudentGroup(StudentGroup studentGroup) {
        if (!studentGroups.contains(studentGroup)) {
            studentGroups.add(studentGroup);
            studentGroup.addStudent(this);
        }
    }

    public void removeStudentGroup(StudentGroup studentGroup) {
        if (studentGroups.contains(studentGroup)) {
            studentGroups.remove(studentGroup);
            studentGroup.removeStudent(this);
        }
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.removeStudent(this);
        }
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Set<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(Set<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<AccountReplenishment> getAccountReplenishments() {
        return accountReplenishments;
    }

    public void setAccountReplenishments(Set<AccountReplenishment> accountReplenishments) {
        this.accountReplenishments = accountReplenishments;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return getUuid() != null && Objects.equals(getUuid(), student.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "uuid = " + getUuid() + ", " +
                "created = " + getCreated() + ", " +
                "updated = " + getUpdated() + ", " +
                "username = " + getUsername() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "email = " + getEmail() + ", " +
                "gender = " + getGender() + ", " +
                "birthDay = " + getBirthDay() + ")";
    }
}
