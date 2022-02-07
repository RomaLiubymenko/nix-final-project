package ua.com.alevel.specification.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.user.AdminFilterDto;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.specification.SpecificationBuilder;

@Service
public class AdminSpecificationBuilder implements SpecificationBuilder<Admin, AdminFilterDto> {

    @Override
    public Specification<Admin> build(String query) {
        return null;
    }

    @Override
    public Specification<Admin> build(AdminFilterDto filterDto) {
        return null;
    }

    @Override
    public Specification<Admin> build(String query, AdminFilterDto filterDto) {
        return null;
    }
}
