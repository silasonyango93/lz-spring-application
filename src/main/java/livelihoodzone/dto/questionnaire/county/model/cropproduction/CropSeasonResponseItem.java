package livelihoodzone.dto.questionnaire.county.model.cropproduction;

import livelihoodzone.dto.questionnaire.county.model.cropproduction.CropProductionResponseValueModel;

public class CropSeasonResponseItem {
    private CropProductionResponseValueModel rainfedCultivatedAreaPercentage;
    private CropProductionResponseValueModel rainfedAverageYieldPerHa;
    private CropProductionResponseValueModel irrigatedCultivatedArea;
    private CropProductionResponseValueModel irrigatedAverageYieldPerHa;

    public CropSeasonResponseItem() {
    }

    public CropSeasonResponseItem(boolean instantiate) {
        if (instantiate) {
            rainfedCultivatedAreaPercentage = new CropProductionResponseValueModel();
            rainfedAverageYieldPerHa = new CropProductionResponseValueModel();
            irrigatedCultivatedArea = new CropProductionResponseValueModel();
            irrigatedAverageYieldPerHa = new CropProductionResponseValueModel();
        }
    }

    public CropProductionResponseValueModel getRainfedCultivatedAreaPercentage() {
        return rainfedCultivatedAreaPercentage;
    }

    public void setRainfedCultivatedAreaPercentage(CropProductionResponseValueModel rainfedCultivatedAreaPercentage) {
        this.rainfedCultivatedAreaPercentage = rainfedCultivatedAreaPercentage;
    }

    public CropProductionResponseValueModel getRainfedAverageYieldPerHa() {
        return rainfedAverageYieldPerHa;
    }

    public void setRainfedAverageYieldPerHa(CropProductionResponseValueModel rainfedAverageYieldPerHa) {
        this.rainfedAverageYieldPerHa = rainfedAverageYieldPerHa;
    }

    public CropProductionResponseValueModel getIrrigatedCultivatedArea() {
        return irrigatedCultivatedArea;
    }

    public void setIrrigatedCultivatedArea(CropProductionResponseValueModel irrigatedCultivatedArea) {
        this.irrigatedCultivatedArea = irrigatedCultivatedArea;
    }

    public CropProductionResponseValueModel getIrrigatedAverageYieldPerHa() {
        return irrigatedAverageYieldPerHa;
    }

    public void setIrrigatedAverageYieldPerHa(CropProductionResponseValueModel irrigatedAverageYieldPerHa) {
        this.irrigatedAverageYieldPerHa = irrigatedAverageYieldPerHa;
    }
}
