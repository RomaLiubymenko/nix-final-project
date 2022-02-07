package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.repository.educationalprocess.StudentGroupRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class StudentGroupService extends AbstractService<StudentGroup, StudentGroupRepository> {

    private final StudentGroupRepository studentGroupRepository;
    private final CrudRepositoryHelper<StudentGroup, StudentGroupRepository> crudRepositoryHelper;

    public StudentGroupService(StudentGroupRepository studentGroupRepository,
                               CrudRepositoryHelper<StudentGroup, StudentGroupRepository> crudRepositoryHelper) {
        super(studentGroupRepository, crudRepositoryHelper);
        this.studentGroupRepository = studentGroupRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
