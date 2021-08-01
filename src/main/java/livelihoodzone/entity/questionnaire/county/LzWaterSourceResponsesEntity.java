package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_water_source_responses")
public class LzWaterSourceResponsesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WaterSourceResponseId")
    private int waterSourceResponseId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "WaterSourceId")
    private int waterSourceId;

    @Column(name = "WetSeasonPercentage")
    private double WetSeasonPercentage;

    @Column(name = "DrySeasonPercentage")
    private double drySeasonPercentage;

    @Column(name = "ExtraDescription")
    private String extraDescription;

    public LzWaterSourceResponsesEntity() {
    }

    public LzWaterSourceResponsesEntity(int lzQuestionnaireSessionId, int waterSourceId, double wetSeasonPercentage, double drySeasonPercentage, String extraDescription) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.waterSourceId = waterSourceId;
        this.WetSeasonPercentage = wetSeasonPercentage;
        this.drySeasonPercentage = drySeasonPercentage;
        this.extraDescription = extraDescription;
    }

    public int getWaterSourceResponseId() {
        return waterSourceResponseId;
    }

    public void setWaterSourceResponseId(int waterSourceResponseId) {
        this.waterSourceResponseId = waterSourceResponseId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getWaterSourceId() {
        return waterSourceId;
    }

    public void setWaterSourceId(int waterSourceId) {
        this.waterSourceId = waterSourceId;
    }

    public double getWetSeasonPercentage() {
        return WetSeasonPercentage;
    }

    public void setWetSeasonPercentage(double wetSeasonPercentage) {
        WetSeasonPercentage = wetSeasonPercentage;
    }

    public double getDrySeasonPercentage() {
        return drySeasonPercentage;
    }

    public void setDrySeasonPercentage(double drySeasonPercentage) {
        this.drySeasonPercentage = drySeasonPercentage;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
