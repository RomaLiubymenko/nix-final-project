package ua.com.alevel.persistence.repository.finance;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.persistence.repository.CommonRepository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends CommonRepository<Attendance> {

    @EntityGraph(attributePaths = {"student"})
    void deleteByStudent_Uuid(UUID uuid);

    @EntityGraph(attributePaths = {"student"})
    void deleteByStudent_UuidIn(Set<UUID> uuids);
}
