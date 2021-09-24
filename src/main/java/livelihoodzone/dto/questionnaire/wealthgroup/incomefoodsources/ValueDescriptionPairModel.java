package livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources;

public class ValueDescriptionPairModel {
    private double value;
    private String description;

    public ValueDescriptionPairModel() {
    }

    public ValueDescriptionPairModel(double value, String description) {
        this.value = value;
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
