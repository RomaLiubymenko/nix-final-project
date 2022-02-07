package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Exercise;
import ua.com.alevel.persistence.repository.educationalprocess.ExerciseRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class ExerciseService extends AbstractService<Exercise, ExerciseRepository> {

    private final ExerciseRepository exerciseRepository;
    private final CrudRepositoryHelper<Exercise, ExerciseRepository> crudRepositoryHelper;

    public ExerciseService(ExerciseRepository exerciseRepository, CrudRepositoryHelper<Exercise, ExerciseRepository> crudRepositoryHelper) {
        super(exerciseRepository, crudRepositoryHelper);
        this.exerciseRepository = exerciseRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
