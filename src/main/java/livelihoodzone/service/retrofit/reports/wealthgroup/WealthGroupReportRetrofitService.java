package livelihoodzone.service.retrofit.reports.wealthgroup;

import livelihoodzone.service.retrofit.reports.zonelevel.LzCropProductionReportRetrofitModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface WealthGroupReportRetrofitService {

    @FormUrlEncoded
    @POST("fetch_wealth_group_questionnaire_details")
    Call<List<WgQuestionnaireDetailsRetrofitModel>> fetchWealthGroupQuestionnaireDetails(@Field("questionnaireTypeId") int questionnaireTypeId);
}
