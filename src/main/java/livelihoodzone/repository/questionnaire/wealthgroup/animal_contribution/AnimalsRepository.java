package livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.AnimalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AnimalsRepository extends JpaRepository<AnimalsEntity, Long> {
    public AnimalsEntity findByAnimalCode(@Param("AnimalCode") int animalCode);
}
