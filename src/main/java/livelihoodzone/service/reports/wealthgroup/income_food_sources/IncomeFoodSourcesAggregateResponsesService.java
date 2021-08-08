package livelihoodzone.service.reports.wealthgroup.income_food_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.NumberDescriptionPairDto;
import livelihoodzone.dto.reports.wealthgroup.WgFoodSourcesDataSetReponseDto;
import livelihoodzone.dto.reports.wealthgroup.WgIncomeSourcesReportResponseDto;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgFoodSourcesRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgIncomeSourcesRetrofitModel;
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
public class IncomeFoodSourcesAggregateResponsesService {

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    public List<WgIncomeSourcesRetrofitModel> fetchWealthGroupIncomeSources(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgIncomeSourcesRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupIncomeSources(countyId, questionnaireTypeId);
        try {
            Response<List<WgIncomeSourcesRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgIncomeSourcesReportResponseDto processWealthGroupIncomeSources(List<WgIncomeSourcesRetrofitModel> wgIncomeSourcesRetrofitModelList) {

        List<NumberDescriptionPairDto> livestockProduction = new ArrayList<>();
        List<NumberDescriptionPairDto> poultryProduction = new ArrayList<>();
        List<NumberDescriptionPairDto> cashCropProduction = new ArrayList<>();
        List<NumberDescriptionPairDto> foodCropProduction = new ArrayList<>();
        List<NumberDescriptionPairDto> casualWagedLabour = new ArrayList<>();
        List<NumberDescriptionPairDto> formalWagedLabour = new ArrayList<>();
        List<NumberDescriptionPairDto> fishingIncome = new ArrayList<>();
        List<NumberDescriptionPairDto> huntingAndGatheringIncome = new ArrayList<>();
        List<NumberDescriptionPairDto> smallBusinesses = new ArrayList<>();
        List<NumberDescriptionPairDto> firewoodOrCharcoal = new ArrayList<>();
        List<NumberDescriptionPairDto> pettyTrading = new ArrayList<>();
        List<NumberDescriptionPairDto> remittanceAndGifts = new ArrayList<>();
        List<NumberDescriptionPairDto> bodabodaTransport = new ArrayList<>();
        List<NumberDescriptionPairDto> beeKeeping = new ArrayList<>();
        List<NumberDescriptionPairDto> sandHarvesting = new ArrayList<>();
        List<NumberDescriptionPairDto> otherIncomeSources = new ArrayList<>();

        for (WgIncomeSourcesRetrofitModel currentItem : wgIncomeSourcesRetrofitModelList) {
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.LIVESTOCK_PRODUCTION).getCashIncomeSourceId()) {
                livestockProduction.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.POULTRY_PRODUCTION).getCashIncomeSourceId()) {
                poultryProduction.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASH_CROP_PRODUCTION).getCashIncomeSourceId()) {
                cashCropProduction.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FOOD_CROP_PRODUCTION).getCashIncomeSourceId()) {
                foodCropProduction.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASUAL_WAGED_LABOUR_INCOME).getCashIncomeSourceId()) {
                casualWagedLabour.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FORMAL_WAGED_LABOUR).getCashIncomeSourceId()) {
                formalWagedLabour.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FISHING).getCashIncomeSourceId()) {
                fishingIncome.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.HUNTING_AND_GATHERING).getCashIncomeSourceId()) {
                huntingAndGatheringIncome.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SMALL_BUSINESSES).getCashIncomeSourceId()) {
                smallBusinesses.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FIREWOOD_COLLECTION).getCashIncomeSourceId()) {
                firewoodOrCharcoal.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.PETTY_TRADING).getCashIncomeSourceId()) {
                pettyTrading.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.REMITTANCE_AND_GIFTS).getCashIncomeSourceId()) {
                remittanceAndGifts.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BODABODA_TRANSPORT).getCashIncomeSourceId()) {
                bodabodaTransport.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BEE_KEEPING).getCashIncomeSourceId()) {
                beeKeeping.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SAND_HARVESTING).getCashIncomeSourceId()) {
                sandHarvesting.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage()));
            }
            if (currentItem.getCashIncomeSourceId() == cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.OTHERS_INCOME_SOURCES).getCashIncomeSourceId()) {
                otherIncomeSources.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage(), currentItem.getExtraDescription()));
            }
        }

        return new WgIncomeSourcesReportResponseDto(
                livestockProduction,
                poultryProduction,
                cashCropProduction,
                foodCropProduction,
                casualWagedLabour,
                formalWagedLabour,
                fishingIncome,
                huntingAndGatheringIncome,
                smallBusinesses,
                firewoodOrCharcoal,
                pettyTrading,
                remittanceAndGifts,
                bodabodaTransport,
                beeKeeping,
                sandHarvesting,
                otherIncomeSources
        );
    }

    public List<WgFoodSourcesRetrofitModel> fetchWealthGroupFoodSources(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgFoodSourcesRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupFoodSources(countyId, questionnaireTypeId);
        try {
            Response<List<WgFoodSourcesRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgFoodSourcesDataSetReponseDto processFoodSourcesDataSet(List<WgFoodSourcesRetrofitModel> wgFoodSourcesRetrofitModelList) {

        List<String> maizeAndPosho = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.MAIZE_AND_POSHO, wgFoodSourcesRetrofitModelList));
        List<String> wheatBarleyRhye = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.WHEAT_BARLEY_RYE, wgFoodSourcesRetrofitModelList));
        List<String> sorghumMillet = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.SORGHUM_MILLET_PRODUCTS, wgFoodSourcesRetrofitModelList));
        List<String> rice = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.RICE_AND_PRODUCTS, wgFoodSourcesRetrofitModelList));
        List<String> beans = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.BEANS, wgFoodSourcesRetrofitModelList));
        List<String> otherPulsesSeedsAndNuts = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.OTHER_PULSES, wgFoodSourcesRetrofitModelList));
        List<String> vegetables = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.VEGETABLES, wgFoodSourcesRetrofitModelList));
        List<String> fruitsAndBerries = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.FRUITS_AND_BERRIES, wgFoodSourcesRetrofitModelList));
        List<String> whiteRootsTubersAndPlantains = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.WHITE_ROOTS_TUBERS, wgFoodSourcesRetrofitModelList));
        List<String> meat = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.MEAT, wgFoodSourcesRetrofitModelList));
        List<String> milkAndDairyProducts = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.MILKS_AND_DAIRY_PRODUCTS, wgFoodSourcesRetrofitModelList));
        List<String> fishAndSeaFood = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.FISH_AND_SEA_FOOD, wgFoodSourcesRetrofitModelList));
        List<String> eggs = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.EGGS, wgFoodSourcesRetrofitModelList));
        List<String> cookingFatsAndOils = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.COOKING_FATS_AND_OILS, wgFoodSourcesRetrofitModelList));
        List<String> spicesCondimentsAndBeverages = processAParticularFoodTypeAggregatedData(returnListForSpecificFoodType(Constants.SPICES_CONDIMENTS_BEVERAGES, wgFoodSourcesRetrofitModelList));


        return new WgFoodSourcesDataSetReponseDto(
                maizeAndPosho,
                wheatBarleyRhye,
                sorghumMillet,
                rice,
                beans,
                otherPulsesSeedsAndNuts,
                vegetables,
                fruitsAndBerries,
                whiteRootsTubersAndPlantains,
                meat,
                milkAndDairyProducts,
                fishAndSeaFood,
                eggs,
                cookingFatsAndOils,
                spicesCondimentsAndBeverages
        );
    }



    //Process Aggregate data
    public List<String> processAParticularFoodTypeAggregatedData(List<WgFoodSourcesRetrofitModel> wgFoodSourcesRetrofitModelList) {
        List<String> aggregatedDataList = new ArrayList<>();
        int currentQuestionnaireSessionId = wgFoodSourcesRetrofitModelList.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;
        for (WgFoodSourcesRetrofitModel currentItem : wgFoodSourcesRetrofitModelList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getFoodSourceDescription() + " -> " + currentItem.getSourcePercentage() + ", ";
                counter++;
            } else {
                aggregatedDataList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getFoodSourceDescription() + " -> " + currentItem.getSourcePercentage() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }

        }
        aggregatedDataList.add(currentReportString);
        return aggregatedDataList;
    }




    public List<WgFoodSourcesRetrofitModel> returnListForSpecificFoodType(int foodTypeCode, List<WgFoodSourcesRetrofitModel> allItems) {
        List<WgFoodSourcesRetrofitModel> processedList = new ArrayList<>();

        for (WgFoodSourcesRetrofitModel currentItem : allItems) {
            if (currentItem.getFoodTypeCode() == foodTypeCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }


    public List<WgFoodSourcesRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgFoodSourcesRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgFoodSourcesRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

}
