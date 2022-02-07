package ua.com.alevel.facade.educationalprocess;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.educationalprocess.ReportFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.ReportProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ReportTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.educationalprocess.ReportMapper;
import ua.com.alevel.persistence.entity.educationalprocess.Report;
import ua.com.alevel.service.educationalprocess.ReportService;
import ua.com.alevel.specification.educationalprocess.ReportSpecificationBuilder;

@Service
public class ReportFacade extends AbstractFacade<Report, ReportFilterDto, ReportTableDto, ReportProfileDto> {

    private final ReportService reportService;
    private final ReportMapper reportMapper;
    private final ReportSpecificationBuilder reportSpecificationBuilder;

    public ReportFacade(ReportService reportService,
                        ReportMapper reportMapper,
                        ReportSpecificationBuilder reportSpecificationBuilder) {
        super(reportService, reportMapper, reportSpecificationBuilder);
        this.reportService = reportService;
        this.reportMapper = reportMapper;
        this.reportSpecificationBuilder = reportSpecificationBuilder;
    }
}
