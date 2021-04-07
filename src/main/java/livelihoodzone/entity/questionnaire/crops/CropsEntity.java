package livelihoodzone.entity.questionnaire.crops;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "crops")
public class CropsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CropId")
    private int cropId;

    @Column(name = "CropName")
    private String cropName;

    @Column(name = "CropCode")
    private int cropCode;

    public CropsEntity() {
    }

    public CropsEntity(String cropName, int cropCode) {
        this.cropName = cropName;
        this.cropCode = cropCode;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getCropCode() {
        return cropCode;
    }

    public void setCropCode(int cropCode) {
        this.cropCode = cropCode;
    }
}
