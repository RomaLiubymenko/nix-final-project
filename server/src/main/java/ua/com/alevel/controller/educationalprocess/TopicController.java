package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.TopicFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.TopicProfileDto;
import ua.com.alevel.dto.table.educationalprocess.TopicTableDto;
import ua.com.alevel.facade.educationalprocess.TopicFacade;

@RestController
@RequestMapping(value = "/api/v1/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController extends AbstractController<TopicTableDto, TopicProfileDto, TopicFilterDto> {

    private static final String ENTITY_NAME = "topic";
    private static final String URL = "/api/v1/topics";
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private final TopicFacade topicFacade;

    public TopicController(TopicFacade topicFacade) {
        super(URL, ENTITY_NAME, logger, topicFacade);
        this.topicFacade = topicFacade;
    }
}
