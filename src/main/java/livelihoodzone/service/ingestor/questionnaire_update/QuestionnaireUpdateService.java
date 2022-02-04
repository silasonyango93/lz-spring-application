package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.service.reports.wealthgroup.quality_checks.QualityChecksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuestionnaireUpdateService {

    @Autowired
    QualityChecksService qualityChecksService;

    @Autowired
    IncomeSourcesUpdateExcelHelper incomeSourcesUpdateExcelHelper;

    @Autowired
    FoodConsumptionUpdateExcelHelper foodConsumptionUpdateExcelHelper;

    @Autowired
    LivestockOwnershipUpdateExcelHelper livestockOwnershipUpdateExcelHelper;

    @Autowired
    LivestockContributionUpdateExcelHelper livestockContributionUpdateExcelHelper;

    @Autowired
    LabourPatternsUpdateExcelHelper labourPatternsUpdateExcelHelper;

    @Autowired
    ExpenditurePatternsUpdateExcelHelper expenditurePatternsUpdateExcelHelper;

    @Autowired
    MigrationPatternsUpdateExcelService migrationPatternsUpdateExcelService;

    @Autowired
    CropContributionUpdateExcelService cropContributionUpdateExcelService;

    @Autowired
    FgdParticipantsUpdateExcelHelper fgdParticipantsUpdateExcelHelper;

    @Autowired
    ConstraintsUpdateExcelHelper constraintsUpdateExcelHelper;

    public void processQuestionnaireUpdate(MultipartFile file, int wgQuestionnaireSessionId) {

        if (!qualityChecksService.hasIncomeSourcesSection(wgQuestionnaireSessionId)) {
            incomeSourcesUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasFoodConsumptionSection(wgQuestionnaireSessionId)) {
            foodConsumptionUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasLivestockOwnershipSection(wgQuestionnaireSessionId)) {
            livestockOwnershipUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasLivestockContributionSection(wgQuestionnaireSessionId)) {
            livestockContributionUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasLabourPatternsSection(wgQuestionnaireSessionId)) {
            labourPatternsUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasExpenditurePatternsSection(wgQuestionnaireSessionId)) {
            expenditurePatternsUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasMigrationPatternsSection(wgQuestionnaireSessionId)) {
            migrationPatternsUpdateExcelService.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasCropProductionSection(wgQuestionnaireSessionId)) {
            cropContributionUpdateExcelService.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasFgdParticipantsSection(wgQuestionnaireSessionId)) {
            fgdParticipantsUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasConstraintsSection(wgQuestionnaireSessionId)) {
            constraintsUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

    }
}
