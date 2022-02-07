package ua.com.alevel.dto.filter.educationalprocess;

import ua.com.alevel.dto.AbstractFilterDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ReportFilterDto extends AbstractFilterDto {

    private LocalDateTime date;
    private String content;
    private String comment;
    private UUID tutorUuid;
    private UUID studentUuid;

    public UUID getTutorUuid() {
        return tutorUuid;
    }

    public void setTutorUuid(UUID tutorUuid) {
        this.tutorUuid = tutorUuid;
    }

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public void setStudentUuid(UUID studentUuid) {
        this.studentUuid = studentUuid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportFilterDto entity = (ReportFilterDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.comment, entity.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, content, comment);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "content = " + content + ", " +
                "comment = " + comment + ")";
    }
}
