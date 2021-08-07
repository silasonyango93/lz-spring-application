package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.dto.reports.wealthgroup.WgQuestionnaireDetailsResponseObjectDto;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupPopulationPercentageRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class WealthGroupReportService {

    public List<WgQuestionnaireDetailsRetrofitModel> fetchWealthGroupQuestionnaireDetails(int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgQuestionnaireDetailsRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupQuestionnaireDetails(questionnaireTypeId);
        try {
            Response<List<WgQuestionnaireDetailsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgQuestionnaireDetailsResponseObjectDto processQuestionnaireDetails(int questionnaireTypeId) {
        return new WgQuestionnaireDetailsResponseObjectDto(fetchWealthGroupQuestionnaireDetails(questionnaireTypeId));
    }
}
