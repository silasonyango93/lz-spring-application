package livelihoodzone.dto.reports.wealthgroup.charts;

public class WealthGroupNumberValuePair {
    private int wealthGroupCode;
    private String wealthGroupName;
    private double parameterValue;

    public WealthGroupNumberValuePair() {
    }

    public WealthGroupNumberValuePair(int wealthGroupCode, String wealthGroupName, double parameterValue) {
        this.wealthGroupCode = wealthGroupCode;
        this.wealthGroupName = wealthGroupName;
        this.parameterValue = parameterValue;
    }

    public int getWealthGroupCode() {
        return wealthGroupCode;
    }

    public void setWealthGroupCode(int wealthGroupCode) {
        this.wealthGroupCode = wealthGroupCode;
    }

    public String getWealthGroupName() {
        return wealthGroupName;
    }

    public void setWealthGroupName(String wealthGroupName) {
        this.wealthGroupName = wealthGroupName;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }
}
