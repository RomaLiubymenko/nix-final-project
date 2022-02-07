package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.TopicFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.TopicProfileDto;
import ua.com.alevel.dto.table.educationalprocess.TopicTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.TopicMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Topic;
import ua.com.alevel.service.educationalprocess.TopicService;
import ua.com.alevel.specification.educationalprocess.TopicSpecificationBuilder;

@Service
public class TopicFacade extends AbstractFacade<Topic, TopicFilterDto, TopicTableDto, TopicProfileDto> {

    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final TopicSpecificationBuilder topicSpecificationBuilder;

    public TopicFacade(TopicService topicService,
                       TopicMapper topicMapper,
                       TopicSpecificationBuilder topicSpecificationBuilder) {
        super(topicService, topicMapper, topicSpecificationBuilder);
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.topicSpecificationBuilder = topicSpecificationBuilder;
    }
}
