package ua.com.alevel.dto.table.educationalprocess;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.com.alevel.dto.AbstractTableDto;

import java.util.UUID;

public class TopicTableDto extends AbstractTableDto {

    private String name;
    private String description;
    private Double length;
    private SubjectAddInfo subject;
    private CourseAddInfo course;

    public SubjectAddInfo getSubject() {
        return subject;
    }

    public void setSubject(SubjectAddInfo subject) {
        this.subject = subject;
    }

    public CourseAddInfo getCourse() {
        return course;
    }

    public void setCourse(CourseAddInfo course) {
        this.course = course;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicTableDto that = (TopicTableDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getDescription(), that.getDescription())
                .append(getLength(), that.getLength())
                .append(getSubject(), that.getSubject())
                .append(getCourse(), that.getCourse())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getDescription())
                .append(getLength())
                .append(getSubject())
                .append(getCourse())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "length = " + length + ")";
    }

    public static class SubjectAddInfo {

        private UUID uuid;
        private String name;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubjectAddInfo that = (SubjectAddInfo) o;
            return new EqualsBuilder()
                    .append(getUuid(), that.getUuid())
                    .append(getName(), that.getName())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getUuid())
                    .append(getName())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("uuid", uuid)
                    .append("name", name)
                    .toString();
        }
    }

    public static class CourseAddInfo {

        private UUID uuid;
        private String name;

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CourseAddInfo that = (CourseAddInfo) o;
            return new EqualsBuilder()
                    .append(getUuid(), that.getUuid())
                    .append(getName(), that.getName())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(getUuid())
                    .append(getName())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("uuid", uuid)
                    .append("name", name)
                    .toString();
        }
    }
}
