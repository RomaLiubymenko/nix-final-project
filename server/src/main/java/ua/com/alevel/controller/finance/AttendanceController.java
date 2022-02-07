package ua.com.alevel.controller.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.finance.AttendanceFilterDto;
import ua.com.alevel.dto.profile.finance.AttendanceProfileDto;
import ua.com.alevel.dto.table.finance.AttendanceTableDto;
import ua.com.alevel.facade.finance.AttendanceFacade;


@RestController
@RequestMapping(value = "/api/v1/attendances", produces = MediaType.APPLICATION_JSON_VALUE)
public class AttendanceController extends AbstractController<AttendanceTableDto, AttendanceProfileDto, AttendanceFilterDto> {

    private static final String ENTITY_NAME = "attendance";
    private static final String URL = "/api/v1/attendances";
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    private final AttendanceFacade attendanceFacade;

    public AttendanceController(AttendanceFacade attendanceFacade) {
        super(URL, ENTITY_NAME, logger, attendanceFacade);
        this.attendanceFacade = attendanceFacade;
    }
}
