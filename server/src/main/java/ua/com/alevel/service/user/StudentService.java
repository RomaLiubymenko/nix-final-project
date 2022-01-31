package ua.com.alevel.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.finance.Account;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.finance.AccountRepository;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.AbstractService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StudentService extends AbstractService<Student, StudentRepository> {

    private final StudentRepository repository;
    private final AccountRepository accountRepository;
    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;

    public StudentService(
            StudentRepository repository,
            AccountRepository accountRepository,
            CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper) {
        super(repository, crudRepositoryHelper);
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Student create(Student student) {
        Account account = createAccountForStudent(student);
        student.setAccount(account);
        return crudRepositoryHelper.create(repository, student);
    }

    private Account createAccountForStudent(Student student) {
        Account account = new Account();
        account.setName("Student account");
        account.setDescription("Account " + student.getFirstName() + student.getLastName());
        account.setAccountChangedDate(LocalDateTime.now());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }
}
