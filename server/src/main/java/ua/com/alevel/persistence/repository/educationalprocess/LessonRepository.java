package ua.com.alevel.persistence.repository.educationalprocess;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.Set;

@Repository
public interface LessonRepository extends CommonRepository<Lesson> {

    @EntityGraph(attributePaths = {
            "studentGroups",
            "students",
            "topics",
            "tutors"
    })
    Set<Lesson> findBySubjectIn(Set<Subject> subjects);
}
