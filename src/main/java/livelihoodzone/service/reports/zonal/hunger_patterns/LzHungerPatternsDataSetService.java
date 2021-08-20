package livelihoodzone.service.reports.zonal.hunger_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.zonal.LzHungerPatternsDataSetObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHungerPatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
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
public class LzHungerPatternsDataSetService {

    public List<LzHungerPatternsDataSetRetrofitModel> fetchHungerPatternsDataSet() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzHungerPatternsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelHungerPatterns();
        try {
            Response<List<LzHungerPatternsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzHungerPatternsDataSetObject processHungerPatterns(List<LzHungerPatternsDataSetRetrofitModel> list) {
        List<Number> longRains = processDataSetForASpecificRainnySeason(Constants.LONG_RAINS_SEASON, list);
        List<Number> shortRains = processDataSetForASpecificRainnySeason(Constants.SHORT_RAINS_SEASON, list);
        List<Number> endLongBeginShort = processDataSetForASpecificRainnySeason(Constants.BETWEEN_END_LONG_AND_BEGIN_SHORT, list);
        List<Number> endShortBeginLong = processDataSetForASpecificRainnySeason(Constants.BETWEEN_END_SHORT_AND_BEGIN_LONG, list);



        return new LzHungerPatternsDataSetObject(
                longRains,
                shortRains,
                endLongBeginShort,
                endShortBeginLong
        );
    }


    public List<Number> processDataSetForASpecificRainnySeason(int rainySeasonCode, List<LzHungerPatternsDataSetRetrofitModel> list) {
        List<LzHungerPatternsDataSetRetrofitModel> processedList = returnListForSpecificRainySeason(rainySeasonCode, list);
        List<Number> reportList = new ArrayList<>();

        for (LzHungerPatternsDataSetRetrofitModel currentItem : processedList) {
            reportList.add(currentItem.getYearsOfWidespreadHunger());
        }
        return reportList;
    }


    public List<LzHungerPatternsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<LzHungerPatternsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzHungerPatternsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzHungerPatternsDataSetRetrofitModel> returnListForSpecificRainySeason(int rainySeasonCode, List<LzHungerPatternsDataSetRetrofitModel> allItems) {
        List<LzHungerPatternsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (LzHungerPatternsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getRainySeasonCode() == rainySeasonCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }


}
