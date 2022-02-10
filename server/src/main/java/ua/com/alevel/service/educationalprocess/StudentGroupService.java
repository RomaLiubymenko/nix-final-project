package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.StudentGroup;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.educationalprocess.StudentGroupRepository;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.AbstractService;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentGroupService extends AbstractService<StudentGroup, StudentGroupRepository> {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;
    private final CrudRepositoryHelper<StudentGroup, StudentGroupRepository> crudRepositoryHelper;

    public StudentGroupService(StudentGroupRepository studentGroupRepository,
                               StudentRepository studentRepository,
                               CrudRepositoryHelper<StudentGroup, StudentGroupRepository> crudRepositoryHelper) {
        super(studentGroupRepository, crudRepositoryHelper);
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public StudentGroup create(StudentGroup studentGroup) {
        Set<Student> students = prepareStudentForSave(studentGroup);
        studentGroup.setStudents(students);
        for (Student student : students) {
            student.addStudentGroup(studentGroup);
        }
        return studentGroupRepository.save(studentGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<StudentGroup> update(StudentGroup studentGroup, UUID uuid) {
        boolean isExistStudentGroup = studentGroupRepository.existsByUuid(uuid);
        if (isExistStudentGroup) {
            StudentGroup studentGroupReturned = studentGroupRepository.findByUuid(uuid).get();
            studentGroup.setId(studentGroupReturned.getId());
            studentGroup.setCourse(studentGroupReturned.getCourse());
            studentGroup.setLessons(studentGroupReturned.getLessons());
            Set<Student> students = prepareStudentForSave(studentGroup);
            studentGroup.setStudents(students);
            for (Student student : students) {
                student.addStudentGroup(studentGroup);
            }
            StudentGroup savedStudentGroup = studentGroupRepository.save(studentGroup);
            return Optional.of(savedStudentGroup);
        }
        return Optional.empty();
    }

    private Set<Student> prepareStudentForSave(StudentGroup studentGroup) {
        return studentGroup.getStudents().stream()
                .filter(Objects::nonNull)
                .map(student -> studentRepository.getByUuid(student.getUuid()))
                .collect(Collectors.toSet());
    }
}
