package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.LessonProfileDto;
import ua.com.alevel.dto.table.educationalprocess.LessonTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.finance.AttendanceMapper;
import ua.com.alevel.mapper.location.RoomMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Lesson;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AttendanceMapper.class,
                StudentMapper.class,
                SubjectMapper.class,
                TopicMapper.class,
                CourseMapper.class,
                RoomMapper.class,
                TutorMapper.class,
                StudentGroupMapper.class
        }
)
public interface LessonMapper extends CommonMapper<Lesson, LessonTableDto, LessonProfileDto> {

    @Override
    LessonTableDto toTableDto(Lesson entity);

    @Override
    Lesson toEntity(LessonProfileDto profileDto);

    @Override
    @Mapping(target = "attendances", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "students", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "subject", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "topics", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "course", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "room", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "tutors", qualifiedByName = "forLessonProfileDto")
    @Mapping(target = "studentGroups", qualifiedByName = "forLessonProfileDto")
    LessonProfileDto toProfileDto(Lesson entity);

    @Named("forTutorProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForTutorProfileDto(Lesson entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForStudentProfileDto(Lesson entity);

    @Named("forRoomProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForRoomProfileDto(Lesson entity);

    @Named("forCourseProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForCourseProfileDto(Lesson entity);

    @Named("forStudentGroupProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForStudentGroupProfileDto(Lesson entity);

    @Named("forSubjectProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForSubjectProfileDto(Lesson entity);

    @Named("forTopicProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForTopicProfileDto(Lesson entity);

    @Named("forAttendanceProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "topics", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "tutors", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    LessonProfileDto lessonToLessonProfileDtoForAttendanceProfileDto(Lesson entity);
}
