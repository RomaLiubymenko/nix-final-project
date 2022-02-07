package ua.com.alevel.service.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.educationalprocess.Report;
import ua.com.alevel.persistence.repository.educationalprocess.ReportRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class ReportService extends AbstractService<Report, ReportRepository> {

    private final ReportRepository reportRepository;
    private final CrudRepositoryHelper<Report, ReportRepository> crudRepositoryHelper;

    public ReportService(ReportRepository reportRepository, CrudRepositoryHelper<Report, ReportRepository> crudRepositoryHelper) {
        super(reportRepository, crudRepositoryHelper);
        this.reportRepository = reportRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
