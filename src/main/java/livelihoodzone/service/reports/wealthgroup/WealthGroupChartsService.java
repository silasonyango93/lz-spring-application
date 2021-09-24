package livelihoodzone.service.reports.wealthgroup;

import livelihoodzone.common.Constants;
import livelihoodzone.dao.reports.zonal.wealthgroup.WgQuestionnaireSessionDao;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.ValueDescriptionPairModel;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesRepository;
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

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

    public List<WgLivelihoodZoneDataObject> prepareWealthGroupChart(int countyId, int wealthGroupId, int questionnaireSectionCode) {
        List<WgLivelihoodZoneDataObject> livelihoodZoneDataObjectList = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionDao.fetchQuestionnaireSessionsByCountyAndWealthGroup(countyId,wealthGroupId);

        for (WgQuestionnaireSessionEntity currentQuestionnaire : wgQuestionnaireSessionEntityList) {
            WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject = new WgLivelihoodZoneDataObject();
            wgLivelihoodZoneDataObject.setLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId());
            wgLivelihoodZoneDataObject.setLivelihoodZoneName(livelihoodZonesRepository.findByLivelihoodZoneId(currentQuestionnaire.getLivelihoodZoneId()).getLivelihoodZoneName());

            if (questionnaireSectionCode == Constants.MAIN_SOURCE_OF_INCOME_AND_FOOD) {
                wgLivelihoodZoneDataObject = processMainSourceOfIncomeAndFood(wgLivelihoodZoneDataObject, currentQuestionnaire.getWgQuestionnaireSessionId());
            }

            livelihoodZoneDataObjectList.add(wgLivelihoodZoneDataObject);
        }

        return livelihoodZoneDataObjectList;
    }

    public WgLivelihoodZoneDataObject processMainSourceOfIncomeAndFood(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {

        IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses = new IncomeAndFoodSourcesResponses();

        List<WgIncomeSourcesEntity> wgIncomeSourcesEntities = wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgIncomeSourcesEntity wgIncomeSourcesEntity : wgIncomeSourcesEntities) {
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.LIVESTOCK_PRODUCTION) {
                incomeAndFoodSourcesResponses.setLivestockProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.POULTRY_PRODUCTION) {
                incomeAndFoodSourcesResponses.setPoultryProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.PASTURE_FODDER_PRODUCTION) {
                incomeAndFoodSourcesResponses.setPastureFodderProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.CASH_CROP_PRODUCTION) {
                incomeAndFoodSourcesResponses.setCashCropProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FOOD_CROP_PRODUCTION) {
                incomeAndFoodSourcesResponses.setFoodCropProduction(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.CASUAL_WAGED_LABOUR_INCOME) {
                incomeAndFoodSourcesResponses.setCasualOrWagedLabour(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FORMAL_WAGED_LABOUR) {
                incomeAndFoodSourcesResponses.setFormalWagedLabour(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FISHING) {
                incomeAndFoodSourcesResponses.setFishing(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.HUNTING_AND_GATHERING) {
                incomeAndFoodSourcesResponses.setHuntingAndGathering(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.SMALL_BUSINESSES) {
                incomeAndFoodSourcesResponses.setSmallBusiness(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.FIREWOOD_COLLECTION) {
                incomeAndFoodSourcesResponses.setFirewoodOrCharcoal(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.PETTY_TRADING) {
                incomeAndFoodSourcesResponses.setPettyTrading(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.REMITTANCE_AND_GIFTS) {
                incomeAndFoodSourcesResponses.setRemittance(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.BODABODA_TRANSPORT) {
                incomeAndFoodSourcesResponses.setBodaboda(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.BEE_KEEPING) {
                incomeAndFoodSourcesResponses.setBeeKeeping(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.SAND_HARVESTING) {
                incomeAndFoodSourcesResponses.setSandHarvesting(wgIncomeSourcesEntity.getIncomeSourcePercentage());
            }
            if (cashIncomeSourcesRepository.findByCashIncomeSourceId(wgIncomeSourcesEntity.getCashIncomeSourceId()).getCashIncomeSourceCode() == Constants.OTHERS_INCOME_SOURCES) {
                incomeAndFoodSourcesResponses.setOther(new ValueDescriptionPairModel(
                        wgIncomeSourcesEntity.getIncomeSourcePercentage(),
                        wgIncomeSourcesEntity.getExtraDescription()
                ));
            }
        }

        wgLivelihoodZoneDataObject.setIncomeAndFoodSourcesResponses(incomeAndFoodSourcesResponses);

        return wgLivelihoodZoneDataObject;
    }
}
