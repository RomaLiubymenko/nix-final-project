package ua.com.alevel.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.table.user.AdminTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.finance.AccountMapper;
import ua.com.alevel.persistence.entity.user.Admin;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AccountMapper.class
        }
)
public interface AdminMapper extends CommonMapper<Admin, AdminTableDto, AdminProfileDto> {

    @Override
    AdminTableDto toTableDto(Admin admin);

    @Override
    @Mapping(target = "account", qualifiedByName = "forUserProfileDto")
    AdminProfileDto toProfileDto(Admin admin);

    @Override
    @Mapping(target = "account", ignore = true)
    Admin toEntity(AdminProfileDto adminProfileDto);
}
