package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.*;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.persistence.repository.educationalprocess.LessonRepository;
import ua.com.alevel.persistence.repository.educationalprocess.SubjectRepository;
import ua.com.alevel.persistence.repository.educationalprocess.TopicRepository;
import ua.com.alevel.persistence.repository.user.TutorRepository;
import ua.com.alevel.service.AbstractService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SubjectService extends AbstractService<Subject, SubjectRepository> {

    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final TopicRepository topicRepository;
    private final TutorRepository tutorRepository;
    private final CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper;

    public SubjectService(SubjectRepository subjectRepository,
                          LessonRepository lessonRepository,
                          TopicRepository topicRepository,
                          TutorRepository tutorRepository,
                          CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper) {
        super(subjectRepository, crudRepositoryHelper);
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.topicRepository = topicRepository;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteByUuids(Set<UUID> uuids) {
        Set<Subject> subjects = subjectRepository.findByUuidIn(uuids);
        Set<Lesson> lessons = lessonRepository.findBySubjectIn(subjects);
        for (Subject subject : subjects) {
            for (Course course : new HashSet<>(subject.getCourses())) {
                subject.removeCourse(course);
            }
        }
        for (Lesson lesson : lessons) {
            for (StudentGroup studentGroup : new HashSet<>(lesson.getStudentGroups())) {
                lesson.removeStudentGroup(studentGroup);
            }
            for (Student student : new HashSet<>(lesson.getStudents())) {
                lesson.removeStudent(student);
            }
            for (Topic topic : new HashSet<>(lesson.getTopics())) {
                lesson.removeTopic(topic);
            }
            for (Tutor tutor : new HashSet<>(lesson.getTutors())) {
                lesson.removeTutor(tutor);
            }
        }
        subjectRepository.deleteByUuidIn(uuids);
    }

    private Tutor prepareTutorToSave(Subject subject) {
        return tutorRepository.getByUuid(subject.getTutor().getUuid());
    }
}
