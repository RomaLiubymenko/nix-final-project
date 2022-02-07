package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.LmsFile;
import ua.com.alevel.persistence.repository.educationalprocess.LmsFileRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class LmsFileService extends AbstractService<LmsFile, LmsFileRepository> {

    private final LmsFileRepository lmsFileRepository;
    private final CrudRepositoryHelper<LmsFile, LmsFileRepository> crudRepositoryHelper;

    public LmsFileService(LmsFileRepository lmsFileRepository, CrudRepositoryHelper<LmsFile, LmsFileRepository> crudRepositoryHelper) {
        super(lmsFileRepository, crudRepositoryHelper);
        this.lmsFileRepository = lmsFileRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
