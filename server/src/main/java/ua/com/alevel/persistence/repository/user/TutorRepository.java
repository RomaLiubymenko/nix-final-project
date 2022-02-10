package ua.com.alevel.persistence.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TutorRepository extends CommonRepository<Tutor> {

    @Query(
            value = "SELECT tutor FROM Tutor tutor LEFT JOIN FETCH tutor.subjects",
            countQuery = "SELECT COUNT(tutor) FROM Tutor tutor"
    )
    List<Tutor> findAll();

    @Query(
            value = "SELECT tutor FROM Tutor tutor LEFT JOIN FETCH tutor.subjects",
            countQuery = "SELECT COUNT(tutor) FROM Tutor tutor"
    )
    Page<Tutor> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"subjects"})
    Page<Tutor> findAll(Specification<Tutor> specification, Pageable pageable);

    @EntityGraph(attributePaths = {
            "account",
            "role",
            "accountingOfPayments",
            "reports",
            "courses",
            "lessons",
            "subjects",
            "exercises"
    })
    Optional<Tutor> findByUuid(UUID uuid);

    @EntityGraph(attributePaths = {"exercises", "reports", "subjects"})
    Set<Tutor> findByUuidIn(Set<UUID> uuids);
}
