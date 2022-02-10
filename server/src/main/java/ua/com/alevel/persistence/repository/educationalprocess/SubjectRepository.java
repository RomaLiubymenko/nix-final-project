package ua.com.alevel.persistence.repository.educationalprocess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends CommonRepository<Subject> {

    @Query(
            value = "SELECT subject FROM Subject subject LEFT JOIN FETCH subject.tutor",
            countQuery = "SELECT COUNT(subject) FROM Subject subject"
    )
    List<Subject> findAll();

    @Query(
            value = "SELECT subject FROM Subject subject LEFT JOIN FETCH subject.tutor",
            countQuery = "SELECT COUNT(subject) FROM Subject subject"
    )
    Page<Subject> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"tutor"})
    Page<Subject> findAll(Specification<Subject> specification, Pageable pageable);

    @EntityGraph(attributePaths = {
            "courses",
            "lessons",
            "topics",
            "exercises",
            "tutor",
            "tutor.role",
            "tutor.account"
    })
    Optional<Subject> findByUuid(UUID uuid);
}
