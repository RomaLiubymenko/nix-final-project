package ua.com.alevel.persistence.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends CommonRepository<Student> {

    @Query(
            value = "SELECT student FROM Student student LEFT JOIN FETCH student.studentGroups",
            countQuery = "SELECT COUNT(student) FROM Student student"
    )
    Page<Student> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"studentGroups"})
    Page<Student> findAll(Specification<Student> specification, Pageable pageable);

    @EntityGraph(attributePaths = {
            "account",
            "studentGroups",
            "accountReplenishments",
            "courses",
            "attendances",
            "lessons",
            "exercises",
            "reports",
            "role"
    })
    Optional<Student> findByUuid(UUID uuid);
}
