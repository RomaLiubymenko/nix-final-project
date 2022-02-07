package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.LessonFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.LessonProfileDto;
import ua.com.alevel.dto.table.educationalprocess.LessonTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.LessonMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;
import ua.com.alevel.service.educationalprocess.LessonService;
import ua.com.alevel.specification.educationalprocess.LessonSpecificationBuilder;

@Service
public class LessonFacade extends AbstractFacade<Lesson, LessonFilterDto, LessonTableDto, LessonProfileDto> {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;
    private final LessonSpecificationBuilder lessonSpecificationBuilder;

    public LessonFacade(LessonService lessonService,
                        LessonMapper lessonMapper,
                        LessonSpecificationBuilder lessonSpecificationBuilder) {
        super(lessonService, lessonMapper, lessonSpecificationBuilder);
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
        this.lessonSpecificationBuilder = lessonSpecificationBuilder;
    }
}
