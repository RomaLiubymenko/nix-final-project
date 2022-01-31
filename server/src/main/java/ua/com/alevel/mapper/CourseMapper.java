package ua.com.alevel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.CourseProfileDto;
import ua.com.alevel.persistence.entity.educationalprocess.Course;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CourseMapper {

    Course courseProfileDtoToCourse(CourseProfileDto courseProfileDto);

    // For student
    @Named("forStudentProfileDto")
    @Mapping(target = "students", ignore = true)
    CourseProfileDto courseToCourseProfileDtoForStudentProfileDto(Course course);


}
