package livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_animal_contributions")
public class WgAnimalContributionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgAnimalContributionId")
    private int wgAnimalContributionId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "AnimalId")
    private int animalId;

    @Column(name = "IncomeContributionRank")
    private int incomeContributionRank;

    @Column(name = "IncomeContributionApproxPercentage")
    private double incomeContributionApproxPercentage;

    @Column(name = "ConsumptionContributionRank")
    private int consumptionContributionRank;

    @Column(name = "ConsumptionContributionApproxPercentage")
    private double consumptionContributionApproxPercentage;

    public WgAnimalContributionsEntity() {
    }

    public WgAnimalContributionsEntity(int wgQuestionnaireSessionId, int animalId, int incomeContributionRank, double incomeContributionApproxPercentage, int consumptionContributionRank, double consumptionContributionApproxPercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.animalId = animalId;
        this.incomeContributionRank = incomeContributionRank;
        this.incomeContributionApproxPercentage = incomeContributionApproxPercentage;
        this.consumptionContributionRank = consumptionContributionRank;
        this.consumptionContributionApproxPercentage = consumptionContributionApproxPercentage;
    }

    public int getWgAnimalContributionId() {
        return wgAnimalContributionId;
    }

    public void setWgAnimalContributionId(int wgAnimalContributionId) {
        this.wgAnimalContributionId = wgAnimalContributionId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getIncomeContributionRank() {
        return incomeContributionRank;
    }

    public void setIncomeContributionRank(int incomeContributionRank) {
        this.incomeContributionRank = incomeContributionRank;
    }

    public double getIncomeContributionApproxPercentage() {
        return incomeContributionApproxPercentage;
    }

    public void setIncomeContributionApproxPercentage(double incomeContributionApproxPercentage) {
        this.incomeContributionApproxPercentage = incomeContributionApproxPercentage;
    }

    public int getConsumptionContributionRank() {
        return consumptionContributionRank;
    }

    public void setConsumptionContributionRank(int consumptionContributionRank) {
        this.consumptionContributionRank = consumptionContributionRank;
    }

    public double getConsumptionContributionApproxPercentage() {
        return consumptionContributionApproxPercentage;
    }

    public void setConsumptionContributionApproxPercentage(double consumptionContributionApproxPercentage) {
        this.consumptionContributionApproxPercentage = consumptionContributionApproxPercentage;
    }
}
