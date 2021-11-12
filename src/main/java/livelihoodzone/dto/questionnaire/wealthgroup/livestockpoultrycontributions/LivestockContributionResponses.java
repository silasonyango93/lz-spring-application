package livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions;

public class LivestockContributionResponses {
    private WgLivestockContributionResponseItem cattle;
    private WgLivestockContributionResponseItem dairyCattle;
    private WgLivestockContributionResponseItem goats;
    private WgLivestockContributionResponseItem sheep;
    private WgLivestockContributionResponseItem donkeys;
    private WgLivestockContributionResponseItem camels;
    private WgLivestockContributionResponseItem pigs;
    private WgLivestockContributionResponseItem chicken;
    private WgLivestockContributionResponseItem improvedChicken;
    private WgLivestockContributionResponseItem ducks;
    private WgLivestockContributionResponseItem beeHives;
    private WgLivestockContributionResponseItem fishPonds;
    private WgLivestockContributionResponseItem improvedCattle;
    private WgLivestockContributionResponseItem fishCages;

    public LivestockContributionResponses() {
    }

    public LivestockContributionResponses(boolean instantiate) {
        if (instantiate) {
            cattle = new WgLivestockContributionResponseItem(true);
            dairyCattle = new WgLivestockContributionResponseItem(true);
            goats = new WgLivestockContributionResponseItem(true);
            sheep = new WgLivestockContributionResponseItem(true);
            donkeys = new WgLivestockContributionResponseItem(true);
            camels = new WgLivestockContributionResponseItem(true);
            pigs = new WgLivestockContributionResponseItem(true);
            chicken = new WgLivestockContributionResponseItem(true);
            improvedChicken = new WgLivestockContributionResponseItem(true);
            ducks = new WgLivestockContributionResponseItem(true);
            beeHives = new WgLivestockContributionResponseItem(true);
            fishPonds = new WgLivestockContributionResponseItem(true);
            improvedCattle = new WgLivestockContributionResponseItem(true);
            fishCages = new WgLivestockContributionResponseItem(true);
        }
    }

    public WgLivestockContributionResponseItem getCattle() {
        return cattle;
    }

    public void setCattle(WgLivestockContributionResponseItem cattle) {
        this.cattle = cattle;
    }

    public WgLivestockContributionResponseItem getDairyCattle() {
        return dairyCattle;
    }

    public void setDairyCattle(WgLivestockContributionResponseItem dairyCattle) {
        this.dairyCattle = dairyCattle;
    }

    public WgLivestockContributionResponseItem getGoats() {
        return goats;
    }

    public void setGoats(WgLivestockContributionResponseItem goats) {
        this.goats = goats;
    }

    public WgLivestockContributionResponseItem getSheep() {
        return sheep;
    }

    public void setSheep(WgLivestockContributionResponseItem sheep) {
        this.sheep = sheep;
    }

    public WgLivestockContributionResponseItem getDonkeys() {
        return donkeys;
    }

    public void setDonkeys(WgLivestockContributionResponseItem donkeys) {
        this.donkeys = donkeys;
    }

    public WgLivestockContributionResponseItem getCamels() {
        return camels;
    }

    public void setCamels(WgLivestockContributionResponseItem camels) {
        this.camels = camels;
    }

    public WgLivestockContributionResponseItem getPigs() {
        return pigs;
    }

    public void setPigs(WgLivestockContributionResponseItem pigs) {
        this.pigs = pigs;
    }

    public WgLivestockContributionResponseItem getChicken() {
        return chicken;
    }

    public void setChicken(WgLivestockContributionResponseItem chicken) {
        this.chicken = chicken;
    }

    public WgLivestockContributionResponseItem getImprovedChicken() {
        return improvedChicken;
    }

    public void setImprovedChicken(WgLivestockContributionResponseItem improvedChicken) {
        this.improvedChicken = improvedChicken;
    }

    public WgLivestockContributionResponseItem getDucks() {
        return ducks;
    }

    public void setDucks(WgLivestockContributionResponseItem ducks) {
        this.ducks = ducks;
    }

    public WgLivestockContributionResponseItem getBeeHives() {
        return beeHives;
    }

    public void setBeeHives(WgLivestockContributionResponseItem beeHives) {
        this.beeHives = beeHives;
    }

    public WgLivestockContributionResponseItem getFishPonds() {
        return fishPonds;
    }

    public void setFishPonds(WgLivestockContributionResponseItem fishPonds) {
        this.fishPonds = fishPonds;
    }

    public WgLivestockContributionResponseItem getImprovedCattle() {
        return improvedCattle;
    }

    public void setImprovedCattle(WgLivestockContributionResponseItem improvedCattle) {
        this.improvedCattle = improvedCattle;
    }

    public WgLivestockContributionResponseItem getFishCages() {
        return fishCages;
    }

    public void setFishCages(WgLivestockContributionResponseItem fishCages) {
        this.fishCages = fishCages;
    }
}
