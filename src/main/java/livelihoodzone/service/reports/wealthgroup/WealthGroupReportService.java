package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.dto.reports.wealthgroup.WgIncomeSourcesReportResponseDto;
import livelihoodzone.dto.reports.wealthgroup.WgQuestionnaireDetailsResponseObjectDto;
import livelihoodzone.service.reports.wealthgroup.income_food_sources.IncomeFoodSourcesAggregateResponsesService;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class WealthGroupReportService {

    @Autowired
    IncomeFoodSourcesAggregateResponsesService incomeFoodSourcesAggregateResponsesService;

    public List<WgQuestionnaireDetailsRetrofitModel> fetchWealthGroupQuestionnaireDetails(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgQuestionnaireDetailsRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupQuestionnaireDetails(countyId,questionnaireTypeId);
        try {
            Response<List<WgQuestionnaireDetailsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgQuestionnaireDetailsResponseObjectDto processQuestionnaireDetails(int countyId, int questionnaireTypeId) {
        return new WgQuestionnaireDetailsResponseObjectDto(fetchWealthGroupQuestionnaireDetails(countyId,questionnaireTypeId));
    }

    public WgIncomeSourcesReportResponseDto processIncomeSourcesIntegratedData(int countyId, int questionnaireTypeId) {
        return incomeFoodSourcesAggregateResponsesService.processWealthGroupIncomeSources(incomeFoodSourcesAggregateResponsesService.fetchWealthGroupIncomeSources(countyId,questionnaireTypeId));
    }

}
