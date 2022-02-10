package ua.com.alevel.persistence.entity.user;

import org.hibernate.Hibernate;
import ua.com.alevel.enumeration.TutorStatus;
import ua.com.alevel.persistence.entity.educationalprocess.*;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("tutor")
public class Tutor extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TutorStatus status = TutorStatus.WORKING;

    @OneToMany(mappedBy = "tutor", orphanRemoval = true)
    private Set<AccountingOfPayment> accountingOfPayments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tutor")
    private Set<Report> reports = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "tutor_courses",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "tutor_lessons",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<Lesson> lessons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tutor")
    private Set<Subject> subjects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tutor")
    private Set<Exercise> exercises = new LinkedHashSet<>();

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setTutor(this);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setTutor(null);
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setTutor(this);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        exercise.setTutor(null);
    }

    public void addReport(Report report) {
        reports.add(report);
        report.setTutor(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setTutor(null);
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<AccountingOfPayment> getAccountingOfPayments() {
        return accountingOfPayments;
    }

    public void setAccountingOfPayments(Set<AccountingOfPayment> accountingOfPayments) {
        this.accountingOfPayments = accountingOfPayments;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tutor tutor = (Tutor) o;
        return getUuid() != null && Objects.equals(getUuid(), tutor.getUuid());
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
                "status = " + getStatus() + ", " +
                "username = " + getUsername() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "email = " + getEmail() + ", " +
                "gender = " + getGender() + ", " +
                "birthDay = " + getBirthDay() + ")";
    }
}
