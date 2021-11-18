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
import livelihoodzone.service.reports.zonal.ZoneLevelChartsService;
import livelihoodzone.service.reports.zonal.ZoneLevelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/zone-level-reports")
@Api(tags = "zone-level-reports")
public class ZoneLevelReportsController {

    @Autowired
    ZoneLevelReportService zoneLevelReportService;

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

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
}
