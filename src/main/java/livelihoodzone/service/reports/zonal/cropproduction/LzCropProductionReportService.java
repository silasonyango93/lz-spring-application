package livelihoodzone.service.reports.zonal.cropproduction;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.cropproduction.LzCropProductionReportObjectDto;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzHarvestingMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLandPreparationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzPlantingMonthsEntity;
import livelihoodzone.repository.questionnaire.county.LzCropProductionResponsesRepository;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzCropProductionReportRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LzCropProductionReportService {

    @Autowired
    LzCropProductionResponsesRepository lzCropProductionResponsesRepository;

    @Autowired
    CropsRepository cropsRepository;

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


        //Process Long Rains Rainfed Crops Percentage Cultivated Area

        int longRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String longRainsRainFedPercentageCultivatedAreaStringReport = "";
        int longRainsRainFedPercentageCultivatedAreaCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.LONG_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.RAINFED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == longRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId) {

                        longRainsRainFedPercentageCultivatedAreaStringReport = longRainsRainFedPercentageCultivatedAreaStringReport + longRainsRainFedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        longRainsRainFedPercentageCultivatedAreaCounter++;
                    } else {
                        longRainsRainFedPercentageCultivatedArea.add(longRainsRainFedPercentageCultivatedAreaStringReport);
                        longRainsRainFedPercentageCultivatedAreaCounter = 1;
                        longRainsRainFedPercentageCultivatedAreaStringReport = longRainsRainFedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        longRainsRainFedPercentageCultivatedAreaCounter++;
                        longRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        longRainsRainFedPercentageCultivatedArea.add(longRainsRainFedPercentageCultivatedAreaStringReport);
        lzCropProductionReportObjectDto.setLongRainsRainFedPercentageCultivatedArea(longRainsRainFedPercentageCultivatedArea);


        //Process Long Rains Rainfed Crops Average Yield

        int longRainsRainFedAverageYieldCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String longRainsRainFedAverageYieldStringReport = "";
        int longRainsRainFedAverageYieldCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.LONG_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.RAINFED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == longRainsRainFedAverageYieldCurrentQuestionnaireSessionId) {

                        longRainsRainFedAverageYieldStringReport = longRainsRainFedAverageYieldStringReport + longRainsRainFedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        longRainsRainFedAverageYieldCounter++;
                    } else {
                        longRainsRainFedAverageYieldKgPerHa.add(longRainsRainFedAverageYieldStringReport);
                        longRainsRainFedAverageYieldCounter = 1;
                        longRainsRainFedAverageYieldStringReport = longRainsRainFedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        longRainsRainFedAverageYieldCounter++;
                        longRainsRainFedAverageYieldCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        longRainsRainFedAverageYieldKgPerHa.add(longRainsRainFedAverageYieldStringReport);
        lzCropProductionReportObjectDto.setLongRainsRainFedAverageYieldKgPerHa(longRainsRainFedAverageYieldKgPerHa);


        //Process Long Rains Irrigated Crops Percentage Cultivated Area

        int longRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String longRainsIrrigatedPercentageCultivatedAreaStringReport = "";
        int longRainsIrrigatedPercentageCultivatedAreaCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.LONG_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.IRRIGATED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == longRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId) {

                        longRainsIrrigatedPercentageCultivatedAreaStringReport = longRainsIrrigatedPercentageCultivatedAreaStringReport + longRainsIrrigatedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        longRainsIrrigatedPercentageCultivatedAreaCounter++;
                    } else {
                        longRainsIrrigatedPercentageCultivatedArea.add(longRainsIrrigatedPercentageCultivatedAreaStringReport);
                        longRainsIrrigatedPercentageCultivatedAreaCounter = 1;
                        longRainsIrrigatedPercentageCultivatedAreaStringReport = longRainsIrrigatedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        longRainsIrrigatedPercentageCultivatedAreaCounter++;
                        longRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        longRainsIrrigatedPercentageCultivatedArea.add(longRainsIrrigatedPercentageCultivatedAreaStringReport);
        lzCropProductionReportObjectDto.setLongRainsIrrigatedPercentageCultivatedArea(longRainsIrrigatedPercentageCultivatedArea);


        //Process Long Rains Irrigated Crops Average Yield

        int longRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String longRainsIrrigatedAverageYieldStringReport = "";
        int longRainsIrrigatedAverageYieldCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.LONG_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.IRRIGATED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == longRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId) {

                        longRainsIrrigatedAverageYieldStringReport = longRainsIrrigatedAverageYieldStringReport + longRainsIrrigatedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        longRainsIrrigatedAverageYieldCounter++;
                    } else {
                        longRainsIrrigatedAverageYieldKgPerHa.add(longRainsIrrigatedAverageYieldStringReport);
                        longRainsIrrigatedAverageYieldCounter = 1;
                        longRainsIrrigatedAverageYieldStringReport = longRainsIrrigatedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        longRainsIrrigatedAverageYieldCounter++;
                        longRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        longRainsIrrigatedAverageYieldKgPerHa.add(longRainsIrrigatedAverageYieldStringReport);
        lzCropProductionReportObjectDto.setLongRainsIrrigatedAverageYieldKgPerHa(longRainsIrrigatedAverageYieldKgPerHa);


        //Process Short Rains Rainfed Crops Percentage Cultivated Area

        int shortRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String shortRainsRainFedPercentageCultivatedAreaStringReport = "";
        int shortRainsRainFedPercentageCultivatedAreaCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.SHORT_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.RAINFED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == shortRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId) {

                        shortRainsRainFedPercentageCultivatedAreaStringReport = shortRainsRainFedPercentageCultivatedAreaStringReport + shortRainsRainFedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        shortRainsRainFedPercentageCultivatedAreaCounter++;
                    } else {
                        shortRainsRainFedPercentageCultivatedArea.add(shortRainsRainFedPercentageCultivatedAreaStringReport);
                        shortRainsRainFedPercentageCultivatedAreaCounter = 1;
                        shortRainsRainFedPercentageCultivatedAreaStringReport = shortRainsRainFedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        shortRainsRainFedPercentageCultivatedAreaCounter++;
                        shortRainsRainFedPercentageCultivatedAreaCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        shortRainsRainFedPercentageCultivatedArea.add(shortRainsRainFedPercentageCultivatedAreaStringReport);
        lzCropProductionReportObjectDto.setShortRainsRainFedPercentageCultivatedArea(shortRainsRainFedPercentageCultivatedArea);


        //Process Short Rains Rainfed Crops Average Yield

        int shortRainsRainFedAverageYieldCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String shortRainsRainFedAverageYieldStringReport = "";
        int shortRainsRainFedAverageYieldCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.SHORT_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.RAINFED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == shortRainsRainFedAverageYieldCurrentQuestionnaireSessionId) {

                        shortRainsRainFedAverageYieldStringReport = shortRainsRainFedAverageYieldStringReport + shortRainsRainFedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        shortRainsRainFedAverageYieldCounter++;
                    } else {
                        shortRainsRainFedAverageYieldKgPerHa.add(shortRainsRainFedAverageYieldStringReport);
                        shortRainsRainFedAverageYieldCounter = 1;
                        shortRainsRainFedAverageYieldStringReport = shortRainsRainFedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        shortRainsRainFedAverageYieldCounter++;
                        shortRainsRainFedAverageYieldCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        shortRainsRainFedAverageYieldKgPerHa.add(shortRainsRainFedAverageYieldStringReport);
        lzCropProductionReportObjectDto.setShortRainsRainFedAverageYieldKgPerHa(shortRainsRainFedAverageYieldKgPerHa);


        //Process Short Rains Irrigated Crops Percentage Cultivated Area

        int shortRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String shortRainsIrrigatedPercentageCultivatedAreaStringReport = "";
        int shortRainsIrrigatedPercentageCultivatedAreaCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.SHORT_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.IRRIGATED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == shortRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId) {

                        shortRainsIrrigatedPercentageCultivatedAreaStringReport = shortRainsIrrigatedPercentageCultivatedAreaStringReport + shortRainsIrrigatedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        shortRainsIrrigatedPercentageCultivatedAreaCounter++;
                    } else {
                        shortRainsIrrigatedPercentageCultivatedArea.add(shortRainsIrrigatedPercentageCultivatedAreaStringReport);
                        shortRainsIrrigatedPercentageCultivatedAreaCounter = 1;
                        shortRainsIrrigatedPercentageCultivatedAreaStringReport = shortRainsIrrigatedPercentageCultivatedAreaCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getCultivatedAreaPercentage() + "%, ";
                        shortRainsIrrigatedPercentageCultivatedAreaCounter++;
                        shortRainsIrrigatedPercentageCultivatedAreaCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        shortRainsIrrigatedPercentageCultivatedArea.add(shortRainsIrrigatedPercentageCultivatedAreaStringReport);
        lzCropProductionReportObjectDto.setShortRainsIrrigatedPercentageCultivatedArea(shortRainsIrrigatedPercentageCultivatedArea);


        //Process Short Rains Irrigated Crops Average Yield

        int shortRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId = lzCropProductionReportRetrofitModelList.get(0).getLzQuestionnaireSessionId();
        String shortRainsIrrigatedAverageYieldStringReport = "";
        int shortRainsIrrigatedAverageYieldCounter = 1;
        for (LzCropProductionReportRetrofitModel currentItem : lzCropProductionReportRetrofitModelList) {
            if (currentItem.getRainySeasonCode() == Constants.SHORT_RAINS_SEASON) {
                if (currentItem.getCropWaterAccessTypeCode() == Constants.IRRIGATED_CROPS) {
                    if (currentItem.getLzQuestionnaireSessionId() == shortRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId) {

                        shortRainsIrrigatedAverageYieldStringReport = shortRainsIrrigatedAverageYieldStringReport + shortRainsIrrigatedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        shortRainsIrrigatedAverageYieldCounter++;
                    } else {
                        shortRainsIrrigatedAverageYieldKgPerHa.add(shortRainsIrrigatedAverageYieldStringReport);
                        shortRainsIrrigatedAverageYieldCounter = 1;
                        shortRainsIrrigatedAverageYieldStringReport = shortRainsIrrigatedAverageYieldCounter + ")" + currentItem.getCropName()
                                + " -> " + currentItem.getAverageYieldKgPerHectare() + ", ";
                        shortRainsIrrigatedAverageYieldCounter++;
                        shortRainsIrrigatedAverageYieldCurrentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
                    }
                }
            }

        }
        shortRainsIrrigatedAverageYieldKgPerHa.add(shortRainsIrrigatedAverageYieldStringReport);
        lzCropProductionReportObjectDto.setShortRainsIrrigatedAverageYieldKgPerHa(shortRainsIrrigatedAverageYieldKgPerHa);


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


    public WgCropProductionResponseItem extractFullCropProfile(int cropId, int lzQuestionnaireSessionId) {
        WgCropProductionResponseItem wgCropProductionResponseItem = new WgCropProductionResponseItem(true, cropsRepository.findByCropId(cropId));

        //Long rains
        double longRainsRainfedPercentageCultivatedArea = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                1,
                1
        ).getCultivatedAreaPercentage();

        double longRainsRainfedAverageYieldKgPerHa = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                1,
                1
        ).getAverageYieldKgPerHectare();


        double longRainsIrrigatedPercentageCultivatedArea = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                1,
                2
        ).getCultivatedAreaPercentage();

        double longRainsIrrigatedAverageYieldKgPerHa = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                1,
                2
        ).getAverageYieldKgPerHectare();





        //Short rains
        double shortRainsRainfedPercentageCultivatedArea = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                2,
                1
        ).getCultivatedAreaPercentage();

        double shortRainsRainfedAverageYieldKgPerHa = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                2,
                1
        ).getAverageYieldKgPerHectare();

        double shortRainsIrrigatedPercentageCultivatedArea = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                2,
                2
        ).getCultivatedAreaPercentage();

        double shortRainsIrrigatedAverageYieldKgPerHa = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(
                lzQuestionnaireSessionId,
                cropId,
                2,
                2
        ).getAverageYieldKgPerHectare();


        //Long rains
        wgCropProductionResponseItem.getLongRainsSeason().getRainfedCultivatedAreaPercentage().setValue(longRainsRainfedPercentageCultivatedArea);
        wgCropProductionResponseItem.getLongRainsSeason().getRainfedAverageYieldPerHa().setValue(longRainsRainfedAverageYieldKgPerHa);

        wgCropProductionResponseItem.getLongRainsSeason().getIrrigatedCultivatedArea().setValue(longRainsIrrigatedPercentageCultivatedArea);
        wgCropProductionResponseItem.getLongRainsSeason().getIrrigatedAverageYieldPerHa().setValue(longRainsIrrigatedAverageYieldKgPerHa);


        //Short rains
        wgCropProductionResponseItem.getShortRainsSeason().getRainfedCultivatedAreaPercentage().setValue(shortRainsRainfedPercentageCultivatedArea);
        wgCropProductionResponseItem.getShortRainsSeason().getRainfedAverageYieldPerHa().setValue(shortRainsRainfedAverageYieldKgPerHa);

        wgCropProductionResponseItem.getShortRainsSeason().getIrrigatedCultivatedArea().setValue(shortRainsIrrigatedPercentageCultivatedArea);
        wgCropProductionResponseItem.getShortRainsSeason().getIrrigatedAverageYieldPerHa().setValue(shortRainsIrrigatedAverageYieldKgPerHa);

        return wgCropProductionResponseItem;
    }


    public LzLivelihoodZoneDataObject processCropProductionChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        LzCropProductionResponses lzCropProductionResponses = new LzCropProductionResponses(true);
        List<LzCropProductionResponsesEntity> lzCropProductionResponsesEntityList = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<Number> uniqueCropIds = extractUniqueCropsFromCropResponses(lzCropProductionResponsesEntityList);

        List<WgCropProductionResponseItem> wgCropProductionResponseItemList = new ArrayList<>();

        for (Number currentCropId : uniqueCropIds) {
            wgCropProductionResponseItemList.add(extractFullCropProfile(currentCropId.intValue(), lzQuestionnaireSessionId));
        }

        lzCropProductionResponses.getCropProductionResponses().addAll(wgCropProductionResponseItemList);
        lzLivelihoodZoneDataObject.setLzCropProductionResponses(lzCropProductionResponses);
        return lzLivelihoodZoneDataObject;
    }

    public List<Number> extractUniqueCropsFromCropResponses(List<LzCropProductionResponsesEntity> lzCropProductionResponsesEntityList) {
        List<Number> uniqueCropIds = new ArrayList<>();
        for (LzCropProductionResponsesEntity lzCropProductionResponsesEntity : lzCropProductionResponsesEntityList) {
            if (!doesIdAlreadyExist(uniqueCropIds, lzCropProductionResponsesEntity.getCropId())) {
                uniqueCropIds.add(lzCropProductionResponsesEntity.getCropId());
            }
        }
        return uniqueCropIds;
    }

    public boolean doesIdAlreadyExist(List<Number> uniqueCropIds, int currentId) {
        for (Number number : uniqueCropIds) {
            if (number.intValue() == currentId) {
                return true;
            }
        }
        return false;
    }
}
