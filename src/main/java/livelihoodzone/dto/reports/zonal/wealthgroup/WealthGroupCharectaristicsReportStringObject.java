package livelihoodzone.dto.reports.zonal.wealthgroup;

import java.util.List;

public class WealthGroupCharectaristicsReportStringObject {
    private List<String> veryPoorCharacteristics;
    private List<String> poorCharacteristics;
    private List<String> mediumCharacteristics;
    private List<String> betterOffCharacteristics;

    public WealthGroupCharectaristicsReportStringObject(List<String> veryPoorCharacteristics, List<String> poorCharacteristics, List<String> mediumCharacteristics, List<String> betterOffCharacteristics) {
        this.veryPoorCharacteristics = veryPoorCharacteristics;
        this.poorCharacteristics = poorCharacteristics;
        this.mediumCharacteristics = mediumCharacteristics;
        this.betterOffCharacteristics = betterOffCharacteristics;
    }

    public List<String> getVeryPoorCharacteristics() {
        return veryPoorCharacteristics;
    }

    public void setVeryPoorCharacteristics(List<String> veryPoorCharacteristics) {
        this.veryPoorCharacteristics = veryPoorCharacteristics;
    }

    public List<String> getPoorCharacteristics() {
        return poorCharacteristics;
    }

    public void setPoorCharacteristics(List<String> poorCharacteristics) {
        this.poorCharacteristics = poorCharacteristics;
    }

    public List<String> getMediumCharacteristics() {
        return mediumCharacteristics;
    }

    public void setMediumCharacteristics(List<String> mediumCharacteristics) {
        this.mediumCharacteristics = mediumCharacteristics;
    }

    public List<String> getBetterOffCharacteristics() {
        return betterOffCharacteristics;
    }

    public void setBetterOffCharacteristics(List<String> betterOffCharacteristics) {
        this.betterOffCharacteristics = betterOffCharacteristics;
    }
}
