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
    private String wgQuestionnaireSectionCode;

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

    public String getWgQuestionnaireSectionCode() {
        return wgQuestionnaireSectionCode;
    }

    public void setWgQuestionnaireSectionCode(String wgQuestionnaireSectionCode) {
        this.wgQuestionnaireSectionCode = wgQuestionnaireSectionCode;
    }
}
