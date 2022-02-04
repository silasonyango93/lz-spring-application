package livelihoodzone.controller.reports.wealthgroup;

import io.swagger.annotations.*;
import livelihoodzone.dto.reports.wealthgroup.*;
import livelihoodzone.dto.reports.wealthgroup.charts.*;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.WgQuestionnaireSectionsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.AnimalsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.MigrationPatternsEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.WgQuestionnaireSectionsRepository;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.MigrationPatternsRepository;
import livelihoodzone.service.reports.wealthgroup.WealthGroupChartsService;
import livelihoodzone.service.reports.wealthgroup.WealthGroupReportService;
import livelihoodzone.service.reports.wealthgroup.excel.WealthGroupExcelService;
import livelihoodzone.service.reports.wealthgroup.excel.WgMapExcelService;
import livelihoodzone.service.reports.wealthgroup.excel.country_files.WgCountryFileExcelService;
import livelihoodzone.service.reports.wealthgroup.excel.wealth_group_comparison.WealthGroupComparisonExcelService;
import livelihoodzone.service.reports.wealthgroup.quality_checks.QualityChecksService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static livelihoodzone.common.Constants.*;

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

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    @Autowired
    MigrationPatternsRepository migrationPatternsRepository;

    @Autowired
    WgMapExcelService wgMapExcelService;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    WealthGroupComparisonExcelService wealthGroupComparisonExcelService;

    @Autowired
    WgQuestionnaireSectionsRepository wgQuestionnaireSectionsRepository;

    @Autowired
    WgCountryFileExcelService wgCountryFileExcelService;

    @Autowired
    QualityChecksService qualityChecksService;

    @Autowired
    CropsRepository cropsRepository;

    @GetMapping(value = "/zone-wealthgroup-distribution")
    @ApiOperation(value = "${WealthGroupReports.wealthgroup-distribution}", response = WealthGroupReportResponseDto.class, authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ResponseEntity<WealthGroupReportResponseDto> getZoneWealthGroupDistribution(@RequestParam("countyId") int countyId, @RequestParam("livelihoodZoneId") int livelihoodZoneId) {
        WealthGroupReportResponseDto reportResponse = lzWealthGroupDistributionReportsService.retrieveWealthGroupPopulationPercentageReportByCountyAndLivelihoodZone(countyId, livelihoodZoneId);

        return new ResponseEntity<WealthGroupReportResponseDto>(reportResponse, HttpStatus.valueOf(200));
    }


    @PostMapping(value = "/wealthgroup-aggregate-responses")
    @ApiOperation(value = "${WealthGroupReports.wealthgroup-aggregate-responses}", response = WealthGroupReportResponseDto.class, authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Invalid parameter data provided")})
    public ResponseEntity<WealthGroupReportResponseHashMapObject> getWealthGroupAggregateResponses(@ApiParam("Questionnaire sections to report on") @RequestBody WealthGroupReportRequestDto wealthGroupReportRequestDto, HttpServletRequest httpServletRequest) {
        WealthGroupReportResponseHashMapObject wealthGroupReportResponseHashMapObject = new WealthGroupReportResponseHashMapObject();


        WgQuestionnaireTypesEntity questionnaireType = wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeId(wealthGroupReportRequestDto.getQuestionnaireTypeId());
        if (questionnaireType == null) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage", "Invalid questionnaire type id");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }

        CountiesEntity countiesEntity = countiesRepository.findByCountyId(wealthGroupReportRequestDto.getCountyId());
        if (countiesEntity == null) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage", "Invalid county id");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }

        List<WgQuestionnaireDetailsRetrofitModel> questionnairesList = wealthGroupReportService.fetchWealthGroupQuestionnaireDetails(wealthGroupReportRequestDto.getCountyId(), wealthGroupReportRequestDto.getQuestionnaireTypeId());
        if (questionnairesList.isEmpty()) {
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage", "There is no wealth group " + extractWealthGroupQuestionnaireType(wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeId(wealthGroupReportRequestDto.getQuestionnaireTypeId()).getWgQuestionnaireTypeCode()) + " that has been filled for " + countiesRepository.findByCountyId(wealthGroupReportRequestDto.getCountyId()).getCountyName() + " county");
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
            wealthGroupReportResponseHashMapObject.setReportHashMapObject("errorMessage", "Response returned an empty result");
            return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(422));
        }


        return new ResponseEntity<WealthGroupReportResponseHashMapObject>(wealthGroupReportResponseHashMapObject, HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/wealth-group-questionnaire-types")
    @ApiOperation(value = "${WealthGroupReports.wealth-group-questionnaire-types}", response = WgQuestionnaireTypesEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
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
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(wealthGroupChartsRequestDto.getCountyId(), wealthGroupChartsRequestDto.getWealthGroupId(), wealthGroupChartsRequestDto.getQuestionnaireSectionCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/charts/byLivelihoodzone")
    @ApiOperation(value = "${WealthGroupReports.charts}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getWealthGroupChartsData(@ApiParam("Wealth group charts by livelihood zone") @RequestBody WealthGroupChartsByLivelihoodZoneRequestDto wealthGroupChartsByLivelihoodZoneRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChartByLivelihoodZone(wealthGroupChartsByLivelihoodZoneRequestDto.getCountyId(), wealthGroupChartsByLivelihoodZoneRequestDto.getLivelihoodZoneId(), wealthGroupChartsByLivelihoodZoneRequestDto.getQuestionnaireSectionCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/wealth-group-categories")
    @ApiOperation(value = "${WealthGroupReports.wealth-group-categories}", response = WealthGroupEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
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
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.livestockOwnershipChartByLivestock(wgLivestockOwnershipChartRequestDto.getCountyId(), wgLivestockOwnershipChartRequestDto.getWealthGroupId(), wgLivestockOwnershipChartRequestDto.getLivestockCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/all-livestock-types")
    @ApiOperation(value = "${WealthGroupReports.all-livestock-types}", response = AnimalsEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<AnimalsEntity>> getAllLivestockTypes() {
        return new ResponseEntity<List<AnimalsEntity>>(animalsRepository.findAll(), HttpStatus.valueOf(200));
    }


    @PostMapping("/maps/income-sources-map-data")
    @ApiOperation(value = "${WealthGroupReports.income-sources-map-data}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getIncomeSourcesMapData(@ApiParam("Livestock Ownership charts") @RequestBody WgIncomeSourcesMapRequestDto wgIncomeSourcesMapRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.mainSourcesOfIncomeAndFoodMapData(wgIncomeSourcesMapRequestDto.getCountyId(), wgIncomeSourcesMapRequestDto.getWealthGroupId(), wgIncomeSourcesMapRequestDto.getIncomeSourceCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/maps/crop-contribution-map-data")
    @ApiOperation(value = "${WealthGroupReports.crop-contribution-map-data}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getIncomeSourcesMapData(@ApiParam("Crop contribution map data") @RequestBody CropContributionMapDataRequestDto cropContributionMapDataRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.cropContributionMapData(cropContributionMapDataRequestDto.getCountyId(), cropContributionMapDataRequestDto.getWealthGroupId(), cropContributionMapDataRequestDto.getCropId(),cropContributionMapDataRequestDto.getContributionAspectCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/crop-contribution-aspects")
    @ApiOperation(value = "${WealthGroupReports.crop-contribution-aspects}", response = CropContributionAspectDto.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<CropContributionAspectDto>> getAllCropContributionAspects() {
        List<CropContributionAspectDto> cropContributionAspectDtoList = new ArrayList<>();
        cropContributionAspectDtoList.add(CASH_INCOME_RANK);
        cropContributionAspectDtoList.add(CASH_INCOME_APPROX_PERCENTAGE);
        cropContributionAspectDtoList.add(FOOD_CONSUMPTION_RANK);
        cropContributionAspectDtoList.add(FOOD_CONSUMPTION_APPROX_PERCENTAGE);
        return new ResponseEntity<List<CropContributionAspectDto>>(cropContributionAspectDtoList, HttpStatus.valueOf(200));
    }


    @PostMapping("/maps/migration-patterns-map-data")
    @ApiOperation(value = "${WealthGroupReports.migration-patterns-map-data}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getMigrationPatternsMapData(@ApiParam("Livestock Ownership charts") @RequestBody WgMigrationPatternsMapDataRequestDto wgMigrationPatternsMapDataRequestDto) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.migrationPatternsMapData(wgMigrationPatternsMapDataRequestDto.getCountyId(), wgMigrationPatternsMapDataRequestDto.getWealthGroupId(), wgMigrationPatternsMapDataRequestDto.getMigrationPatternsCode());
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/all-cash-income-sources")
    @ApiOperation(value = "${WealthGroupReports.all-income-sources}", response = CashIncomeSourcesEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<CashIncomeSourcesEntity>> getAllIncomeSources() {
        return new ResponseEntity<List<CashIncomeSourcesEntity>>(cashIncomeSourcesRepository.findAll(), HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/all-crops")
    @ApiOperation(value = "${WealthGroupReports.all-crops}", response = CropsEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<CropsEntity>> getAllCrops() {
        return new ResponseEntity<List<CropsEntity>>(cropsRepository.findAll(), HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/all-migration-patterns")
    @ApiOperation(value = "${WealthGroupReports.all-migration-patterns}", response = MigrationPatternsEntity.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<MigrationPatternsEntity>> getAllMigrationPatterns() {
        return new ResponseEntity<List<MigrationPatternsEntity>>(migrationPatternsRepository.findAll(), HttpStatus.valueOf(200));
    }


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("countyId") int countyId, @RequestParam("wealthGroupId") int wealthGroupId) throws IOException {

        try {
            CountiesEntity countiesEntity = countiesRepository.findByCountyId(countyId);
            WealthGroupEntity wealthGroupEntity = wealthGroupRepository.findByWealthGroupId(wealthGroupId);
            String fileName = countiesEntity.getCountyName() + " COUNTY " + wealthGroupEntity.getWealthGroupDescription() + " ANALYSIS FILE";
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            wealthGroupExcelService.export(response, countyId, wealthGroupId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/export/excel/mapdata")
    public void exportMapDataToExcel(HttpServletResponse response, @RequestParam("wealthGroupId") int wealthGroupId, @RequestParam("questionnaireSectionCode") int questionnaireSectionCode) throws IOException {

        try {

            WealthGroupEntity wealthGroupEntity = wealthGroupRepository.findByWealthGroupId(wealthGroupId);
            String fileName = wealthGroupEntity.getWealthGroupDescription() + " ANALYSIS FILE";
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            wgMapExcelService.export(response, wealthGroupId,questionnaireSectionCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/export/excel/wealthgroup-comparison")
    public void exportWealthGroupComparisonToExcel(HttpServletResponse response, @RequestParam("countyId") int countyId, @RequestParam("livelihoodZoneId") int livelihoodZoneId, @RequestParam("questionnaireSectionCode") int questionnaireSectionCode) throws IOException {

        try {

            CountiesEntity countiesEntity = countiesRepository.findByCountyId(countyId);
            LivelihoodZonesEntity livelihoodZonesEntity = livelihoodZonesRepository.findByLivelihoodZoneId(livelihoodZoneId);
            String fileName = countiesEntity.getCountyName() + " COUNTY "+ livelihoodZonesEntity.getLivelihoodZoneName().toUpperCase() + " WEALTH GROUP COMPARISON";
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            wealthGroupComparisonExcelService.export(response, countyId,livelihoodZoneId,questionnaireSectionCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/export/excel/country-file")
    public void exportCountryFileToExcel(HttpServletResponse response, @RequestParam("wealthGroupId") int wealthGroupId, @RequestParam("questionnaireSectionCode") int questionnaireSectionCode) throws IOException {

        try {

            WgQuestionnaireSectionsEntity wgQuestionnaireSectionsEntity = wgQuestionnaireSectionsRepository.findByWgQuestionnaireSectionCode(questionnaireSectionCode);
            String fileName = wgQuestionnaireSectionsEntity.getWgQuestionnaireSectionName() + " Country File";
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            wgCountryFileExcelService.export(response,wealthGroupId,questionnaireSectionCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/tlu/byLivelihoodzone")
    @ApiOperation(value = "${WealthGroupReports.charts}", response = WealthGroupNumberValuePair.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WealthGroupNumberValuePair>> getTluByLivelihoodZoneChartData(@ApiParam("TLU chart by livelihood zone") @RequestBody TluByLivelihoodZoneRequestDto tluByLivelihoodZoneRequestDto) {

        try {
            List<WealthGroupNumberValuePair> wealthGroupNumberValuePairList = wealthGroupChartsService.processTluByLivelihoodZone(tluByLivelihoodZoneRequestDto.getCountyId(), tluByLivelihoodZoneRequestDto.getLivelihoodZoneId());
            return new ResponseEntity<List<WealthGroupNumberValuePair>>(wealthGroupNumberValuePairList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WealthGroupNumberValuePair>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/quality-check/incomplete-questionnaires")
    @ApiOperation(value = "${WealthGroupReports.quality}", response = Number.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<Number>> getIncompleteQuestionnaires(@ApiParam("Incomplete questionnaires") @RequestBody IncompleteQuestionnairesRequestDto incompleteQuestionnairesRequestDto) {

        try {
            List<Number> incompleteQuestionnaireIds = qualityChecksService.extractIncompleteWealthGroupQuestionnaires(incompleteQuestionnairesRequestDto.getCountyId(),incompleteQuestionnairesRequestDto.getQuestionnaireTypeId());
            return new ResponseEntity<List<Number>>(incompleteQuestionnaireIds, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Number>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping("/quality-check/country-wide-incomplete-questionnaires")
    public ResponseEntity<List<Number>> getCountryWideIncompleteQuestionnaires(@RequestParam("questionnaireTypeId") int questionnaireTypeId) {

        try {
            List<Number> incompleteQuestionnaireIds = qualityChecksService.extractCountryWideIncompleteWealthGroupQuestionnaires(questionnaireTypeId);
            return new ResponseEntity<List<Number>>(incompleteQuestionnaireIds, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Number>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }
    }


    @GetMapping("/quality-check/missing-questionnaire-sections")
    public ResponseEntity<WgIncompleteQuestionnaireResponseDto> getMissingQuestionnaireSections(@RequestParam("wgQuestionnaireSessionId") int wgQuestionnaireSessionId) {

        try {
            WgIncompleteQuestionnaireResponseDto wgIncompleteQuestionnaireResponseDto = qualityChecksService.returnMissingQuestionnaireSections(wgQuestionnaireSessionId);
            return new ResponseEntity<WgIncompleteQuestionnaireResponseDto>(wgIncompleteQuestionnaireResponseDto, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<WgIncompleteQuestionnaireResponseDto>(new WgIncompleteQuestionnaireResponseDto(), HttpStatus.valueOf(500));
        }
    }


    @GetMapping("/livestock-ownership/tlu")
    @ApiOperation(value = "${WealthGroupReports.tlu-map-data}", response = WgLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgLivelihoodZoneDataObject>> getTluMapData(@RequestParam("countyId") int countyId, @RequestParam("wealthGroupId") int wealthGroupId) {

        try {
            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.livestockOwnershipChartByLivestock(countyId, wealthGroupId, 0);
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(wgLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/crops/search-crop")
    @ApiOperation(value = "${WealthGroupReports.search-crop}", response = CropsEntity.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<CropsEntity>> searchCrops(@ApiParam("Search crops") @RequestParam("cropName") String cropName) {

        try {
            List<CropsEntity> returnedCrops = cropsRepository.findByCropNameLike(cropName);
            return new ResponseEntity<List<CropsEntity>>(returnedCrops, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CropsEntity>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }
}
