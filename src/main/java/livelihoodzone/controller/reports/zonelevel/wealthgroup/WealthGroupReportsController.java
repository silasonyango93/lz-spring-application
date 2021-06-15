package livelihoodzone.controller.reports.zonelevel.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.service.reports.zonal.wealthgroup.WealthGroupReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wealthgroup-reports")
@Api(tags = "wealthgroup-reports")
public class WealthGroupReportsController {

    @Autowired
    WealthGroupReportsService wealthGroupReportsService;

    @GetMapping(value = "/zone-wealthgroup-distribution")
    @ApiOperation(value = "${WealthGroupReports.wealthgroup-distribution}", response = WealthGroupReportResponseDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ResponseEntity<WealthGroupReportResponseDto> getZoneWealthGroupDistribution(@RequestParam("countyId") int countyId, @RequestParam("livelihoodZoneId") int livelihoodZoneId) {
        WealthGroupReportResponseDto reportResponse = wealthGroupReportsService.retrieveWealthGroupPopulationPercentageReportByCountyAndLivelihoodZone(countyId, livelihoodZoneId);

        if (reportResponse == null) {
            return new ResponseEntity<WealthGroupReportResponseDto>(reportResponse, HttpStatus.valueOf(422));
        }

        return new ResponseEntity<WealthGroupReportResponseDto>(reportResponse, HttpStatus.valueOf(200));
    }
}
