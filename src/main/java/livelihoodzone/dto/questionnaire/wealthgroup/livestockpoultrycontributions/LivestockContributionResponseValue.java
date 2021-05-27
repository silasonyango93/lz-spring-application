package livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions;

public class LivestockContributionResponseValue {
    private double actualValue;
    private boolean hasBeenSubmitted;

    public double getActualValue() {
        return actualValue;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }

    public boolean isHasBeenSubmitted() {
        return hasBeenSubmitted;
    }

    public void setHasBeenSubmitted(boolean hasBeenSubmitted) {
        this.hasBeenSubmitted = hasBeenSubmitted;
    }
}
