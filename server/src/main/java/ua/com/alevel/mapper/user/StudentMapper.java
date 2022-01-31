package ua.com.alevel.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.mapper.*;
import ua.com.alevel.persistence.entity.user.Student;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AccountReplenishmentMapper.class,
                StudentGroupMapper.class,
                CourseMapper.class,
                AccountMapper.class
        }
)
public abstract class StudentMapper implements CommonMapper<Student, StudentTableDto, StudentProfileDto> {

    @Override
    public abstract StudentTableDto toTableDto(Student student);

    @Override
    @Mapping(target = "accountReplenishments", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "studentGroups", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "courses", qualifiedByName = "forStudentProfileDto")
    @Mapping(target = "account", qualifiedByName = "forUserProfileDto")
    public abstract StudentProfileDto toProfileDto(Student student);

    @Override
    @Mapping(target = "accountReplenishments", ignore = true)
    @Mapping(target = "studentGroups", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "account", ignore = true)
    public abstract Student toEntity(StudentProfileDto studentProfileDto);
}
