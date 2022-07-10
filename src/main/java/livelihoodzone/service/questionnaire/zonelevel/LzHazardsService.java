package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.model.hazards.HazardResponses;
import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.county.LzHazardResponsesRepository;
import livelihoodzone.repository.questionnaire.county.LzHazardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LzHazardsService {
    @Autowired
    LzHazardResponsesRepository lzHazardResponsesRepository;

    @Autowired
    LzHazardsRepository lzHazardsRepository;


    public void saveHazards(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        HazardResponses hazardResponses = countyLevelQuestionnaireRequestDto.getHazardResponses();

        //Animal rustling
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_ANIMAL_RUSTLING).getLzHazardId(),
                hazardResponses.getAnimalRustling().getImportanceRank(),
                hazardResponses.getAnimalRustling().getNoExperiencedYears()
        ));

        //Bandsitry
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_BANDITRY).getLzHazardId(),
                hazardResponses.getBanditry().getImportanceRank(),
                hazardResponses.getBanditry().getNoExperiencedYears()
        ));

        //Terrorism
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_TERRORISM).getLzHazardId(),
                hazardResponses.getTerrorism().getImportanceRank(),
                hazardResponses.getTerrorism().getNoExperiencedYears()
        ));

        //Ethnic conflict
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_ETHNIC_CONFLICT).getLzHazardId(),
                hazardResponses.getEthnicConflict().getImportanceRank(),
                hazardResponses.getEthnicConflict().getNoExperiencedYears()
        ));

        //Poliical conflict
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_POLITICAL_CONFLICT).getLzHazardId(),
                hazardResponses.getPoliticalViolence().getImportanceRank(),
                hazardResponses.getPoliticalViolence().getNoExperiencedYears()
        ));

        //Drought
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_DROUGHT).getLzHazardId(),
                hazardResponses.getDrought().getImportanceRank(),
                hazardResponses.getDrought().getNoExperiencedYears()
        ));

        //Livestock pests diseases
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_LIVESTOCK_PESTS_DISEASES).getLzHazardId(),
                hazardResponses.getLivestockPestsAndDiseases().getImportanceRank(),
                hazardResponses.getLivestockPestsAndDiseases().getNoExperiencedYears()
        ));

        //Hail storms
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_HAILSTORMS_OR_FROST).getLzHazardId(),
                hazardResponses.getHailstormsOrFrost().getImportanceRank(),
                hazardResponses.getHailstormsOrFrost().getNoExperiencedYears()
        ));

        //Flooding
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_FLOODING).getLzHazardId(),
                hazardResponses.getFlooding().getImportanceRank(),
                hazardResponses.getFlooding().getNoExperiencedYears()
        ));

        //Landslides
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_LANDSLIDES).getLzHazardId(),
                hazardResponses.getLandslides().getImportanceRank(),
                hazardResponses.getLandslides().getNoExperiencedYears()
        ));

        //High winds
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_HIGH_WINDS).getLzHazardId(),
                hazardResponses.getHighWindsOrCyclones().getImportanceRank(),
                hazardResponses.getHighWindsOrCyclones().getNoExperiencedYears()
        ));

        //Bush fires
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_BUSH_FIRES).getLzHazardId(),
                hazardResponses.getBushFires().getImportanceRank(),
                hazardResponses.getBushFires().getNoExperiencedYears()
        ));

        //Crop pests
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_CROP_PESTS).getLzHazardId(),
                hazardResponses.getCropPests().getImportanceRank(),
                hazardResponses.getCropPests().getNoExperiencedYears()
        ));

        //Locust invasion
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_LOCUST_INVASION).getLzHazardId(),
                hazardResponses.getLocustInvasion().getImportanceRank(),
                hazardResponses.getLocustInvasion().getNoExperiencedYears()
        ));

        //Crop diseases
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_CROP_DISEASES).getLzHazardId(),
                hazardResponses.getCropDiseases().getImportanceRank(),
                hazardResponses.getCropDiseases().getNoExperiencedYears()
        ));

        //Terminal illness
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_TERMINAL_ILLNESS).getLzHazardId(),
                hazardResponses.getTerminalIllnesses().getImportanceRank(),
                hazardResponses.getTerminalIllnesses().getNoExperiencedYears()
        ));

        //Malaria outbreak
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_MALARIA_OUTBREAK).getLzHazardId(),
                hazardResponses.getMalariaPowerOutBreak().getImportanceRank(),
                hazardResponses.getMalariaPowerOutBreak().getNoExperiencedYears()
        ));

        //Water-borne diseases
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_WATER_BORNE_DISEASE).getLzHazardId(),
                hazardResponses.getWaterBornDiseases().getImportanceRank(),
                hazardResponses.getWaterBornDiseases().getNoExperiencedYears()
        ));

        //Human-wildlife conflict
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_HUMAN_WILDLIFE_CONFLICT).getLzHazardId(),
                hazardResponses.getHumanWildlifeConflict().getImportanceRank(),
                hazardResponses.getHumanWildlifeConflict().getNoExperiencedYears()
        ));

        //High food prices
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_HIGH_FOOD_PRICES).getLzHazardId(),
                hazardResponses.getHighFoodPrices().getImportanceRank(),
                hazardResponses.getHighFoodPrices().getNoExperiencedYears()
        ));

        //Market food shortage
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET).getLzHazardId(),
                hazardResponses.getMarketFoodShortages().getImportanceRank(),
                hazardResponses.getMarketFoodShortages().getNoExperiencedYears()
        ));

        //Drinking water shortage
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_DRINKING_WATER_SHORTAGES).getLzHazardId(),
                hazardResponses.getDrinkingWaterShortages().getImportanceRank(),
                hazardResponses.getDrinkingWaterShortages().getNoExperiencedYears()
        ));

        //Invasive plants
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_INVASIVE_PLANT_SPECIES).getLzHazardId(),
                hazardResponses.getInvasivePlants().getImportanceRank(),
                hazardResponses.getInvasivePlants().getNoExperiencedYears()
        ));

        //Others specify
        lzHazardResponsesRepository.save(new LzHazardResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                lzHazardsRepository.findByLzHazardCode(Constants.HZ_OTHERS).getLzHazardId(),
                hazardResponses.getOthers().getImportanceRank(),
                hazardResponses.getOthers().getNoExperiencedYears()
        ));
    }

    public void updateHazards(int lzQuestionnaireSessionId, HazardResponses hazardResponses) {
        lzHazardResponsesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setHazardResponses(hazardResponses);
        saveHazards(countyLevelQuestionnaireRequestDto,savedQuestionnaireSession);
    }
}
