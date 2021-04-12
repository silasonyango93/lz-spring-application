package livelihoodzone.service.questionnaire;

import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyLevelService {

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    public QuestionnaireResponseDto submitCountyLevelQuestionnaire(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, User dataCollector) {

        List<LzQuestionnaireSessionEntity> existingQuestionnaires = lzQuestionnaireSessionRepository.findByLzQuestionnaireUniqueId(countyLevelQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(
                    QuestionnaireResponseStatus.DUPLICATE,
                    "Duplicate questionnaire"
            );
        }

        lzQuestionnaireSessionRepository.save(new LzQuestionnaireSessionEntity(
                dataCollector.getUserId(),
                dataCollector.getCountyId(),
                countyLevelQuestionnaireRequestDto.getSelectedLivelihoodZone().getLivelihoodZoneId(),
                "none",
                countyLevelQuestionnaireRequestDto.getLatitude(),
                countyLevelQuestionnaireRequestDto.getLongitude(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireStartDate(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireEndDate(),
                countyLevelQuestionnaireRequestDto.getUniqueId()
        ));

        return new QuestionnaireResponseDto(
                QuestionnaireResponseStatus.ACCEPTED,
                "Questionnaire submitted successfully"
        );
    }
}
