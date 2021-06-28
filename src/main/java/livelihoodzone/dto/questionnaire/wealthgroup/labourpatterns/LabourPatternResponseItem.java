package livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns;

public class LabourPatternResponseItem {
    private double women;
    private double men;
    private String extraDescription;

    public double getWomen() {
        return women;
    }

    public void setWomen(double women) {
        this.women = women;
    }

    public double getMen() {
        return men;
    }

    public void setMen(double men) {
        this.men = men;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
