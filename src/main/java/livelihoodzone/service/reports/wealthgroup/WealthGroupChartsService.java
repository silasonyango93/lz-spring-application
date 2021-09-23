package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.common.Constants;
import livelihoodzone.dao.reports.zonal.wealthgroup.WgQuestionnaireSessionDao;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WealthGroupChartsService {

    @Autowired
    WgQuestionnaireSessionDao wgQuestionnaireSessionDao;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    public void prepareWealthGroupChart(int countyId, int wealthGroupId, int questionnaireSectionCode) {
        List<WgLivelihoodZoneDataObject> livelihoodZoneDataObjectList = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionDao.fetchQuestionnaireSessionsByCountyAndWealthGroup(countyId,wealthGroupId);

        for (WgQuestionnaireSessionEntity currentQuestionnaire : wgQuestionnaireSessionEntityList) {
            WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject = new WgLivelihoodZoneDataObject();
            wgLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            wgLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());

            if (questionnaireSectionCode == Constants.MAIN_SOURCE_OF_INCOME_AND_FOOD) {

            }
        }

    }

    public WgLivelihoodZoneDataObject processMainSourceOfIncomeAndFood(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {

        List<WgIncomeSourcesEntity> wgIncomeSourcesEntities = wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgIncomeSourcesEntity wgIncomeSourcesEntity : wgIncomeSourcesEntities) {
//            if (wgIncomeSourcesRepository.findByWgIncomeSourceId(wgIncomeSourcesEntity.getWgIncomeSourceId()).) {
//
//            }
        }

        return wgLivelihoodZoneDataObject;
    }
}
