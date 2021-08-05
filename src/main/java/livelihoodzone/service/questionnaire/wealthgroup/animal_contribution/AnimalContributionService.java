package livelihoodzone.service.questionnaire.wealthgroup.animal_contribution;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalContributionService {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    public void saveAverageNoAnimalsPerHousehold(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses = wealthGroupQuestionnaireRequestDto.getLivestockPoultryOwnershipResponses();

        //Cattle
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.CATTLE).getAnimalId(),
                livestockPoultryOwnershipResponses.getCattle()
        ));

        //Goats
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.GOATS).getAnimalId(),
                livestockPoultryOwnershipResponses.getGoats()
        ));

        //Sheep
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.SHEEP).getAnimalId(),
                livestockPoultryOwnershipResponses.getSheep()
        ));

        //Donkeys
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DONKEYS).getAnimalId(),
                livestockPoultryOwnershipResponses.getDonkeys()
        ));

        //Camels
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.CAMELS).getAnimalId(),
                livestockPoultryOwnershipResponses.getCamels()
        ));

        //Pigs
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.PIGS).getAnimalId(),
                livestockPoultryOwnershipResponses.getPigs()
        ));

        //Chicken
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.CHICKEN).getAnimalId(),
                livestockPoultryOwnershipResponses.getChicken()
        ));

        //Ducks
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DUCKS).getAnimalId(),
                livestockPoultryOwnershipResponses.getDucks()
        ));

        //BeeHives
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.BEE_HIVES).getAnimalId(),
                livestockPoultryOwnershipResponses.getBeeHives()
        ));

        //Fish Ponds
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.FISH_PONDS).getAnimalId(),
                livestockPoultryOwnershipResponses.getFishPonds()
        ));
    }
}
