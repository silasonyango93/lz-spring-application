package livelihoodzone.service.reports.zonal.seasonal_calendar;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.zonal.LzSeasonalCalendarDataSetObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHazardsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHungerPatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import livelihoodzone.service.retrofit.reports.zonelevel.seasonal_calendar.LzSeasonMonthsDataSetRetrofitModel;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LzSeasonalCalendarDataSetService {


    public List<LzSeasonMonthsDataSetRetrofitModel> fetchSeasonMonths() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzSeasonMonthsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelSeasonMonths();
        try {
            Response<List<LzSeasonMonthsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzSeasonalCalendarDataSetObject processSeasonalCalendar(List<LzSeasonMonthsDataSetRetrofitModel> list) {
        LzSeasonalCalendarDataSetObject lzSeasonalCalendarDataSetObject = new LzSeasonalCalendarDataSetObject();

        //Set season months
        lzSeasonalCalendarDataSetObject = processSeasonMonths(list, lzSeasonalCalendarDataSetObject);

        return lzSeasonalCalendarDataSetObject;
    }

    public LzSeasonalCalendarDataSetObject processSeasonMonths(List<LzSeasonMonthsDataSetRetrofitModel> list, LzSeasonalCalendarDataSetObject lzSeasonalCalendarDataSetObject) {

        lzSeasonalCalendarDataSetObject.setDrySeasonMonths(processListForAParticularSeason(list, Constants.SC_DRY_SEASON));
        lzSeasonalCalendarDataSetObject.setLongRainsMonths(processListForAParticularSeason(list, Constants.SC_LONG_RAINS));
        lzSeasonalCalendarDataSetObject.setShortRainsMonths(processListForAParticularSeason(list, Constants.SC_SHORT_RAINS));
        return lzSeasonalCalendarDataSetObject;
    }

    public List<String> processListForAParticularSeason(List<LzSeasonMonthsDataSetRetrofitModel> list, int seasonCode) {
        List<LzSeasonMonthsDataSetRetrofitModel> processedList = returnListForSpecificSeason(seasonCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (LzSeasonMonthsDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ")" + currentItem.getMonthName() + ",  ";
                counter++;
            } else {
                stringReportList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ")" + currentItem.getMonthName() + ",  ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }


    public List<LzSeasonMonthsDataSetRetrofitModel> clusterSameSeasonMonthsQuestionnaireItemsTogether(List<LzSeasonMonthsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzSeasonMonthsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzSeasonMonthsDataSetRetrofitModel> returnListForSpecificSeason(int seasonCode, List<LzSeasonMonthsDataSetRetrofitModel> allItems) {
        List<LzSeasonMonthsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (LzSeasonMonthsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getLzSeasonCode() == seasonCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameSeasonMonthsQuestionnaireItemsTogether(processedList);
    }
}
