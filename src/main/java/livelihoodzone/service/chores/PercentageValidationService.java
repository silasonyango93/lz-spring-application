package livelihoodzone.service.chores;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.WgCropContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution.WgCropContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.FoodTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternsPercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PercentageValidationService {

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    WgFoodSourcesResponsesRepository wgFoodSourcesResponsesRepository;

    @Autowired
    FoodTypesRepository foodTypesRepository;

    @Autowired
    WgCropContributionsRepository wgCropContributionsRepository;

    @Autowired
    WgAnimalContributionsRepository wgAnimalContributionsRepository;

    @Autowired
    WgGenderLivelihoodActivitiesRepository wgGenderLivelihoodActivitiesRepository;

    @Autowired
    WgExpenditurePercentagesRepository wgExpenditurePercentagesRepository;

    @Autowired
    WgExpenditureItemsRepository wgExpenditureItemsRepository;

    @Autowired
    WgMigrationPatternsPercentagesRepository wgMigrationPatternsPercentagesRepository;

    public List<String> getQuestionnairesWithPercentageValidationIssues(List<Number> countyIds) {
        List<String> affectedQuestionnaires = new ArrayList<>();

        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionRepository.findByCountyIdInAndWgQuestionnaireTypeId(countyIds,1);

        for (WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity : wgQuestionnaireSessionEntityList) {
            if (incomeSourcesHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || foodConsumptionHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || cropContributionHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || livestockContributionHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || labourPatternsHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || expenditurePatternsHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || migrationPatternsHasPercentageValidationError(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())) {

                affectedQuestionnaires.add(wgQuestionnaireSessionEntity.getQuestionnaireSessionDescription());

            }
        }
        return affectedQuestionnaires;
    }


    public boolean incomeSourcesHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgIncomeSourcesEntity> wgIncomeSourcesEntityList = wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);

        double percentageTotal = 0.0;

        for (WgIncomeSourcesEntity wgIncomeSourcesEntity : wgIncomeSourcesEntityList) {
            percentageTotal = percentageTotal + wgIncomeSourcesEntity.getIncomeSourcePercentage();
        }

        return percentageTotal != 100.0;
    }


    public boolean foodConsumptionHasPercentageValidationError(int wgQuestionnaireSessionId) {

        boolean foodConsumptionHasPercentageValidationError = false;

        List<WgFoodSourcesResponsesEntity> wgFoodSourcesResponsesEntityList = wgFoodSourcesResponsesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);

        for (WgFoodSourcesResponsesEntity wgFoodSourcesResponsesEntity : wgFoodSourcesResponsesEntityList) {

            //MAIZE_AND_POSHO

            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.MAIZE_AND_POSHO, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.WHEAT_BARLEY_RYE, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.SORGHUM_MILLET_PRODUCTS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.RICE_AND_PRODUCTS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.BEANS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.OTHER_PULSES, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.VEGETABLES, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.FRUITS_AND_BERRIES, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.WHITE_ROOTS_TUBERS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.MEAT, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.MILKS_AND_DAIRY_PRODUCTS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.FISH_AND_SEA_FOOD, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.EGGS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.COOKING_FATS_AND_OILS, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }
            if (givenCropTypeHasPercentageValidationError(extractSimilarFoodType(Constants.SPICES_CONDIMENTS_BEVERAGES, wgFoodSourcesResponsesEntityList))) {
                foodConsumptionHasPercentageValidationError = true;
            }

        }

        return foodConsumptionHasPercentageValidationError;
    }


    public List<WgFoodSourcesResponsesEntity> extractSimilarFoodType(int foodTypeCode, List<WgFoodSourcesResponsesEntity> wgFoodSourcesResponsesEntityList) {
        return wgFoodSourcesResponsesEntityList
                .stream()
                .filter(item -> item.getFoodTypeId() == foodTypesRepository.findByFoodTypeCode(foodTypeCode).getFoodTypeId())
                .collect(Collectors.toList());
    }

    public boolean givenCropTypeHasPercentageValidationError(List<WgFoodSourcesResponsesEntity> wgFoodSourcesResponsesEntityList) {
        double totalPercentage = 0.0;

        for (WgFoodSourcesResponsesEntity wgFoodSourcesResponsesEntity : wgFoodSourcesResponsesEntityList) {
            totalPercentage = totalPercentage + wgFoodSourcesResponsesEntity.getSourcePercentage();
        }

        return totalPercentage != 100.0;
    }


    public boolean cropContributionHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgCropContributionsEntity> wgCropContributionsEntityList = wgCropContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);
        double cashIncomePercentage = 0.0;
        double foodConsumptionPercentage = 0.0;

        for (WgCropContributionsEntity wgCropContributionsEntity : wgCropContributionsEntityList) {
            cashIncomePercentage = cashIncomePercentage + wgCropContributionsEntity.getCashIncomeApproxPercentage();
            foodConsumptionPercentage = foodConsumptionPercentage + wgCropContributionsEntity.getFoodConsumptionApproxPercentage();
        }

        return cashIncomePercentage != 100.0 || foodConsumptionPercentage != 100.0;
    }


    public boolean livestockContributionHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgAnimalContributionsEntity> wgAnimalContributionsEntityList = wgAnimalContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);
        double cashIncomePercentage = 0.0;
        double foodConsumptionPercentage = 0.0;

        for (WgAnimalContributionsEntity wgAnimalContributionsEntity : wgAnimalContributionsEntityList) {
            cashIncomePercentage = cashIncomePercentage + wgAnimalContributionsEntity.getIncomeContributionApproxPercentage();
            foodConsumptionPercentage = foodConsumptionPercentage + wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage();
        }
        return cashIncomePercentage != 100.0 || foodConsumptionPercentage != 100.0;
    }


    public boolean labourPatternsHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgGenderLivelihoodActivitiesEntity> wgGenderLivelihoodActivitiesEntityList = wgGenderLivelihoodActivitiesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);
        double menTotalPercentage = 0.0;
        double womenTotalPercentage = 0.0;

        for (WgGenderLivelihoodActivitiesEntity wgGenderLivelihoodActivitiesEntity : wgGenderLivelihoodActivitiesEntityList) {
            menTotalPercentage = menTotalPercentage + wgGenderLivelihoodActivitiesEntity.getMenPercentage();
            womenTotalPercentage = womenTotalPercentage + wgGenderLivelihoodActivitiesEntity.getWomenPercentage();
        }
        return menTotalPercentage != 100.0 || womenTotalPercentage != 100.0;
    }


    public boolean expenditurePatternsHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgExpenditurePercentagesEntity> wgExpenditurePercentagesEntityList = wgExpenditurePercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);

        double foodItemsTotalPercentage = extractPercentageOfExpenditureItem(Constants.EXP_MAIZE_AND_MAIZE_FLOUR, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_OTHER_CEREALS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_PULSES, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_ROOTS_AND_ROOT_TUBERS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_VEGETABLES_AND_FRUITS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_FISH_AND_SEA_FOOD, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_MEAT, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_MILK, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_EGGS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_OILS_AND_FATS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_OTHER_FOODS, wgExpenditurePercentagesEntityList);

        double nonFoodItemsTotalPercentage = extractPercentageOfExpenditureItem(Constants.EXP_SCHOOL_FEES, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_DRUGS_AND_MEDICAL_CARE, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_CLOTHING_BEAUTY_PRODUCTS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_HOUSE_RENT, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_COMMUNICATION_EXPENSES, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_FARM_INPUTS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_LIVESTOCK_DRUGS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_PURCHASE_OF_WATER, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_SOAPS_AND_OTHER_DETERGENTS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_HIRINNG_FARM_LABOUR, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_TRAVEL_EXPENSES, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_LEISURE_AND_ENTERTAINMENT, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_ELECTRICITY_BILLS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_SOCIAL_OBLIGATION, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_MILLING_COSTS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_COOKING_FUEL, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_SAVING_AND_INVESTMENTS, wgExpenditurePercentagesEntityList) +
                extractPercentageOfExpenditureItem(Constants.EXP_LOAN_REPAYMENTS, wgExpenditurePercentagesEntityList);

        return foodItemsTotalPercentage != 100.0 || nonFoodItemsTotalPercentage != 100.0;
    }

    public double extractPercentageOfExpenditureItem(int expenditureItemCode, List<WgExpenditurePercentagesEntity> wgExpenditurePercentagesEntityList) {
        return wgExpenditurePercentagesEntityList
                .stream()
                .filter(item -> item.getExpenditureItemId() == wgExpenditureItemsRepository.findByExpenditureItemCode(expenditureItemCode).getExpenditureItemId())
                .collect(Collectors.toList()).get(0).getExpenditurePercentage();
    }


    public boolean migrationPatternsHasPercentageValidationError(int wgQuestionnaireSessionId) {
        List<WgMigrationPatternPercentagesEntity> wgMigrationPatternPercentagesEntityList = wgMigrationPatternsPercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);
        double totalPercentage = 0.0;

        for (WgMigrationPatternPercentagesEntity wgMigrationPatternPercentagesEntity : wgMigrationPatternPercentagesEntityList) {
            totalPercentage = totalPercentage + wgMigrationPatternPercentagesEntity.getPercentage();
        }
        return totalPercentage != 100.0;
    }
}
