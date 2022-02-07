package ua.com.alevel.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.RoleRepository;
import ua.com.alevel.service.AbstractService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AdminService extends AbstractService<Admin, AdminRepository> {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper;


    public AdminService(AdminRepository adminRepository,
                        AccountRepository accountRepository,
                        RoleRepository roleRepository,
                        CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper) {
        super(adminRepository, crudRepositoryHelper);
        this.adminRepository = adminRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Admin create(Admin admin) {
        Account account = createAccountForAdmin(admin);
        admin.setAccount(account);
        admin.setRole(roleRepository.findByName("ADMIN"));
        return crudRepositoryHelper.create(adminRepository, admin);
    }

    private Account createAccountForAdmin(Admin admin) {
        Account account = new Account();
        account.setName("Admin account");
        account.setDescription("Account " + admin.getFirstName() + " "+ admin.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }
}
