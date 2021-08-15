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
    Call<List<WgQuestionnaireDetailsRetrofitModel>> fetchWealthGroupQuestionnaireDetails(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_income_sources")
    Call<List<WgIncomeSourcesRetrofitModel>> fetchWealthGroupIncomeSources(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_food_sources")
    Call<List<WgFoodSourcesRetrofitModel>> fetchWealthGroupFoodSources(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_crop_production")
    Call<List<WgCropContributionRetrofitModel>> fetchWealthGroupCropProduction(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_livestock_ownership")
    Call<List<WgAnimalOwnershipRetrofitModel>> fetchWealthGroupLivestockOwnership(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_livestock_contribution")
    Call<List<WgLivestockContributionRetrofitModel>> fetchWealthGroupLivestockContribution(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);

    @FormUrlEncoded
    @POST("fetch_wealth_group_labour_patterns")
    Call<List<WgLabourPatternsRetrofitModel>> fetchWealthGroupLabourPatterns(@Field("countyId") int countyId, @Field("questionnaireTypeId") int questionnaireTypeId);
}
