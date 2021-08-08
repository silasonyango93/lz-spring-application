package livelihoodzone.service.reports.wealthgroup.income_food_sources;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.NumberDescriptionPairDto;
import livelihoodzone.dto.reports.wealthgroup.WgIncomeSourcesReportResponseDto;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgIncomeSourcesRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class IncomeFoodSourcesAggregateResponsesService {

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    public List<WgIncomeSourcesRetrofitModel> fetchWealthGroupIncomeSources(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgIncomeSourcesRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupIncomeSources(countyId,questionnaireTypeId);
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
                otherIncomeSources.add(new NumberDescriptionPairDto(currentItem.getIncomeSourcePercentage(),currentItem.getExtraDescription()));
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
}
