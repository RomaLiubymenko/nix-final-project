package ua.com.alevel.specification.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.UserFilterDto;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.specification.SpecificationBuilder;

@Service
public class UserSpecificationBuilder implements SpecificationBuilder<User, UserFilterDto> {

    @Override
    public Specification<User> build(String query) {
        return null;
    }

    @Override
    public Specification<User> build(UserFilterDto filterDto) {
        return null;
    }

    @Override
    public Specification<User> build(String query, UserFilterDto filterDto) {
        return null;
    }
}
