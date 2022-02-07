package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.persistence.repository.educationalprocess.LessonRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class LessonService extends AbstractService<Lesson, LessonRepository> {

    private final LessonRepository lessonRepository;
    private final CrudRepositoryHelper<Lesson, LessonRepository> crudRepositoryHelper;

    public LessonService(LessonRepository lessonRepository, CrudRepositoryHelper<Lesson, LessonRepository> crudRepositoryHelper) {
        super(lessonRepository, crudRepositoryHelper);
        this.lessonRepository = lessonRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
