package livelihoodzone.dto.questionnaire.county;

import java.util.ArrayList;
import java.util.List;

public class WealthGroupCharectaristicsResponses {
    private List<String> veryPoorCharectaristics;
    private List<String> poorCharectaristics;
    private List<String> mediumCharectaristics;
    private List<String> betterOffCharectaristics;

    public List<String> getVeryPoorCharectaristics() {
        return veryPoorCharectaristics;
    }

    public void setVeryPoorCharectaristics(List<String> veryPoorCharectaristics) {
        this.veryPoorCharectaristics = veryPoorCharectaristics;
    }

    public List<String> getPoorCharectaristics() {
        return poorCharectaristics;
    }

    public void setPoorCharectaristics(List<String> poorCharectaristics) {
        this.poorCharectaristics = poorCharectaristics;
    }

    public List<String> getMediumCharectaristics() {
        return mediumCharectaristics;
    }

    public void setMediumCharectaristics(List<String> mediumCharectaristics) {
        this.mediumCharectaristics = mediumCharectaristics;
    }

    public List<String> getBetterOffCharectaristics() {
        return betterOffCharectaristics;
    }

    public void setBetterOffCharectaristics(List<String> betterOffCharectaristics) {
        this.betterOffCharectaristics = betterOffCharectaristics;
    }
}
