package livelihoodzone.service.chores;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChoresService {

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    @Autowired
    AnimalsRepository animalsRepository;

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

}
