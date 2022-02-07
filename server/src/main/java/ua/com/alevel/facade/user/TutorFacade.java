package ua.com.alevel.facade.user;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.TutorFilterDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.table.user.TutorTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.service.user.TutorService;
import ua.com.alevel.specification.user.TutorSpecificationBuilder;

@Service
public class TutorFacade extends AbstractFacade<Tutor, TutorFilterDto, TutorTableDto, TutorProfileDto> {

    private final TutorService tutorService;
    private final TutorMapper tutorMapper;
    private final TutorSpecificationBuilder tutorSpecificationBuilder;

    public TutorFacade(TutorService tutorService,
                       TutorMapper tutorMapper,
                       TutorSpecificationBuilder tutorSpecificationBuilder) {
        super(tutorService, tutorMapper, tutorSpecificationBuilder);
        this.tutorService = tutorService;
        this.tutorMapper = tutorMapper;
        this.tutorSpecificationBuilder = tutorSpecificationBuilder;
    }
}
