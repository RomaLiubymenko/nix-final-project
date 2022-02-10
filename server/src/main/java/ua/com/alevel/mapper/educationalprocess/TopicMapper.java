package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.TopicProfileDto;
import ua.com.alevel.dto.table.educationalprocess.TopicTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Topic;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                SubjectMapper.class,
                CourseMapper.class,
                LessonMapper.class,
                ExerciseMapper.class
        }
)
public interface TopicMapper extends CommonMapper<Topic, TopicTableDto, TopicProfileDto> {

    @Override
    Topic toEntity(TopicProfileDto profileDto);

    @Override
    TopicTableDto toTableDto(Topic entity);

    @Override
    @Mapping(target = "subject", qualifiedByName = "forTopicProfileDto")
    @Mapping(target = "course", qualifiedByName = "forTopicProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forTopicProfileDto")
    @Mapping(target = "exercises", qualifiedByName = "forTopicProfileDto")
    TopicProfileDto toProfileDto(Topic entity);

    @Named("forCourseProfileDto")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    TopicProfileDto topicToTopicProfileDtoForCourseProfileDto(Topic entity);

    @Named("forSubjectProfileDto")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    TopicProfileDto topicToTopicProfileDtoForSubjectProfileDto(Topic entity);

    @Named("forLessonProfileDto")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    TopicProfileDto topicToTopicProfileDtoForLessonProfileDto(Topic entity);

    @Named("forExerciseProfileDto")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    TopicProfileDto topicToTopicProfileDtoForExerciseProfileDto(Topic entity);
}
