package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.ExerciseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ExerciseTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Exercise;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                StudentMapper.class,
                ReportMapper.class,
                TopicMapper.class,
                CourseMapper.class,
                SubjectMapper.class,
                TutorMapper.class
        }
)
public interface ExerciseMapper extends CommonMapper<Exercise, ExerciseTableDto, ExerciseProfileDto> {

    @Override
    ExerciseTableDto toTableDto(Exercise entity);

    @Override
    Exercise toEntity(ExerciseProfileDto profileDto);

    @Override
    @Mapping(target = "students", qualifiedByName = "forExerciseProfileDto")
    @Mapping(target = "report", qualifiedByName = "forExerciseProfileDto")
    @Mapping(target = "topic", qualifiedByName = "forExerciseProfileDto")
    @Mapping(target = "course", qualifiedByName = "forExerciseProfileDto")
    @Mapping(target = "subject", qualifiedByName = "forExerciseProfileDto")
    @Mapping(target = "tutor", qualifiedByName = "forExerciseProfileDto")
    ExerciseProfileDto toProfileDto(Exercise entity);

    @Named("forTutorProfileDto")
    @Mapping(target = "tutor", ignore = true)
    ExerciseProfileDto exerciseToExerciseProfileDtoForTutorProfileDto(Exercise entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "report", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    ExerciseProfileDto exerciseToExerciseProfileDtoForStudentProfileDto(Exercise entity);

    @Named("forCourseProfileDto")
    @Mapping(target = "course", ignore = true)
    ExerciseProfileDto exerciseToExerciseProfileDtoForCourseProfileDto(Exercise entity);

    @Named("forSubjectProfileDto")
    @Mapping(target = "subject", ignore = true)
    ExerciseProfileDto exerciseToExerciseProfileDtoForSubjectProfileDto(Exercise exercise);

    @Named("forTopicProfileDto")
    @Mapping(target = "topic", ignore = true)
    ExerciseProfileDto exerciseToExerciseProfileDtoForTopicProfileDto(Exercise exercise);
}
