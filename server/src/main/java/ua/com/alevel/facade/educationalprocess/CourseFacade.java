package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.CourseFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.CourseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.CourseTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.CourseMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Course;
import ua.com.alevel.service.educationalprocess.CourseService;
import ua.com.alevel.specification.educationalprocess.CourseSpecificationBuilder;

@Service
public class CourseFacade extends AbstractFacade<Course, CourseFilterDto, CourseTableDto, CourseProfileDto> {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final CourseSpecificationBuilder courseSpecificationBuilder;

    public CourseFacade(CourseService courseService,
                        CourseMapper courseMapper,
                        CourseSpecificationBuilder courseSpecificationBuilder) {
        super(courseService, courseMapper, courseSpecificationBuilder);
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.courseSpecificationBuilder = courseSpecificationBuilder;
    }
}
