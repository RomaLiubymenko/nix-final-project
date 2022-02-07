package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.StudentGroupProfileDto;
import ua.com.alevel.dto.table.educationalprocess.StudentGroupTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                StudentMapper.class,
                CourseMapper.class,
                LessonMapper.class
        }
)
public interface StudentGroupMapper extends CommonMapper<StudentGroup, StudentGroupTableDto, StudentGroupProfileDto> {

    @Override
    StudentGroupTableDto toTableDto(StudentGroup entity);

    @Override
    StudentGroup toEntity(StudentGroupProfileDto profileDto);

    @Override
    @Mapping(target = "students", qualifiedByName = "forStudentGroupProfileDto")
    @Mapping(target = "course", qualifiedByName = "forStudentGroupProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forStudentGroupProfileDto")
    StudentGroupProfileDto toProfileDto(StudentGroup entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    StudentGroupProfileDto studentGroupToStudentGroupProfileDtoForStudentProfileDto(StudentGroup studentGroup);

    @Named("forCourseProfileDto")
    @Mapping(target = "course", ignore = true)
    StudentGroupProfileDto studentGroupToStudentGroupProfileDtoForCourseProfileDto(StudentGroup studentGroup);

    @Named("forLessonProfileDto")
    @Mapping(target = "lessons", ignore = true)
    StudentGroupProfileDto studentGroupToStudentGroupProfileDtoForLessonProfileDto(StudentGroup studentGroup);
}
