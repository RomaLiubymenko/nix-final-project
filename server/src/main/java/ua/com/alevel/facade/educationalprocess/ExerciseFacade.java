package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.ExerciseFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.ExerciseProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ExerciseTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.ExerciseMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Exercise;
import ua.com.alevel.service.educationalprocess.ExerciseService;
import ua.com.alevel.specification.educationalprocess.ExerciseSpecificationBuilder;

@Service
public class ExerciseFacade extends AbstractFacade<Exercise, ExerciseFilterDto, ExerciseTableDto, ExerciseProfileDto> {

    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final ExerciseSpecificationBuilder exerciseSpecificationBuilder;

    public ExerciseFacade(ExerciseService exerciseService,
                          ExerciseMapper exerciseMapper,
                          ExerciseSpecificationBuilder exerciseSpecificationBuilder) {
        super(exerciseService, exerciseMapper, exerciseSpecificationBuilder);
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.exerciseSpecificationBuilder = exerciseSpecificationBuilder;
    }
}
