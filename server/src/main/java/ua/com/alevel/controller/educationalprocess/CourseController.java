package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.CourseFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.CourseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.CourseTableDto;
import ua.com.alevel.facade.educationalprocess.CourseFacade;


@RestController
@RequestMapping(value = "/api/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController extends AbstractController<CourseTableDto, CourseProfileDto, CourseFilterDto> {

    private static final String ENTITY_NAME = "course";
    private static final String URL = "/api/v1/courses";
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseFacade courseFacade;

    public CourseController(CourseFacade courseFacade) {
        super(URL, ENTITY_NAME, logger, courseFacade);
        this.courseFacade = courseFacade;
    }
}
