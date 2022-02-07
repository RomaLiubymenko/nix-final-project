package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Grade;
import ua.com.alevel.persistence.repository.educationalprocess.GradeRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class GradeService extends AbstractService<Grade, GradeRepository> {

    private final GradeRepository gradeRepository;
    private final CrudRepositoryHelper<Grade, GradeRepository> crudRepositoryHelper;

    public GradeService(GradeRepository gradeRepository, CrudRepositoryHelper<Grade, GradeRepository> crudRepositoryHelper) {
        super(gradeRepository, crudRepositoryHelper);
        this.gradeRepository = gradeRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
