package livelihoodzone.service.reports.zonal.cropproduction;

import livelihoodzone.dto.reports.zonal.cropproduction.LzCropProductionReportObjectDto;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzCropProductionReportRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LzCropProductionReportService {

    public LzCropProductionReportObjectDto processZoneLevelCropProductionReport(List<LzCropProductionReportRetrofitModel> lzCropProductionReportRetrofitModelList) {
        List<String> selectedCrops = new ArrayList<>();

        List<String> longRainsRainFedPercentageCultivatedArea = new ArrayList<>();
        List<String> longRainsRainFedAverageYieldKgPerHa = new ArrayList<>();

        List<String> longRainsIrrigatedPercentageCultivatedArea = new ArrayList<>();
        List<String> longRainsIrrigatedAverageYieldKgPerHa = new ArrayList<>();

        List<String> shortRainsRainFedPercentageCultivatedArea = new ArrayList<>();
        List<String> shortRainsRainFedAverageYieldKgPerHa = new ArrayList<>();

        List<String> shortRainsIrrigatedPercentageCultivatedArea = new ArrayList<>();
        List<String> shortRainsIrrigatedAverageYieldKgPerHa = new ArrayList<>();

        LzCropProductionReportObjectDto lzCropProductionReportObjectDto = new LzCropProductionReportObjectDto();


        //Process Selected Crops column
        int selectedCropsCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String selectedCropsStringReport = "";
        int selectedCropsCounter = 1;
        List<Number> selectedCropsVisitedQuestionnaireSessionIds = new ArrayList<>();
        selectedCropsVisitedQuestionnaireSessionIds.add(lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId());

        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getLzQuestionnaireSessionId() == selectedCropsCurrentQuestionnaireSessionId) {
                selectedCropsStringReport = selectedCropsStringReport + selectedCropsCounter + ")" + currentItem.getCropName() + ", ";
                selectedCropsCounter++;
            } else {
                if (isQuestionnaireSessionIdAlreadyVisited(selectedCropsVisitedQuestionnaireSessionIds, currentItem.getLzQuestionnaireSessionId()))
                    break;
                selectedCrops.add(selectedCropsStringReport);
                selectedCropsCounter = 1;
                selectedCropsStringReport = selectedCropsCounter + ")" + currentItem.getCropName() + ", ";
                selectedCropsCounter++;
                selectedCropsCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                selectedCropsVisitedQuestionnaireSessionIds.add(currentItem.getLzQuestionnaireSessionId());

            }
        }
        selectedCrops.add(selectedCropsStringReport);
        lzCropProductionReportObjectDto.setSelectedCrops(selectedCrops);


        return lzCropProductionReportObjectDto;
    }

    public List<LzCropProductionReportRetrofitModel> fetchZoneLevelCropProductionReport() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzCropProductionReportRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelCropProduction();
        try {
            Response<List<LzCropProductionReportRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isQuestionnaireSessionIdAlreadyVisited(List<Number> selectedCropsVisitedQuestionnaireSessionIds, int idToBeInvestigated) {
        for (Number currentId : selectedCropsVisitedQuestionnaireSessionIds) {
            if (currentId.intValue() == idToBeInvestigated) {
                return true;
            }
        }
        return false;
    }
}
