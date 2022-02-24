package livelihoodzone.service.chores;

import livelihoodzone.dto.NumberDescriptionPairDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WgDuplicateQuestionnairesService {

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    public List<String> fetchCountyDuplicateWealthGroupQuestionnaires(int countyId) {
        List<String> duplicateQuestionnaires = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);

        for (CountyLivelihoodZonesAssignmentEntity countyLivelihoodZonesAssignmentEntity : countyLivelihoodZonesAssignmentEntityList) {
            List<WgQuestionnaireSessionEntity> veryPoor = wgQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneIdAndWgQuestionnaireTypeIdAndWealthGroupId(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(),1,1);
            List<WgQuestionnaireSessionEntity> poor = wgQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneIdAndWgQuestionnaireTypeIdAndWealthGroupId(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(),1,2);
            List<WgQuestionnaireSessionEntity> medium = wgQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneIdAndWgQuestionnaireTypeIdAndWealthGroupId(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(),1,3);
            List<WgQuestionnaireSessionEntity> betterOff = wgQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneIdAndWgQuestionnaireTypeIdAndWealthGroupId(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(),1,4);
            if (veryPoor.size()  > 1) {
                duplicateQuestionnaires.add(veryPoor.get(0).getQuestionnaireSessionDescription());
            }
            if (poor.size()  > 1) {
                duplicateQuestionnaires.add(poor.get(0).getQuestionnaireSessionDescription());
            }
            if (medium.size()  > 1) {
                duplicateQuestionnaires.add(medium.get(0).getQuestionnaireSessionDescription());
            }
            if (betterOff.size()  > 1) {
                duplicateQuestionnaires.add(betterOff.get(0).getQuestionnaireSessionDescription());
            }
        }
        return duplicateQuestionnaires;
    }


    public List<String> fetchCountrywideDuplicateWealthGroupQuestionnaires() {
        List<String> duplicateQuestionnaires = new ArrayList<>();

        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            duplicateQuestionnaires.addAll(fetchCountyDuplicateWealthGroupQuestionnaires(countiesEntity.getCountyId()));
        }
        return duplicateQuestionnaires;
    }


    public List<String> fetchCountyDuplicateZoneLevelQuestionnaires(int countyId) {
        List<String> duplicateQuestionnaires = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);

        for (CountyLivelihoodZonesAssignmentEntity countyLivelihoodZonesAssignmentEntity : countyLivelihoodZonesAssignmentEntityList) {
            List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneId(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId());
            if (lzQuestionnaireSessionEntityList.size()  > 1) {
                duplicateQuestionnaires.add(lzQuestionnaireSessionEntityList.get(0).getQuestionnaireSessionDescription());
            }
        }
        return duplicateQuestionnaires;
    }

    public List<String> fetchCountrywideDuplicateZoneLevelQuestionnaires() {
        List<String> duplicateQuestionnaires = new ArrayList<>();

        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            duplicateQuestionnaires.addAll(fetchCountyDuplicateZoneLevelQuestionnaires(countiesEntity.getCountyId()));
        }
        return duplicateQuestionnaires;
    }
}
