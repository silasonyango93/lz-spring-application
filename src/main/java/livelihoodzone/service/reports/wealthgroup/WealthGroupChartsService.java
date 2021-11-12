package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.common.Constants;
import livelihoodzone.dao.reports.zonal.wealthgroup.WgQuestionnaireSessionDao;
import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponseItem;
import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponsesDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.ValueDescriptionPairModel;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.*;
import livelihoodzone.service.reports.wealthgroup.animal_ownership.AnimalOwnershipService;
import livelihoodzone.service.reports.wealthgroup.expenditure_patterns.ExpenditurePatternsDataSetService;
import livelihoodzone.service.reports.wealthgroup.labour_patterns.LabourPatternsDataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WealthGroupChartsService {

    @Autowired
    WgQuestionnaireSessionDao wgQuestionnaireSessionDao;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    @Autowired
    WgFoodSourcesResponsesRepository wgFoodSourcesResponsesRepository;

    @Autowired
    FoodTypesRepository foodTypesRepository;

    @Autowired
    FoodSourcesRepository foodSourcesRepository;

    @Autowired
    AnimalOwnershipService animalOwnershipService;

    @Autowired
    ExpenditurePatternsDataSetService expenditurePatternsDataSetService;

    @Autowired
    LabourPatternsDataSetService labourPatternsDataSetService;

    public List<WgLivelihoodZoneDataObject> prepareWealthGroupChart(int countyId, int wealthGroupId, int questionnaireSectionCode) {
        List<WgLivelihoodZoneDataObject> livelihoodZoneDataObjectList = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionDao.fetchQuestionnaireSessionsByCountyAndWealthGroup(countyId,wealthGroupId);

        for (WgQuestionnaireSessionEntity currentQuestionnaire : wgQuestionnaireSessionEntityList) {
            WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject = new WgLivelihoodZoneDataObject();
            wgLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            wgLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());

            if (questionnaireSectionCode == Constants.MAIN_SOURCE_OF_INCOME_AND_FOOD) {
                wgLivelihoodZoneDataObject = processMainSourceOfIncomeAndFood(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.PERCENTAGE_FOOD_CONSUMPTION) {
                wgLivelihoodZoneDataObject = processFoodConsumptionPercentages(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.LIVESTOCK_AND_POULTRY_OWNERSHIP) {
                wgLivelihoodZoneDataObject = animalOwnershipService.processLivestockOwnershipChart(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.LIVESTOCK_AND_POULTRY_CONTRIBUTION) {
                wgLivelihoodZoneDataObject = animalOwnershipService.processLivestockContributionChart(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.EXPENDITURE_PATTERNS) {
                wgLivelihoodZoneDataObject = expenditurePatternsDataSetService.processExpenditurePatternsChart(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }
            if (questionnaireSectionCode == Constants.LABOUR_PATTERNS) {
                wgLivelihoodZoneDataObject = labourPatternsDataSetService.processLabourPatternsChart(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }

            livelihoodZoneDataObjectList.add(wgLivelihoodZoneDataObject);
        }

        return livelihoodZoneDataObjectList;
    }

    public WgLivelihoodZoneDataObject processMainSourceOfIncomeAndFood(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {

        IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses = new IncomeAndFoodSourcesResponses();

        List<WgIncomeSourcesEntity> wgIncomeSourcesEntities = wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgIncomeSourcesEntity wgIncomeSourcesEntity : wgIncomeSourcesEntities) {
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.LIVESTOCK_PRODUCTION) {
                incomeAndFoodSourcesResponses.setLivestockProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.POULTRY_PRODUCTION) {
                incomeAndFoodSourcesResponses.setPoultryProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.PASTURE_FODDER_PRODUCTION) {
                incomeAndFoodSourcesResponses.setPastureFodderProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.CASH_CROP_PRODUCTION) {
                incomeAndFoodSourcesResponses.setCashCropProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FOOD_CROP_PRODUCTION) {
                incomeAndFoodSourcesResponses.setFoodCropProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.CASUAL_WAGED_LABOUR_INCOME) {
                incomeAndFoodSourcesResponses.setCasualOrWagedLabour(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FORMAL_WAGED_LABOUR) {
                incomeAndFoodSourcesResponses.setFormalWagedLabour(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FISHING) {
                incomeAndFoodSourcesResponses.setFishing(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.HUNTING_AND_GATHERING) {
                incomeAndFoodSourcesResponses.setHuntingAndGathering(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.SMALL_BUSINESSES) {
                incomeAndFoodSourcesResponses.setSmallBusiness(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FIREWOOD_COLLECTION) {
                incomeAndFoodSourcesResponses.setFirewoodOrCharcoal(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.PETTY_TRADING) {
                incomeAndFoodSourcesResponses.setPettyTrading(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.REMITTANCE_AND_GIFTS) {
                incomeAndFoodSourcesResponses.setRemittance(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.BODABODA_TRANSPORT) {
                incomeAndFoodSourcesResponses.setBodaboda(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.BEE_KEEPING) {
                incomeAndFoodSourcesResponses.setBeeKeeping(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.SAND_HARVESTING) {
                incomeAndFoodSourcesResponses.setSandHarvesting(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.OTHERS_INCOME_SOURCES) {
                incomeAndFoodSourcesResponses.setOther(new ValueDescriptionPairModel(
                        wgIncomeSourcesEntity.getIncomeSourcePercentage(),
                        wgIncomeSourcesEntity.getExtraDescription()
                ));
            }
        }

        wgLivelihoodZoneDataObject.setIncomeAndFoodSourcesResponses(incomeAndFoodSourcesResponses);

        return wgLivelihoodZoneDataObject;
    }


    public WgLivelihoodZoneDataObject processFoodConsumptionPercentages(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        FoodConsumptionResponsesDto foodConsumptionPercentages = new FoodConsumptionResponsesDto();
        foodConsumptionPercentages.setMaizeAndPosho(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setWheatOrBarley(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setSorghumOrMillet(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setRice(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setBeans(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setPulses(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setVegetables(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setFruits(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setWhiteRoots(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setMeat(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setMilk(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setFish(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setEggs(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setCookingFats(new FoodConsumptionResponseItem());
        foodConsumptionPercentages.setSpices(new FoodConsumptionResponseItem());

        List<WgFoodSourcesResponsesEntity> wgFoodSourcesResponsesEntityList = wgFoodSourcesResponsesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgFoodSourcesResponsesEntity currentResponse : wgFoodSourcesResponsesEntityList) {
            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.MAIZE_AND_POSHO) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getMaizeAndPosho().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getMaizeAndPosho().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getMaizeAndPosho().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getMaizeAndPosho().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.WHEAT_BARLEY_RYE) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getWheatOrBarley().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getWheatOrBarley().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getWheatOrBarley().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getWheatOrBarley().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.SORGHUM_MILLET_PRODUCTS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getSorghumOrMillet().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getSorghumOrMillet().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getSorghumOrMillet().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getSorghumOrMillet().setGifts(currentResponse.getSourcePercentage());
                }
            }



            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.RICE_AND_PRODUCTS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getRice().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getRice().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getRice().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getRice().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.BEANS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getBeans().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getBeans().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getBeans().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getBeans().setGifts(currentResponse.getSourcePercentage());
                }
            }



            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.OTHER_PULSES) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getPulses().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getPulses().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getPulses().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getPulses().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.VEGETABLES) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getVegetables().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getVegetables().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getVegetables().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getVegetables().setGifts(currentResponse.getSourcePercentage());
                }
            }




            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.FRUITS_AND_BERRIES) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getFruits().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getFruits().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getFruits().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getFruits().setGifts(currentResponse.getSourcePercentage());
                }
            }




            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.WHITE_ROOTS_TUBERS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getWhiteRoots().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getWhiteRoots().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getWhiteRoots().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getWhiteRoots().setGifts(currentResponse.getSourcePercentage());
                }
            }



            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.MEAT) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getMeat().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getMeat().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getMeat().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getMeat().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.MILKS_AND_DAIRY_PRODUCTS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getMilk().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getMilk().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getMilk().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getMilk().setGifts(currentResponse.getSourcePercentage());
                }
            }



            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.FISH_AND_SEA_FOOD) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getFish().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getFish().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getFish().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getFish().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.EGGS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getEggs().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getEggs().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getEggs().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getEggs().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.COOKING_FATS_AND_OILS) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getCookingFats().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getCookingFats().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getCookingFats().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getCookingFats().setGifts(currentResponse.getSourcePercentage());
                }
            }


            if (foodTypesRepository.findByFoodTypeId(currentResponse.getFoodTypeId()).getFoodTypeCode() == Constants.SPICES_CONDIMENTS_BEVERAGES) {
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.OWN_FARM_PRODUCTION) {
                    foodConsumptionPercentages.getSpices().setOwnFarm(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.MARKET_FOOD_PURCHASE) {
                    foodConsumptionPercentages.getSpices().setMarketFoodPurchase(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE) {
                    foodConsumptionPercentages.getSpices().setHuntingGatheringFishing(currentResponse.getSourcePercentage());
                }
                if (foodSourcesRepository.findByFoodSourceId(currentResponse.getFoodSourceId()).getFoodSourceCode() == Constants.GIFTS_AND_FOOD_AID) {
                    foodConsumptionPercentages.getSpices().setGifts(currentResponse.getSourcePercentage());
                }
            }
        }
        wgLivelihoodZoneDataObject.setFoodConsumptionPercentages(foodConsumptionPercentages);
        return wgLivelihoodZoneDataObject;
    }
}
