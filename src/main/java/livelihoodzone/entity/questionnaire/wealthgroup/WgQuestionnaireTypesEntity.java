package livelihoodzone.entity.questionnaire.wealthgroup;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_questionnaire_types")
public class WgQuestionnaireTypesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgQuestionnaireTypeId")
    private int wgQuestionnaireTypeId;

    @Column(name = "WgQuestionnaireTypeDescription")
    private String wgQuestionnaireTypeDescription;

    @Column(name = "WgQuestionnaireTypeCode")
    private int wgQuestionnaireTypeCode;

    public int getWgQuestionnaireTypeId() {
        return wgQuestionnaireTypeId;
    }

    public void setWgQuestionnaireTypeId(int wgQuestionnaireTypeId) {
        this.wgQuestionnaireTypeId = wgQuestionnaireTypeId;
    }

    public String getWgQuestionnaireTypeDescription() {
        return wgQuestionnaireTypeDescription;
    }

    public void setWgQuestionnaireTypeDescription(String wgQuestionnaireTypeDescription) {
        this.wgQuestionnaireTypeDescription = wgQuestionnaireTypeDescription;
    }

    public int getWgQuestionnaireTypeCode() {
        return wgQuestionnaireTypeCode;
    }

    public void setWgQuestionnaireTypeCode(int wgQuestionnaireTypeCode) {
        this.wgQuestionnaireTypeCode = wgQuestionnaireTypeCode;
    }
}
