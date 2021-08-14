package livelihoodzone.controller.reports.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.wealthgroup.*;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.service.reports.wealthgroup.WealthGroupReportService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wealthgroup-reports")
@Api(tags = "wealthgroup-reports")
public class WealthGroupReportsController {

    @Autowired
    LzWealthGroupDistributionReportsService lzWealthGroupDistributionReportsService;

    @Autowired
    WealthGroupReportService wealthGroupReportService;


    @Autowired
    WgQuestionnaireTypesRepository wgQuestionnaireTypesRepository;

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
            WgQuestionnaireDetailsResponseObjectDto wgQuestionnaireDetailsResponseObjectDto = wealthGroupReportService.processQuestionnaireDetails(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("questionnaireDetails", wgQuestionnaireDetailsResponseObjectDto);
        }
        if (wealthGroupReportRequestDto.isSourcesOfIncome()) {
            WgIncomeSourcesReportResponseDto wgIncomeSourcesReportResponseDto = wealthGroupReportService.processIncomeSourcesIntegratedData(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("incomeSources", wgIncomeSourcesReportResponseDto);
        }

        if (wealthGroupReportRequestDto.isSourcesOfFood()) {
            WgFoodSourcesDataSetReponseDto wgFoodSourcesDataSetReponseDto = wealthGroupReportService.processFoodSourcesAggregatedResponses(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("foodSources", wgFoodSourcesDataSetReponseDto);
        }

        if (wealthGroupReportRequestDto.isCropProduction()) {
            WgCropContributionReportResponseObject wgCropContributionReportResponseObject = wealthGroupReportService.processCropContribution(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("cropProduction", wgCropContributionReportResponseObject);
        }

        return wealthGroupReportResponseHashMapObject;
    }


    @GetMapping(value = "/wealth-group-questionnaire-types")
    @ApiOperation(value = "${WealthGroupReports.wealth-group-questionnaire-types}", response = WgQuestionnaireTypesEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ResponseEntity<List<WgQuestionnaireTypesEntity>> fetchWealthGroupQuestionnaireTypes() {
        return new ResponseEntity<List<WgQuestionnaireTypesEntity>>(wgQuestionnaireTypesRepository.findAll(), HttpStatus.valueOf(200));
    }
}
