package livelihoodzone.service.questionnaire.wealthgroup.animal_contribution;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions.LivestockContributionResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalContributionService {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    @Autowired
    WgAnimalContributionsRepository wgAnimalContributionsRepository;

    public void saveAverageNoAnimalsPerHousehold(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses = wealthGroupQuestionnaireRequestDto.getLivestockPoultryOwnershipResponses();

        //Cattle
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.LOCAL_CATTLE).getAnimalId(),
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
                animalsRepository.findByAnimalCode(Constants.LOCAL_CHICKEN).getAnimalId(),
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

        //Improved Cattle
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.IMPROVED_CATTLE).getAnimalId(),
                livestockPoultryOwnershipResponses.getImprovedCattle()
        ));

        //Improved Chicken
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.IMPROVED_CHICKEN).getAnimalId(),
                livestockPoultryOwnershipResponses.getImprovedChicken()
        ));

        //Fish Cages
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId(),
                0.0 //livestockPoultryOwnershipResponses.getFishCages() -- Commented out since the live app sends improved chicken data to this field on the backend
        ));

        //Dairy Cattle
        wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DAIRY_CATTLE).getAnimalId(),
                livestockPoultryOwnershipResponses.getDairyCattle()
        ));
    }




    public void saveAnimalContributions(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        LivestockContributionResponses livestockContributionResponses = wealthGroupQuestionnaireRequestDto.getLivestockContributionResponses();

        //Cattle
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.LOCAL_CATTLE).getAnimalId(),
                (int) livestockContributionResponses.getCattle().getIncomeRank().getActualValue(),
                livestockContributionResponses.getCattle().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getCattle().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getCattle().getConsumptionPercentage().getActualValue()

        ));


        //Goats
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.GOATS).getAnimalId(),
                (int) livestockContributionResponses.getGoats().getIncomeRank().getActualValue(),
                livestockContributionResponses.getGoats().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getGoats().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getGoats().getConsumptionPercentage().getActualValue()

        ));


        //Sheep
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.SHEEP).getAnimalId(),
                (int) livestockContributionResponses.getSheep().getIncomeRank().getActualValue(),
                livestockContributionResponses.getSheep().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getSheep().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getSheep().getConsumptionPercentage().getActualValue()

        ));

        //Donkeys
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DONKEYS).getAnimalId(),
                (int) livestockContributionResponses.getDonkeys().getIncomeRank().getActualValue(),
                livestockContributionResponses.getDonkeys().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getDonkeys().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getDonkeys().getConsumptionPercentage().getActualValue()

        ));

        //Camels
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.CAMELS).getAnimalId(),
                (int) livestockContributionResponses.getCamels().getIncomeRank().getActualValue(),
                livestockContributionResponses.getCamels().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getCamels().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getCamels().getConsumptionPercentage().getActualValue()

        ));

        //Pigs
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.PIGS).getAnimalId(),
                (int) livestockContributionResponses.getPigs().getIncomeRank().getActualValue(),
                livestockContributionResponses.getPigs().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getPigs().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getPigs().getConsumptionPercentage().getActualValue()

        ));

        //Chicken
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.LOCAL_CHICKEN).getAnimalId(),
                (int) livestockContributionResponses.getChicken().getIncomeRank().getActualValue(),
                livestockContributionResponses.getChicken().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getChicken().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getChicken().getConsumptionPercentage().getActualValue()

        ));

        //Improved Chicken
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.IMPROVED_CHICKEN).getAnimalId(),
                (int) livestockContributionResponses.getImprovedChicken().getIncomeRank().getActualValue(),
                livestockContributionResponses.getImprovedChicken().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getImprovedChicken().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getImprovedChicken().getConsumptionPercentage().getActualValue()

        ));

        //Ducks
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DUCKS).getAnimalId(),
                (int) livestockContributionResponses.getDucks().getIncomeRank().getActualValue(),
                livestockContributionResponses.getDucks().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getDucks().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getDucks().getConsumptionPercentage().getActualValue()

        ));

        //Bee Hives
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.BEE_HIVES).getAnimalId(),
                (int) livestockContributionResponses.getBeeHives().getIncomeRank().getActualValue(),
                livestockContributionResponses.getBeeHives().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getBeeHives().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getBeeHives().getConsumptionPercentage().getActualValue()

        ));

        //Fish Ponds
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.FISH_PONDS).getAnimalId(),
                (int) livestockContributionResponses.getFishPonds().getIncomeRank().getActualValue(),
                livestockContributionResponses.getFishPonds().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getFishPonds().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getFishPonds().getConsumptionPercentage().getActualValue()

        ));


        //Fish Cages
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId(),
                (int) livestockContributionResponses.getFishCages().getIncomeRank().getActualValue(),
                livestockContributionResponses.getFishCages().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getFishCages().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getFishCages().getConsumptionPercentage().getActualValue()

        ));


        //Dairy Cattle
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.DAIRY_CATTLE).getAnimalId(),
                (int) livestockContributionResponses.getDairyCattle().getIncomeRank().getActualValue(),
                livestockContributionResponses.getDairyCattle().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getDairyCattle().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getDairyCattle().getConsumptionPercentage().getActualValue()

        ));

        //Improved Cattle
        wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                animalsRepository.findByAnimalCode(Constants.IMPROVED_CATTLE).getAnimalId(),
                (int) livestockContributionResponses.getImprovedCattle().getIncomeRank().getActualValue(),
                livestockContributionResponses.getImprovedCattle().getIncomePercentage().getActualValue(),
                (int) livestockContributionResponses.getImprovedCattle().getConsumptionRank().getActualValue(),
                livestockContributionResponses.getImprovedCattle().getConsumptionPercentage().getActualValue()

        ));
    }
}
