package livelihoodzone.service.retrofit.reports.zonelevel;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface WealthGroupReportsRetrofitService {

    @GET("fetch_wealth_group_reports_comprehensively")
    Call<List<WealthGroupCharacteristicsRetrofitModel>> fetchWealthGroupCharacteristicsReportsComprehensively();
}
