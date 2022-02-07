package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.ExerciseFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.ExerciseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ExerciseTableDto;
import ua.com.alevel.facade.educationalprocess.ExerciseFacade;

@RestController
@RequestMapping(value = "/api/v1/exercises", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExerciseController extends AbstractController<ExerciseTableDto, ExerciseProfileDto, ExerciseFilterDto> {

    private static final String ENTITY_NAME = "exercise";
    private static final String URL = "/api/v1/exercises";
    private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    private final ExerciseFacade exerciseFacade;

    public ExerciseController(ExerciseFacade exerciseFacade) {
        super(URL, ENTITY_NAME, logger, exerciseFacade);
        this.exerciseFacade = exerciseFacade;
    }
}
