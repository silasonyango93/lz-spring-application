package livelihoodzone.dto.reports.zonal.wealthgroup;

import java.util.List;

public class WealthGroupPopulationPercentageReportResponseObject {
    private List<Number> veryPoorPopulationPercentage;
    private List<Number> poorPopulationPercentage;
    private List<Number> mediumPopulationPercentage;
    private List<Number> betterOffPopulationPercentage;

    public WealthGroupPopulationPercentageReportResponseObject() {
    }

    public WealthGroupPopulationPercentageReportResponseObject(List<Number> veryPoorPopulationPercentage, List<Number> poorPopulationPercentage, List<Number> mediumPopulationPercentage, List<Number> betterOffPopulationPercentage) {
        this.veryPoorPopulationPercentage = veryPoorPopulationPercentage;
        this.poorPopulationPercentage = poorPopulationPercentage;
        this.mediumPopulationPercentage = mediumPopulationPercentage;
        this.betterOffPopulationPercentage = betterOffPopulationPercentage;
    }

    public List<Number> getVeryPoorPopulationPercentage() {
        return veryPoorPopulationPercentage;
    }

    public void setVeryPoorPopulationPercentage(List<Number> veryPoorPopulationPercentage) {
        this.veryPoorPopulationPercentage = veryPoorPopulationPercentage;
    }

    public List<Number> getPoorPopulationPercentage() {
        return poorPopulationPercentage;
    }

    public void setPoorPopulationPercentage(List<Number> poorPopulationPercentage) {
        this.poorPopulationPercentage = poorPopulationPercentage;
    }

    public List<Number> getMediumPopulationPercentage() {
        return mediumPopulationPercentage;
    }

    public void setMediumPopulationPercentage(List<Number> mediumPopulationPercentage) {
        this.mediumPopulationPercentage = mediumPopulationPercentage;
    }

    public List<Number> getBetterOffPopulationPercentage() {
        return betterOffPopulationPercentage;
    }

    public void setBetterOffPopulationPercentage(List<Number> betterOffPopulationPercentage) {
        this.betterOffPopulationPercentage = betterOffPopulationPercentage;
    }
}
