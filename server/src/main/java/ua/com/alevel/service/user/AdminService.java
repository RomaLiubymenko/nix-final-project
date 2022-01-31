package ua.com.alevel.service.user;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class AdminService extends AbstractService<Admin, AdminRepository> {
    public AdminService(AdminRepository repository, CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper) {
        super(repository, crudRepositoryHelper);
    }
}
