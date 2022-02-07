package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Course;
import ua.com.alevel.persistence.repository.educationalprocess.CourseRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class CourseService extends AbstractService<Course, CourseRepository> {

    private final CourseRepository courseRepository;
    private final CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper;

    public CourseService(CourseRepository courseRepository, CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper) {
        super(courseRepository, crudRepositoryHelper);
        this.courseRepository = courseRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
