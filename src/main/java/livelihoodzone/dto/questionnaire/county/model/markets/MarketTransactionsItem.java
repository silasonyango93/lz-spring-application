package livelihoodzone.dto.questionnaire.county.model.markets;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.countries.CountriesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;

public class MarketTransactionsItem {
    private String marketUniqueId;
    private String marketName;
    private String nearestVillageOrTownName;
    private SubCountyEntity subCounty;
    private CountiesEntity county;
    private CountriesEntity country;
    private boolean livestockTrade;
    private boolean poultryTrade;
    private boolean farmProduceTrade;
    private boolean retailFarmInput;
    private boolean labourExchange;

    public String getMarketUniqueId() {
        return marketUniqueId;
    }

    public void setMarketUniqueId(String marketUniqueId) {
        this.marketUniqueId = marketUniqueId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public boolean isLivestockTrade() {
        return livestockTrade;
    }

    public void setLivestockTrade(boolean livestockTrade) {
        this.livestockTrade = livestockTrade;
    }

    public boolean isPoultryTrade() {
        return poultryTrade;
    }

    public void setPoultryTrade(boolean poultryTrade) {
        this.poultryTrade = poultryTrade;
    }

    public boolean isFarmProduceTrade() {
        return farmProduceTrade;
    }

    public void setFarmProduceTrade(boolean farmProduceTrade) {
        this.farmProduceTrade = farmProduceTrade;
    }

    public boolean isRetailFarmInput() {
        return retailFarmInput;
    }

    public void setRetailFarmInput(boolean retailFarmInput) {
        this.retailFarmInput = retailFarmInput;
    }

    public boolean isLabourExchange() {
        return labourExchange;
    }

    public void setLabourExchange(boolean labourExchange) {
        this.labourExchange = labourExchange;
    }

    public String getNearestVillageOrTownName() {
        return nearestVillageOrTownName;
    }

    public void setNearestVillageOrTownName(String nearestVillageOrTownName) {
        this.nearestVillageOrTownName = nearestVillageOrTownName;
    }

    public SubCountyEntity getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCountyEntity subCounty) {
        this.subCounty = subCounty;
    }

    public CountiesEntity getCounty() {
        return county;
    }

    public void setCounty(CountiesEntity county) {
        this.county = county;
    }

    public CountriesEntity getCountry() {
        return country;
    }

    public void setCountry(CountriesEntity country) {
        this.country = country;
    }
}
