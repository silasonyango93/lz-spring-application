package livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_ave_animal_no_per_household")
public class WgAveAnimalNoPerHouseholdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgAnimalNoPerHouseHoldId")
    private int wgAnimalNoPerHouseHoldId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "AnimalId")
    private int animalId;

    @Column(name = "AverageNumber")
    private double averageNumber;

    public WgAveAnimalNoPerHouseholdEntity() {
    }

    public WgAveAnimalNoPerHouseholdEntity(int wgQuestionnaireSessionId, int animalId, double averageNumber) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.animalId = animalId;
        this.averageNumber = averageNumber;
    }

    public int getWgAnimalNoPerHouseHoldId() {
        return wgAnimalNoPerHouseHoldId;
    }

    public void setWgAnimalNoPerHouseHoldId(int wgAnimalNoPerHouseHoldId) {
        this.wgAnimalNoPerHouseHoldId = wgAnimalNoPerHouseHoldId;
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

    public double getAverageNumber() {
        return averageNumber;
    }

    public void setAverageNumber(double averageNumber) {
        this.averageNumber = averageNumber;
    }
}
