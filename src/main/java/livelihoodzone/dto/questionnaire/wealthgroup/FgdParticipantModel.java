package livelihoodzone.dto.questionnaire.wealthgroup;

public class FgdParticipantModel {
    private String participantName;
    private double age;
    private int gender;
    private int disability;
    private int levelOfEducation;
    private int consentToParticipate;

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
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
