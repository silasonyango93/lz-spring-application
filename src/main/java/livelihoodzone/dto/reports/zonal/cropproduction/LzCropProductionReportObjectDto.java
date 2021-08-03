package livelihoodzone.dto.reports.zonal.cropproduction;

import java.util.ArrayList;
import java.util.List;

public class LzCropProductionReportObjectDto {
    private List<String> selectedCrops;

    private List<String> longRainsRainFedPercentageCultivatedArea;
    private List<String> longRainsRainFedAverageYieldKgPerHa;
    private List<String> longRainsIrrigatedPercentageCultivatedArea;
    private List<String> longRainsIrrigatedAverageYieldKgPerHa;


    private List<String> shortRainsRainFedPercentageCultivatedArea;
    private List<String> shortRainsRainFedAverageYieldKgPerHa;
    private List<String> shortRainsIrrigatedPercentageCultivatedArea;
    private List<String> shortRainsIrrigatedAverageYieldKgPerHa;

    public List<String> getSelectedCrops() {
        return selectedCrops;
    }

    public void setSelectedCrops(List<String> selectedCrops) {
        this.selectedCrops = selectedCrops;
    }

    public List<String> getLongRainsRainFedPercentageCultivatedArea() {
        return longRainsRainFedPercentageCultivatedArea;
    }

    public void setLongRainsRainFedPercentageCultivatedArea(List<String> longRainsRainFedPercentageCultivatedArea) {
        this.longRainsRainFedPercentageCultivatedArea = longRainsRainFedPercentageCultivatedArea;
    }

    public List<String> getLongRainsRainFedAverageYieldKgPerHa() {
        return longRainsRainFedAverageYieldKgPerHa;
    }

    public void setLongRainsRainFedAverageYieldKgPerHa(List<String> longRainsRainFedAverageYieldKgPerHa) {
        this.longRainsRainFedAverageYieldKgPerHa = longRainsRainFedAverageYieldKgPerHa;
    }

    public List<String> getLongRainsIrrigatedPercentageCultivatedArea() {
        return longRainsIrrigatedPercentageCultivatedArea;
    }

    public void setLongRainsIrrigatedPercentageCultivatedArea(List<String> longRainsIrrigatedPercentageCultivatedArea) {
        this.longRainsIrrigatedPercentageCultivatedArea = longRainsIrrigatedPercentageCultivatedArea;
    }

    public List<String> getLongRainsIrrigatedAverageYieldKgPerHa() {
        return longRainsIrrigatedAverageYieldKgPerHa;
    }

    public void setLongRainsIrrigatedAverageYieldKgPerHa(List<String> longRainsIrrigatedAverageYieldKgPerHa) {
        this.longRainsIrrigatedAverageYieldKgPerHa = longRainsIrrigatedAverageYieldKgPerHa;
    }

    public List<String> getShortRainsRainFedPercentageCultivatedArea() {
        return shortRainsRainFedPercentageCultivatedArea;
    }

    public void setShortRainsRainFedPercentageCultivatedArea(List<String> shortRainsRainFedPercentageCultivatedArea) {
        this.shortRainsRainFedPercentageCultivatedArea = shortRainsRainFedPercentageCultivatedArea;
    }

    public List<String> getShortRainsRainFedAverageYieldKgPerHa() {
        return shortRainsRainFedAverageYieldKgPerHa;
    }

    public void setShortRainsRainFedAverageYieldKgPerHa(List<String> shortRainsRainFedAverageYieldKgPerHa) {
        this.shortRainsRainFedAverageYieldKgPerHa = shortRainsRainFedAverageYieldKgPerHa;
    }

    public List<String> getShortRainsIrrigatedPercentageCultivatedArea() {
        return shortRainsIrrigatedPercentageCultivatedArea;
    }

    public void setShortRainsIrrigatedPercentageCultivatedArea(List<String> shortRainsIrrigatedPercentageCultivatedArea) {
        this.shortRainsIrrigatedPercentageCultivatedArea = shortRainsIrrigatedPercentageCultivatedArea;
    }

    public List<String> getShortRainsIrrigatedAverageYieldKgPerHa() {
        return shortRainsIrrigatedAverageYieldKgPerHa;
    }

    public void setShortRainsIrrigatedAverageYieldKgPerHa(List<String> shortRainsIrrigatedAverageYieldKgPerHa) {
        this.shortRainsIrrigatedAverageYieldKgPerHa = shortRainsIrrigatedAverageYieldKgPerHa;
    }
}
