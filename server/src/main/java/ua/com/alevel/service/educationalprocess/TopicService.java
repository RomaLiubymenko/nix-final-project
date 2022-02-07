package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Topic;
import ua.com.alevel.persistence.repository.educationalprocess.TopicRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class TopicService extends AbstractService<Topic, TopicRepository> {

    private final TopicRepository topicRepository;
    private final CrudRepositoryHelper<Topic, TopicRepository> crudRepositoryHelper;

    public TopicService(TopicRepository topicRepository, CrudRepositoryHelper<Topic, TopicRepository> crudRepositoryHelper) {
        super(topicRepository, crudRepositoryHelper);
        this.topicRepository = topicRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
