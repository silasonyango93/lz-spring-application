package livelihoodzone.service.reports.wealthgroup.crop_contribution;

import livelihoodzone.dto.reports.wealthgroup.WgCropContributionReportResponseObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgCropContributionRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class CropContributionReportsService {

    public List<WgCropContributionRetrofitModel> fetchWealthCropContribution(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgCropContributionRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupCropProduction(countyId,questionnaireTypeId);
        try {
            Response<List<WgCropContributionRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgCropContributionReportResponseObject processCropContribution(List<WgCropContributionRetrofitModel> items) {
        List<String> cropNameList = processCropNamesColumn(items);
        List<String> cashIncomeRankList = processCashIncomeRankColumn(items);
        List<String> cashIncomeApproxPercentageList = processCashIncomeApproxPercentage(items);
        List<String> foodConsumptionRankList = processFoodConsumptionRank(items);
        List<String> foodConsumptionApproxPercentageList = processFoodConsumptionApproxPercentage(items);




        return new WgCropContributionReportResponseObject(
                cropNameList,
                cashIncomeRankList,
                cashIncomeApproxPercentageList,
                foodConsumptionRankList,
                foodConsumptionApproxPercentageList
        );
    }

    private List<String> processCropNamesColumn(List<WgCropContributionRetrofitModel> items) {
        List<String> cropNameList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (!items.isEmpty()) {
            currentQuestionnaireSessionId = items.get(0).getWgQuestionnaireSessionId();
        }

        String currentReportString = "";
        int counter = 1;

        for (WgCropContributionRetrofitModel currentItem : items) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getCropName() + ", ";
                counter++;
            } else {
                cropNameList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getCropName() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        cropNameList.add(currentReportString);
        return cropNameList;
    }


    private List<String> processCashIncomeRankColumn(List<WgCropContributionRetrofitModel> items) {
        List<String> cashIncomeRankList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (!items.isEmpty()) {
            currentQuestionnaireSessionId = items.get(0).getWgQuestionnaireSessionId();
        }

        String currentReportString = "";
        int counter = 1;

        for (WgCropContributionRetrofitModel currentItem : items) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getCropName() + " -> " +currentItem.getCashIncomeRank() + ", ";
                counter++;
            } else {
                cashIncomeRankList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getCropName() + " -> " +currentItem.getCashIncomeRank() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        cashIncomeRankList.add(currentReportString);
        return cashIncomeRankList;
    }


    private List<String> processCashIncomeApproxPercentage(List<WgCropContributionRetrofitModel> items) {
        List<String> cashIncomeApproxPercentageList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (!items.isEmpty()) {
            currentQuestionnaireSessionId = items.get(0).getWgQuestionnaireSessionId();
        }

        String currentReportString = "";
        int counter = 1;

        for (WgCropContributionRetrofitModel currentItem : items) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getCropName() + " -> " +currentItem.getCashIncomeApproxPercentage() + ", ";
                counter++;
            } else {
                cashIncomeApproxPercentageList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getCropName() + " -> " +currentItem.getCashIncomeApproxPercentage() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        cashIncomeApproxPercentageList.add(currentReportString);
        return cashIncomeApproxPercentageList;
    }


    private List<String> processFoodConsumptionRank(List<WgCropContributionRetrofitModel> items) {
        List<String> foodConsumptionRankList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (!items.isEmpty()) {
            currentQuestionnaireSessionId = items.get(0).getWgQuestionnaireSessionId();
        }

        String currentReportString = "";
        int counter = 1;

        for (WgCropContributionRetrofitModel currentItem : items) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getCropName() + " -> " +currentItem.getFoodConsumptionRank() + ", ";
                counter++;
            } else {
                foodConsumptionRankList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getCropName() + " -> " +currentItem.getFoodConsumptionRank() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        foodConsumptionRankList.add(currentReportString);
        return foodConsumptionRankList;
    }


    private List<String> processFoodConsumptionApproxPercentage(List<WgCropContributionRetrofitModel> items) {
        List<String> foodConsumptionApproxPercentageList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (!items.isEmpty()) {
            currentQuestionnaireSessionId = items.get(0).getWgQuestionnaireSessionId();
        }

        String currentReportString = "";
        int counter = 1;

        for (WgCropContributionRetrofitModel currentItem : items) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getCropName() + " -> " +currentItem.getFoodConsumptionApproxPercentage() + ", ";
                counter++;
            } else {
                foodConsumptionApproxPercentageList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getCropName() + " -> " +currentItem.getFoodConsumptionApproxPercentage() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        foodConsumptionApproxPercentageList.add(currentReportString);
        return foodConsumptionApproxPercentageList;
    }
}
