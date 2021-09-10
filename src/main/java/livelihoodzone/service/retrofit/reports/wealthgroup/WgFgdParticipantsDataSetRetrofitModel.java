package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgFgdParticipantsDataSetRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("ParticipantName")
    private String participantName;

    @SerializedName("AgeBracket")
    private int ageBracket;

    @SerializedName("Gender")
    private int gender;

    @SerializedName("Disability")
    private int disability;

    @SerializedName("LevelOfEducation")
    private int levelOfEducation;

    @SerializedName("ConsentToParticipate")
    private int consentToParticipate;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(int ageBracket) {
        this.ageBracket = ageBracket;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDisability() {
        return disability;
    }

    public void setDisability(int disability) {
        this.disability = disability;
    }

    public int getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(int levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public int getConsentToParticipate() {
        return consentToParticipate;
    }

    public void setConsentToParticipate(int consentToParticipate) {
        this.consentToParticipate = consentToParticipate;
    }
}
