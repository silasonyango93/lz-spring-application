package livelihoodzone.service.reports.zonal.ethnic_groups;

import livelihoodzone.dto.questionnaire.county.model.ethnicgroup.EthnicityResponseItem;
import livelihoodzone.dto.reports.zonal.LzEthnicGroupsDataSetObject;
import livelihoodzone.dto.reports.zonal.charts.EthnicityResponseObject;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsPercentagesEntity;
import livelihoodzone.repository.questionnaire.tribe.EthnicGroupsRepository;
import livelihoodzone.repository.questionnaire.tribe.LzEthnicGroupsPercentagesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzEthnicGroupsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHazardsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
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
public class EthnicGroupsDataSetService {

    @Autowired
    LzEthnicGroupsPercentagesRepository lzEthnicGroupsPercentagesRepository;

    @Autowired
    EthnicGroupsRepository ethnicGroupsRepository;

    public List<LzEthnicGroupsDataSetRetrofitModel> fetchEthnicGroupsDataSet() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzEthnicGroupsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelEthnicGroups();
        try {
            Response<List<LzEthnicGroupsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzEthnicGroupsDataSetObject processEthnicGroups(List<LzEthnicGroupsDataSetRetrofitModel> list) {
        List<LzEthnicGroupsDataSetRetrofitModel> processedList = clusterSameQuestionnaireItemsTogether(list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (LzEthnicGroupsDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getEthnicGroupName() + " -> " + currentItem.getPopulationPercentage() + ",  ";
                counter++;
            } else {
                counter = 1;
                stringReportList.add(currentReportString);
                currentReportString = counter + ") " + currentItem.getEthnicGroupName() + " -> " + currentItem.getPopulationPercentage() + ",  ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);

        return new LzEthnicGroupsDataSetObject(
                stringReportList
        );
    }

    public List<LzEthnicGroupsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<LzEthnicGroupsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzEthnicGroupsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public LzLivelihoodZoneDataObject processEthnicGroupsChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        EthnicityResponseObject ethnicityResponseObject = new EthnicityResponseObject(true);
        List<EthnicGroupsPercentagesEntity> ethnicGroupsPercentagesEntityList = lzEthnicGroupsPercentagesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        for (EthnicGroupsPercentagesEntity ethnicGroupsPercentagesEntity : ethnicGroupsPercentagesEntityList) {
            ethnicityResponseObject.getEthnicGroupResponseList().add(new EthnicityResponseItem(
                    ethnicGroupsRepository.findByEthnicGroupId(ethnicGroupsPercentagesEntity.getEthnicGroupId()),
                    ethnicGroupsPercentagesEntity.getPopulationPercentage()
            ));
        }
        lzLivelihoodZoneDataObject.setEthnicityResponseObject(ethnicityResponseObject);
        return lzLivelihoodZoneDataObject;
    }

}
