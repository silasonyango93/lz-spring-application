package livelihoodzone.service.retrofit.livelihoodzones;

import livelihoodzone.service.retrofit.user_management.UserRolesRetrofitModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface CountySubLocationsLzAssignmentsRetrofitService {
    @FormUrlEncoded
    @POST("fetch_a_county_sublocations_livelihoodzone_assignments")
    Call<List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel>> fetchACountySubLocationsLivelihoodZoneAssignment(@Field("countyId") int countyId);
}
