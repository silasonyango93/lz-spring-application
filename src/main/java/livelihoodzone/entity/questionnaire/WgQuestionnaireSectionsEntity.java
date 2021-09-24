package livelihoodzone.entity.questionnaire;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_questionnaire_sections")
public class WgQuestionnaireSectionsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgQuestionnaireSectionId")
    private int wgQuestionnaireSectionId;

    @Column(name = "WgQuestionnaireSectionName")
    private String wgQuestionnaireSectionName;

    @Column(name = "WgQuestionnaireSectionCode")
    private int wgQuestionnaireSectionCode;

    public int getWgQuestionnaireSectionId() {
        return wgQuestionnaireSectionId;
    }

    public void setWgQuestionnaireSectionId(int wgQuestionnaireSectionId) {
        this.wgQuestionnaireSectionId = wgQuestionnaireSectionId;
    }

    public String getWgQuestionnaireSectionName() {
        return wgQuestionnaireSectionName;
    }

    public void setWgQuestionnaireSectionName(String wgQuestionnaireSectionName) {
        this.wgQuestionnaireSectionName = wgQuestionnaireSectionName;
    }

    public int getWgQuestionnaireSectionCode() {
        return wgQuestionnaireSectionCode;
    }

    public void setWgQuestionnaireSectionCode(int wgQuestionnaireSectionCode) {
        this.wgQuestionnaireSectionCode = wgQuestionnaireSectionCode;
    }
}
