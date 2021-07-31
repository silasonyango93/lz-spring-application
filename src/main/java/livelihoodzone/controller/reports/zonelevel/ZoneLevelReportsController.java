package livelihoodzone.controller.reports.zonelevel;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.zonal.ZoneLevelReportDto;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupCharectaristicsReportStringObject;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.service.reports.zonal.ZoneLevelReportService;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupCharacteristicsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zone-level-reports")
@Api(tags = "zone-level-reports")
public class ZoneLevelReportsController {

    @Autowired
    ZoneLevelReportService zoneLevelReportService;

    @GetMapping(value = "/zone-level-report")
    @ApiOperation(value = "${ZoneLevelReports.zone-level-report}", response = ZoneLevelReportDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ZoneLevelReportDto getZoneWealthGroupDistribution() {
        ZoneLevelReportDto zoneLevelReportDto = new ZoneLevelReportDto();
        WealthGroupCharectaristicsReportStringObject wealthGroupCharacteristicsData = zoneLevelReportService.comprehensivelyFetchWealthGroupCharacteristicsReport();
        zoneLevelReportDto.setReportHashMapObject("wealthGroupCharacteristics",wealthGroupCharacteristicsData);
        return zoneLevelReportDto;
    }
}
