package livelihoodzone.dto.questionnaire.county.model.cropproduction;

import livelihoodzone.dto.questionnaire.county.model.cropproduction.CropSeasonResponseItem;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;

import java.util.List;

public class WgCropProductionResponseItem {
    private CropsEntity crop;
    private CropSeasonResponseItem longRainsSeason;
    private CropSeasonResponseItem shortRainsSeason;
    private List<MonthsEntity> landPreparationPeriod;
    private List<MonthsEntity> plantingPeriod;
    private List<MonthsEntity> harvestingPeriod;

    public CropsEntity getCrop() {
        return crop;
    }

    public void setCrop(CropsEntity crop) {
        this.crop = crop;
    }

    public CropSeasonResponseItem getLongRainsSeason() {
        return longRainsSeason;
    }

    public void setLongRainsSeason(CropSeasonResponseItem longRainsSeason) {
        this.longRainsSeason = longRainsSeason;
    }

    public CropSeasonResponseItem getShortRainsSeason() {
        return shortRainsSeason;
    }

    public void setShortRainsSeason(CropSeasonResponseItem shortRainsSeason) {
        this.shortRainsSeason = shortRainsSeason;
    }

    public List<MonthsEntity> getLandPreparationPeriod() {
        return landPreparationPeriod;
    }

    public void setLandPreparationPeriod(List<MonthsEntity> landPreparationPeriod) {
        this.landPreparationPeriod = landPreparationPeriod;
    }

    public List<MonthsEntity> getPlantingPeriod() {
        return plantingPeriod;
    }

    public void setPlantingPeriod(List<MonthsEntity> plantingPeriod) {
        this.plantingPeriod = plantingPeriod;
    }

    public List<MonthsEntity> getHarvestingPeriod() {
        return harvestingPeriod;
    }

    public void setHarvestingPeriod(List<MonthsEntity> harvestingPeriod) {
        this.harvestingPeriod = harvestingPeriod;
    }
}
