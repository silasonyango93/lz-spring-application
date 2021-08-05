package livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_gender_livelihood_activities")
public class WgGenderLivelihoodActivitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgGenderLivelihoodActivityId")
    private int wgGenderLivelihoodActivityId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "LivelihoodActivityId")
    private int livelihoodActivityId;

    @Column(name = "MenPercentage")
    private double menPercentage;

    @Column(name = "WomenPercentage")
    private double womenPercentage;

    @Column(name = "ExtraDescription")
    private String extraDescription;

    public WgGenderLivelihoodActivitiesEntity() {
    }

    public WgGenderLivelihoodActivitiesEntity(int wgQuestionnaireSessionId, int livelihoodActivityId, double menPercentage, double womenPercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.livelihoodActivityId = livelihoodActivityId;
        this.menPercentage = menPercentage;
        this.womenPercentage = womenPercentage;
    }

    public int getWgGenderLivelihoodActivityId() {
        return wgGenderLivelihoodActivityId;
    }

    public void setWgGenderLivelihoodActivityId(int wgGenderLivelihoodActivityId) {
        this.wgGenderLivelihoodActivityId = wgGenderLivelihoodActivityId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getLivelihoodActivityId() {
        return livelihoodActivityId;
    }

    public void setLivelihoodActivityId(int livelihoodActivityId) {
        this.livelihoodActivityId = livelihoodActivityId;
    }

    public double getMenPercentage() {
        return menPercentage;
    }

    public void setMenPercentage(double menPercentage) {
        this.menPercentage = menPercentage;
    }

    public double getWomenPercentage() {
        return womenPercentage;
    }

    public void setWomenPercentage(double womenPercentage) {
        this.womenPercentage = womenPercentage;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
