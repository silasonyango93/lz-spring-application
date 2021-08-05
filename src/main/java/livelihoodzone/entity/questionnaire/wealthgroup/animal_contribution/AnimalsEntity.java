package livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "animals")
public class AnimalsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnimalId")
    private int animalId;

    @Column(name = "AnimalName")
    private String animalName;

    @Column(name = "AnimalCode")
    private int animalCode;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(int animalCode) {
        this.animalCode = animalCode;
    }
}
