package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.LessonFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.LessonProfileDto;
import ua.com.alevel.dto.table.educationalprocess.LessonTableDto;
import ua.com.alevel.facade.educationalprocess.LessonFacade;

@RestController
@RequestMapping(value = "/api/v1/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
public class LessonController extends AbstractController<LessonTableDto, LessonProfileDto, LessonFilterDto> {

    private static final String ENTITY_NAME = "lesson";
    private static final String URL = "/api/v1/lessons";
    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);
    private final LessonFacade lessonFacade;

    public LessonController(LessonFacade lessonFacade) {
        super(URL, ENTITY_NAME, logger, lessonFacade);
        this.lessonFacade = lessonFacade;
    }
}
