package ua.com.alevel.facade.user;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.dto.filter.user.StudentFilterDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.service.user.StudentService;
import ua.com.alevel.specification.user.StudentSpecificationBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StudentFacadeTest {

    @Mock
    StudentService studentService;

    @Mock
    StudentMapper studentMapper;

    @Mock
    StudentSpecificationBuilder studentSpecificationBuilder;

    @InjectMocks
    StudentFacade studentFacade;

    private UUID uuid;
    private Student student;
    private StudentProfileDto studentProfileDto;
    private StudentTableDto studentTableDto;

    @BeforeEach
    void setUp() {
        uuid = UUID.fromString("3315439c-66d0-4e59-b88a-8bdef03bc07c");
        studentProfileDto = new StudentProfileDto();
        studentProfileDto.setUsername("Student");
        studentProfileDto.setFirstName("Student");
        studentProfileDto.setLastName("Student");
        studentProfileDto.setEmail("student@sd.sd");
        studentProfileDto.setGender(GenderType.MALE);
        studentProfileDto.setBirthDay(LocalDate.now());
        studentProfileDto.setActivated(true);

        studentTableDto = new StudentTableDto();
        studentTableDto.setFirstName("Student");
        studentTableDto.setLastName("Student");
        studentTableDto.setEmail("student@sd.sd");
        studentTableDto.setGender(GenderType.MALE);
        studentTableDto.setBirthDay(LocalDate.now());
        studentTableDto.setActivated(true);

        student = new Student();
        student.setUsername("Student");
        student.setFirstName("Student");
        student.setLastName("Student");
        student.setEmail("student@sd.sd");
        student.setGender(GenderType.MALE);
        student.setBirthDay(LocalDate.now());
        student.setActivated(true);
    }

    @Test
    void shouldCreateStudentAndReturnedProfileDto() {
        Mockito.when(studentMapper.toEntity(ArgumentMatchers.any(StudentProfileDto.class))).thenReturn(student);
        Mockito.when(studentService.create(ArgumentMatchers.any(Student.class))).thenReturn(student);
        Mockito.when(studentMapper.toProfileDto(ArgumentMatchers.any(Student.class))).thenReturn(studentProfileDto);

        StudentProfileDto actual = studentFacade.create(studentProfileDto);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentService, Mockito.times(1)).create(studentCaptor.capture());
        MatcherAssert.assertThat(student, Matchers.equalTo(studentCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(studentProfileDto));
    }

    @Test
    void shouldUpdatedStudentAndReturnedProfileDto() {
        Mockito.when(studentMapper.toEntity(ArgumentMatchers.any(StudentProfileDto.class))).thenReturn(student);
        Mockito.when(studentService.update(ArgumentMatchers.any(Student.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toProfileDto(ArgumentMatchers.any(Student.class))).thenReturn(studentProfileDto);

        Optional<StudentProfileDto> actual = studentFacade.update(studentProfileDto, uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentService, Mockito.times(1)).update(studentCaptor.capture(), uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(student, Matchers.equalTo(studentCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(studentProfileDto)));
    }

    @Test
    void shouldDeleteStudentByUuid() {
        Mockito.doNothing().when(studentService).deleteByUuid(ArgumentMatchers.any(UUID.class));

        studentFacade.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(studentService, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteStudentByUuids() {
        Mockito.doNothing().when(studentService).deleteByUuids(ArgumentMatchers.anySet());

        studentFacade.deleteByUuids(Set.of(uuid));

        ArgumentCaptor<Set<UUID>> uuidCaptor = ArgumentCaptor.forClass(Set.class);
        Mockito.verify(studentService, Mockito.times(1)).deleteByUuids(uuidCaptor.capture());
        MatcherAssert.assertThat(Set.of(uuid), Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldFindByUuidStudent() {
        Mockito.when(studentService.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toProfileDto(ArgumentMatchers.any(Student.class))).thenReturn(studentProfileDto);

        Optional<StudentProfileDto> actual = studentFacade.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(studentService, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(studentProfileDto)));
    }

    @Test
    void shouldFindAllStudents() {
        Mockito.when(studentService.findAll()).thenReturn(List.of(student));
        Mockito.when(studentMapper.toTableDto(ArgumentMatchers.any(Student.class))).thenReturn(studentTableDto);

        Set<StudentTableDto> actual = studentFacade.findAll();

        MatcherAssert.assertThat(actual, Matchers.equalTo(Set.of(studentTableDto)));
    }

    @Test
    void shouldFindAllStudentsByPageable() {
        Page<Student> studentPage = new PageImpl<>(List.of(student));
        Page<StudentTableDto> dtoPage = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, studentPage.getSize(), Sort.by("created").ascending());

        Mockito.when(studentService.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(studentPage);
        Mockito.when(studentMapper.toTableDto(ArgumentMatchers.any(Student.class))).thenReturn(studentTableDto);

        Page<StudentTableDto> actual = studentFacade.findAll(pageable);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(studentService, Mockito.times(1)).findAll(pageableCaptor.capture());

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllStudentsByPageableAndQuery() {
        String query = "student";
        Page<Student> studentPage = new PageImpl<>(List.of(student));
        Page<StudentTableDto> dtoPage = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, studentPage.getSize(), Sort.by("created").ascending());

        Mockito.when(studentSpecificationBuilder.build(ArgumentMatchers.any(String.class))).thenReturn(null);
        Mockito.when(studentService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(studentPage);
        Mockito.when(studentMapper.toTableDto(ArgumentMatchers.any(Student.class))).thenReturn(studentTableDto);

        Page<StudentTableDto> actual = studentFacade.findAll(query, pageable);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(studentService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(studentSpecificationBuilder, Mockito.times(1)).build(queryCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllStudentsByPageableAndFilter() {
        StudentFilterDto studentFilterDto = new StudentFilterDto();
        studentFilterDto.setFirstName("student");
        Page<Student> studentPage = new PageImpl<>(List.of(student));
        Page<StudentTableDto> dtoPage = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, studentPage.getSize(), Sort.by("created").ascending());

        Mockito.when(studentSpecificationBuilder.build(ArgumentMatchers.any(StudentFilterDto.class))).thenReturn(null);
        Mockito.when(studentService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(studentPage);
        Mockito.when(studentMapper.toTableDto(ArgumentMatchers.any(Student.class))).thenReturn(studentTableDto);

        Page<StudentTableDto> actual = studentFacade.findAll(studentFilterDto, pageable);

        ArgumentCaptor<StudentFilterDto> filterCaptor = ArgumentCaptor.forClass(StudentFilterDto.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(studentService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(studentSpecificationBuilder, Mockito.times(1)).build(filterCaptor.capture());

        MatcherAssert.assertThat(studentFilterDto, Matchers.equalTo(filterCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllStudentsByPageableAndFilterAndQuery() {
        String query = "student";
        StudentFilterDto studentFilterDto = new StudentFilterDto();
        studentFilterDto.setFirstName("student");
        Page<Student> studentPage = new PageImpl<>(List.of(student));
        Page<StudentTableDto> dtoPage = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, studentPage.getSize(), Sort.by("created").ascending());

        Mockito.when(studentSpecificationBuilder.build(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(StudentFilterDto.class)))
                .thenReturn(null);

        Mockito.when(studentService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(studentPage);
        Mockito.when(studentMapper.toTableDto(ArgumentMatchers.any(Student.class))).thenReturn(studentTableDto);

        Page<StudentTableDto> actual = studentFacade.findAll(query, studentFilterDto, pageable);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<StudentFilterDto> filterCaptor = ArgumentCaptor.forClass(StudentFilterDto.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(studentService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(studentSpecificationBuilder, Mockito.times(1)).build(queryCaptor.capture(), filterCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(studentFilterDto, Matchers.equalTo(filterCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }
}
