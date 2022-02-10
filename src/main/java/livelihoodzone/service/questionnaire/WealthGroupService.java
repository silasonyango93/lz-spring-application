package livelihoodzone.service.questionnaire;

import com.google.gson.Gson;
import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesSubCountiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesSubLocationsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesWardsEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.summaries.WgSummariesSubCountiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.summaries.WgSummariesWardsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.summaries.WgSummarisedSubLocationsRepository;
import livelihoodzone.service.questionnaire.wealthgroup.animal_contribution.AnimalContributionService;
import livelihoodzone.service.questionnaire.wealthgroup.constraints.IncomeConstraintsService;
import livelihoodzone.service.questionnaire.wealthgroup.cropcontribution.CropContributionService;
import livelihoodzone.service.questionnaire.wealthgroup.expenditure_patterns.ExpenditurePatternsService;
import livelihoodzone.service.questionnaire.wealthgroup.fgd_participants.FgdParticipantsService;
import livelihoodzone.service.questionnaire.wealthgroup.income_food_sources.IncomeFoodSourcesService;
import livelihoodzone.service.questionnaire.wealthgroup.labour_patterns.LabourPatternsService;
import livelihoodzone.service.questionnaire.wealthgroup.migration_patterns.MigrationPatternsService;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgAnimalOwnershipRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgWealthGroupSummaryAssociatedRawDataRetrofitModel;
import livelihoodzone.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
@Transactional
public class WealthGroupService {

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    @Autowired
    WgQuestionnaireTypesRepository wgQuestionnaireTypesRepository;

    @Autowired
    IncomeFoodSourcesService incomeFoodSourcesService;

    @Autowired
    CropContributionService cropContributionService;

    @Autowired
    AnimalContributionService animalContributionService;

    @Autowired
    LabourPatternsService labourPatternsService;

    @Autowired
    ExpenditurePatternsService expenditurePatternsService;

    @Autowired
    MigrationPatternsService migrationPatternsService;

    @Autowired
    IncomeConstraintsService incomeConstraintsService;

    @Autowired
    FgdParticipantsService fgdParticipantsService;

    @Autowired
    WgSummariesSubCountiesRepository wgSummariesSubCountiesRepository;

    @Autowired
    WgSummariesWardsRepository wgSummariesWardsRepository;

    @Autowired
    WgSummarisedSubLocationsRepository wgSummarisedSubLocationsRepository;

    public QuestionnaireResponseDto processQuestionnaire(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, User dataCollector) {

        Gson gson = new Gson();
        String questionnaireJsonString = gson.toJson(new WealthGroupQuestionnaireRequestDto());

        //System.out.println(gson.toJson(wealthGroupQuestionnaireRequestDto));

        List<WgQuestionnaireSessionEntity> existingQuestionnaires = wgQuestionnaireSessionRepository.findByQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(
                    QuestionnaireResponseStatus.DUPLICATE,
                    "Duplicate questionnaire",
                    null,
                    2
            );
        }

        boolean isASummaryQuestionnaire = wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWgQuestionnaireType().getWgQuestionnaireTypeCode() == Constants.WEALTH_GROUP_SUMMARISED_QUESTIONNAIRE_TYPE_CODE;
        WgQuestionnaireSessionEntity questionnaireSession = new WgQuestionnaireSessionEntity();
        questionnaireSession.setUserId(dataCollector.getUserId());
        questionnaireSession.setWealthGroupId(wealthGroupRepository.findByWealthGroupCode(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWealthGroup().getWealthGroupCode()).getWealthGroupId());
        questionnaireSession.setCountyId(dataCollector.getCountyId());
        questionnaireSession.setSubCountyId(!isASummaryQuestionnaire ? wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubCounty().getSubCountyId() : Constants.GENERAL_SUBCOUNTY_ID);
        questionnaireSession.setWardId(!isASummaryQuestionnaire ?  wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWard().getWardId() : Constants.GENERAL_WARD_ID);
        questionnaireSession.setSubLocationId(!isASummaryQuestionnaire ? wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubLocation().getSubLocationId() : Constants.GENERAL_SUBLOCATION_ID);
        questionnaireSession.setLivelihoodZoneId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedLivelihoodZone().getLivelihoodZoneId());
        questionnaireSession.setQuestionnaireSessionDescription(wealthGroupQuestionnaireRequestDto.getQuestionnaireName());
        questionnaireSession.setLatitude(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getLatitude());
        questionnaireSession.setLongitude(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getLongitude());
        questionnaireSession.setSessionStartDate(wealthGroupQuestionnaireRequestDto.getQuestionnaireStartDate() != null ? wealthGroupQuestionnaireRequestDto.getQuestionnaireStartDate() : Util.getNow());
        questionnaireSession.setSessionEndDate(wealthGroupQuestionnaireRequestDto.getQuestionnaireEndDate() != null? wealthGroupQuestionnaireRequestDto.getQuestionnaireEndDate() : Util.getNow());
        questionnaireSession.setHasBeenSubmitted(1);
        questionnaireSession.setQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());
        questionnaireSession.setWgQuestionnaireTypeId(wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeCode(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWgQuestionnaireType().getWgQuestionnaireTypeCode()).getWgQuestionnaireTypeId());
        questionnaireSession.setQuestionnaireJsonString(questionnaireJsonString);

        WgQuestionnaireSessionEntity savedQuestionnaireSessionEntity = wgQuestionnaireSessionRepository.save(questionnaireSession);

        if (isASummaryQuestionnaire) {
            processSummaryBoundaries(savedQuestionnaireSessionEntity);
        }


        /*Process Questionnaire Sections and Commit to db ***********************************************************************************************/


        incomeFoodSourcesService.saveIncomeSources(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        incomeFoodSourcesService.saveFoodSources(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        cropContributionService.saveCropContribution(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        animalContributionService.saveAverageNoAnimalsPerHousehold(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        animalContributionService.saveAnimalContributions(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        labourPatternsService.saveLabourPatterns(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        expenditurePatternsService.saveExpenditureService(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        migrationPatternsService.saveMigrationPatterns(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        incomeConstraintsService.saveIncomeConstraints(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        fgdParticipantsService.saveFgdParticipants(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);

        /* ***********************************************************************************************************************************************/

        return new QuestionnaireResponseDto(
                QuestionnaireResponseStatus.ACCEPTED,
                "Questionnaire submitted successfully",
                wealthGroupQuestionnaireRequestDto.getUniqueId(),
                2
        );
    }

    public void processSummaryBoundaries(WgQuestionnaireSessionEntity savedQuestionnaireSessionEntity) {
        List<WgWealthGroupSummaryAssociatedRawDataRetrofitModel> associatedRawDataList = fetchAWealthGroupSumaryAssociatedRawData(savedQuestionnaireSessionEntity.getCountyId(), savedQuestionnaireSessionEntity.getLivelihoodZoneId(), 2, savedQuestionnaireSessionEntity.getWealthGroupId());

        for (WgWealthGroupSummaryAssociatedRawDataRetrofitModel currentSession : associatedRawDataList) {

            //Processing sub-counties
            List<WgSummariesSubCountiesEntity> wgSummariesSubCountiesEntities = wgSummariesSubCountiesRepository.findByWgQuestionnaireSessionId(savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId());
            List<WgSummariesSubCountiesEntity> existingSubCounties = wgSummariesSubCountiesEntities
                    .stream()
                    .filter(c -> c.getSubCountyId() == currentSession.getSubCountyId())
                    .collect(Collectors.toList());

            if (existingSubCounties.size() == 0) {
                wgSummariesSubCountiesRepository.save(new WgSummariesSubCountiesEntity(
                        savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId(),
                        currentSession.getSubCountyId()
                ));
            }



            //Processing wards
            List<WgSummariesWardsEntity> wgSummariesWardsEntityList = wgSummariesWardsRepository.findByWgQuestionnaireSessionId(savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId());
            List<WgSummariesWardsEntity> existingWards = wgSummariesWardsEntityList
                    .stream()
                    .filter(c -> c.getWardId() == currentSession.getWardId())
                    .collect(Collectors.toList());
            if (existingWards.size() == 0) {
                wgSummariesWardsRepository.save(new WgSummariesWardsEntity(
                        savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId(),
                        currentSession.getWardId()
                ));
            }



            //Processing sub-locaions
            List<WgSummariesSubLocationsEntity> wgSummariesSubLocationsEntityList = wgSummarisedSubLocationsRepository.findByWgQuestionnaireSessionId(savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId());
            List<WgSummariesSubLocationsEntity> existingSubLocations = wgSummariesSubLocationsEntityList
                    .stream()
                    .filter(c -> c.getSubLocationId() == currentSession.getSubLocationId())
                    .collect(Collectors.toList());
            if (existingSubLocations.size() == 0) {
                wgSummarisedSubLocationsRepository.save(new WgSummariesSubLocationsEntity(
                        savedQuestionnaireSessionEntity.getWgQuestionnaireSessionId(),
                        currentSession.getSubLocationId()
                ));
            }
        }
    }

    public List<WgWealthGroupSummaryAssociatedRawDataRetrofitModel> fetchAWealthGroupSumaryAssociatedRawData(int countyId, int livelihoodZoneId, int wgQuestionnaireTypeId, int wealthGroupId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgWealthGroupSummaryAssociatedRawDataRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchAWealthGroupSumaryAssociatedRawData(countyId, livelihoodZoneId, wgQuestionnaireTypeId, wealthGroupId);
        try {
            Response<List<WgWealthGroupSummaryAssociatedRawDataRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }
}
