package ua.com.alevel.facade.user;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.UserFilterDto;
import ua.com.alevel.dto.profile.user.UserProfileDto;
import ua.com.alevel.dto.table.user.UserTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.user.UserMapper;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.user.UserService;
import ua.com.alevel.specification.user.UserSpecificationBuilder;

import java.util.Optional;

@Service
public class UserFacade extends AbstractFacade<User, UserFilterDto, UserTableDto, UserProfileDto> {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserSpecificationBuilder userSpecificationBuilder;

    protected UserFacade(
            UserService userService,
            UserMapper userMapper,
            UserSpecificationBuilder userSpecificationBuilder) {
        super(userService, userMapper, userSpecificationBuilder);
        this.userService = userService;
        this.userMapper = userMapper;
        this.userSpecificationBuilder = userSpecificationBuilder;
    }

    public Optional<UserProfileDto> findByUsername(String username) {
        return userService.findByUsername(username)
                .map(userMapper::toProfileDto);
    }

}
