package ua.com.alevel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.AccountReplenishmentProfileDto;
import ua.com.alevel.dto.table.finance.AccountReplenishmentTableDto;
import ua.com.alevel.persistence.entity.finance.AccountReplenishment;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountReplenishmentMapper extends CommonMapper<AccountReplenishment, AccountReplenishmentTableDto, AccountReplenishmentProfileDto> {

    @Override
    AccountReplenishment toEntity(AccountReplenishmentProfileDto profileDto);

    @Override
    AccountReplenishmentTableDto toTableDto(AccountReplenishment entity);

    @Override
    AccountReplenishmentProfileDto toProfileDto(AccountReplenishment entity);

    // For student
    @Named("forStudentProfileDto")
    @Mapping(target = "student", ignore = true)
    AccountReplenishmentProfileDto accountReplenishmentToAccountReplenishmentProfileDtoForStudentProfileDto(AccountReplenishment accountReplenishment);

}
