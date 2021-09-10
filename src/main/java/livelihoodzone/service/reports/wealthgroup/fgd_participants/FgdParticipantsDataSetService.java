package livelihoodzone.service.reports.wealthgroup.fgd_participants;

import livelihoodzone.dto.reports.wealthgroup.WgFgdParticipantsDataSetObject;
import livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants.FgdParticipantsEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants.FgdParticipantsRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgConstraintsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgCropContributionRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgFgdParticipantsDataSetRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class FgdParticipantsDataSetService {

    @Autowired
    FgdParticipantsRepository fgdParticipantsRepository;

    public List<WgFgdParticipantsDataSetRetrofitModel> fetchWealthGroupFgdParticipants(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgFgdParticipantsDataSetRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupFgdParticipants(countyId,questionnaireTypeId);
        try {
            Response<List<WgFgdParticipantsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }



    public WgFgdParticipantsDataSetObject processFgdParticipants(List<WgFgdParticipantsDataSetRetrofitModel> list) {
        List<WgFgdParticipantsDataSetRetrofitModel> processedList = clusterSameQuestionnaireItemsTogether(list);
        List<String> stringReportList = new ArrayList<>();

        int currentQuestionnaireSessionId = list.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (WgFgdParticipantsDataSetRetrofitModel currentItem : list) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getParticipantName() + "  "
                        +  determineGender(currentItem.getGender()) + "  " + determineAgeBand(currentItem.getAgeBracket()) + "  "
                        +  determineLevelOfEducation(currentItem.getLevelOfEducation()) + "  "
                        +  determineConsentToParticipate(currentItem.getConsentToParticipate()) + "  ";
                counter++;
            } else {
                stringReportList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getParticipantName() + "  "
                        +  determineGender(currentItem.getGender()) + "  " + determineAgeBand(currentItem.getAgeBracket()) + "  "
                        +  determineLevelOfEducation(currentItem.getLevelOfEducation()) + "  "
                        +  determineConsentToParticipate(currentItem.getConsentToParticipate()) + "  ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return new WgFgdParticipantsDataSetObject(
                stringReportList
        );
    }

    public List<WgFgdParticipantsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgFgdParticipantsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgFgdParticipantsDataSetRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

    private String determineGender(int genderCode) {
        return genderCode == 1 ? "Male" : "Female";
    }

    private String determineAgeBand(int ageBandCode) {
        if (ageBandCode == 1) {
            return "18-24 years";
        }
        if (ageBandCode == 2) {
            return "25-35 years";
        }
        if (ageBandCode == 3) {
            return "36-49 years";
        }
        if (ageBandCode == 4) {
            return "50-59 years";
        }
        return "50-59 years";
    }

    private String determineLevelOfEducation(int educationLevelCode) {
        if (educationLevelCode == 1) {
            return "Non-formal education";
        }
        if (educationLevelCode == 2) {
            return "Primary";
        }
        if (educationLevelCode == 3) {
            return "Secondary";
        }
        return "Post-Secondary";
    }

    private String determineConsentToParticipate(int consentCode) {
        return consentCode == 1 ? "Consented to participate" : "Did not consent to participate";
    }
}
