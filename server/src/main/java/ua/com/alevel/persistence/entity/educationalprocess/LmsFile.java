package ua.com.alevel.persistence.entity.educationalprocess;

import org.hibernate.Hibernate;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.enumeration.LmsFileType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lms_file", indexes = {
        @Index(name = "idx_lms_file_uuid", columnList = "uuid")
})
public class LmsFile extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lms_file_id")
    @SequenceGenerator(name = "lms_file_id", sequenceName = "lms_file_id", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "file_type", nullable = false)
    private LmsFileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public LmsFileType getFileType() {
        return fileType;
    }

    public void setFileType(LmsFileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LmsFile lmsFile = (LmsFile) o;
        return getUuid() != null && Objects.equals(getUuid(), lmsFile.getUuid());
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
                "name = " + getName() + ", " +
                "fileName = " + getFileName() + ", " +
                "mimeType = " + getMimeType() + ", " +
                "fileType = " + getFileType() + ")";
    }
}
