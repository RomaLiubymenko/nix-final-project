package ua.com.alevel.persistence.repository.finance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface AttendanceRepository extends CommonRepository<Attendance> {
}
