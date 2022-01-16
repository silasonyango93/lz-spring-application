package livelihoodzone.service.reports.zonal.water_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.LzWaterSourceDataSetResponseObject;
import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.county.LzWaterSourceResponsesEntity;
import livelihoodzone.repository.questionnaire.county.LzWaterSourceResponsesRepository;
import livelihoodzone.repository.questionnaire.county.WaterSourceRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgLabourPatternsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.QuestionnaireDetailsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class WaterSourcesDataSetService {

    @Autowired
    LzWaterSourceResponsesRepository lzWaterSourceResponsesRepository;

    @Autowired
    WaterSourceRepository waterSourceRepository;

    public List<LzWaterSourceDataSetRetrofitModel> fetchWaterSourceDataSet() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzWaterSourceDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelWaterSources();
        try {
            Response<List<LzWaterSourceDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzWaterSourceDataSetResponseObject processWaterSourcesDataSet(List<LzWaterSourceDataSetRetrofitModel> list) {

        List<String> riversStringReportList = processAWaterSourceReportList(Constants.RIVERS, list);
        List<String> traditionalRiversStringReportList = processAWaterSourceReportList(Constants.TRADITIONAL_RIVERS, list);
        List<String> naturalPondsStringReportList = processAWaterSourceReportList(Constants.NATURAL_PONDS, list);
        List<String> pansAndDamsStringReportList = processAWaterSourceReportList(Constants.PANS_AND_DAMS, list);
        List<String> shallowWellsStringReportList = processAWaterSourceReportList(Constants.SHALLOW_WELLS, list);
        List<String> boreHolesStringReportList = processAWaterSourceReportList(Constants.BOREHOLES, list);
        List<String> springsStringReportList = processAWaterSourceReportList(Constants.SPRINGS, list);
        List<String> lakesStringReportList = processAWaterSourceReportList(Constants.LAKES, list);
        List<String> rockCatchmentsStringReportList = processAWaterSourceReportList(Constants.ROCK_CATCHMENT, list);
        List<String> pipedWaterStringReportList = processAWaterSourceReportList(Constants.PIPED_WATER, list);
        List<String> waterTruckingStringReportList = processAWaterSourceReportList(Constants.WATER_TRUCKING, list);
        List<String> roofCatchmentsStringReportList = processAWaterSourceReportList(Constants.ROOF_CATCHMENTS, list);
        List<String> othersStringReportList = processAWaterSourceReportList(Constants.OTHERS, list);

        return new LzWaterSourceDataSetResponseObject(
                riversStringReportList,
                traditionalRiversStringReportList,
                naturalPondsStringReportList,
                pansAndDamsStringReportList,
                shallowWellsStringReportList,
                boreHolesStringReportList,
                springsStringReportList,
                lakesStringReportList,
                rockCatchmentsStringReportList,
                pipedWaterStringReportList,
                waterTruckingStringReportList,
                roofCatchmentsStringReportList,
                othersStringReportList
        );
    }


    public List<String> processAWaterSourceReportList(int waterSourceCode, List<LzWaterSourceDataSetRetrofitModel> list) {
        List<LzWaterSourceDataSetRetrofitModel> processedList = returnListForSpecificWaterSource(waterSourceCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = 0;
        if (!processedList.isEmpty()) {
            currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        }

        String currentReportString = "";

        for (LzWaterSourceDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                if (waterSourceCode == Constants.OTHERS && !currentItem.getExtraDescription().isEmpty()) {
                    currentReportString = currentReportString + "Description -> " + currentItem.getExtraDescription() + "  " + "Wet season percentage -> " + currentItem.getWetSeasonPercentage() + "  Dry season percentage -> " + currentItem.getDrySeasonPercentage() + ", ";
                } else {
                    currentReportString = currentReportString + "Wet season percentage -> " + currentItem.getWetSeasonPercentage() + "  Dry season percentage -> " + currentItem.getDrySeasonPercentage() + ", ";
                }
            } else {
                stringReportList.add(currentReportString);
                if (waterSourceCode == Constants.OTHERS && !currentItem.getExtraDescription().isEmpty()) {
                    currentReportString = "Description -> " + currentItem.getExtraDescription() + "  " + "Wet season percentage -> " + currentItem.getWetSeasonPercentage() + "  Dry season percentage -> " + currentItem.getDrySeasonPercentage() + ", ";
                } else {
                    currentReportString = "Wet season percentage -> " + currentItem.getWetSeasonPercentage() + "  Dry season percentage -> " + currentItem.getDrySeasonPercentage() + ", ";
                }
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }


    public List<LzWaterSourceDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<LzWaterSourceDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzWaterSourceDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzWaterSourceDataSetRetrofitModel> returnListForSpecificWaterSource(int waterSourceCode, List<LzWaterSourceDataSetRetrofitModel> allItems) {
        List<LzWaterSourceDataSetRetrofitModel> processedList = new ArrayList<>();

        if (allItems != null) {
            for (LzWaterSourceDataSetRetrofitModel currentItem : allItems) {
                if (currentItem.getWaterSourceCode() == waterSourceCode) {
                    processedList.add(currentItem);
                }
            }
            return clusterSameQuestionnaireItemsTogether(processedList);
        }
        System.out.println(waterSourceCode);
        return new ArrayList<>();
    }


    public LzLivelihoodZoneDataObject processWaterSourcesChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        WaterSourcesResponsesDto waterSourceResponses = new WaterSourcesResponsesDto(true);
        List<LzWaterSourceResponsesEntity> lzWaterSourceResponsesEntityList = lzWaterSourceResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzWaterSourceResponsesEntity lzWaterSourceResponsesEntity : lzWaterSourceResponsesEntityList) {
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.RIVERS) {
                waterSourceResponses.getRivers().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getRivers().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.TRADITIONAL_RIVERS) {
                waterSourceResponses.getTraditionalRiversWells().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getTraditionalRiversWells().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.NATURAL_PONDS) {
                waterSourceResponses.getNaturalPonds().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getNaturalPonds().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.PANS_AND_DAMS) {
                waterSourceResponses.getPansAndDams().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getPansAndDams().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.SHALLOW_WELLS) {
                waterSourceResponses.getShallowWells().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getShallowWells().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.BOREHOLES) {
                waterSourceResponses.getBoreholes().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getBoreholes().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.SPRINGS) {
                waterSourceResponses.getSprings().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getSprings().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.LAKES) {
                waterSourceResponses.getLakes().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getLakes().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.ROCK_CATCHMENT) {
                waterSourceResponses.getRockCatchments().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getRockCatchments().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.PIPED_WATER) {
                waterSourceResponses.getPipedWater().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getPipedWater().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.WATER_TRUCKING) {
                waterSourceResponses.getWaterTrucking().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getWaterTrucking().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.ROOF_CATCHMENTS) {
                waterSourceResponses.getRoofCatchments().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getRoofCatchments().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.OTHERS) {
                waterSourceResponses.getOthers().setDrySeasonPopulationResponse(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                waterSourceResponses.getOthers().setWetSeasonPopulation(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                waterSourceResponses.getOthers().setExtraDescription(lzWaterSourceResponsesEntity.getExtraDescription());
            }
        }
        lzLivelihoodZoneDataObject.setWaterSourceResponses(waterSourceResponses);
        return lzLivelihoodZoneDataObject;
    }




    public LzLivelihoodZoneDataObject processWaterSourcesChartByWaterSourceCode(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId, int waterSourceCode, int seasonCode) {
        WaterSourcesResponsesDto waterSourceResponses = new WaterSourcesResponsesDto();
        List<LzWaterSourceResponsesEntity> lzWaterSourceResponsesEntityList = lzWaterSourceResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzWaterSourceResponsesEntity lzWaterSourceResponsesEntity : lzWaterSourceResponsesEntityList) {
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.RIVERS && waterSourceCode == Constants.RIVERS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.TRADITIONAL_RIVERS && waterSourceCode == Constants.TRADITIONAL_RIVERS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.NATURAL_PONDS && waterSourceCode == Constants.NATURAL_PONDS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.PANS_AND_DAMS && waterSourceCode == Constants.PANS_AND_DAMS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.SHALLOW_WELLS && waterSourceCode == Constants.SHALLOW_WELLS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.BOREHOLES && waterSourceCode == Constants.BOREHOLES) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.SPRINGS && waterSourceCode == Constants.SPRINGS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.LAKES && waterSourceCode == Constants.LAKES) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.ROCK_CATCHMENT && waterSourceCode == Constants.ROCK_CATCHMENT) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.PIPED_WATER && waterSourceCode == Constants.PIPED_WATER) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.WATER_TRUCKING && waterSourceCode == Constants.WATER_TRUCKING) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.ROOF_CATCHMENTS && waterSourceCode == Constants.ROOF_CATCHMENTS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
            if (waterSourceRepository.findByWaterSourceId(lzWaterSourceResponsesEntity.getWaterSourceId()).getWaterSourceCode() == Constants.OTHERS && waterSourceCode == Constants.OTHERS) {
                if (seasonCode == Constants.DRY_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getDrySeasonPercentage());
                }
                if (seasonCode == Constants.WET_SEASON.getSeasonCode()) {
                    waterSourceResponses.setParameterValue(lzWaterSourceResponsesEntity.getWetSeasonPercentage());
                }
            }
        }
        lzLivelihoodZoneDataObject.setWaterSourceResponses(waterSourceResponses);
        return lzLivelihoodZoneDataObject;
    }

}
