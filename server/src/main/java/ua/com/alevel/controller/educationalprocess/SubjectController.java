package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.SubjectFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.SubjectProfileDto;
import ua.com.alevel.dto.table.educationalprocess.SubjectTableDto;
import ua.com.alevel.facade.educationalprocess.SubjectFacade;

@RestController
@RequestMapping(value = "/api/v1/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController extends AbstractController<SubjectTableDto, SubjectProfileDto, SubjectFilterDto> {

    private static final String ENTITY_NAME = "subject";
    private static final String URL = "/api/v1/subjects";
    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
    private final SubjectFacade subjectFacade;

    public SubjectController(SubjectFacade subjectFacade) {
        super(URL, ENTITY_NAME, logger, subjectFacade);
        this.subjectFacade = subjectFacade;
    }
}
