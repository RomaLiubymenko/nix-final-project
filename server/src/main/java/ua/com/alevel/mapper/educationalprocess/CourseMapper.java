package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.CourseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.CourseTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Course;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                GradeMapper.class,
                ExerciseMapper.class,
                SubjectMapper.class,
                TopicMapper.class,
                LessonMapper.class,
                StudentGroupMapper.class,
                StudentMapper.class,
                TutorMapper.class
        }
)
public interface CourseMapper extends CommonMapper<Course, CourseTableDto, CourseProfileDto> {

    @Override
    CourseTableDto toTableDto(Course entity);

    @Override
    Course toEntity(CourseProfileDto profileDto);

    @Override
    @Mapping(target = "grades", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "exercises", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "subjects", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "topics", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "studentGroup", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "students", qualifiedByName = "forCourseProfileDto")
    @Mapping(target = "tutors", qualifiedByName = "forCourseProfileDto")
    CourseProfileDto toProfileDto(Course entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "grades", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "studentGroup", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForStudentProfileDto(Course course);

    @Named("forTutorProfileDto")
    @Mapping(target = "tutors", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForTutorProfileDto(Course course);

    @Named("forStudentGroupProfileDto")
    @Mapping(target = "studentGroup", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForStudentGroupProfileDto(Course course);

    @Named("forSubjectProfileDto")
    @Mapping(target = "subjects", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForSubjectsProfileDto(Course course);

    @Named("forTopicProfileDto")
    @Mapping(target = "topics", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForTopicProfileDto(Course course);

    @Named("forLessonProfileDto")
    @Mapping(target = "lessons", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForLessonProfileDto(Course course);

    @Named("forGradeProfileDto")
    @Mapping(target = "grades", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForGradeProfileDto(Course course);

    @Named("forExerciseProfileDto")
    @Mapping(target = "exercises", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForExerciseProfileDto(Course course);
}
