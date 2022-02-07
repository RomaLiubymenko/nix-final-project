package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.GradeProfileDto;
import ua.com.alevel.dto.table.educationalprocess.GradeTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Grade;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                ReportMapper.class,
                CourseMapper.class,
                StudentMapper.class,
                TutorMapper.class
        }
)
public interface GradeMapper extends CommonMapper<Grade, GradeTableDto, GradeProfileDto> {

    @Override
    Grade toEntity(GradeProfileDto profileDto);

    @Override
    @Mapping(target = "report.uuid", source = "report.uuid")
    @Mapping(target = "report.content", source = "report.content")
    @Mapping(target = "report.comment", source = "report.comment")
    @Mapping(target = "report.studentUuid", source = "report.student.uuid")
    @Mapping(target = "report.tutorUuid", source = "report.tutor.uuid")
    @Mapping(target = "report.studentFirstName", source = "report.student.firstName")
    @Mapping(target = "report.tutorFirstName", source = "report.tutor.firstName")
    @Mapping(target = "report.studentLastName", source = "report.student.lastName")
    @Mapping(target = "report.tutorLastName", source = "report.tutor.lastName")
    GradeTableDto toTableDto(Grade entity);

    @Override
    @Mapping(target = "report", qualifiedByName = "forGradeProfileDto")
    @Mapping(target = "course", qualifiedByName = "forGradeProfileDto")
    GradeProfileDto toProfileDto(Grade entity);

    @Named("forCourseProfileDto")
    @Mapping(target = "course", ignore = true)
    GradeProfileDto gradeToGradeProfileDtoForCourseProfileDto(Grade entity);

    @Named("forReportProfileDto")
    @Mapping(target = "report", ignore = true)
    GradeProfileDto gradeToGradeProfileDtoForReportProfileDto(Grade entity);
}
