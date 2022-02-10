package ua.com.alevel.mapper.educationalprocess;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.educationalprocess.ReportProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ReportTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Report;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                TutorMapper.class,
                StudentMapper.class,
                GradeMapper.class,
        }
)
public interface ReportMapper extends CommonMapper<Report, ReportTableDto, ReportProfileDto> {

    @Override
    ReportTableDto toTableDto(Report entity);

    @Override
    Report toEntity(ReportProfileDto profileDto);

    @Override
    @Mapping(target = "tutor", qualifiedByName = "forReportProfileDto")
    @Mapping(target = "student", qualifiedByName = "forReportProfileDto")
    @Mapping(target = "grade", qualifiedByName = "forReportProfileDto")
    ReportProfileDto toProfileDto(Report entity);

    @Named("forTutorProfileDto")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    ReportProfileDto reportToReportProfileDtoForTutorProfileDto(Report entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "grade", ignore = true)
    ReportProfileDto reportToReportProfileDtoForStudentProfileDto(Report entity);

    @Named("forGradeProfileDto")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "grade", ignore = true)
    ReportProfileDto reportToReportProfileDtoForGradeProfileDto(Report entity);

    @Named("forExerciseProfileDto")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "tutor", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    @Mapping(target = "grade", ignore = true)
    ReportProfileDto reportToReportProfileDtoForExerciseProfileDto(Report entity);
}
