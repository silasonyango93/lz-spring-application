package livelihoodzone.dto;

public class NumberDescriptionPairDto {
    private Number numberValue;
    private String extraDescription;

    public NumberDescriptionPairDto() {
    }

    public NumberDescriptionPairDto(Number numberValue, String extraDescription) {
        this.numberValue = numberValue;
        this.extraDescription = extraDescription;
    }

    public NumberDescriptionPairDto(Number numberValue) {
        this.numberValue = numberValue;
    }

    public Number getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Number numberValue) {
        this.numberValue = numberValue;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
