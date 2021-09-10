package livelihoodzone.controller.reports.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.wealthgroup.*;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.service.reports.wealthgroup.WealthGroupReportService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Autowired
    CountiesRepository countiesRepository;

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
            @ApiResponse(code = 422, message = "Invalid parameter data provided")})
    public ResponseEntity<WealthGroupReportResponseHashMapObject> getWealthGroupAggregateResponses(@ApiParam("Questionnaire sections to report on") @RequestBody WealthGroupReportRequestDto wealthGroupReportRequestDto, HttpServletRequest httpServletRequest) {
        WealthGroupReportResponseHashMapObject wealthGroupReportResponseHashMapObject = new WealthGroupReportResponseHashMapObject();


        WgQuestionnaireTypesEntity questionnaireType = wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeId(wealthGroupReportRequestDto.getQuestionnaireTypeId());
        if (questionnaireType == null) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage","Invalid questionnaire type id");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }

        CountiesEntity countiesEntity = countiesRepository.findByCountyId(wealthGroupReportRequestDto.getCountyId());
        if (countiesEntity == null) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage","Invalid county id");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }

        List<WgQuestionnaireDetailsRetrofitModel> questionnairesList = wealthGroupReportService.fetchWealthGroupQuestionnaireDetails(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
        if (questionnairesList.isEmpty()) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage","There is no wealth group " + extractWealthGroupQuestionnaireType(wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeId(wealthGroupReportRequestDto.getQuestionnaireTypeId()).getWgQuestionnaireTypeCode()) + " that has been filled for " + countiesRepository.findByCountyId(wealthGroupReportRequestDto.getCountyId()).getCountyName() + " county");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }


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

        if (wealthGroupReportRequestDto.isLivestockAndPoultryOwnership()) {
            WgLivestockOwnershipDataSetObject wgLivestockOwnershipDataSetObject = wealthGroupReportService.processLivestockOwnership(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("livestockOwnership", wgLivestockOwnershipDataSetObject);
        }

        if (wealthGroupReportRequestDto.isLivestockAndPoultryContributions()) {
            WgAnimalContributionDataSetObject wgAnimalContributionDataSetObject = wealthGroupReportService.processLivestockContributions(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("livestockContribution", wgAnimalContributionDataSetObject);
        }

        if (wealthGroupReportRequestDto.isLabourPatterns()) {
            WgLabourPatternsDataSetObject wgLabourPatternsDataSetObject = wealthGroupReportService.processLabourPatterns(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("labourPatterns", wgLabourPatternsDataSetObject);
        }

        if (wealthGroupReportRequestDto.isExpenditurePatterns()) {
            WgExpenditurePatternsDataSetObject wgExpenditurePatternsDataSetObject = wealthGroupReportService.processExpenditurePatterns(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("expenditurePatterns", wgExpenditurePatternsDataSetObject);
        }

        if (wealthGroupReportRequestDto.isSettlementAndMigration()) {
            WgMigrationPatternsDataSetObject wgMigrationPatternsDataSetObject = wealthGroupReportService.processMigrationPatterns(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("migrationPatterns", wgMigrationPatternsDataSetObject);
        }

        if (wealthGroupReportRequestDto.isConstraintsToEconomicActivities()) {
            WgIncomeConstraintsDataSetObject wgIncomeConstraintsDataSetObject = wealthGroupReportService.processIncomeConstraints(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("incomeConstraints", wgIncomeConstraintsDataSetObject);
        }

        if (wealthGroupReportRequestDto.isFgdParticipants()) {
            WgFgdParticipantsDataSetObject wgFgdParticipantsDataSetObject = wealthGroupReportService.processFgdParticipants(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("fgdParticipants", wgFgdParticipantsDataSetObject);
        }

        if (wealthGroupReportResponseHashMapObject.getReportHashMapObject().isEmpty()) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage","Response returned an empty result");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }


        return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(200));
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

    public String extractWealthGroupQuestionnaireType(int questionnaireTypeCode) {
        return questionnaireTypeCode == 1 ? "summarized questionnaire" : "raw data questionnaire";
    }
}
