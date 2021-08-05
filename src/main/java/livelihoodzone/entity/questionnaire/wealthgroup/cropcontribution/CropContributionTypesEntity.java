package livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "crop_contribution_types")
public class CropContributionTypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CropContributionTypeId")
    private int cropContributionTypeId;

    @Column(name = "CropContributionTypeDescription")
    private String cropContributionTypeDescription;

    @Column(name = "CropContributionTypeCode")
    private int cropContributionTypeCode;

    public int getCropContributionTypeId() {
        return cropContributionTypeId;
    }

    public void setCropContributionTypeId(int cropContributionTypeId) {
        this.cropContributionTypeId = cropContributionTypeId;
    }

    public String getCropContributionTypeDescription() {
        return cropContributionTypeDescription;
    }

    public void setCropContributionTypeDescription(String cropContributionTypeDescription) {
        this.cropContributionTypeDescription = cropContributionTypeDescription;
    }

    public int getCropContributionTypeCode() {
        return cropContributionTypeCode;
    }

    public void setCropContributionTypeCode(int cropContributionTypeCode) {
        this.cropContributionTypeCode = cropContributionTypeCode;
    }
}
