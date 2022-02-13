package ua.com.alevel.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.com.alevel.dto.filter.user.StudentFilterDto;
import ua.com.alevel.dto.profile.user.StudentProfileDto;
import ua.com.alevel.dto.table.user.StudentTableDto;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.facade.user.StudentFacade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @MockBean
    StudentFacade studentFacade;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID uuid;
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
    }

    @Test
    void shouldSaveStudentAndReturnedWithStatus201() throws Exception {
        Mockito.when(studentFacade.create(Mockito.any(StudentProfileDto.class))).thenReturn(studentProfileDto);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(studentProfileDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<StudentProfileDto> studentCaptor = ArgumentCaptor.forClass(StudentProfileDto.class);
        Mockito.verify(studentFacade, Mockito.times(1)).create(studentCaptor.capture());
        MatcherAssert.assertThat(studentProfileDto, Matchers.equalTo(studentCaptor.getValue()));
    }

    @Test
    void shouldTryToSaveStudentWithNotNullUuidAndReturnedWithStatus400() throws Exception {
        studentProfileDto.setUuid(uuid);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(studentFacade, Mockito.times(0)).create(ArgumentMatchers.any());
    }

    @Test
    void shouldUpdateStudentAndReturnedWithStatus200() throws Exception {
        studentProfileDto.setUuid(uuid);
        Mockito.when(studentFacade.update(ArgumentMatchers.any(StudentProfileDto.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.ofNullable(studentProfileDto));

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/students/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(studentProfileDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<StudentProfileDto> studentCaptor = ArgumentCaptor.forClass(StudentProfileDto.class);

        Mockito.verify(studentFacade, Mockito.times(1)).update(studentCaptor.capture(), uuidCaptor.capture());

        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(studentProfileDto, Matchers.equalTo(studentCaptor.getValue()));
    }

    @Test
    void shouldTryToUpdateStudentAndReturnedWithStatus404WhenStudentNotExistByUuid() throws Exception {
        studentProfileDto.setUuid(uuid);
        Mockito.when(studentFacade.update(ArgumentMatchers.any(StudentProfileDto.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/students/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(studentFacade, Mockito.times(1)).update(ArgumentMatchers.any(StudentProfileDto.class), uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteStudentByUuidAndReturnedWithStatus204() throws Exception {
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.doNothing().when(studentFacade).deleteByUuid(uuidCaptor.capture());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/{uuid}", uuid)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteStudentByUuidsAndReturnedWithStatus204() throws Exception {
        ArgumentCaptor<Set<UUID>> uuidsCaptor = ArgumentCaptor.forClass(Set.class);
        Mockito.doNothing().when(studentFacade).deleteByUuids(uuidsCaptor.capture());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/uuids")
                        .param("uuid", String.valueOf(uuid))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        MatcherAssert.assertThat(Set.of(uuid), Matchers.equalTo(uuidsCaptor.getValue()));
    }

    @Test
    void shouldReturnedStudentByUuidWithStatus200() throws Exception {
        studentProfileDto.setUuid(uuid);
        Mockito.when(studentFacade.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(studentProfileDto));

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students/{uuid}", uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(studentProfileDto)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(studentFacade, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldTryToFindStudentByUuidAndReturnedWithStatus404() throws Exception {
        Mockito.when(studentFacade.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students/{uuid}", uuid))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(studentFacade, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }


    @Test
    void shouldReturnedListOfStudentsWhenPageableIsNullWithStatus200() throws Exception {
        Set<StudentTableDto> students = Set.of(studentTableDto);
        Mockito.when(studentFacade.findAll()).thenReturn(students);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("pageable", (String) null))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(students)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(studentFacade, Mockito.times(1)).findAll();
    }

    @Test
    void shouldReturnedPageWithStudentsWhenPageableNotNullWithStatus200() throws Exception {
        Set<StudentTableDto> students = Set.of(studentTableDto);
        Page<StudentTableDto> page = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(studentFacade.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(students)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        Mockito.verify(studentFacade, Mockito.times(1)).findAll(pageableCaptor.capture());

        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
    }

    @Test
    void shouldReturnedPageWithStudentsWhenPageableNotNullAndQueryNotBlankWithStatus200() throws Exception {
        String query = "student";
        Set<StudentTableDto> students = Set.of(studentTableDto);
        Page<StudentTableDto> page = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(studentFacade.findAll(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("query", "student")
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(students)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(studentFacade, Mockito.times(1)).findAll(queryCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
    }

    @Test
    void shouldReturnedPageWithStudentsWhenPageableNotNullAndQueryNotBlankAndFilterIsExistWithStatus200() throws Exception {
        String query = "student";
        Set<StudentTableDto> students = Set.of(studentTableDto);
        Page<StudentTableDto> page = new PageImpl<>(List.of(studentTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        StudentFilterDto studentFilterDto = new StudentFilterDto();
        studentFilterDto.setFirstName("student");

        Mockito.when(studentFacade.findAll(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(StudentFilterDto.class),
                        ArgumentMatchers.any(Pageable.class)))
                .thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("firstName", "student")
                        .param("query", "student")
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(students)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<StudentFilterDto> studentFilterCaptor = ArgumentCaptor.forClass(StudentFilterDto.class);

        Mockito.verify(studentFacade, Mockito.times(1))
                .findAll(queryCaptor.capture(), studentFilterCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(studentFilterDto, Matchers.equalTo(studentFilterCaptor.getValue()));
    }
}
