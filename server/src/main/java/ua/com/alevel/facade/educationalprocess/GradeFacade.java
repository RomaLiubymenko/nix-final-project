package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.GradeFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.GradeProfileDto;
import ua.com.alevel.dto.table.educationalprocess.GradeTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.GradeMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Grade;
import ua.com.alevel.service.educationalprocess.GradeService;
import ua.com.alevel.specification.educationalprocess.GradeSpecificationBuilder;

@Service
public class GradeFacade extends AbstractFacade<Grade, GradeFilterDto, GradeTableDto, GradeProfileDto> {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;
    private final GradeSpecificationBuilder gradeSpecificationBuilder;

    public GradeFacade(GradeService gradeService,
                       GradeMapper gradeMapper,
                       GradeSpecificationBuilder gradeSpecificationBuilder) {
        super(gradeService, gradeMapper, gradeSpecificationBuilder);
        this.gradeService = gradeService;
        this.gradeMapper = gradeMapper;
        this.gradeSpecificationBuilder = gradeSpecificationBuilder;
    }
}
