package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.StudentGroupFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.StudentGroupProfileDto;
import ua.com.alevel.dto.table.educationalprocess.StudentGroupTableDto;
import ua.com.alevel.facade.educationalprocess.StudentGroupFacade;

@RestController
@RequestMapping(value = "/api/v1/student-groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentGroupController extends AbstractController<StudentGroupTableDto, StudentGroupProfileDto, StudentGroupFilterDto> {

    private static final String ENTITY_NAME = "student-group";
    private static final String URL = "/api/v1/student-groups";
    private static final Logger logger = LoggerFactory.getLogger(StudentGroupController.class);
    private final StudentGroupFacade studentGroupFacade;

    public StudentGroupController(StudentGroupFacade studentGroupFacade) {
        super(URL, ENTITY_NAME, logger, studentGroupFacade);
        this.studentGroupFacade = studentGroupFacade;
    }
}
