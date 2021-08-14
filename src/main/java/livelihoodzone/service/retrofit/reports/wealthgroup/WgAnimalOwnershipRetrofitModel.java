package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgAnimalOwnershipRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("AnimalName")
    private String animalName;

    @SerializedName("AverageNumber")
    private double averageNumber;

    @SerializedName("AnimalCode")
    private int animalCode;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public double getAverageNumber() {
        return averageNumber;
    }

    public void setAverageNumber(double averageNumber) {
        this.averageNumber = averageNumber;
    }

    public int getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(int animalCode) {
        this.animalCode = animalCode;
    }
}
