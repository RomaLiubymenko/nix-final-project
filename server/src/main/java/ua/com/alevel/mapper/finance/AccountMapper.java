package ua.com.alevel.mapper.finance;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.finance.AccountProfileDto;
import ua.com.alevel.dto.table.finance.AccountTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.UserMapper;
import ua.com.alevel.persistence.entity.finance.Account;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                UserMapper.class
        }
)
public interface AccountMapper extends CommonMapper<Account, AccountTableDto, AccountProfileDto> {

    @Override
    Account toEntity(AccountProfileDto profileDto);

    @Override
    AccountTableDto toTableDto(Account entity);

    @Override
    @Mapping(target = "users", ignore = true)
    AccountProfileDto toProfileDto(Account entity);

    @Named("forUserProfileDto")
    @Mapping(target = "users", ignore = true)
    AccountProfileDto accountToAccountProfileDtoForStudentProfileDto(Account account);
}
