package livelihoodzone.service.questionnaire.wealthgroup.labour_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabourPatternsService {

    @Autowired
    LivelihoodActivitiesRepository livelihoodActivitiesRepository;

    @Autowired
    WgGenderLivelihoodActivitiesRepository wgGenderLivelihoodActivitiesRepository;

    public void saveLabourPatterns(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {

        LabourPatternResponses labourPatternResponses = wealthGroupQuestionnaireRequestDto.getLabourPatternResponses();

        //Labour on own farms (crop production)
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LABOUR_OWN_FARM).getLivelihoodActivityId(),
                labourPatternResponses.getOwnFarmCropProduction().getMen(),
                labourPatternResponses.getOwnFarmCropProduction().getWomen()
        ));

        //Livestock husbandry
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LIVESTOCK_HUSBANDRY).getLivelihoodActivityId(),
                labourPatternResponses.getLivestockHusbandry().getMen(),
                labourPatternResponses.getLivestockHusbandry().getWomen()
        ));


        //Transport services
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.TRANSPORT_SERVICES).getLivelihoodActivityId(),
                labourPatternResponses.getTransportServices().getMen(),
                labourPatternResponses.getTransportServices().getWomen()
        ));


        //Waged labour on other farms
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.WAGED_LABOUR).getLivelihoodActivityId(),
                labourPatternResponses.getWagedLabourOnFarms().getMen(),
                labourPatternResponses.getWagedLabourOnFarms().getWomen()
        ));

        //Low-skilled non farm labour (including paid manual.
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LOW_SKILLED_NON_FARM_LABOUR).getLivelihoodActivityId(),
                labourPatternResponses.getLowSkilledNonFarmLabour().getMen(),
                labourPatternResponses.getLowSkilledNonFarmLabour().getWomen()
        ));

        //Skilled labor (carpentry, masonry, artisans, )
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.SKILLED_LABOUR).getLivelihoodActivityId(),
                labourPatternResponses.getSkilledLabour().getMen(),
                labourPatternResponses.getSkilledLabour().getWomen()
        ));

        //Formal employment
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.FORMAL_EMPLOYMENT).getLivelihoodActivityId(),
                labourPatternResponses.getFormalEmployment().getMen(),
                labourPatternResponses.getFormalEmployment().getWomen()
        ));

        //Hunting and gathering
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.HUNTING_AND_GATHERING_LZ_ACTIVITY).getLivelihoodActivityId(),
                labourPatternResponses.getHuntingAndGathering().getMen(),
                labourPatternResponses.getHuntingAndGathering().getWomen()
        ));

        //Fishing
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.FISHING_LZ_ACTIVITY).getLivelihoodActivityId(),
                labourPatternResponses.getFishing().getMen(),
                labourPatternResponses.getFishing().getWomen()
        ));

        //Trading
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.TRADING_LZ_ACTIVITY).getLivelihoodActivityId(),
                labourPatternResponses.getTrading().getMen(),
                labourPatternResponses.getTrading().getWomen()
        ));

        //Domestic (unpaid) work including childcare
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.DOMESTIC_UNPAID_WORK).getLivelihoodActivityId(),
                labourPatternResponses.getDomesticUnpaidWork().getMen(),
                labourPatternResponses.getDomesticUnpaidWork().getWomen()
        ));

        //Begging
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.BEGGING_LZ_ACTIVITY).getLivelihoodActivityId(),
                labourPatternResponses.getBegging().getMen(),
                labourPatternResponses.getBegging().getWomen()
        ));

        //Commercial sex work
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.COMMERCIAL_SEX_WORK).getLivelihoodActivityId(),
                labourPatternResponses.getCommercialSexWork().getMen(),
                labourPatternResponses.getCommercialSexWork().getWomen()
        ));

        //Leisure, socializing and entertainment
        wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LEISURE_SOCIALIZING_ENTERTAINMENT).getLivelihoodActivityId(),
                labourPatternResponses.getLeisure().getMen(),
                labourPatternResponses.getLeisure().getWomen()
        ));

        //Others
        WgGenderLivelihoodActivitiesEntity othersObject = new WgGenderLivelihoodActivitiesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.OTHER_LIVELIHOOD_ACTIVITIES).getLivelihoodActivityId(),
                labourPatternResponses.getOthers().getMen(),
                labourPatternResponses.getOthers().getWomen()
        );
        othersObject.setExtraDescription(labourPatternResponses.getOthers().getExtraDescription());
        wgGenderLivelihoodActivitiesRepository.save(othersObject);
    }
}
