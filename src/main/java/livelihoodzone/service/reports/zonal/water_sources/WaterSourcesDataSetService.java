package livelihoodzone.service.reports.zonal.water_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.LzWaterSourceDataSetResponseObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgLabourPatternsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.QuestionnaireDetailsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
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

}
