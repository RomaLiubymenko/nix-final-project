package ua.com.alevel.facade.user;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.AdminFilterDto;
import ua.com.alevel.dto.profile.user.AdminProfileDto;
import ua.com.alevel.dto.table.user.AdminTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.user.AdminMapper;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.service.user.AdminService;
import ua.com.alevel.specification.user.AdminSpecificationBuilder;

@Service
public class AdminFacade extends AbstractFacade<Admin, AdminFilterDto, AdminTableDto, AdminProfileDto> {

    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final AdminSpecificationBuilder adminSpecificationBuilder;

    public AdminFacade(AdminService adminService,
                       AdminMapper adminMapper,
                       AdminSpecificationBuilder adminSpecificationBuilder) {
        super(adminService, adminMapper, adminSpecificationBuilder);
        this.adminService = adminService;
        this.adminMapper = adminMapper;
        this.adminSpecificationBuilder = adminSpecificationBuilder;
    }
}
