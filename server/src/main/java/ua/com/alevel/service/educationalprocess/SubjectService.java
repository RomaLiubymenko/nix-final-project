package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.educationalprocess.SubjectRepository;
import ua.com.alevel.persistence.repository.user.TutorRepository;
import ua.com.alevel.service.AbstractService;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService extends AbstractService<Subject, SubjectRepository> {

    private final SubjectRepository subjectRepository;
    private final TutorRepository tutorRepository;
    private final CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper;

    public SubjectService(SubjectRepository subjectRepository,
                          TutorRepository tutorRepository,
                          CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper) {
        super(subjectRepository, crudRepositoryHelper);
        this.subjectRepository = subjectRepository;
        this.tutorRepository = tutorRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Subject create(Subject subject) {
        Tutor tutor = prepareTutorToSave(subject);
        tutor.addSubject(subject);
        return subjectRepository.save(subject);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<Subject> update(Subject subject, UUID uuid) {
        boolean isExistSubject = subjectRepository.existsByUuid(uuid);
        if (isExistSubject) {
            Subject subjectReturned = subjectRepository.findByUuid(uuid).get();
            subject.setId(subjectReturned.getId());
            subject.setCourses(subjectReturned.getCourses());
            subject.setLessons(subjectReturned.getLessons());
            subject.setTopics(subjectReturned.getTopics());
            subject.setExercises(subjectReturned.getExercises());
            if (subjectReturned.getTutor() != null) {
                subjectReturned.getTutor().removeSubject(subjectReturned);
            }
            Tutor tutor = prepareTutorToSave(subject);
            tutor.addSubject(subject);
            return Optional.of(subjectRepository.save(subject));
        }
        return Optional.empty();
    }

    private Tutor prepareTutorToSave(Subject subject) {
        return tutorRepository.getByUuid(subject.getTutor().getUuid());
    }
}
