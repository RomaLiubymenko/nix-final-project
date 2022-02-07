package ua.com.alevel.mapper.user;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.user.TutorProfileDto;
import ua.com.alevel.dto.table.user.TutorTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.educationalprocess.*;
import ua.com.alevel.mapper.finance.AccountMapper;
import ua.com.alevel.mapper.finance.AccountingOfPaymentMapper;
import ua.com.alevel.persistence.entity.user.Tutor;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                ReportMapper.class,
                CourseMapper.class,
                LessonMapper.class,
                SubjectMapper.class,
                ExerciseMapper.class,
                AccountingOfPaymentMapper.class,
                AccountMapper.class
        }
)
public interface TutorMapper extends CommonMapper<Tutor, TutorTableDto, TutorProfileDto> {

    @Override
    TutorTableDto toTableDto(Tutor tutor);

    @Override
    Tutor toEntity(TutorProfileDto tutorProfileDto);

    @Override
    @Mapping(target = "reports", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "courses", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "lessons", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "subjects", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "exercises", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "accountingOfPayments", qualifiedByName = "forTutorProfileDto")
    @Mapping(target = "account", qualifiedByName = "forUserProfileDto")
    TutorProfileDto toProfileDto(Tutor tutor);

    @Named("forAccountingOfPaymentProfileDto")
    @Mapping(target = "accountingOfPayments", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForAccountingOfPaymentProfileDto(Tutor tutor);

    @Named("forCourseProfileDto")
    @Mapping(target = "courses", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForCourseProfileDto(Tutor tutor);

    @Named("forSubjectProfileDto")
    @Mapping(target = "subjects", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForSubjectProfileDto(Tutor tutor);

    @Named("forLessonProfileDto")
    @Mapping(target = "lessons", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForLessonProfileDto(Tutor tutor);

    @Named("forReportProfileDto")
    @Mapping(target = "reports", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForReportProfileDto(Tutor tutor);

    @Named("forExerciseProfileDto")
    @Mapping(target = "exercises", ignore = true)
    TutorProfileDto tutorToTutorProfileDtoForExerciseProfileDto(Tutor tutor);
}
