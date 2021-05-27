package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "crop_water_access_types")
public class CropWaterAccessTypesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CropWaterAccessTypeId")
    private int cropWaterAccessTypeId;

    @Column(name = "CropWaterAccessTypeDescription")
    private String cropWaterAccessTypeDescription;

    @Column(name = "CropWaterAccessTypeCode")
    private int cropWaterAccessTypeCode;

    public int getCropWaterAccessTypeId() {
        return cropWaterAccessTypeId;
    }

    public void setCropWaterAccessTypeId(int cropWaterAccessTypeId) {
        this.cropWaterAccessTypeId = cropWaterAccessTypeId;
    }

    public String getCropWaterAccessTypeDescription() {
        return cropWaterAccessTypeDescription;
    }

    public void setCropWaterAccessTypeDescription(String cropWaterAccessTypeDescription) {
        this.cropWaterAccessTypeDescription = cropWaterAccessTypeDescription;
    }

    public int getCropWaterAccessTypeCode() {
        return cropWaterAccessTypeCode;
    }

    public void setCropWaterAccessTypeCode(int cropWaterAccessTypeCode) {
        this.cropWaterAccessTypeCode = cropWaterAccessTypeCode;
    }
}
