package livelihoodzone.service.chores;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import livelihoodzone.common.Constants;
import livelihoodzone.dto.CountySampledSubLocationsObject;
import livelihoodzone.dto.LivelihoodZoneSampledSubLocationsObject;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.ZoneLevelSampledSubLocationsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.county.ZoneLevelSampledSubLocationsRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import livelihoodzone.service.reports.sampled_sublocations.excel.CountySampledSubLocationsExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
@Transactional
public class ChoresService {

    private XSSFWorkbook workbook;

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    @Autowired
    ZoneLevelSampledSubLocationsRepository zoneLevelSampledSubLocationsRepository;

    @Autowired
    SubLocationRepository subLocationRepository;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    CountySampledSubLocationsExcelService countySampledSubLocationsExcelService;

    public void resetCountyFishCagesValue(int countyId) {

        List<WgAveAnimalNoPerHouseholdEntity> updatedEntities = new ArrayList<>();

        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionRepository.findByCountyId(countyId);


        for (WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity : wgQuestionnaireSessionEntityList) {
            List<WgAveAnimalNoPerHouseholdEntity> wgAveAnimalNoPerHouseholdEntityList = wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId());

            for (WgAveAnimalNoPerHouseholdEntity wgAveAnimalNoPerHouseholdEntity : wgAveAnimalNoPerHouseholdEntityList) {
                if (wgAveAnimalNoPerHouseholdEntity.getAnimalId() == animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId()) {
                    wgAveAnimalNoPerHouseholdEntity.setAverageNumber(0.0);
                    updatedEntities.add(wgAveAnimalNoPerHouseholdEntity);
                }
            }
        }

        wgAveAnimalNoPerHouseholdRepository.saveAll(updatedEntities);

    }


    public void updateFishCagesValue(int wgQuestionnaireSessionId, double fishCagesValue) {
        List<WgAveAnimalNoPerHouseholdEntity> updatedEntities = new ArrayList<>();
        List<WgAveAnimalNoPerHouseholdEntity> wgAveAnimalNoPerHouseholdEntityList = wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);

        for (WgAveAnimalNoPerHouseholdEntity wgAveAnimalNoPerHouseholdEntity : wgAveAnimalNoPerHouseholdEntityList) {
            if (wgAveAnimalNoPerHouseholdEntity.getAnimalId() == animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId()) {
                wgAveAnimalNoPerHouseholdEntity.setAverageNumber(fishCagesValue);
                updatedEntities.add(wgAveAnimalNoPerHouseholdEntity);
            }
        }

        wgAveAnimalNoPerHouseholdRepository.saveAll(updatedEntities);

    }


    public void downloadCountySampledSubLocationsExcelFile(HttpServletResponse response, int countyId) throws IOException {
        workbook = new XSSFWorkbook();
        workbook.createSheet("Sampled Sub-Locations");

        workbook = countySampledSubLocationsExcelService.processData(generateCountySampledSubLocations(countyId),workbook);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }


    public CountySampledSubLocationsObject generateCountySampledSubLocations(int countyId) {
        CountySampledSubLocationsObject countySampledSubLocationsObject = new CountySampledSubLocationsObject();
        countySampledSubLocationsObject.setCountyId(countyId);
        countySampledSubLocationsObject.setCountyName(countiesRepository.findByCountyId(countyId).getCountyName());

        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);

        List<LivelihoodZoneSampledSubLocationsObject> livelihoodZoneSampledSubLocationsObjectList = new ArrayList<>();

        for (CountyLivelihoodZonesAssignmentEntity countyLivelihoodZonesAssignmentEntity : countyLivelihoodZonesAssignmentEntityList) {

            if (countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId() != 17) {

                //Add zone level sampled sub-locations
                List<SubLocationEntity> CountyLivelihoodZoneSampledSubLocations = new ArrayList<>(generateZoneLevelSampledSubLocations(countyId, countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId()));

                //Add wealth group sampled sub-locations
                CountyLivelihoodZoneSampledSubLocations.addAll(generateWealthGroupSampledSubLocations(countyId, countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId()));

                //Remove duplicate sub-locations
                Set<SubLocationEntity> sublocationsSet = new HashSet<>(CountyLivelihoodZoneSampledSubLocations);
                CountyLivelihoodZoneSampledSubLocations.clear();
                CountyLivelihoodZoneSampledSubLocations.addAll(sublocationsSet);

                CountyLivelihoodZoneSampledSubLocations.remove(subLocationRepository.findBySubLocationId(7597));

                livelihoodZoneSampledSubLocationsObjectList.add(new LivelihoodZoneSampledSubLocationsObject(
                        countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(),
                        livelihoodZonesRepository.findByLivelihoodZoneId(countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId()).getLivelihoodZoneName(),
                        CountyLivelihoodZoneSampledSubLocations
                ));

            }


        }

        countySampledSubLocationsObject.setLivelihoodZoneSampledSubLocationsObjectList(livelihoodZoneSampledSubLocationsObjectList);
        return countySampledSubLocationsObject;
    }


    public List<SubLocationEntity> generateZoneLevelSampledSubLocations(int countyId, int livelihoodZoneId) {
        List<SubLocationEntity> subLocationEntityList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneId(countyId, livelihoodZoneId);

        List<ZoneLevelSampledSubLocationsEntity> zoneLevelSampledSubLocationsEntityList = new ArrayList<>();
        for (LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity : lzQuestionnaireSessionEntityList) {
            List<ZoneLevelSampledSubLocationsEntity> fetchedQuestionnaireList = zoneLevelSampledSubLocationsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId());
            zoneLevelSampledSubLocationsEntityList.addAll(fetchedQuestionnaireList);
        }

        for (ZoneLevelSampledSubLocationsEntity zoneLevelSampledSubLocationsEntity : zoneLevelSampledSubLocationsEntityList) {
            subLocationEntityList.add(subLocationRepository.findBySubLocationId(zoneLevelSampledSubLocationsEntity.getSubLocationId()));
        }
        return subLocationEntityList;
    }


    public List<SubLocationEntity> generateWealthGroupSampledSubLocations(int countyId, int livelihoodZoneId) {
        List<SubLocationEntity> subLocationEntityList = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneId(countyId,livelihoodZoneId);

        for (WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity : wgQuestionnaireSessionEntityList) {
            subLocationEntityList.add(subLocationRepository.findBySubLocationId(wgQuestionnaireSessionEntity.getSubLocationId()));
        }
        return subLocationEntityList;
    }

}
