package livelihoodzone.service.reports.wealthgroup.income_constraints;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.reports.wealthgroup.WgIncomeConstraintsDataSetObject;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgConstraintsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgExpenditurePatternsDataSetRetrofitModel;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class WgIncomeConstraintsDataSetService {

    public List<WgConstraintsDataSetRetrofitModel> fetchWealthGroupIncomeConstraints(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgConstraintsDataSetRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupIncomeConstraints(countyId, questionnaireTypeId);
        try {
            Response<List<WgConstraintsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgIncomeConstraintsDataSetObject processIncomeConstraints(List<WgConstraintsDataSetRetrofitModel> list) {

        List<Number> lowEducation = processAConstraint(Constants.INCO_WL_LOW_EDUCATION, list);
        List<Number> poorHealth = processAConstraint(Constants.INCO_WL_POOR_HEALTH, list);
        List<Number> tooFewJobs = processAConstraint(Constants.INCO_WL_TOO_FEW_JOBS, list);
        List<Number> tooMuchFarmTime = processAConstraint(Constants.INCO_WL_TOO_MUCH_FARM_TIME, list);
        List<Number> lowAverageWageRates = processAConstraint(Constants.INCO_WL_LOW_AVERAGE_WAGE_RATES, list);
        List<Number> smallLandHoldings = processAConstraint(Constants.INCO_CP_SMALL_LAND_HOLDINGS, list);
        List<Number> lackOfCredit = processAConstraint(Constants.INCO_CP_LACK_OF_CREDIT, list);
        List<Number> highInputCost = processAConstraint(Constants.INCO_CP_HIGH_INPUT_COSTS, list);
        List<Number> lowLandFertility = processAConstraint(Constants.INCO_CP_LOW_LAND_FERTILITY, list);
        List<Number> lackOfReliableWater = processAConstraint(Constants.INCO_CP_LACK_OF_RELIABLE_WATER, list);
        List<Number> lowTechnicalSkills = processAConstraint(Constants.INCO_CP_LOW_TECHNICAL_SKILLS, list);
        List<Number> lowQualitySeedStock = processAConstraint(Constants.INCO_CP_LOW_QUALITY_SEED_STOCK, list);
        List<Number> lackOfMarketAccess = processAConstraint(Constants.INCO_CP_LACK_MARKET_ACCESS, list);
        List<Number> endemicCropPests = processAConstraint(Constants.INCO_CP_ENDEMIC_CROP_PESTS_DISEASES, list);
        List<Number> cropsLackOfAgriculturalExtension = processAConstraint(Constants.INCO_CP_LACK_OF_AGRICULTURAL_EXTENSION_SERVICES, list);
        List<Number> lackOfPatureAndBrowse = processAConstraint(Constants.INCO_LP_LACK_OF_PASTURE, list);
        List<Number> lackAnimalDrinkingWatere = processAConstraint(Constants.INCO_LP_LACK_OF_ANIMAL_DRINKING_WATER, list);
        List<Number> lowYieldingAnimals = processAConstraint(Constants.INCO_LP_LOW_YIELDING_ANIMALS, list);
        List<Number> highCostVeterinaryDrugs = processAConstraint(Constants.INCO_LP_HIGH_COST_VETERINARY_DRUGS, list);
        List<Number> endemicLivestockPests = processAConstraint(Constants.INCO_LP_ENDEMIC_LIVESTOCK_PESTS_DISEASES, list);
        List<Number> animalsLackOfMarket = processAConstraint(Constants.INCO_LP_LACK_OF_MARKET, list);
        List<Number> insecurity = processAConstraint(Constants.INCO_LP_INSECURITY, list);
        List<Number> livestockLowTechnicalSkills = processAConstraint(Constants.INCO_LP_LOW_TECHNICAL_SKILLS_KNOWLEDGE, list);
        List<Number> livestockUnfavourableClimate = processAConstraint(Constants.INCO_LP_UNFAVOURABLE_CLIMATE, list);
        List<Number> lackOfLivestockExtensionServices = processAConstraint(Constants.INCO_LP_LACK_OF_LIVESTOCK_EXTENSION_SERVICES, list);
        List<Number> lowFishStocks = processAConstraint(Constants.INCO_FI_LOW_FISH_STOCKS, list);
        List<Number> lowFishPrices = processAConstraint(Constants.INCO_FI_LOW_FISH_PRICE, list);
        List<Number> fishLackOfEquipment = processAConstraint(Constants.INCO_FI_LACK_OF_EQUIPMENT, list);
        List<Number> fishTooMuchCompetition = processAConstraint(Constants.INCO_FI_TOO_MUCH_COMPETITION, list);
        List<Number> fishLackOfExpertise = processAConstraint(Constants.INCO_FI_LACK_OF_EXPERTISE, list);
        List<Number> restrictionOnFishingRights = processAConstraint(Constants.INCO_FI_RESTRICTIONS_ON_FISHING_RIGHTS, list);
        List<Number> fishInadequateColdStorageFacilities = processAConstraint(Constants.INCO_FI_INADEQUATE_COLD_STORAGE_FACILITIES, list);
        List<Number> decliningNaturalResources = processAConstraint(Constants.INCO_NR_DECLINING_NATURAL_RESOURCES, list);
        List<Number> naturalResourceTooMuchPopulation = processAConstraint(Constants.INCO_NR_TOO_MUCH_POPULATION_PRESSURE, list);
        List<Number> naturalResourceRestrictionOnRights = processAConstraint(Constants.INCO_NR_RESTRICTIONS_RIGHTS_TO_EXPLOIT_NR, list);
        List<Number> lowValueNaturalResourceBasedProducts = processAConstraint(Constants.INCO_NR_LOW_VALUE_NR_BASED_PRODUCTS, list);
        List<Number> smallEnterpriseLackOfCapital = processAConstraint(Constants.INCO_SE_LACK_OF_CAPITAL, list);
        List<Number> smallEnterpriseTooMuchRedTape = processAConstraint(Constants.INCO_SE_TOO_MUCH_RED_TAPE, list);
        List<Number> smallEnterpriseTooManyTaxes = processAConstraint(Constants.INCO_SE_TOO_MANY_TAXES, list);
        List<Number> smallEnterpriseLackOfAccessToMarket = processAConstraint(Constants.INCO_SE_LACK_OF_ACCESS_TO_MARKETS, list);
        List<Number> smallEnterpriseLackOfExpertise = processAConstraint(Constants.INCO_SE_LACK_OF_EXPERTISE, list);

        return new WgIncomeConstraintsDataSetObject(
                lowEducation,
                poorHealth,
                tooFewJobs,
                tooMuchFarmTime,
                lowAverageWageRates,
                smallLandHoldings,
                lackOfCredit,
                highInputCost,
                lowLandFertility,
                lackOfReliableWater,
                lowTechnicalSkills,
                lowQualitySeedStock,
                lackOfMarketAccess,
                endemicCropPests,
                cropsLackOfAgriculturalExtension,
                lackOfPatureAndBrowse,
                lackAnimalDrinkingWatere,
                lowYieldingAnimals,
                highCostVeterinaryDrugs,
                endemicLivestockPests,
                animalsLackOfMarket,
                insecurity,
                livestockLowTechnicalSkills,
                livestockUnfavourableClimate,
                lackOfLivestockExtensionServices,
                lowFishStocks,
                lowFishPrices,
                fishLackOfEquipment,
                fishTooMuchCompetition,
                fishLackOfExpertise,
                restrictionOnFishingRights,
                fishInadequateColdStorageFacilities,
                decliningNaturalResources,
                naturalResourceTooMuchPopulation,
                naturalResourceRestrictionOnRights,
                lowValueNaturalResourceBasedProducts,
                smallEnterpriseLackOfCapital,
                smallEnterpriseTooMuchRedTape,
                smallEnterpriseTooManyTaxes,
                smallEnterpriseLackOfAccessToMarket,
                smallEnterpriseLackOfExpertise
        );
    }


    public List<Number> processAConstraint(int constraintCode, List<WgConstraintsDataSetRetrofitModel> list) {
        List<WgConstraintsDataSetRetrofitModel> processedList = returnListForSpecificConstraint(constraintCode, list);
        List<Number> specificConstraintReportList = new ArrayList<>();
        for (WgConstraintsDataSetRetrofitModel currentItem : processedList) {
            specificConstraintReportList.add(currentItem.getRank());
        }
        return specificConstraintReportList;
    }


    public List<WgConstraintsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgConstraintsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgConstraintsDataSetRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

    public List<WgConstraintsDataSetRetrofitModel> returnListForSpecificConstraint(int constraintCode, List<WgConstraintsDataSetRetrofitModel> allItems) {
        List<WgConstraintsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (WgConstraintsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getIncomeConstraintCode() == constraintCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }

}
