package livelihoodzone.service.livelihoodzones;

import livelihoodzone.dto.livelihoodzones.CountyLivelihoodZonesAssignmentDto;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentStatus;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.livelihoodzones.CountyLivelihoodZonesRetrofitModel;
import livelihoodzone.service.retrofit.livelihoodzones.CountySubLocationsLivelihoodZonesAssignmentRetrofitModel;
import livelihoodzone.service.retrofit.livelihoodzones.LivelihoodZonesRetrofitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LivelihoodZonesService {

    @Autowired
    CountiesRepository countiesRepository;

    public CountyLivelihoodZonesAssignmentDto fetchACountyLiveliHoodZones(int countyId) {
        List<CountyLivelihoodZonesRetrofitModel> countyLivelihoodZonesRetrofitModelList = retrofitFetchACountyLiveliHoodZones(countyId);
        if (countyLivelihoodZonesRetrofitModelList == null) {
            return null;
        }

        if (countyLivelihoodZonesRetrofitModelList.size() == 0) {
            return new CountyLivelihoodZonesAssignmentDto(
                    CountyLivelihoodZonesAssignmentStatus.SUCCESS_EMPTY_LIST,
                    countyId,
                    countiesRepository.findByCountyId(countyId).getCountyName(),
                    new ArrayList<LivelihoodZonesEntity>()
            );
        }


        List<LivelihoodZonesEntity> livelihoodZonesEntityList = new ArrayList<>();

        for (CountyLivelihoodZonesRetrofitModel currentRetrofitModel : countyLivelihoodZonesRetrofitModelList) {
            livelihoodZonesEntityList.add(new LivelihoodZonesEntity(
                    currentRetrofitModel.getLivelihoodZoneId(),
                    currentRetrofitModel.getLivelihoodZoneName(),
                    currentRetrofitModel.getLivelihoodZoneCode()
            ));
        }

        return new CountyLivelihoodZonesAssignmentDto(
                CountyLivelihoodZonesAssignmentStatus.SUCCESS_WITH_ITEMS,
                countyId,
                countiesRepository.findByCountyId(countyId).getCountyName(),
                livelihoodZonesEntityList
        );

    }


    public List<CountyLivelihoodZonesRetrofitModel> retrofitFetchACountyLiveliHoodZones(int countyId) {
        LivelihoodZonesRetrofitService livelihoodZonesRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(LivelihoodZonesRetrofitService.class);
        Call<List<CountyLivelihoodZonesRetrofitModel>> callSync = livelihoodZonesRetrofitService.fetchACountyLivelihoodZones(countyId);
        try {
            Response<List<CountyLivelihoodZonesRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }
}
