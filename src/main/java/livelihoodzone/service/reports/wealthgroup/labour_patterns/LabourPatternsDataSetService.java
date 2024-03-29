package livelihoodzone.service.reports.wealthgroup.labour_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
import livelihoodzone.dto.reports.wealthgroup.WgLabourPatternsDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgAnimalOwnershipRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgLabourPatternsRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgLivestockContributionRetrofitModel;
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
public class LabourPatternsDataSetService {

    @Autowired
    WgGenderLivelihoodActivitiesRepository wgGenderLivelihoodActivitiesRepository;

    @Autowired
    LivelihoodActivitiesRepository livelihoodActivitiesRepository;

    public List<WgLabourPatternsRetrofitModel> fetchWealthGroupLabourPatterns(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgLabourPatternsRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupLabourPatterns(countyId, questionnaireTypeId);
        try {
            Response<List<WgLabourPatternsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WgLabourPatternsDataSetObject processLabourPatterns(List<WgLabourPatternsRetrofitModel> list) {
        List<String> labourOnOwnFarms = processAnActivityReport(Constants.LABOUR_OWN_FARM, list);
        List<String> livestockhusbandry = processAnActivityReport(Constants.LIVESTOCK_HUSBANDRY, list);
        List<String> wagedLabourOnOtherFarms = processAnActivityReport(Constants.WAGED_LABOUR, list);
        List<String> lowSkilledNonFarmLabour = processAnActivityReport(Constants.LOW_SKILLED_NON_FARM_LABOUR, list);
        List<String> skilledLabour = processAnActivityReport(Constants.SKILLED_LABOUR, list);
        List<String> formalEmployment = processAnActivityReport(Constants.FORMAL_EMPLOYMENT, list);
        List<String> huntingAndGathering = processAnActivityReport(Constants.HUNTING_AND_GATHERING_LZ_ACTIVITY, list);
        List<String> fishing = processAnActivityReport(Constants.FISHING_LZ_ACTIVITY, list);
        List<String> trading = processAnActivityReport(Constants.TRADING_LZ_ACTIVITY, list);
        List<String> domesticUnpaidWork = processAnActivityReport(Constants.DOMESTIC_UNPAID_WORK, list);
        List<String> begging = processAnActivityReport(Constants.BEGGING_LZ_ACTIVITY, list);
        List<String> commercialSexWork = processAnActivityReport(Constants.COMMERCIAL_SEX_WORK, list);
        List<String> leisureSocializingEntertainment = processAnActivityReport(Constants.LEISURE_SOCIALIZING_ENTERTAINMENT, list);
        List<String> others = processAnActivityReport(Constants.OTHER_LIVELIHOOD_ACTIVITIES, list);
        List<String> transportServices = processAnActivityReport(Constants.TRANSPORT_SERVICES, list);

        return new WgLabourPatternsDataSetObject(
                labourOnOwnFarms,
                livestockhusbandry,
                wagedLabourOnOtherFarms,
                lowSkilledNonFarmLabour,
                skilledLabour,
                formalEmployment,
                huntingAndGathering,
                fishing,
                trading,
                domesticUnpaidWork,
                begging,
                commercialSexWork,
                leisureSocializingEntertainment,
                others,
                transportServices
        );
    }


    public List<String> processAnActivityReport(int activityCode, List<WgLabourPatternsRetrofitModel> list) {
        List<WgLabourPatternsRetrofitModel> processedList = returnListForSpecificActivity(activityCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = -1;
        if (processedList.size() > 0) {
            currentQuestionnaireSessionId = processedList.get(0).getWgQuestionnaireSessionId();
        }
        String currentReportString = "";

        for (WgLabourPatternsRetrofitModel currentItem : processedList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {

                if (activityCode == Constants.OTHER_LIVELIHOOD_ACTIVITIES && !currentItem.getLivelihoodActivityDescription().isEmpty()) {
                    currentReportString = currentReportString + "Activity description -> " + currentItem.getLivelihoodActivityDescription() + "  "  +"Men -> " + currentItem.getMenPercentage() + "%" + "  Women -> " + currentItem.getWomenPercentage() + "% ";
                } else {
                    currentReportString = currentReportString + "Men -> " + currentItem.getMenPercentage() + "%" + "  Women -> " + currentItem.getWomenPercentage() + "% ";
                }
            } else {
                stringReportList.add(currentReportString);
                if (activityCode == Constants.OTHER_LIVELIHOOD_ACTIVITIES && !currentItem.getLivelihoodActivityDescription().isEmpty()) {
                    currentReportString = "Activity description -> " + currentItem.getLivelihoodActivityDescription() + "  "  +"Men -> " + currentItem.getMenPercentage() + "%" + "  Women -> " + currentItem.getWomenPercentage() + "% ";
                } else {
                    currentReportString = "Men -> " + currentItem.getMenPercentage() + "%" + "  Women -> " + currentItem.getWomenPercentage() + "% ";
                }
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }

    public List<WgLabourPatternsRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgLabourPatternsRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgLabourPatternsRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

    public List<WgLabourPatternsRetrofitModel> returnListForSpecificActivity(int activityCode, List<WgLabourPatternsRetrofitModel> allItems) {
        List<WgLabourPatternsRetrofitModel> processedList = new ArrayList<>();

        for (WgLabourPatternsRetrofitModel currentItem : allItems) {
            if (currentItem.getLivelihoodActivityCode() == activityCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }

    public WgLivelihoodZoneDataObject processLabourPatternsChart(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        LabourPatternResponses labourPatternResponses = new LabourPatternResponses(true);
        List<WgGenderLivelihoodActivitiesEntity> wgGenderLivelihoodActivitiesEntityList = wgGenderLivelihoodActivitiesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgGenderLivelihoodActivitiesEntity wgGenderLivelihoodActivitiesEntity : wgGenderLivelihoodActivitiesEntityList) {
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.LABOUR_OWN_FARM) {
                labourPatternResponses.getOwnFarmCropProduction().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getOwnFarmCropProduction().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.LIVESTOCK_HUSBANDRY) {
                labourPatternResponses.getLivestockHusbandry().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getLivestockHusbandry().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.WAGED_LABOUR) {
                labourPatternResponses.getWagedLabourOnFarms().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getWagedLabourOnFarms().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.LOW_SKILLED_NON_FARM_LABOUR) {
                labourPatternResponses.getLowSkilledNonFarmLabour().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getLowSkilledNonFarmLabour().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.SKILLED_LABOUR) {
                labourPatternResponses.getSkilledLabour().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getSkilledLabour().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.FORMAL_EMPLOYMENT) {
                labourPatternResponses.getFormalEmployment().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getFormalEmployment().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.HUNTING_AND_GATHERING_LZ_ACTIVITY) {
                labourPatternResponses.getHuntingAndGathering().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getHuntingAndGathering().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.FISHING_LZ_ACTIVITY) {
                labourPatternResponses.getFishing().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getFishing().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.TRADING_LZ_ACTIVITY) {
                labourPatternResponses.getTrading().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getTrading().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.DOMESTIC_UNPAID_WORK) {
                labourPatternResponses.getDomesticUnpaidWork().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getDomesticUnpaidWork().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.BEGGING_LZ_ACTIVITY) {
                labourPatternResponses.getBegging().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getBegging().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.COMMERCIAL_SEX_WORK) {
                labourPatternResponses.getCommercialSexWork().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getCommercialSexWork().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.LEISURE_SOCIALIZING_ENTERTAINMENT) {
                labourPatternResponses.getLeisure().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getLeisure().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.OTHER_LIVELIHOOD_ACTIVITIES) {
                labourPatternResponses.getOthers().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getOthers().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
                labourPatternResponses.getOthers().setExtraDescription(wgGenderLivelihoodActivitiesEntity.getExtraDescription());
            }
            if (livelihoodActivitiesRepository.findByLivelihoodActivityId(wgGenderLivelihoodActivitiesEntity.getLivelihoodActivityId()).getLivelihoodActivityCode() == Constants.TRANSPORT_SERVICES) {
                labourPatternResponses.getTransportServices().setMen(wgGenderLivelihoodActivitiesEntity.getMenPercentage());
                labourPatternResponses.getTransportServices().setWomen(wgGenderLivelihoodActivitiesEntity.getWomenPercentage());
            }
        }
        wgLivelihoodZoneDataObject.setLabourPatterns(labourPatternResponses);
        return wgLivelihoodZoneDataObject;
    }
}
