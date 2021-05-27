package livelihoodzone.dto.questionnaire.county.model.hazards;

public class HazardResponseItem {
    private int importanceRank;
    private double noExperiencedYears;

    public int getImportanceRank() {
        return importanceRank;
    }

    public void setImportanceRank(int importanceRank) {
        this.importanceRank = importanceRank;
    }

    public double getNoExperiencedYears() {
        return noExperiencedYears;
    }

    public void setNoExperiencedYears(double noExperiencedYears) {
        this.noExperiencedYears = noExperiencedYears;
    }
}
