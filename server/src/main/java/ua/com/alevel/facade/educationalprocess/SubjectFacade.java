package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.SubjectFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.SubjectProfileDto;
import ua.com.alevel.dto.table.educationalprocess.SubjectTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.SubjectMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.service.educationalprocess.SubjectService;
import ua.com.alevel.specification.educationalprocess.SubjectSpecificationBuilder;

@Service
public class SubjectFacade extends AbstractFacade<Subject, SubjectFilterDto, SubjectTableDto, SubjectProfileDto> {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;
    private final SubjectSpecificationBuilder subjectSpecificationBuilder;

    public SubjectFacade(SubjectService subjectService,
                         SubjectMapper subjectMapper,
                         SubjectSpecificationBuilder subjectSpecificationBuilder) {
        super(subjectService, subjectMapper, subjectSpecificationBuilder);
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
        this.subjectSpecificationBuilder = subjectSpecificationBuilder;
    }
}
