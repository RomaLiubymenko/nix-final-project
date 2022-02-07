package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.StudentGroupFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.StudentGroupProfileDto;
import ua.com.alevel.dto.table.educationalprocess.StudentGroupTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.StudentGroupMapper;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.service.educationalprocess.StudentGroupService;
import ua.com.alevel.specification.educationalprocess.StudentGroupSpecificationBuilder;

@Service
public class StudentGroupFacade extends AbstractFacade<StudentGroup, StudentGroupFilterDto, StudentGroupTableDto, StudentGroupProfileDto> {

    private final StudentGroupService studentGroupService;
    private final StudentGroupMapper studentGroupMapper;
    private final StudentGroupSpecificationBuilder studentGroupSpecificationBuilder;

    public StudentGroupFacade(StudentGroupService studentGroupService,
                              StudentGroupMapper studentGroupMapper,
                              StudentGroupSpecificationBuilder studentGroupSpecificationBuilder) {
        super(studentGroupService, studentGroupMapper, studentGroupSpecificationBuilder);
        this.studentGroupService = studentGroupService;
        this.studentGroupMapper = studentGroupMapper;
        this.studentGroupSpecificationBuilder = studentGroupSpecificationBuilder;
    }
}
