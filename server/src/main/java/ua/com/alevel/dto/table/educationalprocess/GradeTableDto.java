package ua.com.alevel.dto.table.educationalprocess;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractTableDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GradeTableDto extends AbstractTableDto {

    private String value;
    private Integer weight;
    private LocalDateTime date;
    private ReportAddInfo report;

    public ReportAddInfo getReport() {
        return report;
    }

    public void setReport(ReportAddInfo report) {
        this.report = report;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        if (o == null || getClass() != o.getClass()) return false;
        GradeTableDto that = (GradeTableDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getValue(), that.getValue())
                .append(getWeight(), that.getWeight())
                .append(getDate(), that.getDate())
                .append(getReport(), that.getReport())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getValue())
                .append(getWeight())
                .append(getDate())
                .append(getReport())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "value = " + value + ", " +
                "weight = " + weight + ", " +
                "date = " + date + ")";
    }

    public static class ReportAddInfo {

        private UUID uuid;
        private String content;
        private String comment;
        private UUID studentUuid;
        private UUID tutorUuid;
        private String studentFirstName;
        private String tutorFirstName;
        private String studentLastName;
        private String tutorLastName;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public UUID getStudentUuid() {
            return studentUuid;
        }

        public void setStudentUuid(UUID studentUuid) {
            this.studentUuid = studentUuid;
        }

        public UUID getTutorUuid() {
            return tutorUuid;
        }

        public void setTutorUuid(UUID tutorUuid) {
            this.tutorUuid = tutorUuid;
        }

        public String getStudentFirstName() {
            return studentFirstName;
        }

        public void setStudentFirstName(String studentFirstName) {
            this.studentFirstName = studentFirstName;
        }

        public String getTutorFirstName() {
            return tutorFirstName;
        }

        public void setTutorFirstName(String tutorFirstName) {
            this.tutorFirstName = tutorFirstName;
        }

        public String getStudentLastName() {
            return studentLastName;
        }

        public void setStudentLastName(String studentLastName) {
            this.studentLastName = studentLastName;
        }

        public String getTutorLastName() {
            return tutorLastName;
        }

        public void setTutorLastName(String tutorLastName) {
            this.tutorLastName = tutorLastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            ReportAddInfo that = (ReportAddInfo) o;

            return new EqualsBuilder()
                    .append(getUuid(), that.getUuid())
                    .append(getContent(), that.getContent())
                    .append(getComment(), that.getComment())
                    .append(getStudentUuid(), that.getStudentUuid())
                    .append(getTutorUuid(), that.getTutorUuid())
                    .append(getStudentFirstName(), that.getStudentFirstName())
                    .append(getTutorFirstName(), that.getTutorFirstName())
                    .append(getStudentLastName(), that.getStudentLastName())
                    .append(getTutorLastName(), that.getTutorLastName())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getUuid())
                    .append(getContent())
                    .append(getComment())
                    .append(getStudentUuid())
                    .append(getTutorUuid())
                    .append(getStudentFirstName())
                    .append(getTutorFirstName())
                    .append(getStudentLastName())
                    .append(getTutorLastName())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("uuid", uuid)
                    .append("content", content)
                    .append("comment", comment)
                    .append("studentUuid", studentUuid)
                    .append("tutorUuid", tutorUuid)
                    .append("studentFirstName", studentFirstName)
                    .append("tutorFirstName", tutorFirstName)
                    .append("studentLastName", studentLastName)
                    .append("tutorLastName", tutorLastName)
                    .toString();
        }
    }
}
