package ua.com.alevel.facade.finance;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.finance.AttendanceFilterDto;
import ua.com.alevel.dto.profile.finance.AttendanceProfileDto;
import ua.com.alevel.dto.table.finance.AttendanceTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.finance.AttendanceMapper;
import ua.com.alevel.persistence.entity.finance.Attendance;
import ua.com.alevel.service.finance.AttendanceService;
import ua.com.alevel.specification.finance.AttendanceSpecificationBuilder;

@Service
public class AttendanceFacade extends AbstractFacade<Attendance, AttendanceFilterDto, AttendanceTableDto, AttendanceProfileDto> {

    private final AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;
    private final AttendanceSpecificationBuilder attendanceSpecificationBuilder;

    public AttendanceFacade(AttendanceService attendanceService,
                            AttendanceMapper attendanceMapper,
                            AttendanceSpecificationBuilder attendanceSpecificationBuilder) {
        super(attendanceService, attendanceMapper, attendanceSpecificationBuilder);
        this.attendanceService = attendanceService;
        this.attendanceMapper = attendanceMapper;
        this.attendanceSpecificationBuilder = attendanceSpecificationBuilder;
    }
}
