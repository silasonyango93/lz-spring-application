package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgMigrationPatternsDataSetRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("MigrationPatternDescription")
    private String migrationPatternDescription;

    @SerializedName("MigrationPatternCode")
    private int migrationPatternCode;

    @SerializedName("Percentage")
    private double percentage;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getMigrationPatternDescription() {
        return migrationPatternDescription;
    }

    public void setMigrationPatternDescription(String migrationPatternDescription) {
        this.migrationPatternDescription = migrationPatternDescription;
    }

    public int getMigrationPatternCode() {
        return migrationPatternCode;
    }

    public void setMigrationPatternCode(int migrationPatternCode) {
        this.migrationPatternCode = migrationPatternCode;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
