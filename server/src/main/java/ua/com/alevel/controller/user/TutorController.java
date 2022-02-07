package ua.com.alevel.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.user.TutorFilterDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.table.user.TutorTableDto;
import ua.com.alevel.facade.user.TutorFacade;

@RestController
@RequestMapping(value = "/api/v1/tutors", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorController extends AbstractController<TutorTableDto, TutorProfileDto, TutorFilterDto> {

    private static final String ENTITY_NAME = "tutor";
    private static final String URL = "/api/v1/tutors";
    private static final Logger logger = LoggerFactory.getLogger(TutorController.class);
    private final TutorFacade tutorFacade;

    public TutorController(TutorFacade tutorFacade) {
        super(URL, ENTITY_NAME, logger, tutorFacade);
        this.tutorFacade = tutorFacade;
    }
}
