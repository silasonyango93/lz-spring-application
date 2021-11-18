package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_questionnaire_sections")
public class LzQuestionnaireSectionsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzQuestionnaireSectionId")
    private int lzQuestionnaireSectionId;

    @Column(name = "LzQuestionnaireSectionName")
    private String lzQuestionnaireSectionName;

    @Column(name = "LzQuestionnaireSectionCode")
    private int lzQuestionnaireSectionCode;

    public int getLzQuestionnaireSectionId() {
        return lzQuestionnaireSectionId;
    }

    public void setLzQuestionnaireSectionId(int lzQuestionnaireSectionId) {
        this.lzQuestionnaireSectionId = lzQuestionnaireSectionId;
    }

    public String getLzQuestionnaireSectionName() {
        return lzQuestionnaireSectionName;
    }

    public void setLzQuestionnaireSectionName(String lzQuestionnaireSectionName) {
        this.lzQuestionnaireSectionName = lzQuestionnaireSectionName;
    }

    public int getLzQuestionnaireSectionCode() {
        return lzQuestionnaireSectionCode;
    }

    public void setLzQuestionnaireSectionCode(int lzQuestionnaireSectionCode) {
        this.lzQuestionnaireSectionCode = lzQuestionnaireSectionCode;
    }
}
