package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.dto.questionnaire.county.LzWaterSourceDataSetResponseObject;
import livelihoodzone.dto.reports.wealthgroup.*;
import livelihoodzone.service.reports.wealthgroup.animal_ownership.AnimalOwnershipService;
import livelihoodzone.service.reports.wealthgroup.crop_contribution.CropContributionReportsService;
import livelihoodzone.service.reports.wealthgroup.expenditure_patterns.ExpenditurePatternsDataSetService;
import livelihoodzone.service.reports.wealthgroup.income_food_sources.IncomeFoodSourcesAggregateResponsesService;
import livelihoodzone.service.reports.wealthgroup.labour_patterns.LabourPatternsDataSetService;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class WealthGroupReportService {

    @Autowired
    IncomeFoodSourcesAggregateResponsesService incomeFoodSourcesAggregateResponsesService;

    @Autowired
    CropContributionReportsService cropContributionReportsService;

    @Autowired
    AnimalOwnershipService animalOwnershipService;

    @Autowired
    LabourPatternsDataSetService labourPatternsDataSetService;

    @Autowired
    ExpenditurePatternsDataSetService expenditurePatternsDataSetService;

    public List<WgQuestionnaireDetailsRetrofitModel> fetchWealthGroupQuestionnaireDetails(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgQuestionnaireDetailsRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupQuestionnaireDetails(countyId,questionnaireTypeId);
        try {
            Response<List<WgQuestionnaireDetailsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgQuestionnaireDetailsResponseObjectDto processQuestionnaireDetails(int countyId, int questionnaireTypeId) {
        return new WgQuestionnaireDetailsResponseObjectDto(fetchWealthGroupQuestionnaireDetails(countyId,questionnaireTypeId));
    }

    public WgIncomeSourcesReportResponseDto processIncomeSourcesIntegratedData(int countyId, int questionnaireTypeId) {
        return incomeFoodSourcesAggregateResponsesService.processWealthGroupIncomeSources(incomeFoodSourcesAggregateResponsesService.fetchWealthGroupIncomeSources(countyId,questionnaireTypeId));
    }

    public WgFoodSourcesDataSetReponseDto processFoodSourcesAggregatedResponses(int countyId, int questionnaireTypeId) {
        return incomeFoodSourcesAggregateResponsesService.processFoodSourcesDataSet(incomeFoodSourcesAggregateResponsesService.fetchWealthGroupFoodSources(countyId,questionnaireTypeId));
    }

    public WgCropContributionReportResponseObject processCropContribution(int countyId, int questionnaireTypeId) {
        return cropContributionReportsService.processCropContribution(cropContributionReportsService.fetchWealthCropContribution(countyId,questionnaireTypeId));
    }

    public WgLivestockOwnershipDataSetObject processLivestockOwnership(int countyId, int questionnaireTypeId) {
        return animalOwnershipService.processLivestockOwnership(animalOwnershipService.fetchWealthGroupAnimalOwnership(countyId,questionnaireTypeId));
    }

    public WgAnimalContributionDataSetObject processLivestockContributions(int countyId, int questionnaireTypeId) {
        return animalOwnershipService.processLivestockContribution(animalOwnershipService.fetchWealthGroupAnimalContribution(countyId,questionnaireTypeId));
    }

    public WgLabourPatternsDataSetObject processLabourPatterns(int countyId, int questionnaireTypeId) {
        return labourPatternsDataSetService.processLabourPatterns(labourPatternsDataSetService.fetchWealthGroupLabourPatterns(countyId,questionnaireTypeId));
    }

    public WgExpenditurePatternsDataSetObject processExpenditurePatterns(int countyId, int questionnaireTypeId) {
        return expenditurePatternsDataSetService.processExpenditurePatterns(expenditurePatternsDataSetService.fetchWealthGroupExpenditurePatterns(countyId,questionnaireTypeId));
    }
}
