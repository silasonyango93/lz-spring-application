package livelihoodzone.service.livelihoodzones;

import livelihoodzone.dto.livelihoodzones.CountyLivelihoodZonesAssignmentDto;
import livelihoodzone.dto.livelihoodzones.CountyLivelihoodZonesUpdateDetailsDto;
import livelihoodzone.dto.livelihoodzones.LivelihoodZonesUpdateRequestModel;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentStatus;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.livelihoodzones.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LivelihoodZonesService {

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    SubLocationRepository subLocationRepository;

    @Autowired
    SubLocationsLivelihoodZoneAssignmentRepository subLocationsLivelihoodZoneAssignmentRepository;

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


    public boolean updateACountyLivelihoodZones(CountyLivelihoodZonesUpdateDetailsDto countyLivelihoodZonesUpdateDetailsDto) {
        if (countyLivelihoodZonesUpdateDetailsDto.isActive()) {
            return addACountyLivelihoodZones(countyLivelihoodZonesUpdateDetailsDto.getCountyId(), countyLivelihoodZonesUpdateDetailsDto.getLivelihoodZoneIds());
        }
        return softDeleteACountyLivelihoodZones(countyLivelihoodZonesUpdateDetailsDto.getCountyId(), countyLivelihoodZonesUpdateDetailsDto.getLivelihoodZoneIds());
    }

    public boolean addACountyLivelihoodZones(int countyId, List<LivelihoodZonesUpdateRequestModel> livelihoodZoneIds) {
        List<CountyLivelihoodZonesAssignmentEntity> currentlyExistingAssignments = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);
        List<LivelihoodZonesUpdateRequestModel> noneDuplicateLivelihoodZoneIds = new ArrayList<>();
        List<LivelihoodZonesUpdateRequestModel> duplicateLivelihoodZoneIds = new ArrayList<>();

        for (LivelihoodZonesUpdateRequestModel currentId : livelihoodZoneIds) {
            List<CountyLivelihoodZonesAssignmentEntity> foundExistingAssignments = new ArrayList<>();
            for (CountyLivelihoodZonesAssignmentEntity currentAssignment : currentlyExistingAssignments) {
                if (currentId.getLivelihoodZoneId() == currentAssignment.getLivelihoodZoneId()) {
                    foundExistingAssignments.add(currentAssignment);
                }
            }

            if (foundExistingAssignments.size() == 0) {
                noneDuplicateLivelihoodZoneIds.add(currentId);
            } else {
                duplicateLivelihoodZoneIds.add(currentId);
            }
        }


        List<CountyLivelihoodZonesAssignmentEntity> newCountyLivelihoodZonesAssignmentEntityList = new ArrayList<>();
        for (LivelihoodZonesUpdateRequestModel currentRequestModel : noneDuplicateLivelihoodZoneIds) {
            newCountyLivelihoodZonesAssignmentEntityList.add(new CountyLivelihoodZonesAssignmentEntity(
                    countyId,
                    currentRequestModel.getLivelihoodZoneId(),
                    1
            ));
        }

        List<CountyLivelihoodZonesAssignmentEntity> existingCountyLivelihoodZonesAssignmentEntityList = new ArrayList<>();
        for (LivelihoodZonesUpdateRequestModel currentLzId : duplicateLivelihoodZoneIds) {
            existingCountyLivelihoodZonesAssignmentEntityList.add(countyLivelihoodZonesAssignmentRepository.findByLivelihoodZoneId(currentLzId.getLivelihoodZoneId()).get(0));
        }

        for (CountyLivelihoodZonesAssignmentEntity currentAssignment : existingCountyLivelihoodZonesAssignmentEntityList) {
            currentAssignment.setIsActive(1);
        }

        return countyLivelihoodZonesAssignmentRepository.saveAll(newCountyLivelihoodZonesAssignmentEntityList).size() > 0 || countyLivelihoodZonesAssignmentRepository.saveAll(existingCountyLivelihoodZonesAssignmentEntityList).size() > 0;
    }

    public boolean softDeleteACountyLivelihoodZones(int countyId, List<LivelihoodZonesUpdateRequestModel> livelihoodZoneIds) {
        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);
        List<CountyLivelihoodZonesAssignmentEntity> assignmentsToBeDeleted = new ArrayList<>();

        for (LivelihoodZonesUpdateRequestModel currentLzId : livelihoodZoneIds) {
            for (CountyLivelihoodZonesAssignmentEntity currentAssignment : countyLivelihoodZonesAssignmentEntityList) {

                if (currentAssignment.getLivelihoodZoneId() == currentLzId.getLivelihoodZoneId()) {
                    currentAssignment.setIsActive(0);
                    assignmentsToBeDeleted.add(currentAssignment);
                }

            }
        }

        return countyLivelihoodZonesAssignmentRepository.saveAll(assignmentsToBeDeleted).size() > 0;
    }


    public boolean subLocationLivelihoodZoneAssignment(int sublocationId, int livelihoodzoneId) {
        if (fetchASubLocationLivelihoodZonePair(sublocationId, livelihoodzoneId).isEmpty()) {
            subLocationsLivelihoodZoneAssignmentRepository.save(new SubLocationsLivelihoodZoneAssignmentEntity(
                    sublocationId,
                    livelihoodzoneId
            ));
            return true;
        }
        return false;
    }

    public List<SubLocationLivelihoodZonePairRetrofitModel> fetchASubLocationLivelihoodZonePair(int sublocationId, int livelihoodzoneId) {
        LivelihoodZonesRetrofitService livelihoodZonesRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(LivelihoodZonesRetrofitService.class);
        Call<List<SubLocationLivelihoodZonePairRetrofitModel>> callSync = livelihoodZonesRetrofitService.fetchASubLocationLivelihoodZonePair(sublocationId, livelihoodzoneId);
        try {
            Response<List<SubLocationLivelihoodZonePairRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public List<SubLocationSearchRetrofitModel> searchSubLocationByNameFromSpecificCounty(String subLocationName, int countyId) {
        LivelihoodZonesRetrofitService livelihoodZonesRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(LivelihoodZonesRetrofitService.class);
        Call<List<SubLocationSearchRetrofitModel>> callSync = livelihoodZonesRetrofitService.searchSubLocationByNameFromSpecificCounty(subLocationName,countyId);
        try {
            Response<List<SubLocationSearchRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public void updateASubLocationLivelihoodZone(int subLocationId, int livelihoodZoneId, int countyId) {
        SubLocationsLivelihoodZoneAssignmentEntity subLocationsLivelihoodZoneAssignmentEntity = subLocationsLivelihoodZoneAssignmentRepository.findBySubLocationId(subLocationId);

        if (subLocationsLivelihoodZoneAssignmentEntity == null) {
            subLocationsLivelihoodZoneAssignmentRepository.save(new SubLocationsLivelihoodZoneAssignmentEntity(
                    subLocationId,
                    livelihoodZoneId
            ));
        } else {
            subLocationsLivelihoodZoneAssignmentEntity.setLivelihoodZoneId(livelihoodZoneId);
            subLocationsLivelihoodZoneAssignmentRepository.save(subLocationsLivelihoodZoneAssignmentEntity);
        }

        List<CountyLivelihoodZonesAssignmentEntity> currentlyExistingAssignments = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);

        List<CountyLivelihoodZonesAssignmentEntity> assignmentsUnderSameLivelihoodZone = currentlyExistingAssignments
                .stream()
                .filter(c -> c.getLivelihoodZoneId() == livelihoodZoneId)
                .collect(Collectors.toList());

        if (assignmentsUnderSameLivelihoodZone.isEmpty()) {
            countyLivelihoodZonesAssignmentRepository.save(new CountyLivelihoodZonesAssignmentEntity(
                    countyId,
                    livelihoodZoneId,
                    1
            ));
        }
    }
}
