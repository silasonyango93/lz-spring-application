package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "markets")
public class LzMarketsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MarketId")
    private int marketId;

    @Column(name = "CountryId")
    private int countryId;

    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "SubCountyId")
    private int subCountyId;

    @Column(name = "MarketName")
    private String marketName;

    @Column(name = "NearestVillageOrTown")
    private String nearestVillageOrTown;

    public LzMarketsEntity() {
    }

    public LzMarketsEntity(int countryId, int countyId, int subCountyId, String marketName, String nearestVillageOrTown) {
        this.countryId = countryId;
        this.countyId = countyId;
        this.subCountyId = subCountyId;
        this.marketName = marketName;
        this.nearestVillageOrTown = nearestVillageOrTown;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getNearestVillageOrTown() {
        return nearestVillageOrTown;
    }

    public void setNearestVillageOrTown(String nearestVillageOrTown) {
        this.nearestVillageOrTown = nearestVillageOrTown;
    }
}
