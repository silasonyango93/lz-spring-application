package livelihoodzone.controller.reports.zonelevel;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.reports.zonal.ZoneLevelReportRequestDto;
import livelihoodzone.dto.reports.zonal.ZoneLevelReportResponseDto;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupCharectaristicsReportStringObject;
import livelihoodzone.service.reports.zonal.ZoneLevelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zone-level-reports")
@Api(tags = "zone-level-reports")
public class ZoneLevelReportsController {

    @Autowired
    ZoneLevelReportService zoneLevelReportService;

    @GetMapping(value = "/zone-level-report")
    @ApiOperation(value = "${ZoneLevelReports.zone-level-report}", response = ZoneLevelReportResponseDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ZoneLevelReportResponseDto getZoneWealthGroupDistribution(@ApiParam("Questionnaire sections to report on") @RequestBody ZoneLevelReportRequestDto zoneLevelReportRequestDto, HttpServletRequest httpServletRequest) {
        ZoneLevelReportResponseDto zoneLevelReportResponseDto = new ZoneLevelReportResponseDto();

        if (zoneLevelReportRequestDto.isWealthGroupCharacteristics()) {
            WealthGroupCharectaristicsReportStringObject wealthGroupCharacteristicsData = zoneLevelReportService.comprehensivelyFetchWealthGroupCharacteristicsReport();
            zoneLevelReportResponseDto.setReportHashMapObject("wealthGroupCharacteristics",wealthGroupCharacteristicsData);
        }

        return zoneLevelReportResponseDto;
    }
}
