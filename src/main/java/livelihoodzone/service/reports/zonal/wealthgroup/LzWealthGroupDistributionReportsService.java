package livelihoodzone.service.reports.zonal.wealthgroup;

import livelihoodzone.common.Constants;
import livelihoodzone.dao.reports.zonal.wealthgroup.LzQuestionnaireSessionDao;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupPopulationPercentageReportResponseObject;
import livelihoodzone.dto.reports.zonal.wealthgroup.WealthGroupReportResponseDto;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupCharacteristicsEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupPopulationPercentageEntity;
import livelihoodzone.repository.questionnaire.county.LzWealthGroupCharacteristicsRepository;
import livelihoodzone.repository.questionnaire.county.LzWealthGroupPopulationPercentageRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupCharacteristicsRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupPopulationPercentageRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LzWealthGroupDistributionReportsService {

    @Autowired
    LzQuestionnaireSessionDao lzQuestionnaireSessionDao;

    @Autowired
    LzWealthGroupPopulationPercentageRepository lzWealthGroupPopulationPercentageRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    @Autowired
    LzWealthGroupCharacteristicsRepository lzWealthGroupCharacteristicsRepository;

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


    public List<WealthGroupCharacteristicsRetrofitModel> fetchWealthGroupCharacteristicsReportsComprehensively() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<WealthGroupCharacteristicsRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchWealthGroupCharacteristicsReportsComprehensively();
        try {
            Response<List<WealthGroupCharacteristicsRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public List<WealthGroupPopulationPercentageRetrofitModel> fetchWealthGroupPopulationPercentage() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<WealthGroupPopulationPercentageRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchWealthGroupPopulationPercentage();
        try {
            Response<List<WealthGroupPopulationPercentageRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public WealthGroupPopulationPercentageReportResponseObject processWealthGroupPopulationReport(List<WealthGroupPopulationPercentageRetrofitModel> wealthGroupPopulationPercentageRetrofitModelList) {
        List<Number> veryPoorPopulationPercentage = new ArrayList<>();
        List<Number> poorPopulationPercentage = new ArrayList<>();
        List<Number> mediumPopulationPercentage = new ArrayList<>();
        List<Number> betterOffPopulationPercentage = new ArrayList<>();

        for (WealthGroupPopulationPercentageRetrofitModel currentItem : wealthGroupPopulationPercentageRetrofitModelList) {
            if (currentItem.getWealthGroupCode() == Constants.VERY_POOR_CODE) {
                veryPoorPopulationPercentage.add(currentItem.getPopulationPercentage());
            }

            if (currentItem.getWealthGroupCode() == Constants.POOR_CODE) {
                poorPopulationPercentage.add(currentItem.getPopulationPercentage());
            }

            if (currentItem.getWealthGroupCode() == Constants.MEDIUM_CODE) {
                mediumPopulationPercentage.add(currentItem.getPopulationPercentage());
            }

            if (currentItem.getWealthGroupCode() == Constants.BETTER_OFF_CODE) {
                betterOffPopulationPercentage.add(currentItem.getPopulationPercentage());
            }
        }

        return new WealthGroupPopulationPercentageReportResponseObject(veryPoorPopulationPercentage,poorPopulationPercentage,mediumPopulationPercentage,betterOffPopulationPercentage);
    }


    public LzLivelihoodZoneDataObject processWealthGroupCharacteristicsChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        WealthGroupCharectaristicsResponses wealthGroupCharectariticsResponses = new WealthGroupCharectaristicsResponses(true);
        List<LzWealthGroupCharacteristicsEntity> lzWealthGroupCharacteristicsEntityList = lzWealthGroupCharacteristicsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzWealthGroupCharacteristicsEntity lzWealthGroupCharacteristicsEntity : lzWealthGroupCharacteristicsEntityList) {
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupCharacteristicsEntity.getWealthGroupId()).getWealthGroupCode() == Constants.VERY_POOR_CODE) {
                wealthGroupCharectariticsResponses.getVeryPoorCharectaristics().add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupCharacteristicsEntity.getWealthGroupId()).getWealthGroupCode() == Constants.POOR_CODE) {
                wealthGroupCharectariticsResponses.getPoorCharectaristics().add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupCharacteristicsEntity.getWealthGroupId()).getWealthGroupCode() == Constants.MEDIUM_CODE) {
                wealthGroupCharectariticsResponses.getMediumCharectaristics().add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupCharacteristicsEntity.getWealthGroupId()).getWealthGroupCode() == Constants.BETTER_OFF_CODE) {
                wealthGroupCharectariticsResponses.getBetterOffCharectaristics().add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
        }
        lzLivelihoodZoneDataObject.setWealthGroupCharectariticsResponses(wealthGroupCharectariticsResponses);
        return lzLivelihoodZoneDataObject;
    }


    public LzLivelihoodZoneDataObject processWealthGroupPopulationPercentageChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        WealthGroupPercentageResponse wealthGroupResponse = new WealthGroupPercentageResponse();
        List<LzWealthGroupPopulationPercentageEntity> lzWealthGroupPopulationPercentageEntityList = lzWealthGroupPopulationPercentageRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzWealthGroupPopulationPercentageEntity lzWealthGroupPopulationPercentageEntity : lzWealthGroupPopulationPercentageEntityList) {
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.VERY_POOR_CODE) {
                wealthGroupResponse.setVerPoorResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.POOR_CODE) {
                wealthGroupResponse.setPoorResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.MEDIUM_CODE) {
                wealthGroupResponse.setMediumResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.BETTER_OFF_CODE) {
                wealthGroupResponse.setBetterOfResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }

        }
        lzLivelihoodZoneDataObject.setWealthGroupResponse(wealthGroupResponse);
        return lzLivelihoodZoneDataObject;
    }


    public LzLivelihoodZoneDataObject processWealthGroupPopulationPercentageChartByWealthGroup(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId, int wealthGroupCode) {
        WealthGroupPercentageResponse wealthGroupResponse = new WealthGroupPercentageResponse();
        List<LzWealthGroupPopulationPercentageEntity> lzWealthGroupPopulationPercentageEntityList = lzWealthGroupPopulationPercentageRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzWealthGroupPopulationPercentageEntity lzWealthGroupPopulationPercentageEntity : lzWealthGroupPopulationPercentageEntityList) {
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.VERY_POOR_CODE && wealthGroupCode == Constants.VERY_POOR_CODE) {
                wealthGroupResponse.setVerPoorResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
                wealthGroupResponse.setParameterValue(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.POOR_CODE && wealthGroupCode == Constants.POOR_CODE) {
                wealthGroupResponse.setPoorResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
                wealthGroupResponse.setParameterValue(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.MEDIUM_CODE && wealthGroupCode == Constants.MEDIUM_CODE) {
                wealthGroupResponse.setMediumResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
                wealthGroupResponse.setParameterValue(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }
            if (wealthGroupRepository.findByWealthGroupId(lzWealthGroupPopulationPercentageEntity.getWealthGroupId()).getWealthGroupCode() == Constants.BETTER_OFF_CODE && wealthGroupCode == Constants.BETTER_OFF_CODE) {
                wealthGroupResponse.setBetterOfResponse(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
                wealthGroupResponse.setParameterValue(lzWealthGroupPopulationPercentageEntity.getPopulationPercentage());
            }

        }
        lzLivelihoodZoneDataObject.setWealthGroupResponse(wealthGroupResponse);
        return lzLivelihoodZoneDataObject;
    }
}
