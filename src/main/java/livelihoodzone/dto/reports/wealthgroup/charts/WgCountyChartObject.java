package livelihoodzone.dto.reports.wealthgroup.charts;

import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;

import javax.validation.constraints.Max;
import java.util.List;

public class WgCountyChartObject {
    private int countyId;
    private String countyName;
    private int wealthGroupId;
    private String wealthGroupName;
    private int questionnaireSectionId;
    private String questionnaireSectionName;
    private List<WgLivelihoodZoneDataObject> livelihoodZoneDataObjectList;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getWealthGroupName() {
        return wealthGroupName;
    }

    public void setWealthGroupName(String wealthGroupName) {
        this.wealthGroupName = wealthGroupName;
    }

    public int getQuestionnaireSectionId() {
        return questionnaireSectionId;
    }

    public void setQuestionnaireSectionId(int questionnaireSectionId) {
        this.questionnaireSectionId = questionnaireSectionId;
    }

    public List<WgLivelihoodZoneDataObject> getLivelihoodZoneDataObjectList() {
        return livelihoodZoneDataObjectList;
    }

    public void setLivelihoodZoneDataObjectList(List<WgLivelihoodZoneDataObject> livelihoodZoneDataObjectList) {
        this.livelihoodZoneDataObjectList = livelihoodZoneDataObjectList;
    }

    public String getQuestionnaireSectionName() {
        return questionnaireSectionName;
    }

    public void setQuestionnaireSectionName(String questionnaireSectionName) {
        this.questionnaireSectionName = questionnaireSectionName;
    }
}
