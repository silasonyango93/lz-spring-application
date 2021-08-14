package livelihoodzone.service.reports.wealthgroup.animal_ownership;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.wealthgroup.WgAnimalContributionDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.WgLivestockOwnershipDataSetObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.*;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class AnimalOwnershipService {

    public List<WgAnimalOwnershipRetrofitModel> fetchWealthGroupAnimalOwnership(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgAnimalOwnershipRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupLivestockOwnership(countyId, questionnaireTypeId);
        try {
            Response<List<WgAnimalOwnershipRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgLivestockOwnershipDataSetObject processLivestockOwnership(List<WgAnimalOwnershipRetrofitModel> list) {
        List<String> livestockOwnershipStringReportList = new ArrayList<>();
        List<WgAnimalOwnershipRetrofitModel> processedList = clusterSameAnimalOwnershipQuestionnaireItemsTogether(list);


        int currentQuestionnaireSessionId = processedList.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (WgAnimalOwnershipRetrofitModel currentItem : processedList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getAnimalName() + " -> " + currentItem.getAverageNumber() + ", ";
                counter++;
            } else {
                livestockOwnershipStringReportList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getAnimalName() + " -> " + currentItem.getAverageNumber() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        livestockOwnershipStringReportList.add(currentReportString);

        return new WgLivestockOwnershipDataSetObject(livestockOwnershipStringReportList);
    }

    public List<WgLivestockContributionRetrofitModel> returnListForSpecificAnimalType(int animalCode, List<WgLivestockContributionRetrofitModel> allItems) {
        List<WgLivestockContributionRetrofitModel> processedList = new ArrayList<>();

        for (WgLivestockContributionRetrofitModel currentItem : allItems) {
            if (currentItem.getAnimalCode() == animalCode) {
                processedList.add(currentItem);
            }
        }
        return clusterLivestockContributionSameAnimalOwnershipQuestionnaireItemsTogether(processedList);
    }

    public List<WgAnimalOwnershipRetrofitModel> clusterSameAnimalOwnershipQuestionnaireItemsTogether(List<WgAnimalOwnershipRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgAnimalOwnershipRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<WgLivestockContributionRetrofitModel> clusterLivestockContributionSameAnimalOwnershipQuestionnaireItemsTogether(List<WgLivestockContributionRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgLivestockContributionRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<WgLivestockContributionRetrofitModel> fetchWealthGroupAnimalContribution(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgLivestockContributionRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupLivestockContribution(countyId, questionnaireTypeId);
        try {
            Response<List<WgLivestockContributionRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgAnimalContributionDataSetObject processLivestockContribution(List<WgLivestockContributionRetrofitModel> list) {
        List<String> localCattle = processAnimalTypeData(list, Constants.LOCAL_CATTLE);
        List<String> dairyCattle = processAnimalTypeData(list, Constants.DAIRY_CATTLE);
        List<String> improvedCattle = processAnimalTypeData(list, Constants.IMPROVED_CATTLE);
        List<String> goats = processAnimalTypeData(list, Constants.GOATS);
        List<String> sheep = processAnimalTypeData(list, Constants.SHEEP);
        List<String> donkeys = processAnimalTypeData(list, Constants.DONKEYS);
        List<String> camels = processAnimalTypeData(list, Constants.CAMELS);
        List<String> pigs = processAnimalTypeData(list, Constants.PIGS);
        List<String> chicken = processAnimalTypeData(list, Constants.LOCAL_CHICKEN);
        List<String> improvedChicken = processAnimalTypeData(list, Constants.IMPROVED_CHICKEN);
        List<String> ducks = processAnimalTypeData(list, Constants.DUCKS);
        List<String> beeHives = processAnimalTypeData(list, Constants.BEE_HIVES);
        List<String> fishPonds = processAnimalTypeData(list, Constants.FISH_PONDS);
        List<String> fishCages = processAnimalTypeData(list, Constants.FISH_CAGES);


        return new WgAnimalContributionDataSetObject(
                localCattle,
                dairyCattle,
                improvedCattle,
                goats,
                sheep,
                donkeys,
                camels,
                pigs,
                chicken,
                improvedChicken,
                ducks,
                beeHives,
                fishPonds,
                fishCages
        );
    }

    public List<String> processAnimalTypeData(List<WgLivestockContributionRetrofitModel> list, int animalCode) {
        List<WgLivestockContributionRetrofitModel> processedList = returnListForSpecificAnimalType(animalCode, list);
        List<String> reportString = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";

        for (WgLivestockContributionRetrofitModel currentItem : processedList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + "Income rank -> " + currentItem.getIncomeContributionRank() + " Income approx percentage -> " + currentItem.getIncomeContributionApproxPercentage() + " Consumption rank -> " + currentItem.getConsumptionContributionRank() + " Consumption approx percentage -> " + currentItem.getConsumptionContributionApproxPercentage() + " ";
            } else {
                reportString.add(currentReportString);
                currentReportString = "Income rank -> " + currentItem.getIncomeContributionRank() + " Income approx percentage -> " + currentItem.getIncomeContributionApproxPercentage() + " Consumption rank -> " + currentItem.getConsumptionContributionRank() + " Consumption approx percentage -> " + currentItem.getConsumptionContributionApproxPercentage() + " ";
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        reportString.add(currentReportString);
        return reportString;
    }
}
