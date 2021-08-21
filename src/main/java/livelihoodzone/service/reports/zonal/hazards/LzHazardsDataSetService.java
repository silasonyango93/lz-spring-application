package livelihoodzone.service.reports.zonal.hazards;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.zonal.LzHazardsDataSetObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHazardsDataSetRetrofitModel;
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
public class LzHazardsDataSetService {

    public List<LzHazardsDataSetRetrofitModel> fetchHazardsDataSet() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzHazardsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelHazards();
        try {
            Response<List<LzHazardsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzHazardsDataSetObject processHazards(List<LzHazardsDataSetRetrofitModel> list) {
        List<String> animalRustling = processReportsForAParticularHazard(Constants.HZ_ANIMAL_RUSTLING, list);
        List<String> banditry = processReportsForAParticularHazard(Constants.HZ_BANDITRY, list);
        List<String> terrorism = processReportsForAParticularHazard(Constants.HZ_BANDITRY, list);
        List<String> ethnicConflict = processReportsForAParticularHazard(Constants.HZ_TERRORISM, list);
        List<String> politicalConflict = processReportsForAParticularHazard(Constants.HZ_ETHNIC_CONFLICT, list);
        List<String> drought = processReportsForAParticularHazard(Constants.HZ_POLITICAL_CONFLICT, list);
        List<String> livestockPestsDiseases = processReportsForAParticularHazard(Constants.HZ_DROUGHT, list);
        List<String> hailStorms = processReportsForAParticularHazard(Constants.HZ_HAILSTORMS_OR_FROST, list);
        List<String> flooding = processReportsForAParticularHazard(Constants.HZ_FLOODING, list);
        List<String> landSlides = processReportsForAParticularHazard(Constants.HZ_LANDSLIDES, list);
        List<String> highWids = processReportsForAParticularHazard(Constants.HZ_HIGH_WINDS, list);
        List<String> bushFires = processReportsForAParticularHazard(Constants.HZ_BUSH_FIRES, list);
        List<String> cropPests = processReportsForAParticularHazard(Constants.HZ_CROP_PESTS, list);
        List<String> locustInvasion = processReportsForAParticularHazard(Constants.HZ_LOCUST_INVASION, list);
        List<String> cropDiseases = processReportsForAParticularHazard(Constants.HZ_CROP_DISEASES, list);
        List<String> terminalIllness = processReportsForAParticularHazard(Constants.HZ_TERMINAL_ILLNESS, list);
        List<String> malariaOutbreak = processReportsForAParticularHazard(Constants.HZ_MALARIA_OUTBREAK, list);
        List<String> waterBorneDiseases = processReportsForAParticularHazard(Constants.HZ_WATER_BORNE_DISEASE, list);
        List<String> humaWildlifeConflict = processReportsForAParticularHazard(Constants.HZ_HUMAN_WILDLIFE_CONFLICT, list);
        List<String> highFoodPrices = processReportsForAParticularHazard(Constants.HZ_HIGH_FOOD_PRICES, list);
        List<String> marketFoodShortage = processReportsForAParticularHazard(Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET, list);
        List<String> drinkingWaterShortages = processReportsForAParticularHazard(Constants.HZ_DRINKING_WATER_SHORTAGES, list);
        List<String> invasivePlantSpecies = processReportsForAParticularHazard(Constants.HZ_INVASIVE_PLANT_SPECIES, list);
        List<String> others = processReportsForAParticularHazard(Constants.HZ_OTHERS, list);

        return new LzHazardsDataSetObject(
                animalRustling,
                banditry,
                terrorism,
                ethnicConflict,
                politicalConflict,
                drought,
                livestockPestsDiseases,
                hailStorms,
                flooding,
                landSlides,
                highWids,
                bushFires,
                cropPests,
                locustInvasion,
                cropDiseases,
                terminalIllness,
                malariaOutbreak,
                waterBorneDiseases,
                humaWildlifeConflict,
                highFoodPrices,
                marketFoodShortage,
                drinkingWaterShortages,
                invasivePlantSpecies,
                others
        );
    }

    public List<String> processReportsForAParticularHazard(int hazardCode, List<LzHazardsDataSetRetrofitModel> list) {
        List<LzHazardsDataSetRetrofitModel> processedList = returnListForSpecificHazard(hazardCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        String currentReportString = "";

        for (LzHazardsDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                if (hazardCode == Constants.HZ_OTHERS && !currentItem.getLzHazardDescription().isEmpty()) {
                    currentReportString = currentReportString + "Description -> " + currentItem.getLzHazardDescription() + "  Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                } else {
                    currentReportString = currentReportString + "Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                }
            } else {
                stringReportList.add(currentReportString);
                if (hazardCode == Constants.HZ_OTHERS && !currentItem.getLzHazardDescription().isEmpty()) {
                    currentReportString = "Description -> " + currentItem.getLzHazardDescription() + "  Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                } else {
                    currentReportString = "Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                }
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }

    public List<LzHazardsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<LzHazardsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzHazardsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzHazardsDataSetRetrofitModel> returnListForSpecificHazard(int hazardCode, List<LzHazardsDataSetRetrofitModel> allItems) {
        List<LzHazardsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (LzHazardsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getLzHazardCode() == hazardCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }
}
