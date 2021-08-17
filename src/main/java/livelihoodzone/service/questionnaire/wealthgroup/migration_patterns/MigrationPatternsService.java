package livelihoodzone.service.questionnaire.wealthgroup.migration_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.MigrationPatternsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternsPercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MigrationPatternsService {
    @Autowired
    MigrationPatternsRepository migrationPatternsRepository;

    @Autowired
    WgMigrationPatternsPercentagesRepository wgMigrationPatternsPercentagesRepository;

    public void saveMigrationPatterns(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        MigrationPatternResponses migrationPatternResponses = wealthGroupQuestionnaireRequestDto.getMigrationPatternResponses();

        //Fully nomadic
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_FULLY_NOMADIC).getMigrationPatternId(),
                migrationPatternResponses.getFullyNomadic()
        ));

        //Semi nomadic
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_SEMI_NOMADIC).getMigrationPatternId(),
                migrationPatternResponses.getSemiNomadic()
        ));

        //Occasional nomadic
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_OCCASIONAL_NOMADIC).getMigrationPatternId(),
                migrationPatternResponses.getOccasionalNomadic()
        ));

        //Out-migrant labour
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_OUT_MIGRANT_LABOUR).getMigrationPatternId(),
                migrationPatternResponses.getOutMigrantLabour()
        ));

        //In-migrant labour
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_IN_MIGRANT_LABOUR).getMigrationPatternId(),
                migrationPatternResponses.getInMigrantLabour()
        ));

        //Fully settled
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_FULLY_SETTLED).getMigrationPatternId(),
                migrationPatternResponses.getFullysettled()
        ));

        //Internally displaced
        wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_INTERNALLY_DISPLACED).getMigrationPatternId(),
                migrationPatternResponses.getInternallyDisplaced()
        ));
    }
}
