package livelihoodzone.dto.questionnaire.county.model.cropproduction;

import livelihoodzone.dto.questionnaire.county.model.cropproduction.CropSeasonResponseItem;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;

public class WgCropProductionResponseItem {
    private CropsEntity crop;
    private CropSeasonResponseItem longRainsSeason;
    private CropSeasonResponseItem shortRainsSeason;

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
}
