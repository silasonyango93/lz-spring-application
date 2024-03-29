package livelihoodzone.service.reports.wealthgroup.expenditure_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.wealthgroup.expenditurepatterns.ExpenditurePatternsResponses;
import livelihoodzone.dto.reports.wealthgroup.WgExpenditurePatternsDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgExpenditurePatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgLabourPatternsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class ExpenditurePatternsDataSetService {

    @Autowired
    WgExpenditurePercentagesRepository wgExpenditurePercentagesRepository;

    @Autowired
    WgExpenditureItemsRepository wgExpenditureItemsRepository;



    public List<WgExpenditurePatternsDataSetRetrofitModel> fetchWealthGroupExpenditurePatterns(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgExpenditurePatternsDataSetRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupExpenditurePatterns(countyId, questionnaireTypeId);
        try {
            Response<List<WgExpenditurePatternsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgExpenditurePatternsDataSetObject processExpenditurePatterns(List<WgExpenditurePatternsDataSetRetrofitModel> list) {
        List<Number> maizeAndFlour = processAnExpenditurePattern(Constants.EXP_MAIZE_AND_MAIZE_FLOUR, list);
        List<Number> otherCereals = processAnExpenditurePattern(Constants.EXP_OTHER_CEREALS, list);
        List<Number> pulses = processAnExpenditurePattern(Constants.EXP_PULSES, list);
        List<Number> rootsAndTubers = processAnExpenditurePattern(Constants.EXP_ROOTS_AND_ROOT_TUBERS, list);
        List<Number> vegetablesAndFruits = processAnExpenditurePattern(Constants.EXP_VEGETABLES_AND_FRUITS, list);
        List<Number> fishAndSeaFood = processAnExpenditurePattern(Constants.EXP_FISH_AND_SEA_FOOD, list);
        List<Number> meat = processAnExpenditurePattern(Constants.EXP_MEAT, list);
        List<Number> milk = processAnExpenditurePattern(Constants.EXP_MILK, list);
        List<Number> eggs = processAnExpenditurePattern(Constants.EXP_EGGS, list);
        List<Number> oilsAndfats = processAnExpenditurePattern(Constants.EXP_OILS_AND_FATS, list);
        List<Number> otherFoods = processAnExpenditurePattern(Constants.EXP_OTHER_FOODS, list);
        List<Number> schoolFees = processAnExpenditurePattern(Constants.EXP_SCHOOL_FEES, list);
        List<Number> drugsAndMedicalCare = processAnExpenditurePattern(Constants.EXP_DRUGS_AND_MEDICAL_CARE, list);
        List<Number> clothingAndBeautyProducts = processAnExpenditurePattern(Constants.EXP_CLOTHING_BEAUTY_PRODUCTS, list);
        List<Number> houseRent = processAnExpenditurePattern(Constants.EXP_HOUSE_RENT, list);
        List<Number> communicationExpenses = processAnExpenditurePattern(Constants.EXP_COMMUNICATION_EXPENSES, list);
        List<Number> farmInputs = processAnExpenditurePattern(Constants.EXP_FARM_INPUTS, list);
        List<Number> livestockDrugs = processAnExpenditurePattern(Constants.EXP_LIVESTOCK_DRUGS, list);
        List<Number> purchaseOfWater = processAnExpenditurePattern(Constants.EXP_PURCHASE_OF_WATER, list);
        List<Number> soapsAndOtherDetergents = processAnExpenditurePattern(Constants.EXP_SOAPS_AND_OTHER_DETERGENTS, list);
        List<Number> hiringFarmLabour = processAnExpenditurePattern(Constants.EXP_HIRINNG_FARM_LABOUR, list);
        List<Number> travelExpense = processAnExpenditurePattern(Constants.EXP_TRAVEL_EXPENSES, list);
        List<Number> leisureAndEntertainment = processAnExpenditurePattern(Constants.EXP_LEISURE_AND_ENTERTAINMENT, list);
        List<Number> electricityBill = processAnExpenditurePattern(Constants.EXP_ELECTRICITY_BILLS, list);
        List<Number> socialObligation = processAnExpenditurePattern(Constants.EXP_SOCIAL_OBLIGATION, list);
        List<Number> millingCosts = processAnExpenditurePattern(Constants.EXP_MILLING_COSTS, list);
        List<Number> cookingFuel = processAnExpenditurePattern(Constants.EXP_COOKING_FUEL, list);
        List<Number> savingsAndInvestments = processAnExpenditurePattern(Constants.EXP_SAVING_AND_INVESTMENTS, list);
        List<Number> loanRepayments = processAnExpenditurePattern(Constants.EXP_LOAN_REPAYMENTS, list);

        return new WgExpenditurePatternsDataSetObject(
                maizeAndFlour,
                otherCereals,
                pulses,
                rootsAndTubers,
                vegetablesAndFruits,
                fishAndSeaFood,
                meat,
                milk,
                eggs,
                oilsAndfats,
                otherFoods,
                schoolFees,
                drugsAndMedicalCare,
                clothingAndBeautyProducts,
                houseRent,
                communicationExpenses,
                farmInputs,
                livestockDrugs,
                purchaseOfWater,
                soapsAndOtherDetergents,
                hiringFarmLabour,
                travelExpense,
                leisureAndEntertainment,
                electricityBill,
                socialObligation,
                millingCosts,
                cookingFuel,
                savingsAndInvestments,
                loanRepayments
        );
    }


    public List<Number> processAnExpenditurePattern(int expenditureCode, List<WgExpenditurePatternsDataSetRetrofitModel> list) {
        List<WgExpenditurePatternsDataSetRetrofitModel> processedList = returnListForSpecificExpenditure(expenditureCode, list);
        List<Number> specificExpenditureReportList = new ArrayList<>();
        for (WgExpenditurePatternsDataSetRetrofitModel currentItem : processedList) {
            specificExpenditureReportList.add(currentItem.getExpenditurePercentage());
        }
        return specificExpenditureReportList;
    }

    public List<WgExpenditurePatternsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgExpenditurePatternsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgExpenditurePatternsDataSetRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

    public List<WgExpenditurePatternsDataSetRetrofitModel> returnListForSpecificExpenditure(int activityCode, List<WgExpenditurePatternsDataSetRetrofitModel> allItems) {
        List<WgExpenditurePatternsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (WgExpenditurePatternsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getExpenditureItemCode() == activityCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }

    public WgLivelihoodZoneDataObject processExpenditurePatternsChart(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        ExpenditurePatternsResponses expenditurePatternsResponses = new ExpenditurePatternsResponses();
        List<WgExpenditurePercentagesEntity> wgExpenditurePercentagesEntityList = wgExpenditurePercentagesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgExpenditurePercentagesEntity wgExpenditurePercentagesEntity : wgExpenditurePercentagesEntityList) {
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_MAIZE_AND_MAIZE_FLOUR) {
                expenditurePatternsResponses.setMaizeAndMaizeFlour(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_OTHER_CEREALS) {
                expenditurePatternsResponses.setOtherCereals(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_PULSES) {
                expenditurePatternsResponses.setPulses(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_ROOTS_AND_ROOT_TUBERS) {
                expenditurePatternsResponses.setRootsAndTubers(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_VEGETABLES_AND_FRUITS) {
                expenditurePatternsResponses.setVegetablesAndFruits(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_FISH_AND_SEA_FOOD) {
                expenditurePatternsResponses.setFishandSeaFood(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_MEAT) {
                expenditurePatternsResponses.setMeat(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_MILK) {
                expenditurePatternsResponses.setMilk(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_EGGS) {
                expenditurePatternsResponses.setEggs(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_OILS_AND_FATS) {
                expenditurePatternsResponses.setOilsAndFats(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_OTHER_FOODS) {
                expenditurePatternsResponses.setOtherFoods(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_SCHOOL_FEES) {
                expenditurePatternsResponses.setSchoolFees(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_DRUGS_AND_MEDICAL_CARE) {
                expenditurePatternsResponses.setDrugsAndMedicalCare(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_CLOTHING_BEAUTY_PRODUCTS) {
                expenditurePatternsResponses.setClothingAndBeautyProducts(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_HOUSE_RENT) {
                expenditurePatternsResponses.setHouseRent(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_COMMUNICATION_EXPENSES) {
                expenditurePatternsResponses.setCommunicationExpenses(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_FARM_INPUTS) {
                expenditurePatternsResponses.setFarmInputs(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_LIVESTOCK_DRUGS) {
                expenditurePatternsResponses.setLivestockDrugs(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_PURCHASE_OF_WATER) {
                expenditurePatternsResponses.setWaterPurchase(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_SOAPS_AND_OTHER_DETERGENTS) {
                expenditurePatternsResponses.setSoaps(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_HIRINNG_FARM_LABOUR) {
                expenditurePatternsResponses.setFarmLabour(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_TRAVEL_EXPENSES) {
                expenditurePatternsResponses.setTravelRelatedExpenses(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_LEISURE_AND_ENTERTAINMENT) {
                expenditurePatternsResponses.setLeisureAndEntertainment(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_ELECTRICITY_BILLS) {
                expenditurePatternsResponses.setElectricityBills(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_SOCIAL_OBLIGATION) {
                expenditurePatternsResponses.setSocialObligation(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_MILLING_COSTS) {
                expenditurePatternsResponses.setMillingCosts(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_COOKING_FUEL) {
                expenditurePatternsResponses.setCookingFuel(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_SAVING_AND_INVESTMENTS) {
                expenditurePatternsResponses.setSavingsAndInvestments(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
            if (wgExpenditureItemsRepository.findByExpenditureItemId(wgExpenditurePercentagesEntity.getExpenditureItemId()).getExpenditureItemCode() == Constants.EXP_LOAN_REPAYMENTS) {
                expenditurePatternsResponses.setLoanRepayments(wgExpenditurePercentagesEntity.getExpenditurePercentage());
            }
        }
        wgLivelihoodZoneDataObject.setExpenditurePatterns(expenditurePatternsResponses);
        return wgLivelihoodZoneDataObject;
    }

}
