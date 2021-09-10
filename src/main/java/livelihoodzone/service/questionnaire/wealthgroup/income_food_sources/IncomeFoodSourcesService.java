package livelihoodzone.service.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponsesDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeFoodSourcesService {

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    @Autowired
    FoodTypesRepository foodTypesRepository;

    @Autowired
    FoodSourcesRepository foodSourcesRepository;

    @Autowired
    WgFoodSourcesResponsesRepository wgFoodSourcesResponsesRepository;

    public void saveIncomeSources(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        IncomeAndFoodSourcesResponses incomeAndFoodSourceResponses = wealthGroupQuestionnaireRequestDto.getIncomeAndFoodSourceResponses();

        //Livestock Production
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.LIVESTOCK_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getLivestockProduction()
        ));


        //Pasture/Fodder
        wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.PASTURE_FODDER_PRODUCTION).getCashIncomeSourceId(),
                incomeAndFoodSourceResponses.getPastureFodderProduction()
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

    public void saveFoodSources(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        FoodConsumptionResponsesDto foodConsumptionResponsesDto = wealthGroupQuestionnaireRequestDto.getFoodConsumptionResponses();


        //Maize and Posho
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getMaizeAndPosho().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMaizeAndPosho().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMaizeAndPosho().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getMaizeAndPosho().getGifts()
        ));




        //Wheat Barley Rye
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getWheatOrBarley().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getWheatOrBarley().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getWheatOrBarley().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getWheatOrBarley().getGifts()
        ));





        //Sorghum Millet Products
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getSorghumOrMillet().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getSorghumOrMillet().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getSorghumOrMillet().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getSorghumOrMillet().getGifts()
        ));




        //Rice And Products
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getRice().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getRice().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getRice().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getRice().getGifts()
        ));




        //Beans
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getBeans().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getBeans().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getBeans().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getBeans().getGifts()
        ));




        //Other Pulses
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getPulses().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getPulses().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getPulses().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getPulses().getGifts()
        ));





        //Vegetables
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getVegetables().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getVegetables().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getVegetables().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getVegetables().getGifts()
        ));




        //Fruits And Berries
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getFruits().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getFruits().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getFruits().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getFruits().getGifts()
        ));




        //White Root Tubers
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getWhiteRoots().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getWhiteRoots().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getWhiteRoots().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getWhiteRoots().getGifts()
        ));




        //Meat
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getMeat().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMeat().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMeat().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getMeat().getGifts()
        ));




        //Milk And Dairy Products
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getMilk().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMilk().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getMilk().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getMilk().getGifts()
        ));





        //Fish And Sea Food
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getFish().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getFish().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getFish().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getFish().getGifts()
        ));





        //Eggs
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getEggs().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getEggs().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getEggs().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getEggs().getGifts()
        ));




        //Cooking Fats And Oils
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getCookingFats().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getCookingFats().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getCookingFats().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getCookingFats().getGifts()
        ));




        //Spices and Condiments
        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                foodConsumptionResponsesDto.getSpices().getOwnFarm()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                foodConsumptionResponsesDto.getSpices().getMarketFoodPurchase()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                foodConsumptionResponsesDto.getSpices().getHuntingGatheringFishing()
        ));

        wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                foodConsumptionResponsesDto.getSpices().getGifts()
        ));
    }
}
