package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.repository.educationalprocess.SubjectRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class SubjectService extends AbstractService<Subject, SubjectRepository> {

    private final SubjectRepository subjectRepository;
    private final CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper;

    public SubjectService(SubjectRepository subjectRepository, CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper) {
        super(subjectRepository, crudRepositoryHelper);
        this.subjectRepository = subjectRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
