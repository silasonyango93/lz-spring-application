package livelihoodzone.service.reports.zonal;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.service.reports.wealthgroup.WealthGroupChartsService;
import livelihoodzone.service.reports.zonal.cropproduction.LzCropProductionReportService;
import livelihoodzone.service.reports.zonal.ethnic_groups.EthnicGroupsDataSetService;
import livelihoodzone.service.reports.zonal.hazards.LzHazardsDataSetService;
import livelihoodzone.service.reports.zonal.hunger_patterns.LzHungerPatternsDataSetService;
import livelihoodzone.service.reports.zonal.markets.MarketsReportService;
import livelihoodzone.service.reports.zonal.seasonal_calendar.LzSeasonalCalendarDataSetService;
import livelihoodzone.service.reports.zonal.water_sources.WaterSourcesDataSetService;
import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneLevelChartsService {

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    LzWealthGroupDistributionReportsService lzWealthGroupDistributionReportsService;

    @Autowired
    WaterSourcesDataSetService waterSourcesDataSetService;

    @Autowired
    MarketsReportService marketsReportService;

    @Autowired
    LzHungerPatternsDataSetService lzHungerPatternsDataSetService;

    @Autowired
    LzHazardsDataSetService lzHazardsDataSetService;

    @Autowired
    EthnicGroupsDataSetService ethnicGroupsDataSetService;

    @Autowired
    LzSeasonalCalendarDataSetService lzSeasonalCalendarDataSetService;

    @Autowired
    LzCropProductionReportService lzCropProductionReportService;

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    public List<LzLivelihoodZoneDataObject> prepareZoneLevelChart(int countyId, int questionnaireSectionCode) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {

            LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject = new LzLivelihoodZoneDataObject();
            lzLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            lzLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());


            if (questionnaireSectionCode == Constants.WEALTH_GROUP_CHARACTERISTICS) {
                lzLivelihoodZoneDataObject = lzWealthGroupDistributionReportsService.processWealthGroupCharacteristicsChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.WEALTH_GROUP_POPULATION_PERCENTAGE) {
                lzLivelihoodZoneDataObject = lzWealthGroupDistributionReportsService.processWealthGroupPopulationPercentageChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.MAIN_SOURCES_OF_WATER) {
                lzLivelihoodZoneDataObject = waterSourcesDataSetService.processWaterSourcesChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.MARKETS_SERVING_THE_LIVELIHOOD_ZONE) {
                lzLivelihoodZoneDataObject = marketsReportService.processMarketsChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.PATTERNS_OF_HUNGER) {
                lzLivelihoodZoneDataObject = lzHungerPatternsDataSetService.processPatternsOfHungerChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.HAZARDS) {
                lzLivelihoodZoneDataObject = lzHazardsDataSetService.processHazardsChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.SOCIETY_AND_ETHNICITY) {
                lzLivelihoodZoneDataObject = ethnicGroupsDataSetService.processEthnicGroupsChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.SEASONAL_CALENDAR) {
                lzLivelihoodZoneDataObject = lzSeasonalCalendarDataSetService.processSeasonalCalendarChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.LZ_CROP_PRODUCTION) {
                lzLivelihoodZoneDataObject = lzCropProductionReportService.processCropProductionChart(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId());
            }

            lzLivelihoodZoneDataObjectList.add(lzLivelihoodZoneDataObject);
        }
        return lzLivelihoodZoneDataObjectList;
    }


    public List<LzLivelihoodZoneDataObject> processWealthGroupPopulationMapData(int countyId, int wealthGroupCode) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {

            LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject = new LzLivelihoodZoneDataObject();
            lzLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            lzLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());
            lzLivelihoodZoneDataObject.setSubLocationsUnderTheLivelihoodZone(wealthGroupChartsService.fetchSubLocationsInALivelihoodZoneInAParticularCounty(countyId,currentQuestionnaire.getLivelihoodZoneId()));


            lzLivelihoodZoneDataObject = lzWealthGroupDistributionReportsService.processWealthGroupPopulationPercentageChartByWealthGroup(lzLivelihoodZoneDataObject,currentQuestionnaire.getLzQuestionnaireSessionId(),wealthGroupCode);

            lzLivelihoodZoneDataObjectList.add(lzLivelihoodZoneDataObject);
        }
        return lzLivelihoodZoneDataObjectList;
    }


    public List<LzLivelihoodZoneDataObject> processWaterSourcesMapData(int countyId, int waterSourceCode, int seasonCode) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {

            LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject = new LzLivelihoodZoneDataObject();
            lzLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            lzLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());
            lzLivelihoodZoneDataObject.setSubLocationsUnderTheLivelihoodZone(wealthGroupChartsService.fetchSubLocationsInALivelihoodZoneInAParticularCounty(countyId,currentQuestionnaire.getLivelihoodZoneId()));


            lzLivelihoodZoneDataObject = waterSourcesDataSetService.processWaterSourcesChartByWaterSourceCode(lzLivelihoodZoneDataObject,currentQuestionnaire.getLzQuestionnaireSessionId(),waterSourceCode,seasonCode);

            lzLivelihoodZoneDataObjectList.add(lzLivelihoodZoneDataObject);
        }
        return lzLivelihoodZoneDataObjectList;
    }


    public List<LzLivelihoodZoneDataObject> processHazardsMapData(int countyId, int hazardCode, int hazardAspectCode) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {

            LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject = new LzLivelihoodZoneDataObject();
            lzLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            lzLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());
            lzLivelihoodZoneDataObject.setSubLocationsUnderTheLivelihoodZone(wealthGroupChartsService.fetchSubLocationsInALivelihoodZoneInAParticularCounty(countyId,currentQuestionnaire.getLivelihoodZoneId()));


            lzLivelihoodZoneDataObject = lzHazardsDataSetService.processHazardsChartByHazard(lzLivelihoodZoneDataObject, currentQuestionnaire.getLzQuestionnaireSessionId(), hazardCode, hazardAspectCode);

            lzLivelihoodZoneDataObjectList.add(lzLivelihoodZoneDataObject);
        }
        return lzLivelihoodZoneDataObjectList;
    }


    public List<LzLivelihoodZoneDataObject> processHungerPatternsMapData(int countyId, int rainySeasonCode) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {

            LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject = new LzLivelihoodZoneDataObject();
            lzLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            lzLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());
            lzLivelihoodZoneDataObject.setSubLocationsUnderTheLivelihoodZone(wealthGroupChartsService.fetchSubLocationsInALivelihoodZoneInAParticularCounty(countyId,currentQuestionnaire.getLivelihoodZoneId()));


            lzLivelihoodZoneDataObject = lzHungerPatternsDataSetService.processPatternsOfHungerChartBySeason(lzLivelihoodZoneDataObject,currentQuestionnaire.getLzQuestionnaireSessionId(),rainySeasonCode);

            lzLivelihoodZoneDataObjectList.add(lzLivelihoodZoneDataObject);
        }
        return lzLivelihoodZoneDataObjectList;
    }
}
