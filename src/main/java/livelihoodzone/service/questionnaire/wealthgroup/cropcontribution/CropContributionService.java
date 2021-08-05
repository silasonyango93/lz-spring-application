package livelihoodzone.service.questionnaire.wealthgroup.cropcontribution;

import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.WgCropContributionsEntity;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution.CropContributionTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution.WgCropContributionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropContributionService {

    @Autowired
    WgCropContributionsRepository wgCropContributionsRepository;

    public void saveCropContribution(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        List<WgCropContributionResponseItem> cropContributionResponseItems = wealthGroupQuestionnaireRequestDto.getCropContributionResponseItems();
        List<WgCropContributionsEntity> wgCropContributionsEntityList = new ArrayList<>();

        for (WgCropContributionResponseItem currentItem : cropContributionResponseItems) {
            wgCropContributionsEntityList.add(new WgCropContributionsEntity(
                    questionnaireSession.getWgQuestionnaireSessionId(),
                    currentItem.getCropModel().getCropId(),
                    (int) currentItem.getCashIncomeRank().getActualValue(),
                    currentItem.getCashIncomeApproxPercentage().getActualValue(),
                    (int) currentItem.getFoodConsumptionRank().getActualValue(),
                    currentItem.getFoodConsumptionApproxPercentage().getActualValue()
            ));
        }

        wgCropContributionsRepository.saveAll(wgCropContributionsEntityList);
    }
}
