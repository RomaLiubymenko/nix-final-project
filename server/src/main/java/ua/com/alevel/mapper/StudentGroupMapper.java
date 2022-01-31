package ua.com.alevel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.StudentGroupProfileDto;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StudentGroupMapper {

    StudentGroup studentGroupProfileDtoToStudentGroup(StudentGroupProfileDto studentGroupProfileDto);

    // For student
    @Named("forStudentProfileDto")
    @Mapping(target = "students", ignore = true)
    StudentGroupProfileDto studentGroupToStudentGroupProfileDtoForStudentProfileDto(StudentGroup studentGroup);
}
