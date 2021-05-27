package livelihoodzone.dto.questionnaire.county.model.cropproduction;

public class CropProductionResponseValueModel {
    private double value;
    private boolean hasBeenSubmitted;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isHasBeenSubmitted() {
        return hasBeenSubmitted;
    }

    public void setHasBeenSubmitted(boolean hasBeenSubmitted) {
        this.hasBeenSubmitted = hasBeenSubmitted;
    }
}
