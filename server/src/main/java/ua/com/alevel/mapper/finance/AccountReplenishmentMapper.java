package ua.com.alevel.mapper.finance;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.finance.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.table.finance.AccountReplenishmentTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.StudentMapper;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                StudentMapper.class
        }
)
public interface AccountReplenishmentMapper extends CommonMapper<AccountReplenishment, AccountReplenishmentTableDto, AccountReplenishmentProfileDto> {

    @Override
    AccountReplenishment toEntity(AccountReplenishmentProfileDto profileDto);

    @Override
    AccountReplenishmentTableDto toTableDto(AccountReplenishment entity);

    @Override
    @Mapping(target = "student", qualifiedByName = "forAccountReplenishmentProfileDto")
    AccountReplenishmentProfileDto toProfileDto(AccountReplenishment entity);

    @Named("forStudentProfileDto")
    @Mapping(target = "student", ignore = true)
    AccountReplenishmentProfileDto accountReplenishmentToAccountReplenishmentProfileDtoForStudentProfileDto(AccountReplenishment accountReplenishment);
}
