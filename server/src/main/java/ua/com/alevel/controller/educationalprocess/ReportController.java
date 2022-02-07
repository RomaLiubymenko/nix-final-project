package ua.com.alevel.controller.educationalprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.educationalprocess.ReportFilterDto;
import ua.com.alevel.dto.profile.educationalprocess.ReportProfileDto;
import ua.com.alevel.dto.table.educationalprocess.ReportTableDto;
import ua.com.alevel.facade.educationalprocess.ReportFacade;

@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController extends AbstractController<ReportTableDto, ReportProfileDto, ReportFilterDto> {

    private static final String ENTITY_NAME = "report";
    private static final String URL = "/api/v1/reports";
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportFacade reportFacade;

    public ReportController(ReportFacade reportFacade) {
        super(URL, ENTITY_NAME, logger, reportFacade);
        this.reportFacade = reportFacade;
    }
}
