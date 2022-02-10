package ua.com.alevel.persistence.repository.educationalprocess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentGroupRepository extends CommonRepository<StudentGroup> {

    @Query(
            value = "SELECT studentGroup FROM StudentGroup studentGroup LEFT JOIN FETCH studentGroup.students",
            countQuery = "SELECT COUNT(studentGroup) FROM StudentGroup studentGroup"
    )
    List<StudentGroup> findAll();

    @Query(
            value = "SELECT studentGroup FROM StudentGroup studentGroup LEFT JOIN FETCH studentGroup.students",
            countQuery = "SELECT COUNT(studentGroup) FROM StudentGroup studentGroup"
    )
    Page<StudentGroup> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"students"})
    Page<StudentGroup> findAll(Specification<StudentGroup> specification, Pageable pageable);

    @EntityGraph(attributePaths = {
            "students",
            "course",
            "lessons"
    })
    Optional<StudentGroup> findByUuid(UUID uuid);
}
