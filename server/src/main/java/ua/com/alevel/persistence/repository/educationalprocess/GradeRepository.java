package ua.com.alevel.persistence.repository.educationalprocess;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.educationalprocess.Grade;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface GradeRepository extends CommonRepository<Grade> {
}
