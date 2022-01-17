package livelihoodzone.dto.questionnaire.county.model.hunger;

public class HungerPatternsResponses {
    private double longRainsPeriod;
    private double endLongBeginShort;
    private double shortRainsPeriod;
    private double endShortBeginLong;
    private double parameterValue;

    public double getLongRainsPeriod() {
        return longRainsPeriod;
    }

    public void setLongRainsPeriod(double longRainsPeriod) {
        this.longRainsPeriod = longRainsPeriod;
    }

    public double getEndLongBeginShort() {
        return endLongBeginShort;
    }

    public void setEndLongBeginShort(double endLongBeginShort) {
        this.endLongBeginShort = endLongBeginShort;
    }

    public double getShortRainsPeriod() {
        return shortRainsPeriod;
    }

    public void setShortRainsPeriod(double shortRainsPeriod) {
        this.shortRainsPeriod = shortRainsPeriod;
    }

    public double getEndShortBeginLong() {
        return endShortBeginLong;
    }

    public void setEndShortBeginLong(double endShortBeginLong) {
        this.endShortBeginLong = endShortBeginLong;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }
}
