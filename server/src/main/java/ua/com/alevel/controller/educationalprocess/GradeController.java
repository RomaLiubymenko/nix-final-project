package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.GradeFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.GradeProfileDto;
import ua.com.alevel.dto.table.educationalprocess.GradeTableDto;
import ua.com.alevel.facade.educationalprocess.GradeFacade;

@RestController
@RequestMapping(value = "/api/v1/grades", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController extends AbstractController<GradeTableDto, GradeProfileDto, GradeFilterDto> {

    private static final String ENTITY_NAME = "grade";
    private static final String URL = "/api/v1/grades";
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    private final GradeFacade gradeFacade;

    public GradeController(GradeFacade gradeFacade) {
        super(URL, ENTITY_NAME, logger, gradeFacade);
        this.gradeFacade = gradeFacade;
    }
}
