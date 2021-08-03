package livelihoodzone.service.retrofit.reports.zonelevel;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ZoneLevelReportRetrofitService {

    @GET("fetch_wealth_group_reports_comprehensively")
    Call<List<WealthGroupCharacteristicsRetrofitModel>> fetchWealthGroupCharacteristicsReportsComprehensively();

    @GET("fetch_questionnaire_details")
    Call<List<QuestionnaireDetailsRetrofitModel>> fetchQuestionnaireDetails();

    @GET("fetch_wealth_group_population_distribution")
    Call<List<WealthGroupPopulationPercentageRetrofitModel>> fetchWealthGroupPopulationPercentage();

    @GET("fetch_zone_level_crop_production")
    Call<List<LzCropProductionReportRetrofitModel>> fetchZoneLevelCropProduction();
}
