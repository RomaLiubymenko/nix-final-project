package ua.com.alevel.mapper.finance;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.finance.AttendanceProfileDto;
import ua.com.alevel.dto.table.finance.AttendanceTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.educationalprocess.LessonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.persistence.entity.finance.Attendance;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                LessonMapper.class,
                StudentMapper.class
        }
)
public interface AttendanceMapper extends CommonMapper<Attendance, AttendanceTableDto, AttendanceProfileDto> {

    @Override
    AttendanceTableDto toTableDto(Attendance entity);

    @Override
    Attendance toEntity(AttendanceProfileDto profileDto);

    @Override
    @Mapping(target = "lesson", qualifiedByName = "forAttendanceProfileDto")
    @Mapping(target = "student", qualifiedByName = "forAttendanceProfileDto")
    AttendanceProfileDto toProfileDto(Attendance entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    AttendanceProfileDto attendanceToAttendanceProfileDtoForStudentProfileDto(Attendance entity);

    @Named("forLessonProfileDto")
    @Mapping(target = "lesson", ignore = true)
    AttendanceProfileDto attendanceToAttendanceProfileDtoForLessonProfileDto(Attendance entity);
}
