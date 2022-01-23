package ua.com.alevel.persistence.entity.educationalprocess;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Tutor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "report", indexes = {
        @Index(name = "idx_report_uuid", columnList = "uuid")
})
public class Report extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_id")
    @SequenceGenerator(name = "report_id", sequenceName = "report_id", allocationSize = 1)
    private Long id;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime date;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment", length = 510)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @OneToMany(mappedBy = "report", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<LmsFile> lmsFiles = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<LmsFile> getLmsFiles() {
        return lmsFiles;
    }

    public void setLmsFiles(Set<LmsFile> lmsFiles) {
        this.lmsFiles = lmsFiles;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Report report = (Report) o;
        return getUuid() != null && Objects.equals(getUuid(), report.getUuid());
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
                "date = " + getDate() + ", " +
                "content = " + getContent() + ", " +
                "comment = " + getComment() + ")";
    }
}
