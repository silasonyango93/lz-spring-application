package livelihoodzone.service.questionnaire.wealthgroup.fgd_participants;

import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.FgdParticipantModel;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants.FgdParticipantsEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants.FgdParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FgdParticipantsService {

    @Autowired
    FgdParticipantsRepository fgdParticipantsRepository;

    public void saveFgdParticipants(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        List<FgdParticipantsEntity> participantsToBeSaved = new ArrayList<>();
        List<FgdParticipantModel> fdgParticipants = wealthGroupQuestionnaireRequestDto.getFdgParticipants();

        for (FgdParticipantModel currentParticipant : fdgParticipants) {
            participantsToBeSaved.add(new FgdParticipantsEntity(
                    questionnaireSession.getWgQuestionnaireSessionId(),
                    currentParticipant.getParticipantName(),
                    (int) currentParticipant.getAge(),
                    currentParticipant.getGender(),
                    currentParticipant.getDisability(),
                    currentParticipant.getLevelOfEducation(),
                    currentParticipant.getConsentToParticipate()
            ));
        }

        fgdParticipantsRepository.saveAll(participantsToBeSaved);

    }
}
