package ua.com.alevel.service.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.persistence.repository.finance.AttendanceRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class AttendanceService extends AbstractService<Attendance, AttendanceRepository> {

    private final AttendanceRepository attendanceRepository;
    private final CrudRepositoryHelper<Attendance, AttendanceRepository> crudRepositoryHelper;

    public AttendanceService(AttendanceRepository attendanceRepository, CrudRepositoryHelper<Attendance, AttendanceRepository> crudRepositoryHelper) {
        super(attendanceRepository, crudRepositoryHelper);
        this.attendanceRepository = attendanceRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
