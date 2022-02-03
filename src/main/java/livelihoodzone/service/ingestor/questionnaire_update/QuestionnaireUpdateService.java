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

    public void processQuestionnaireUpdate(MultipartFile file, int wgQuestionnaireSessionId) {

        if (!qualityChecksService.hasIncomeSourcesSection(wgQuestionnaireSessionId)) {
            incomeSourcesUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

        if (!qualityChecksService.hasFoodConsumptionSection(wgQuestionnaireSessionId)) {
            incomeSourcesUpdateExcelHelper.processExcelQuestionnaire(file,wgQuestionnaireSessionId);
        }

    }
}
