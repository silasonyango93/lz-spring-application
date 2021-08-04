package livelihoodzone.service.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.CashIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgIncomeSourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeFoodSourcesService {

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    public void saveIncomeAndFoodSources(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        IncomeAndFoodSourcesResponses incomeAndFoodSourceResponses = wealthGroupQuestionnaireRequestDto.getIncomeAndFoodSourceResponses();

        //Livestock Production
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.LIVESTOCK_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getLivestockProduction()
        ));

        //Poultry Production
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.POULTRY_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getPoultryProduction()
        ));

        //Cash Crop Production
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASH_CROP_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getCashCropProduction()
        ));

        //Food Crop Production
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FOOD_CROP_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getFoodCropProduction()
        ));

        //Casual Waged Labour
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASUAL_WAGED_LABOUR_INCOME).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getCasualOrWagedLabour()
        ));

        //Formal Waged Labour
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FORMAL_WAGED_LABOUR).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getFormalWagedLabour()
        ));

        //Fishing
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FISHING).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getFishing()
        ));

        //Hunting and gathering
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.HUNTING_AND_GATHERING).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getHuntingAndGathering()
        ));

        //Small Businesses
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SMALL_BUSINESSES).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getSmallBusiness()
        ));

        //Firewood Collection
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FIREWOOD_COLLECTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getFirewoodOrCharcoal()
        ));

        //Petty Trading
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.PETTY_TRADING).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getPettyTrading()
        ));

        //Remittance And Gifts
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.REMITTANCE_AND_GIFTS).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getRemittance()
        ));

        //Bodaboda Transport
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BODABODA_TRANSPORT).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getBodaboda()
        ));

        //Bee Keeping
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BEE_KEEPING).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getBeeKeeping()
        ));

        //Sand Harvesting
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SAND_HARVESTING).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getSandHarvesting()
        ));


        //Other Income Sources
        WgIncomeSourcesEntity othersObject = new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.OTHERS_INCOME_SOURCES).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getOther().getValue()
        );
        othersObject.setExtraDescription(incomeAndFoodSourceResponses.getOther().getDescription());
        wgIncomeSourcesRepository.save(othersObject);
    }
}
