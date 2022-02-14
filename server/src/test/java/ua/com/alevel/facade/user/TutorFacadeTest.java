package ua.com.alevel.facade.user;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.dto.filter.user.TutorFilterDto;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.table.user.TutorTableDto;
import ua.com.alevel.enumeration.GenderType;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.user.Tutor;
import ua.com.alevel.service.user.TutorService;
import ua.com.alevel.specification.user.TutorSpecificationBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TutorFacadeTest {

    private static final Logger LOG = LoggerFactory.getLogger(TutorFacadeTest.class);

    @Mock
    TutorService tutorService;

    @Mock
    TutorMapper tutorMapper;

    @Mock
    TutorSpecificationBuilder tutorSpecificationBuilder;

    @InjectMocks
    TutorFacade tutorFacade;

    private UUID uuid;
    private Tutor tutor;
    private TutorProfileDto tutorProfileDto;
    private TutorTableDto tutorTableDto;

    @BeforeAll
    public static void setUp() {
        LOG.info("Start test: {}", TutorFacade.class.getSimpleName());
    }

    @AfterAll
    public static void tearDown() {
        LOG.info("Complete testing: {}", TutorFacade.class.getSimpleName());
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

        tutor = new Tutor();
        tutor.setUsername("Tutor");
        tutor.setFirstName("Tutor");
        tutor.setLastName("Tutor");
        tutor.setEmail("tutor@sd.sd");
        tutor.setGender(GenderType.MALE);
        tutor.setBirthDay(LocalDate.now());
        tutor.setActivated(true);
    }

    @Test
    void shouldCreateTutorAndReturnedProfileDto() {
        Mockito.when(tutorMapper.toEntity(ArgumentMatchers.any(TutorProfileDto.class))).thenReturn(tutor);
        Mockito.when(tutorService.create(ArgumentMatchers.any(Tutor.class))).thenReturn(tutor);
        Mockito.when(tutorMapper.toProfileDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorProfileDto);

        TutorProfileDto actual = tutorFacade.create(tutorProfileDto);

        ArgumentCaptor<Tutor> tutorCaptor = ArgumentCaptor.forClass(Tutor.class);

        Mockito.verify(tutorService, Mockito.times(1)).create(tutorCaptor.capture());
        MatcherAssert.assertThat(tutor, Matchers.equalTo(tutorCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(tutorProfileDto));
    }

    @Test
    void shouldUpdatedTutorAndReturnedProfileDto() {
        Mockito.when(tutorMapper.toEntity(ArgumentMatchers.any(TutorProfileDto.class))).thenReturn(tutor);
        Mockito.when(tutorService.update(ArgumentMatchers.any(Tutor.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(tutor));
        Mockito.when(tutorMapper.toProfileDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorProfileDto);

        Optional<TutorProfileDto> actual = tutorFacade.update(tutorProfileDto, uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Tutor> tutorCaptor = ArgumentCaptor.forClass(Tutor.class);

        Mockito.verify(tutorService, Mockito.times(1)).update(tutorCaptor.capture(), uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(tutor, Matchers.equalTo(tutorCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(tutorProfileDto)));
    }

    @Test
    void shouldDeleteTutorByUuid() {
        Mockito.doNothing().when(tutorService).deleteByUuid(ArgumentMatchers.any(UUID.class));

        tutorFacade.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(tutorService, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldDeleteTutorByUuids() {
        Mockito.doNothing().when(tutorService).deleteByUuids(ArgumentMatchers.anySet());

        tutorFacade.deleteByUuids(Set.of(uuid));

        ArgumentCaptor<Set<UUID>> uuidCaptor = ArgumentCaptor.forClass(Set.class);
        Mockito.verify(tutorService, Mockito.times(1)).deleteByUuids(uuidCaptor.capture());
        MatcherAssert.assertThat(Set.of(uuid), Matchers.equalTo(uuidCaptor.getValue()));
    }

    @Test
    void shouldFindByUuidTutor() {
        Mockito.when(tutorService.findByUuid(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(tutor));
        Mockito.when(tutorMapper.toProfileDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorProfileDto);

        Optional<TutorProfileDto> actual = tutorFacade.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(tutorService, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        MatcherAssert.assertThat(uuid, Matchers.equalTo(uuidCaptor.getValue()));
        MatcherAssert.assertThat(actual, Matchers.equalTo(Optional.of(tutorProfileDto)));
    }

    @Test
    void shouldFindAllTutors() {
        Mockito.when(tutorService.findAll()).thenReturn(List.of(tutor));
        Mockito.when(tutorMapper.toTableDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorTableDto);

        Set<TutorTableDto> actual = tutorFacade.findAll();

        MatcherAssert.assertThat(actual, Matchers.equalTo(Set.of(tutorTableDto)));
    }

    @Test
    void shouldFindAllTutorsByPageable() {
        Page<Tutor> tutorPage = new PageImpl<>(List.of(tutor));
        Page<TutorTableDto> dtoPage = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, tutorPage.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorService.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(tutorPage);
        Mockito.when(tutorMapper.toTableDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorTableDto);

        Page<TutorTableDto> actual = tutorFacade.findAll(pageable);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.verify(tutorService, Mockito.times(1)).findAll(pageableCaptor.capture());

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllTutorsByPageableAndQuery() {
        String query = "tutor";
        Page<Tutor> tutorPage = new PageImpl<>(List.of(tutor));
        Page<TutorTableDto> dtoPage = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, tutorPage.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorSpecificationBuilder.build(ArgumentMatchers.any(String.class))).thenReturn(null);
        Mockito.when(tutorService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(tutorPage);
        Mockito.when(tutorMapper.toTableDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorTableDto);

        Page<TutorTableDto> actual = tutorFacade.findAll(query, pageable);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(tutorService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(tutorSpecificationBuilder, Mockito.times(1)).build(queryCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllTutorsByPageableAndFilter() {
        TutorFilterDto tutorFilterDto = new TutorFilterDto();
        tutorFilterDto.setFirstName("tutor");
        Page<Tutor> tutorPage = new PageImpl<>(List.of(tutor));
        Page<TutorTableDto> dtoPage = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, tutorPage.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorSpecificationBuilder.build(ArgumentMatchers.any(TutorFilterDto.class))).thenReturn(null);
        Mockito.when(tutorService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(tutorPage);
        Mockito.when(tutorMapper.toTableDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorTableDto);

        Page<TutorTableDto> actual = tutorFacade.findAll(tutorFilterDto, pageable);

        ArgumentCaptor<TutorFilterDto> filterCaptor = ArgumentCaptor.forClass(TutorFilterDto.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(tutorService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(tutorSpecificationBuilder, Mockito.times(1)).build(filterCaptor.capture());

        MatcherAssert.assertThat(tutorFilterDto, Matchers.equalTo(filterCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }

    @Test
    void shouldFindAllTutorsByPageableAndFilterAndQuery() {
        String query = "tutor";
        TutorFilterDto tutorFilterDto = new TutorFilterDto();
        tutorFilterDto.setFirstName("tutor");
        Page<Tutor> tutorPage = new PageImpl<>(List.of(tutor));
        Page<TutorTableDto> dtoPage = new PageImpl<>(List.of(tutorTableDto));
        Pageable pageable = PageRequest.of(1, tutorPage.getSize(), Sort.by("created").ascending());

        Mockito.when(tutorSpecificationBuilder.build(
                        ArgumentMatchers.any(String.class),
                        ArgumentMatchers.any(TutorFilterDto.class)))
                .thenReturn(null);

        Mockito.when(tutorService.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(tutorPage);
        Mockito.when(tutorMapper.toTableDto(ArgumentMatchers.any(Tutor.class))).thenReturn(tutorTableDto);

        Page<TutorTableDto> actual = tutorFacade.findAll(query, tutorFilterDto, pageable);

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<TutorFilterDto> filterCaptor = ArgumentCaptor.forClass(TutorFilterDto.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        ArgumentCaptor<Specification> specificationCaptor = ArgumentCaptor.forClass(Specification.class);

        Mockito.verify(tutorService, Mockito.times(1))
                .findAll(specificationCaptor.capture(), pageableCaptor.capture());
        Mockito.verify(tutorSpecificationBuilder, Mockito.times(1)).build(queryCaptor.capture(), filterCaptor.capture());

        MatcherAssert.assertThat(query, Matchers.equalTo(queryCaptor.getValue()));
        MatcherAssert.assertThat(tutorFilterDto, Matchers.equalTo(filterCaptor.getValue()));
        MatcherAssert.assertThat(pageable, Matchers.equalTo(pageableCaptor.getValue()));
        MatcherAssert.assertThat(null, Matchers.equalTo(specificationCaptor.getValue()));

        MatcherAssert.assertThat(actual, Matchers.equalTo(dtoPage));
    }
}
