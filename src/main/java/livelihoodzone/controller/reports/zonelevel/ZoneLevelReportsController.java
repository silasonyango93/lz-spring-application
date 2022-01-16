package livelihoodzone.controller.reports.zonelevel;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.LzWaterSourceDataSetResponseObject;
import livelihoodzone.dto.reports.zonal.*;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.charts.ZoneLevelChartsRequestDto;
import livelihoodzone.dto.reports.zonal.cropproduction.LzCropProductionReportObjectDto;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupCharectaristicsReportStringObject;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupPopulationPercentageReportResponseObject;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.service.reports.zonal.ZoneLevelChartsService;
import livelihoodzone.service.reports.zonal.ZoneLevelReportService;
import livelihoodzone.service.reports.zonal.excel.ZonalExcelService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionExcelExporterService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
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

@RestController
@RequestMapping("/zone-level-reports")
@Api(tags = "zone-level-reports")
public class ZoneLevelReportsController {

    @Autowired
    ZoneLevelReportService zoneLevelReportService;

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    @Autowired
    ZonalExcelService zonalExcelService;

    @Autowired
    CountiesRepository countiesRepository;

    @PostMapping(value = "/zone-level-report")
    @ApiOperation(value = "${ZoneLevelReports.zone-level-report}", response = ZoneLevelReportResponseDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "Data is not available")})
    public ZoneLevelReportResponseDto getZoneLevelReport(@ApiParam("Questionnaire sections to report on") @RequestBody ZoneLevelReportRequestDto zoneLevelReportRequestDto, HttpServletRequest httpServletRequest) {
        ZoneLevelReportResponseDto zoneLevelReportResponseDto = new ZoneLevelReportResponseDto();

        try {
            if (zoneLevelReportRequestDto.isQuestionnaireDetails()) {
                QuestionnaireDetailsReportObjectDto questionnaireDetailsReportObjectDto = zoneLevelReportService.fetchQuestionnaireDetails();
                zoneLevelReportResponseDto.setReportHashMapObject("questionnaireDetails",questionnaireDetailsReportObjectDto);
            }

            if (zoneLevelReportRequestDto.isWealthGroupCharacteristics()) {
                WealthGroupCharectaristicsReportStringObject wealthGroupCharacteristicsData = zoneLevelReportService.comprehensivelyFetchWealthGroupCharacteristicsReport();
                zoneLevelReportResponseDto.setReportHashMapObject("wealthGroupCharacteristics",wealthGroupCharacteristicsData);
            }

            if (zoneLevelReportRequestDto.isWealthGroupPopulationDistribution()) {
                WealthGroupPopulationPercentageReportResponseObject wealthGroupPopulationPercentageReportResponseObject = zoneLevelReportService.fetchWealthGroupsPopulationPercentages();
                zoneLevelReportResponseDto.setReportHashMapObject("wealthGroupsPopulationPercentages",wealthGroupPopulationPercentageReportResponseObject);
            }

            if (zoneLevelReportRequestDto.isCropProduction()) {
                LzCropProductionReportObjectDto lzCropProductionReportObjectDto = zoneLevelReportService.fetchZoneLevelCropProductionReport();
                zoneLevelReportResponseDto.setReportHashMapObject("cropProduction",lzCropProductionReportObjectDto);
            }

            if (zoneLevelReportRequestDto.isMainSourcesOfWater()) {
                LzWaterSourceDataSetResponseObject lzWaterSourceDataSetResponseObject = zoneLevelReportService.processWaterSourcesDataSet();
                zoneLevelReportResponseDto.setReportHashMapObject("mainWaterSources",lzWaterSourceDataSetResponseObject);
            }

            if (zoneLevelReportRequestDto.isPatternsOfHunger()) {
                LzHungerPatternsDataSetObject lzHungerPatternsDataSetObject = zoneLevelReportService.processHungerPatterns();
                zoneLevelReportResponseDto.setReportHashMapObject("patternsOfHunger",lzHungerPatternsDataSetObject);
            }

            if (zoneLevelReportRequestDto.isHazards()) {
                LzHazardsDataSetObject lzHazardsDataSetObject = zoneLevelReportService.processHazards();
                zoneLevelReportResponseDto.setReportHashMapObject("hazards",lzHazardsDataSetObject);
            }

            if (zoneLevelReportRequestDto.isEthnicGroups()) {
                LzEthnicGroupsDataSetObject lzEthnicGroupsDataSetObject = zoneLevelReportService.processEthnicGroups();
                zoneLevelReportResponseDto.setReportHashMapObject("ethnicGroups",lzEthnicGroupsDataSetObject);
            }

            if (zoneLevelReportRequestDto.isSeasonalCalendar()) {
                LzSeasonalCalendarDataSetObject lzSeasonalCalendarDataSetObject = zoneLevelReportService.processSeasonalCalendar();
                zoneLevelReportResponseDto.setReportHashMapObject("seasonalCalendar",lzSeasonalCalendarDataSetObject);
            }


            return zoneLevelReportResponseDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping("/charts")
    @ApiOperation(value = "${ZoneLevelReports.charts}", response = LzLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<LzLivelihoodZoneDataObject>> getZoneLevelChartsData(@ApiParam("Zone Level charts") @RequestBody ZoneLevelChartsRequestDto zoneLevelChartsRequestDto) {

        try {
            List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(zoneLevelChartsRequestDto.getCountyId(),zoneLevelChartsRequestDto.getQuestionnaireSectionCode());
            return new ResponseEntity<List<LzLivelihoodZoneDataObject>>(lzLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<LzLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, @RequestParam("countyId") int countyId) throws IOException {

        try {
            CountiesEntity countiesEntity = countiesRepository.findByCountyId(countyId);
            String fileName = countiesEntity.getCountyName() + " COUNTY " + " ANALYSIS FILE";
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            zonalExcelService.export(response,countyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/maps/wealth-group-population-distribution")
    @ApiOperation(value = "${ZoneLevelReports.wealth-group-population-distribution}", response = LzLivelihoodZoneDataObject.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<LzLivelihoodZoneDataObject>> fetchWealthGroupMapData(@ApiParam("Wealth Group Map data") @RequestBody WealthGroupPopulationRequestDto wealthGroupPopulationRequestDto) {

        try {
            List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.processWealthGroupPopulationMapData(wealthGroupPopulationRequestDto.getCountyId(),wealthGroupPopulationRequestDto.getWealthGroupCode());
            return new ResponseEntity<List<LzLivelihoodZoneDataObject>>(lzLivelihoodZoneDataObjectList, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<LzLivelihoodZoneDataObject>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }
}
