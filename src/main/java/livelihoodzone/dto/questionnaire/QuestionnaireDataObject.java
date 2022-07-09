package livelihoodzone.dto.questionnaire;

import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;

public class QuestionnaireDataObject {
    private int residentQuestionnaireType;
    private LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject;
    private WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject;

    public QuestionnaireDataObject() {
    }

    public LzLivelihoodZoneDataObject getLzLivelihoodZoneDataObject() {
        return lzLivelihoodZoneDataObject;
    }

    public void setLzLivelihoodZoneDataObject(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject) {
        this.lzLivelihoodZoneDataObject = lzLivelihoodZoneDataObject;
    }

    public WgLivelihoodZoneDataObject getWgLivelihoodZoneDataObject() {
        return wgLivelihoodZoneDataObject;
    }

    public void setWgLivelihoodZoneDataObject(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject) {
        this.wgLivelihoodZoneDataObject = wgLivelihoodZoneDataObject;
    }

    public int getResidentQuestionnaireType() {
        return residentQuestionnaireType;
    }

    public void setResidentQuestionnaireType(int residentQuestionnaireType) {
        this.residentQuestionnaireType = residentQuestionnaireType;
    }
}
