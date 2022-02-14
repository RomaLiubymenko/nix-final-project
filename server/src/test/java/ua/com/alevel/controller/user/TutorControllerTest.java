package ua.com.alevel.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ua.com.alevel.dto.filter.user.TutorFilterDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.table.user.TutorTableDto;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.facade.user.TutorFacade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TutorController.class)
@AutoConfigureMockMvc(addFilters = false)
class TutorControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(TutorControllerTest.class);

    @MockBean
    TutorFacade tutorFacade;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID uuid;
    private TutorProfileDto tutorProfileDto;
    private TutorTableDto tutorTableDto;

    @BeforeAll
    public static void setUp() {
        LOG.info("Start test: {}", TutorController.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOG.info("Complete testing: {}", TutorController.class.getSimpleName());
    }

    @BeforeEach
    void init() {
        uuid = UUID.fromString("3315439c-66d0-4e59-b88a-8bdef03bc07c");
        tutorProfileDto = new TutorProfileDto();
        tutorProfileDto.setUsername("Tutor");
        tutorProfileDto.setFirstName("Tutor");
        tutorProfileDto.setLastName("Tutor");
        tutorProfileDto.setEmail("tutor@sd.sd");
        tutorProfileDto.setGender(GenderType.MALE);
        tutorProfileDto.setBirthDay(LocalDate.now());
        tutorProfileDto.setActivated(true);

        tutorTableDto = new TutorTableDto();
        tutorTableDto.setFirstName("Tutor");
        tutorTableDto.setLastName("Tutor");
        tutorTableDto.setEmail("tutor@sd.sd");
        tutorTableDto.setGender(GenderType.MALE);
        tutorTableDto.setBirthDay(LocalDate.now());
        tutorTableDto.setActivated(true);
    }

    @Test
    void shouldSaveTutorAndReturnedWithStatus201() throws Exception {
        Mockito.when(tutorFacade.create(Mockito.any(TutorProfileDto.class))).thenReturn(tutorProfileDto);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutorProfileDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<TutorProfileDto> tutorCaptor = ArgumentCaptor.forClass(TutorProfileDto.class);
        Mockito.verify(tutorFacade, Mockito.times(1)).create(tutorCaptor.capture());
        MatcherAssert.assertThat(tutorProfileDto, Matchers.equalTo(tutorCaptor.getValue()));
    }

    @Test
    void shouldTryToSaveTutorWithNotNullUuidAndReturnedWithStatus400() throws Exception {
        tutorProfileDto.setUuid(uuid);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(tutorFacade, Mockito.times(0)).create(ArgumentMatchers.any());
    }

    @Test
    void shouldUpdateTutorAndReturnedWithStatus200() throws Exception {
        tutorProfileDto.setUuid(uuid);
        Mockito.when(tutorFacade.update(ArgumentMatchers.any(TutorProfileDto.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.ofNullable(tutorProfileDto));

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/tutors/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutorProfileDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<TutorProfileDto> tutorCaptor = ArgumentCaptor.forClass(TutorProfileDto.class);

        Mockito.verify(tutorFacade, Mockito.times(1)).update(tutorCaptor.capture(), uuidCaptor.capture());

        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(tutorProfileDto, Matchers.equalTo(tutorCaptor.getValue()));
    }

    @Test
    void shouldTryToUpdateTutorAndReturnedWithStatus404WhenTutorNotExistByUuid() throws Exception {
        tutorProfileDto.setUuid(uuid);
        Mockito.when(tutorFacade.update(ArgumentMatchers.any(TutorProfileDto.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.put("/api/v1/tutors/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tutorProfileDto))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(tutorFacade, Mockito.times(1)).update(ArgumentMatchers.any(TutorProfileDto.class), uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteTutorByUuidAndReturnedWithStatus204() throws Exception {
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.doNothing().when(tutorFacade).deleteByUuid(uuidCaptor.capture());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/tutors/{uuid}", uuid)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteTutorByUuidsAndReturnedWithStatus204() throws Exception {
        ArgumentCaptor<Set<UUID>> uuidsCaptor = ArgumentCaptor.forClass(Set.class);
        Mockito.doNothing().when(tutorFacade).deleteByUuids(uuidsCaptor.capture());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/tutors/uuids")
                        .param("uuid", String.valueOf(uuid))
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        MatcherAssert.assertThat(Set.of(uuid), Matchers.equalTo(uuidsCaptor.getValue()));
    }

    @Test
    void shouldReturnedTutorByUuidWithStatus200() throws Exception {
        tutorProfileDto.setUuid(uuid);
        Mockito.when(tutorFacade.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(tutorProfileDto));

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors/{uuid}", uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutorProfileDto)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(tutorFacade, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldTryToFindTutorByUuidAndReturnedWithStatus404() throws Exception {
        Mockito.when(tutorFacade.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors/{uuid}", uuid))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(tutorFacade, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }


    @Test
    void shouldReturnedListOfTutorsWhenPageableIsNullWithStatus200() throws Exception {
        Set<TutorTableDto> tutors = Set.of(tutorTableDto);
        Mockito.when(tutorFacade.findAll()).thenReturn(tutors);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("pageable", (String) null))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutors)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(tutorFacade, Mockito.times(1)).findAll();
    }

    @Test
    void shouldReturnedPageWithTutorsWhenPageableNotNullWithStatus200() throws Exception {
        Set<TutorTableDto> tutors = Set.of(tutorTableDto);
        Page<TutorTableDto> page = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorFacade.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutors)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        Mockito.verify(tutorFacade, Mockito.times(1)).findAll(pageableCaptor.capture());

        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
    }

    @Test
    void shouldReturnedPageWithTutorsWhenPageableNotNullAndQueryNotBlankWithStatus200() throws Exception {
        String query = "tutor";
        Set<TutorTableDto> tutors = Set.of(tutorTableDto);
        Page<TutorTableDto> page = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorFacade.findAll(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("query", "tutor")
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutors)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(tutorFacade, Mockito.times(1)).findAll(queryCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
    }

    @Test
    void shouldReturnedPageWithTutorsWhenPageableNotNullAndQueryNotBlankAndFilterIsExistWithStatus200() throws Exception {
        String query = "tutor";
        Set<TutorTableDto> tutors = Set.of(tutorTableDto);
        Page<TutorTableDto> page = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, page.getSize(), Sort.by("created").ascending());

        TutorFilterDto tutorFilterDto = new TutorFilterDto();
        tutorFilterDto.setFirstName("tutor");

        Mockito.when(tutorFacade.findAll(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(TutorFilterDto.class),
                        ArgumentMatchers.any(Pageable.class)))
                .thenReturn(page);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tutors")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("firstName", "tutor")
                        .param("query", "tutor")
                        .param("page", "1")
                        .param("size", "1")
                        .param("sort", "created,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "1"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tutors)))
                .andDo(MockMvcResultHandlers.print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<TutorFilterDto> tutorFilterCaptor = ArgumentCaptor.forClass(TutorFilterDto.class);

        Mockito.verify(tutorFacade, Mockito.times(1))
                .findAll(queryCaptor.capture(), tutorFilterCaptor.capture(), pageableCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(tutorFilterDto, Matchers.equalTo(tutorFilterCaptor.getValue()));
    }
}
