package ua.com.alevel.mapper.user;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.educationalprocess.*;
import ua.com.alevel.mapper.finance.AccountMapper;
import ua.com.alevel.mapper.finance.AccountReplenishmentMapper;
import ua.com.alevel.mapper.finance.AttendanceMapper;
import ua.com.alevel.persistence.entity.user.Student;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AccountReplenishmentMapper.class,
                AttendanceMapper.class,
                LessonMapper.class,
                StudentGroupMapper.class,
                ExerciseMapper.class,
                CourseMapper.class,
                ReportMapper.class,
                AccountMapper.class
        }
)
public interface StudentMapper extends CommonMapper<Student, StudentTableDto, StudentProfileDto> {

    @Override
    StudentTableDto toTableDto(Student student);

    @Override
    Student toEntity(StudentProfileDto studentProfileDto);

    @Override
    @Mapping(target = "accountReplenishments", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "attendances", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "studentGroups", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "exercises", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "courses", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "reports", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "account", qualifiedByName = "forUserProfileDto")
    StudentProfileDto toProfileDto(Student student);

    @Named("forAccountReplenishmentProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForAccountReplenishmentsProfileDto(Student student);

    @Named("forCourseProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToCourseProfileDtoForCourseProfileDto(Student student);

    @Named("forStudentGroupProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForStudentGroupProfileDto(Student student);

    @Named("forLessonProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForLessonProfileDto(Student student);

    @Named("forAttendanceProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForAttendanceProfileDto(Student student);

    @Named("forReportProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForReportProfileDto(Student student);

    @Named("forExerciseProfileDto")
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    StudentProfileDto studentToStudentProfileDtoForExerciseProfileDto(Student student);
}
