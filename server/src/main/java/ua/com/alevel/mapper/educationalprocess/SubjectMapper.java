package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.SubjectProfileDto;
import ua.com.alevel.dto.table.educationalprocess.SubjectTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                CourseMapper.class,
                LessonMapper.class,
                TopicMapper.class,
                ExerciseMapper.class,
                TutorMapper.class
        }
)
public interface SubjectMapper extends CommonMapper<Subject, SubjectTableDto, SubjectProfileDto> {

    @Override
    Subject toEntity(SubjectProfileDto profileDto);

    @Override
    SubjectTableDto toTableDto(Subject entity);

    @Override
    @Mapping(target = "courses", qualifiedByName = "forSubjectProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forSubjectProfileDto")
    @Mapping(target = "topics", qualifiedByName = "forSubjectProfileDto")
    @Mapping(target = "exercises", qualifiedByName = "forSubjectProfileDto")
    @Mapping(target = "tutor", qualifiedByName = "forSubjectProfileDto")
    SubjectProfileDto toProfileDto(Subject entity);

    @Named("forTutorProfileDto")
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    SubjectProfileDto subjectToSubjectProfileDtoForTutorProfileDto(Subject entity);

    @Named("forCourseProfileDto")
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    SubjectProfileDto subjectToSubjectProfileDtoForCourseProfileDto(Subject entity);

    @Named("forTopicProfileDto")
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    SubjectProfileDto subjectToSubjectProfileDtoForTopicProfileDto(Subject entity);

    @Named("forLessonProfileDto")
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    SubjectProfileDto subjectToSubjectProfileDtoForLessonProfileDto(Subject subject);

    @Named("forExerciseProfileDto")
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    SubjectProfileDto subjectToSubjectProfileDtoForExerciseProfileDto(Subject subject);
}
