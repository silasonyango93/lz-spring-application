package livelihoodzone.dto.questionnaire.county;

import livelihoodzone.dto.questionnaire.county.model.watersources.WaterDependenceResponseItem;

public class WaterSourcesResponsesDto {
    private WaterDependenceResponseItem rivers;
    private WaterDependenceResponseItem traditionalRiversWells;
    private WaterDependenceResponseItem naturalPonds;
    private WaterDependenceResponseItem pansAndDams;
    private WaterDependenceResponseItem shallowWells;
    private WaterDependenceResponseItem boreholes;
    private WaterDependenceResponseItem springs;
    private WaterDependenceResponseItem lakes;
    private WaterDependenceResponseItem rockCatchments;
    private WaterDependenceResponseItem pipedWater;
    private WaterDependenceResponseItem waterTrucking;
    private WaterDependenceResponseItem roofCatchments;
    private WaterDependenceResponseItem others;

    public WaterSourcesResponsesDto() {
    }

    public WaterSourcesResponsesDto(boolean instantiate) {
        if (instantiate) {
            rivers = new WaterDependenceResponseItem();
            traditionalRiversWells = new WaterDependenceResponseItem();
            naturalPonds = new WaterDependenceResponseItem();
            pansAndDams = new WaterDependenceResponseItem();
            shallowWells = new WaterDependenceResponseItem();
            boreholes = new WaterDependenceResponseItem();
            springs = new WaterDependenceResponseItem();
            lakes = new WaterDependenceResponseItem();
            rockCatchments = new WaterDependenceResponseItem();
            pipedWater = new WaterDependenceResponseItem();
            waterTrucking = new WaterDependenceResponseItem();
            roofCatchments = new WaterDependenceResponseItem();
            others = new WaterDependenceResponseItem();
        }
    }

    public WaterDependenceResponseItem getRivers() {
        return rivers;
    }

    public void setRivers(WaterDependenceResponseItem rivers) {
        this.rivers = rivers;
    }

    public WaterDependenceResponseItem getTraditionalRiversWells() {
        return traditionalRiversWells;
    }

    public void setTraditionalRiversWells(WaterDependenceResponseItem traditionalRiversWells) {
        this.traditionalRiversWells = traditionalRiversWells;
    }

    public WaterDependenceResponseItem getNaturalPonds() {
        return naturalPonds;
    }

    public void setNaturalPonds(WaterDependenceResponseItem naturalPonds) {
        this.naturalPonds = naturalPonds;
    }

    public WaterDependenceResponseItem getPansAndDams() {
        return pansAndDams;
    }

    public void setPansAndDams(WaterDependenceResponseItem pansAndDams) {
        this.pansAndDams = pansAndDams;
    }

    public WaterDependenceResponseItem getShallowWells() {
        return shallowWells;
    }

    public void setShallowWells(WaterDependenceResponseItem shallowWells) {
        this.shallowWells = shallowWells;
    }

    public WaterDependenceResponseItem getBoreholes() {
        return boreholes;
    }

    public void setBoreholes(WaterDependenceResponseItem boreholes) {
        this.boreholes = boreholes;
    }

    public WaterDependenceResponseItem getSprings() {
        return springs;
    }

    public void setSprings(WaterDependenceResponseItem springs) {
        this.springs = springs;
    }

    public WaterDependenceResponseItem getLakes() {
        return lakes;
    }

    public void setLakes(WaterDependenceResponseItem lakes) {
        this.lakes = lakes;
    }

    public WaterDependenceResponseItem getRockCatchments() {
        return rockCatchments;
    }

    public void setRockCatchments(WaterDependenceResponseItem rockCatchments) {
        this.rockCatchments = rockCatchments;
    }

    public WaterDependenceResponseItem getPipedWater() {
        return pipedWater;
    }

    public void setPipedWater(WaterDependenceResponseItem pipedWater) {
        this.pipedWater = pipedWater;
    }

    public WaterDependenceResponseItem getWaterTrucking() {
        return waterTrucking;
    }

    public void setWaterTrucking(WaterDependenceResponseItem waterTrucking) {
        this.waterTrucking = waterTrucking;
    }

    public WaterDependenceResponseItem getRoofCatchments() {
        return roofCatchments;
    }

    public void setRoofCatchments(WaterDependenceResponseItem roofCatchments) {
        this.roofCatchments = roofCatchments;
    }

    public WaterDependenceResponseItem getOthers() {
        return others;
    }

    public void setOthers(WaterDependenceResponseItem others) {
        this.others = others;
    }
}
