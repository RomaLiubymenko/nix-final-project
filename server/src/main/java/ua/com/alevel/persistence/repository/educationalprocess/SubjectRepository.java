package ua.com.alevel.persistence.repository.educationalprocess;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.educationalprocess.Subject;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface SubjectRepository extends CommonRepository<Subject> {
}
