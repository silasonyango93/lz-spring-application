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

    public WgCropContributionReportResponseObject processCropContribution() {
        List<String> cropNameList = new ArrayList<>();
        List<String> cashIncomeRankList = new ArrayList<>();
        List<String> cashIncomeApproxPercentageList = new ArrayList<>();
        List<String> foodConsumptionRankList = new ArrayList<>();
        List<String> foodConsumptionApproxPercentageList = new ArrayList<>();

        return new WgCropContributionReportResponseObject(
                cropNameList,
                cashIncomeRankList,
                cashIncomeApproxPercentageList,
                foodConsumptionRankList,
                foodConsumptionApproxPercentageList
        );
    }
}
