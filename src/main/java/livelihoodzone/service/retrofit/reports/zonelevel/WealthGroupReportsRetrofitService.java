package livelihoodzone.service.retrofit.reports.zonelevel;

import livelihoodzone.service.retrofit.livelihoodzones.CountyLivelihoodZonesRetrofitModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface WealthGroupReportsRetrofitService {

    @GET("fetch_wealth_group_reports_comprehensively")
    Call<List<WealthGroupCharectaristicsRetrofitModel>> fetchWealthGroupCharacteristicsReportsComprehensively();
}
