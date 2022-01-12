package livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants.FgdParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FgdParticipantsRepository extends JpaRepository<FgdParticipantsEntity, Long> {
    public List<FgdParticipantsEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
