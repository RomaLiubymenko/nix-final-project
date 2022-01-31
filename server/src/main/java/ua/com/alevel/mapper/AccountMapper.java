package ua.com.alevel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.AccountProfileDto;
import ua.com.alevel.persistence.entity.finance.Account;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountMapper {

    Account accountProfileDtoToAccount(AccountProfileDto accountProfileDto);

    // For users
    @Named("forUserProfileDto")
    @Mapping(target = "users", ignore = true)
    AccountProfileDto accountToAccountProfileDtoForStudentProfileDto(Account account);

}
