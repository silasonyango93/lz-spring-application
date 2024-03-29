package livelihoodzone.dto.questionnaire.wealthgroup.constraints;

public class ConstraintsResponses {
    private WagedLabourIncomeConstraintsResponses wagedLabourIncomeConstraintsResponses;
    private CropProductionIncomeConstraintsResponses cropProductionIncomeConstraintsResponses;
    private LivestockProductionIncomeConstraintsResponses livestockProductionIncomeConstraintsResponses;
    private FishingIncomeConstraintsResponses fishingIncomeConstraintsResponses;
    private NaturalResourceIncomeConstraintsResponses naturalResourceIncomeConstraintsResponses;
    private SmallEnterpriseIncomeConstraintsResponses smallEnterpriseIncomeConstraintsResponses;

    public ConstraintsResponses() {
    }

    public ConstraintsResponses(boolean instantiate) {
        if (instantiate) {
            wagedLabourIncomeConstraintsResponses = new WagedLabourIncomeConstraintsResponses();
            cropProductionIncomeConstraintsResponses = new CropProductionIncomeConstraintsResponses();
            livestockProductionIncomeConstraintsResponses = new LivestockProductionIncomeConstraintsResponses();
            fishingIncomeConstraintsResponses = new FishingIncomeConstraintsResponses();
            naturalResourceIncomeConstraintsResponses = new NaturalResourceIncomeConstraintsResponses();
            smallEnterpriseIncomeConstraintsResponses = new SmallEnterpriseIncomeConstraintsResponses();
        }
    }

    public WagedLabourIncomeConstraintsResponses getWagedLabourIncomeConstraintsResponses() {
        return wagedLabourIncomeConstraintsResponses;
    }

    public void setWagedLabourIncomeConstraintsResponses(WagedLabourIncomeConstraintsResponses wagedLabourIncomeConstraintsResponses) {
        this.wagedLabourIncomeConstraintsResponses = wagedLabourIncomeConstraintsResponses;
    }

    public CropProductionIncomeConstraintsResponses getCropProductionIncomeConstraintsResponses() {
        return cropProductionIncomeConstraintsResponses;
    }

    public void setCropProductionIncomeConstraintsResponses(CropProductionIncomeConstraintsResponses cropProductionIncomeConstraintsResponses) {
        this.cropProductionIncomeConstraintsResponses = cropProductionIncomeConstraintsResponses;
    }

    public LivestockProductionIncomeConstraintsResponses getLivestockProductionIncomeConstraintsResponses() {
        return livestockProductionIncomeConstraintsResponses;
    }

    public void setLivestockProductionIncomeConstraintsResponses(LivestockProductionIncomeConstraintsResponses livestockProductionIncomeConstraintsResponses) {
        this.livestockProductionIncomeConstraintsResponses = livestockProductionIncomeConstraintsResponses;
    }

    public FishingIncomeConstraintsResponses getFishingIncomeConstraintsResponses() {
        return fishingIncomeConstraintsResponses;
    }

    public void setFishingIncomeConstraintsResponses(FishingIncomeConstraintsResponses fishingIncomeConstraintsResponses) {
        this.fishingIncomeConstraintsResponses = fishingIncomeConstraintsResponses;
    }

    public NaturalResourceIncomeConstraintsResponses getNaturalResourceIncomeConstraintsResponses() {
        return naturalResourceIncomeConstraintsResponses;
    }

    public void setNaturalResourceIncomeConstraintsResponses(NaturalResourceIncomeConstraintsResponses naturalResourceIncomeConstraintsResponses) {
        this.naturalResourceIncomeConstraintsResponses = naturalResourceIncomeConstraintsResponses;
    }

    public SmallEnterpriseIncomeConstraintsResponses getSmallEnterpriseIncomeConstraintsResponses() {
        return smallEnterpriseIncomeConstraintsResponses;
    }

    public void setSmallEnterpriseIncomeConstraintsResponses(SmallEnterpriseIncomeConstraintsResponses smallEnterpriseIncomeConstraintsResponses) {
        this.smallEnterpriseIncomeConstraintsResponses = smallEnterpriseIncomeConstraintsResponses;
    }
}
