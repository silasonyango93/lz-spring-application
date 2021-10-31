package livelihoodzone.service.retrofit.livelihoodzones;

import livelihoodzone.service.retrofit.user_management.UserRolesRetrofitModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface LivelihoodZonesRetrofitService {
    @FormUrlEncoded
    @POST("fetch_a_county_sublocations_livelihoodzone_assignments")
    Call<List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel>> fetchACountySubLocationsLivelihoodZoneAssignment(@Field("countyId") int countyId);

    @FormUrlEncoded
    @POST("fetch_a_county_livelihoodzones")
    Call<List<CountyLivelihoodZonesRetrofitModel>> fetchACountyLivelihoodZones(@Field("countyId") int countyId);

    @FormUrlEncoded
    @POST("fetch_a_sublocation_livelihoodzone_pair")
    Call<List<SubLocationLivelihoodZonePairRetrofitModel>> fetchASubLocationLivelihoodZonePair(@Field("sublocationId") int sublocationId, @Field("livelihoodzoneId") int livelihoodzoneId);


    @FormUrlEncoded
    @POST("search_sublocation_by_name_and_county_id")
    Call<List<SubLocationSearchRetrofitModel>> searchSubLocationByNameFromSpecificCounty(@Field("subLocationName") String subLocationName, @Field("countyId") int countyId);


    @FormUrlEncoded
    @POST("fetch_county_sublocation_livelihoodzone_assignment")
    Call<List<CountySubLocationLivelihoodZoneAssignmentRetrofitModel>> getAllCountySubLocationLivelihoodZoneAssignments(@Field("countyId") int countyId);


    @FormUrlEncoded
    @POST("fetch_county_livelihoodzone_sublocations")
    Call<List<SubLocationRetrofitModel>> fetchCountyLivelihoodZoneSubLocations(@Field("countyId") int countyId, @Field("livelihoodzoneId") int livelihoodzoneId);
}
