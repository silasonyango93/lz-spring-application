package livelihoodzone.service.reports.zonal;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.LzWaterSourceDataSetResponseObject;
import livelihoodzone.dto.reports.zonal.*;
import livelihoodzone.dto.reports.zonal.cropproduction.LzCropProductionReportObjectDto;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupCharectaristicsReportStringObject;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupPopulationPercentageReportResponseObject;
import livelihoodzone.repository.questionnaire.county.LzHungerPatternsResponsesRepository;
import livelihoodzone.service.reports.zonal.cropproduction.LzCropProductionReportService;
import livelihoodzone.service.reports.zonal.ethnic_groups.EthnicGroupsDataSetService;
import livelihoodzone.service.reports.zonal.hazards.LzHazardsDataSetService;
import livelihoodzone.service.reports.zonal.hunger_patterns.LzHungerPatternsDataSetService;
import livelihoodzone.service.reports.zonal.seasonal_calendar.LzSeasonalCalendarDataSetService;
import livelihoodzone.service.reports.zonal.water_sources.WaterSourcesDataSetService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupCharacteristicsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneLevelReportService {

    @Autowired
    LzWealthGroupDistributionReportsService lzWealthGroupDistributionReportsService;

    @Autowired
    QuestionnaireDetailsService questionnaireDetailsService;

    @Autowired
    LzCropProductionReportService lzCropProductionReportService;

    @Autowired
    WaterSourcesDataSetService waterSourcesDataSetService;

    @Autowired
    LzHungerPatternsDataSetService lzHungerPatternsDataSetService;

    @Autowired
    LzHazardsDataSetService lzHazardsDataSetService;

    @Autowired
    EthnicGroupsDataSetService ethnicGroupsDataSetService;

    @Autowired
    LzSeasonalCalendarDataSetService lzSeasonalCalendarDataSetService;

    public WealthGroupCharectaristicsReportStringObject comprehensivelyFetchWealthGroupCharacteristicsReport() {
        List<String> veryPoorCharacteristics = new ArrayList<>();
        List<String> poorCharacteristics = new ArrayList<>();
        List<String> mediumCharacteristics = new ArrayList<>();
        List<String> betterOffCharacteristics = new ArrayList<>();

        List<WealthGroupCharacteristicsRetrofitModel> veryPoorList = new ArrayList<>();
        List<WealthGroupCharacteristicsRetrofitModel> poorList = new ArrayList<>();
        List<WealthGroupCharacteristicsRetrofitModel> mediumList = new ArrayList<>();
        List<WealthGroupCharacteristicsRetrofitModel> betterOffList = new ArrayList<>();

        List<WealthGroupCharacteristicsRetrofitModel> wealthGroupCharacteristicsRetrofitModelList = lzWealthGroupDistributionReportsService.fetchWealthGroupCharacteristicsReportsComprehensively();


        for (WealthGroupCharacteristicsRetrofitModel currentItem : wealthGroupCharacteristicsRetrofitModelList) {
            if (currentItem.getWealthGroupCode() == Constants.VERY_POOR_CODE) {
                veryPoorList.add(currentItem);
            }
            if (currentItem.getWealthGroupCode() == Constants.POOR_CODE) {
                poorList.add(currentItem);
            }
            if (currentItem.getWealthGroupCode() == Constants.MEDIUM_CODE) {
                mediumList.add(currentItem);
            }
            if (currentItem.getWealthGroupCode() == Constants.BETTER_OFF_CODE) {
                betterOffList.add(currentItem);
            }
        }

        //Process very poor array list
        int veryPoorCurrentQuestionnaireSessionId = veryPoorList.get(0).getLzQuestionnaireSessionId();
        String veryPoorDataString = "";
        int veryPoorCounter = 1;

        for (WealthGroupCharacteristicsRetrofitModel currentItem : veryPoorList) {
            if (currentItem.getLzQuestionnaireSessionId() == veryPoorCurrentQuestionnaireSessionId) {
                veryPoorDataString = veryPoorDataString + " " + String.valueOf(veryPoorCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                veryPoorCounter++;
            } else {
                veryPoorCharacteristics.add(veryPoorDataString);
                veryPoorCounter = 1;
                veryPoorDataString = " " + String.valueOf(veryPoorCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                veryPoorCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                veryPoorCounter++;
            }
        }
        veryPoorCharacteristics.add(veryPoorDataString);


        //Process Poor array list
        int poorCurrentQuestionnaireSessionId = poorList.get(0).getLzQuestionnaireSessionId();
        String poorDataString = "";
        int poorCounter = 1;

        for (WealthGroupCharacteristicsRetrofitModel currentItem : poorList) {
            if (currentItem.getLzQuestionnaireSessionId() == poorCurrentQuestionnaireSessionId) {
                poorDataString = poorDataString + " " + String.valueOf(poorCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                poorCounter++;
            } else {
                poorCharacteristics.add(poorDataString);
                poorCounter = 1;
                poorDataString = " " + String.valueOf(poorCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                poorCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                poorCounter++;
            }
        }
        poorCharacteristics.add(poorDataString);


        //Process Medium array list
        int mediumCurrentQuestionnaireSessionId = poorList.get(0).getLzQuestionnaireSessionId();
        String mediumDataString = "";
        int mediumCounter = 1;

        for (WealthGroupCharacteristicsRetrofitModel currentItem : mediumList) {
            if (currentItem.getLzQuestionnaireSessionId() == mediumCurrentQuestionnaireSessionId) {
                mediumDataString = mediumDataString + " " + String.valueOf(mediumCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                mediumCounter++;
            } else {
                mediumCharacteristics.add(mediumDataString);
                mediumCounter = 1;
                mediumDataString = " " + String.valueOf(mediumCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                mediumCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                mediumCounter++;
            }
        }
        mediumCharacteristics.add(mediumDataString);


        //Process Better Off array list
        int betterOffCurrentQuestionnaireSessionId = poorList.get(0).getLzQuestionnaireSessionId();
        String betterOffDataString = "";
        int betterOffCounter = 1;

        for (WealthGroupCharacteristicsRetrofitModel currentItem : betterOffList) {
            if (currentItem.getLzQuestionnaireSessionId() == betterOffCurrentQuestionnaireSessionId) {
                betterOffDataString = betterOffDataString + " " + String.valueOf(betterOffCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                betterOffCounter++;
            } else {
                betterOffCharacteristics.add(betterOffDataString);
                betterOffCounter = 1;
                betterOffDataString = " " + String.valueOf(betterOffCounter) + ")" + currentItem.getCharectaristicDescription() + ", ";
                betterOffCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                betterOffCounter++;
            }
        }
        betterOffCharacteristics.add(betterOffDataString);

        return new WealthGroupCharectaristicsReportStringObject(veryPoorCharacteristics,poorCharacteristics,mediumCharacteristics,betterOffCharacteristics);
    }

    public QuestionnaireDetailsReportObjectDto fetchQuestionnaireDetails() {
        return new QuestionnaireDetailsReportObjectDto(questionnaireDetailsService.fetchQuestionnaireDetails());
    }

    public WealthGroupPopulationPercentageReportResponseObject fetchWealthGroupsPopulationPercentages() {
        return lzWealthGroupDistributionReportsService.processWealthGroupPopulationReport(lzWealthGroupDistributionReportsService.fetchWealthGroupPopulationPercentage());
    }

    public LzCropProductionReportObjectDto fetchZoneLevelCropProductionReport() {
        return lzCropProductionReportService.processZoneLevelCropProductionReport(lzCropProductionReportService.fetchZoneLevelCropProductionReport());
    }

    public LzWaterSourceDataSetResponseObject processWaterSourcesDataSet() {
        return waterSourcesDataSetService.processWaterSourcesDataSet(waterSourcesDataSetService.fetchWaterSourceDataSet());
    }

    public LzHungerPatternsDataSetObject processHungerPatterns() {
        return lzHungerPatternsDataSetService.processHungerPatterns(lzHungerPatternsDataSetService.fetchHungerPatternsDataSet());
    }

    public LzHazardsDataSetObject processHazards() {
        return lzHazardsDataSetService.processHazards(lzHazardsDataSetService.fetchHazardsDataSet());
    }

    public LzEthnicGroupsDataSetObject processEthnicGroups() {
        return ethnicGroupsDataSetService.processEthnicGroups(ethnicGroupsDataSetService.fetchEthnicGroupsDataSet());
    }

    public LzSeasonalCalendarDataSetObject processSeasonalCalendar() {
        return lzSeasonalCalendarDataSetService.processSeasonalCalendar(lzSeasonalCalendarDataSetService.fetchSeasonMonths());
    }
}
