package livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "fgd_participants")
public class FgdParticipantsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FgdParticipantId")
    private int fgdParticipantId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "ParticipantName")
    private String participantName;

    @Column(name = "AgeBracket")
    private int ageBracket;

    @Column(name = "Gender")
    private int gender;

    @Column(name = "Disability")
    private int disability;

    @Column(name = "LevelOfEducation")
    private int levelOfEducation;

    @Column(name = "ConsentToParticipate")
    private int consentToParticipate;

    public FgdParticipantsEntity() {
    }

    public FgdParticipantsEntity(int wgQuestionnaireSessionId, String participantName, int ageBracket, int gender, int disability, int levelOfEducation, int consentToParticipate) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.participantName = participantName;
        this.ageBracket = ageBracket;
        this.gender = gender;
        this.disability = disability;
        this.levelOfEducation = levelOfEducation;
        this.consentToParticipate = consentToParticipate;
    }

    public int getFgdParticipantId() {
        return fgdParticipantId;
    }

    public void setFgdParticipantId(int fgdParticipantId) {
        this.fgdParticipantId = fgdParticipantId;
    }

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
