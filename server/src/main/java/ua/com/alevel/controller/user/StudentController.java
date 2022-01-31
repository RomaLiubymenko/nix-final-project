package ua.com.alevel.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.StudentFilterDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.facade.user.StudentFacade;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController extends AbstractController<StudentTableDto, StudentProfileDto, StudentFilterDto> {

    private static final String ENTITY_NAME = "student";
    private static final String URL = "/api/v1/students";
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        super(URL, ENTITY_NAME, logger, studentFacade);
        this.studentFacade = studentFacade;
    }
}
