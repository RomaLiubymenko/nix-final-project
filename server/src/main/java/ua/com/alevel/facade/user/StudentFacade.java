package ua.com.alevel.facade.user;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.StudentFilterDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.service.user.StudentService;
import ua.com.alevel.specification.user.StudentSpecificationBuilder;

@Service
public class StudentFacade extends AbstractFacade<Student, StudentFilterDto, StudentTableDto, StudentProfileDto> {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final StudentSpecificationBuilder studentSpecificationBuilder;

    public StudentFacade(StudentService studentService,
                         StudentMapper studentMapper,
                         StudentSpecificationBuilder studentSpecificationBuilder) {
        super(studentService, studentMapper, studentSpecificationBuilder);
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.studentSpecificationBuilder = studentSpecificationBuilder;
    }
}
