package livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants.FgdParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FgdParticipantsRepository extends JpaRepository<FgdParticipantsEntity, Long> {
}
