package livelihoodzone.repository.questionnaire.crops;

import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropsRepository extends JpaRepository<CropsEntity, Long> {
    public CropsEntity findByCropId(@Param("CropId") int cropId);

    public CropsEntity findByCropName(@Param("CropName") String cropName);

    public List<CropsEntity> findByCropNameContainingIgnoreCase(@Param("CropName") String cropName);
}
