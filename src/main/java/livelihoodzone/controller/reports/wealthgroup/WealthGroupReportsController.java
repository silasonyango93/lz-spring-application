package livelihoodzone.controller.reports.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.CountyDataCollectionProgressReport;
import livelihoodzone.dto.questionnaire.county.CountyRequestDto;
import livelihoodzone.dto.reports.wealthgroup.*;
import livelihoodzone.dto.reports.wealthgroup.charts.WealthGroupChartsRequestDto;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivestockOwnershipChartRequestDto;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.AnimalsEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.service.reports.wealthgroup.WealthGroupChartsService;
import livelihoodzone.service.reports.wealthgroup.WealthGroupReportService;
import livelihoodzone.service.reports.wealthgroup.animal_ownership.AnimalOwnershipService;
import livelihoodzone.service.reports.wealthgroup.excel.WealthGroupExcelService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionExcelExporterService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WealthGroupExcelService wealthGroupExcelService;

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


    @PostMapping("/charts")
    @ApiOperation(value = "${WealthGroupReports.charts}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getWealthGroupChartsData(@ApiParam("Wealth group charts") @RequestBody WealthGroupChartsRequestDto wealthGroupChartsRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(wealthGroupChartsRequestDto.getCountyId(),wealthGroupChartsRequestDto.getWealthGroupId(),wealthGroupChartsRequestDto.getQuestionnaireSectionCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/wealth-group-categories")
    @ApiOperation(value = "${WealthGroupReports.wealth-group-categories}", response = WealthGroupEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<WealthGroupEntity>> getAllWealthGroups() {
        return new ResponseEntity<List<WealthGroupEntity>>(wealthGroupRepository.findAll(), HttpStatus.valueOf(200));
    }


    @PostMapping("/charts/livestock-ownership-by-livestock")
    @ApiOperation(value = "${WealthGroupReports.livestock-ownership-by-livestock}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getLivestockOwnershipChartsData(@ApiParam("Livestock Ownership charts") @RequestBody WgLivestockOwnershipChartRequestDto wgLivestockOwnershipChartRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.livestockOwnershipChartByLivestock(wgLivestockOwnershipChartRequestDto.getCountyId(),wgLivestockOwnershipChartRequestDto.getWealthGroupId(),wgLivestockOwnershipChartRequestDto.getLivestockCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/all-livestock-types")
    @ApiOperation(value = "${WealthGroupReports.all-livestock-types}", response = AnimalsEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<AnimalsEntity>> getAllLivestockTypes() {
        return new ResponseEntity<List<AnimalsEntity>>(animalsRepository.findAll(), HttpStatus.valueOf(200));
    }


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("countyId") int countyId, @RequestParam("wealthGroupId") int wealthGroupId) throws IOException {

        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            wealthGroupExcelService.export(response,countyId,wealthGroupId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
