package livelihoodzone.service.reports.zonal;

import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.QuestionnaireDetailsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupCharacteristicsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class QuestionnaireDetailsService {

    public List<QuestionnaireDetailsRetrofitModel> fetchQuestionnaireDetails() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<QuestionnaireDetailsRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchQuestionnaireDetails();
        try {
            Response<List<QuestionnaireDetailsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }
}
