package livelihoodzone.dto.questionnaire.county;

public class WealthGroupPercentageResponse {
    private double verPoorResponse;
    private double poorResponse;
    private double mediumResponse;
    private double betterOfResponse;
    private double parameterValue;

    public double getVerPoorResponse() {
        return verPoorResponse;
    }

    public void setVerPoorResponse(double verPoorResponse) {
        this.verPoorResponse = verPoorResponse;
    }

    public double getPoorResponse() {
        return poorResponse;
    }

    public void setPoorResponse(double poorResponse) {
        this.poorResponse = poorResponse;
    }

    public double getMediumResponse() {
        return mediumResponse;
    }

    public void setMediumResponse(double mediumResponse) {
        this.mediumResponse = mediumResponse;
    }

    public double getBetterOfResponse() {
        return betterOfResponse;
    }

    public void setBetterOfResponse(double betterOfResponse) {
        this.betterOfResponse = betterOfResponse;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }
}
