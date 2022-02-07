package ua.com.alevel.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.dto.table.user.UserTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.finance.AccountMapper;
import ua.com.alevel.persistence.entity.user.User;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AccountMapper.class
        }
)
public interface UserMapper extends CommonMapper<User, UserTableDto, UserProfileDto> {

    @Override
    UserTableDto toTableDto(User user);

    @Override
    @Mapping(target = "account", ignore = true)
    UserProfileDto toProfileDto(User user);

    @Override
    @Mapping(target = "account", ignore = true)
    User toEntity(UserProfileDto userProfileDto);
}
