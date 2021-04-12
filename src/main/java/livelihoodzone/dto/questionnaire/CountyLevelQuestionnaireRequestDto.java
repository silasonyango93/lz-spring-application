package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.questionnaire.LivelihoodZonesEntity;

public class CountyLevelQuestionnaireRequestDto {
    private String uniqueId;
    private LivelihoodZonesEntity selectedLivelihoodZone;
    private double latitude;
    private double longitude;
    private String questionnaireStartDate;
    private String questionnaireEndDate;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public LivelihoodZonesEntity getSelectedLivelihoodZone() {
        return selectedLivelihoodZone;
    }

    public void setSelectedLivelihoodZone(LivelihoodZonesEntity selectedLivelihoodZone) {
        this.selectedLivelihoodZone = selectedLivelihoodZone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getQuestionnaireStartDate() {
        return questionnaireStartDate;
    }

    public void setQuestionnaireStartDate(String questionnaireStartDate) {
        this.questionnaireStartDate = questionnaireStartDate;
    }

    public String getQuestionnaireEndDate() {
        return questionnaireEndDate;
    }

    public void setQuestionnaireEndDate(String questionnaireEndDate) {
        this.questionnaireEndDate = questionnaireEndDate;
    }
}
