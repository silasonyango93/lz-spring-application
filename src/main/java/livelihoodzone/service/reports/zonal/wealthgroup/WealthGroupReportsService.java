package livelihoodzone.service.reports.zonal.wealthgroup;

import livelihoodzone.common.Constants;
import livelihoodzone.dao.reports.zonal.wealthgroup.LzQuestionnaireSessionDao;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupPopulationPercentageEntity;
import livelihoodzone.repository.questionnaire.county.LzWealthGroupPopulationPercentageRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WealthGroupReportsService {

    @Autowired
    LzQuestionnaireSessionDao lzQuestionnaireSessionDao;

    @Autowired
    LzWealthGroupPopulationPercentageRepository lzWealthGroupPopulationPercentageRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    public WealthGroupReportResponseDto retrieveWealthGroupPopulationPercentageReportByCountyAndLivelihoodZone(int countyId, int livelihoodzoneId) {
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionDao.fetchQuestionnaireSessionByCountyAndLivelihoodZone(countyId,livelihoodzoneId);

        if (!lzQuestionnaireSessionEntityList.isEmpty()) {
            //todo: This logic is to be reviewed
            LzQuestionnaireSessionEntity summarisedZoneQuestionnaire = lzQuestionnaireSessionEntityList.get(0);
            return processWealthGroupPercentagesData(summarisedZoneQuestionnaire.getLzQuestionnaireSessionId());
        }
        return null;
    }

    public WealthGroupReportResponseDto processWealthGroupPercentagesData(int lzQuestionnaireSessionId) {

        WealthGroupReportResponseDto wealthGroupReportResponseDto = new WealthGroupReportResponseDto();

        List<LzWealthGroupPopulationPercentageEntity> lzWealthGroupPopulationPercentageEntityList = lzWealthGroupPopulationPercentageRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        wealthGroupReportResponseDto.setVeryPoor(returnWealthGroupPopulationPercentageValue(wealthGroupRepository.findByWealthGroupCode(Constants.VERY_POOR_CODE).getWealthGroupId(), lzWealthGroupPopulationPercentageEntityList));
        wealthGroupReportResponseDto.setPoor(returnWealthGroupPopulationPercentageValue(wealthGroupRepository.findByWealthGroupCode(Constants.POOR_CODE).getWealthGroupId(), lzWealthGroupPopulationPercentageEntityList));
        wealthGroupReportResponseDto.setMedium(returnWealthGroupPopulationPercentageValue(wealthGroupRepository.findByWealthGroupCode(Constants.MEDIUM_CODE).getWealthGroupId(), lzWealthGroupPopulationPercentageEntityList));
        wealthGroupReportResponseDto.setBetterOff(returnWealthGroupPopulationPercentageValue(wealthGroupRepository.findByWealthGroupCode(Constants.BETTER_OFF_CODE).getWealthGroupId(), lzWealthGroupPopulationPercentageEntityList));

        return wealthGroupReportResponseDto;
    }

    public double returnWealthGroupPopulationPercentageValue(int wealthGroupId, List<LzWealthGroupPopulationPercentageEntity> lzWealthGroupPopulationPercentageEntityList) {
        for (LzWealthGroupPopulationPercentageEntity currentEntity : lzWealthGroupPopulationPercentageEntityList) {
            if (currentEntity.getWealthGroupId() == wealthGroupId) {
                return currentEntity.getPopulationPercentage();
            }
        }
        return 0.0;
    }
}