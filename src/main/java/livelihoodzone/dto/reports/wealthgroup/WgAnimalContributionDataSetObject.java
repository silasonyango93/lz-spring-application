package livelihoodzone.dto.reports.wealthgroup;

import java.util.List;

public class WgAnimalContributionDataSetObject {
    private List<String> localCattle;
    private List<String> dairyCattle;
    private List<String> improvedCattle;
    private List<String> goats;
    private List<String> sheep;
    private List<String> donkeys;
    private List<String> camels;
    private List<String> pigs;
    private List<String> chicken;
    private List<String> improvedChicken;
    private List<String> ducks;
    private List<String> beeHives;
    private List<String> fishPonds;
    private List<String> fishCages;

    public WgAnimalContributionDataSetObject() {
    }

    public WgAnimalContributionDataSetObject(List<String> localCattle, List<String> dairyCattle, List<String> improvedCattle, List<String> goats, List<String> sheep, List<String> donkeys, List<String> camels, List<String> pigs, List<String> chicken, List<String> improvedChicken, List<String> ducks, List<String> beeHives, List<String> fishPonds, List<String> fishCages) {
        this.localCattle = localCattle;
        this.dairyCattle = dairyCattle;
        this.improvedCattle = improvedCattle;
        this.goats = goats;
        this.sheep = sheep;
        this.donkeys = donkeys;
        this.camels = camels;
        this.pigs = pigs;
        this.chicken = chicken;
        this.improvedChicken = improvedChicken;
        this.ducks = ducks;
        this.beeHives = beeHives;
        this.fishPonds = fishPonds;
        this.fishCages = fishCages;
    }


    public List<String> getLocalCattle() {
        return localCattle;
    }

    public void setLocalCattle(List<String> localCattle) {
        this.localCattle = localCattle;
    }

    public List<String> getDairyCattle() {
        return dairyCattle;
    }

    public void setDairyCattle(List<String> dairyCattle) {
        this.dairyCattle = dairyCattle;
    }

    public List<String> getImprovedCattle() {
        return improvedCattle;
    }

    public void setImprovedCattle(List<String> improvedCattle) {
        this.improvedCattle = improvedCattle;
    }

    public List<String> getGoats() {
        return goats;
    }

    public void setGoats(List<String> goats) {
        this.goats = goats;
    }

    public List<String> getSheep() {
        return sheep;
    }

    public void setSheep(List<String> sheep) {
        this.sheep = sheep;
    }

    public List<String> getDonkeys() {
        return donkeys;
    }

    public void setDonkeys(List<String> donkeys) {
        this.donkeys = donkeys;
    }

    public List<String> getCamels() {
        return camels;
    }

    public void setCamels(List<String> camels) {
        this.camels = camels;
    }

    public List<String> getPigs() {
        return pigs;
    }

    public void setPigs(List<String> pigs) {
        this.pigs = pigs;
    }

    public List<String> getChicken() {
        return chicken;
    }

    public void setChicken(List<String> chicken) {
        this.chicken = chicken;
    }

    public List<String> getImprovedChicken() {
        return improvedChicken;
    }

    public void setImprovedChicken(List<String> improvedChicken) {
        this.improvedChicken = improvedChicken;
    }

    public List<String> getDucks() {
        return ducks;
    }

    public void setDucks(List<String> ducks) {
        this.ducks = ducks;
    }

    public List<String> getBeeHives() {
        return beeHives;
    }

    public void setBeeHives(List<String> beeHives) {
        this.beeHives = beeHives;
    }

    public List<String> getFishPonds() {
        return fishPonds;
    }

    public void setFishPonds(List<String> fishPonds) {
        this.fishPonds = fishPonds;
    }

    public List<String> getFishCages() {
        return fishCages;
    }

    public void setFishCages(List<String> fishCages) {
        this.fishCages = fishCages;
    }
}
