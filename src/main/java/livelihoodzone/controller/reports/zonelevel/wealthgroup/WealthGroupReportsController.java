package livelihoodzone.controller.reports.zonelevel.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.wealthgroup.WealthGroupReportRequestDto;
import livelihoodzone.dto.reports.wealthgroup.WealthGroupReportResponseHashMapObject;
import livelihoodzone.dto.reports.wealthgroup.WgQuestionnaireDetailsResponseObjectDto;
import livelihoodzone.dto.reports.zonal.ZoneLevelReportRequestDto;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.service.reports.wealthgroup.WealthGroupReportService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wealthgroup-reports")
@Api(tags = "wealthgroup-reports")
public class WealthGroupReportsController {

    @Autowired
    LzWealthGroupDistributionReportsService lzWealthGroupDistributionReportsService;

    @Autowired
    WealthGroupReportService wealthGroupReportService;

    @GetMapping(value = "/zone-wealthgroup-distribution")
    @ApiOperation(value = "${WealthGroupReports.wealthgroup-distribution}", response = WealthGroupReportResponseDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ResponseEntity<WealthGroupReportResponseDto> getZoneWealthGroupDistribution(@RequestParam("countyId") int countyId, @RequestParam("livelihoodZoneId") int livelihoodZoneId) {
        WealthGroupReportResponseDto reportResponse = lzWealthGroupDistributionReportsService.retrieveWealthGroupPopulationPercentageReportByCountyAndLivelihoodZone(countyId, livelihoodZoneId);

        return new ResponseEntity<WealthGroupReportResponseDto>(reportResponse, HttpStatus.valueOf(200));
    }


    @PostMapping(value = "/wealthgroup-aggregate-responses")
    @ApiOperation(value = "${WealthGroupReports.wealthgroup-aggregate-responses}", response = WealthGroupReportResponseDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public WealthGroupReportResponseHashMapObject getWealthGroupAggregateResponses(@ApiParam("Questionnaire sections to report on") @RequestBody WealthGroupReportRequestDto wealthGroupReportRequestDto, HttpServletRequest httpServletRequest) {
        WealthGroupReportResponseHashMapObject wealthGroupReportResponseHashMapObject = new WealthGroupReportResponseHashMapObject();

        if (wealthGroupReportRequestDto.isQuestionnaireDetails()) {
            WgQuestionnaireDetailsResponseObjectDto wgQuestionnaireDetailsResponseObjectDto = wealthGroupReportService.processQuestionnaireDetails(wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("questionnaireDetails", wgQuestionnaireDetailsResponseObjectDto);
        }

        return wealthGroupReportResponseHashMapObject;
    }
}
