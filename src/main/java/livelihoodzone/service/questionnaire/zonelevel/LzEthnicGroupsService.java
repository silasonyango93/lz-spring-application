package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.model.ethnicgroup.EthnicityResponseItem;
import livelihoodzone.dto.reports.zonal.charts.EthnicityResponseObject;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsPercentagesEntity;
import livelihoodzone.repository.questionnaire.tribe.LzEthnicGroupsPercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LzEthnicGroupsService {

    @Autowired
    LzEthnicGroupsPercentagesRepository lzEthnicGroupsPercentagesRepository;

    public void saveEthnicGroups(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        List<EthnicityResponseItem> ethnicGroupResponseList = countyLevelQuestionnaireRequestDto.getEthnicGroupResponseList();
        List<EthnicGroupsPercentagesEntity> ethnicGroupsPercentageList = new ArrayList<>();

        for (EthnicityResponseItem currentItem : ethnicGroupResponseList) {
            ethnicGroupsPercentageList.add(new EthnicGroupsPercentagesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentItem.getEthnicGroupModel().getEthnicGroupId(),
                    currentItem.getPopulationPercentage()
            ));
        }

        lzEthnicGroupsPercentagesRepository.saveAll(ethnicGroupsPercentageList);
    }

    public void updateEthnicGroups(int lzQuestionnaireSessionId, EthnicityResponseObject ethnicityResponseObject) {
        lzEthnicGroupsPercentagesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setEthnicGroupResponseList(ethnicityResponseObject.getEthnicGroupResponseList());
        saveEthnicGroups(countyLevelQuestionnaireRequestDto,savedQuestionnaireSession);
    }
}
