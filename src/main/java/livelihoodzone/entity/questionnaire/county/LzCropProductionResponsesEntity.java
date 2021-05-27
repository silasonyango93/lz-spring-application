package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_crop_production_responses")
public class LzCropProductionResponsesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzCropProductionResponseId")
    private int lzCropProductionResponseId;

    @Column(name = "RainySeasonId")
    private int rainySeasonId;

    @Column(name = "CropWaterAccessTypeId")
    private int cropWaterAccessTypeId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "CultivatedAreaPercentage")
    private double cultivatedAreaPercentage;

    @Column(name = "AverageYieldKgPerHectare")
    private double averageYieldKgPerHectare;

    public int getLzCropProductionResponseId() {
        return lzCropProductionResponseId;
    }

    public void setLzCropProductionResponseId(int lzCropProductionResponseId) {
        this.lzCropProductionResponseId = lzCropProductionResponseId;
    }

    public int getRainySeasonId() {
        return rainySeasonId;
    }

    public void setRainySeasonId(int rainySeasonId) {
        this.rainySeasonId = rainySeasonId;
    }

    public int getCropWaterAccessTypeId() {
        return cropWaterAccessTypeId;
    }

    public void setCropWaterAccessTypeId(int cropWaterAccessTypeId) {
        this.cropWaterAccessTypeId = cropWaterAccessTypeId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public double getCultivatedAreaPercentage() {
        return cultivatedAreaPercentage;
    }

    public void setCultivatedAreaPercentage(double cultivatedAreaPercentage) {
        this.cultivatedAreaPercentage = cultivatedAreaPercentage;
    }

    public double getAverageYieldKgPerHectare() {
        return averageYieldKgPerHectare;
    }

    public void setAverageYieldKgPerHectare(double averageYieldKgPerHectare) {
        this.averageYieldKgPerHectare = averageYieldKgPerHectare;
    }
}
