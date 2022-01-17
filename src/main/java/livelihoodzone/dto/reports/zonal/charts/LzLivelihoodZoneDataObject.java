package livelihoodzone.dto.reports.zonal.charts;

import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.hazards.HazardResponses;
import livelihoodzone.dto.questionnaire.county.model.hunger.HungerPatternsResponses;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;

import java.util.List;

public class LzLivelihoodZoneDataObject {
    private int livelihoodZoneId;
    private String livelihoodZoneName;
    private WealthGroupCharectaristicsResponses wealthGroupCharectariticsResponses;
    private WealthGroupPercentageResponse wealthGroupResponse;
    private LzCropProductionResponses lzCropProductionResponses;
    private WaterSourcesResponsesDto waterSourceResponses;
    private HungerPatternsResponses hungerPatternsResponses;
    private HazardResponses hazardResponses;
    private LzSeasonsResponses livelihoodZoneSeasonsResponses;
    private MarketTransactionObject marketTransactionObject;
    private EthnicityResponseObject ethnicityResponseObject;
    private List<SubLocationEntity> subLocationsUnderTheLivelihoodZone;

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }

    public WealthGroupCharectaristicsResponses getWealthGroupCharectariticsResponses() {
        return wealthGroupCharectariticsResponses;
    }

    public void setWealthGroupCharectariticsResponses(WealthGroupCharectaristicsResponses wealthGroupCharectariticsResponses) {
        this.wealthGroupCharectariticsResponses = wealthGroupCharectariticsResponses;
    }

    public WealthGroupPercentageResponse getWealthGroupResponse() {
        return wealthGroupResponse;
    }

    public void setWealthGroupResponse(WealthGroupPercentageResponse wealthGroupResponse) {
        this.wealthGroupResponse = wealthGroupResponse;
    }

    public LzCropProductionResponses getLzCropProductionResponses() {
        return lzCropProductionResponses;
    }

    public void setLzCropProductionResponses(LzCropProductionResponses lzCropProductionResponses) {
        this.lzCropProductionResponses = lzCropProductionResponses;
    }

    public WaterSourcesResponsesDto getWaterSourceResponses() {
        return waterSourceResponses;
    }

    public void setWaterSourceResponses(WaterSourcesResponsesDto waterSourceResponses) {
        this.waterSourceResponses = waterSourceResponses;
    }

    public HungerPatternsResponses getHungerPatternsResponses() {
        return hungerPatternsResponses;
    }

    public void setHungerPatternsResponses(HungerPatternsResponses hungerPatternsResponses) {
        this.hungerPatternsResponses = hungerPatternsResponses;
    }

    public HazardResponses getHazardResponses() {
        return hazardResponses;
    }

    public void setHazardResponses(HazardResponses hazardResponses) {
        this.hazardResponses = hazardResponses;
    }

    public LzSeasonsResponses getLivelihoodZoneSeasonsResponses() {
        return livelihoodZoneSeasonsResponses;
    }

    public void setLivelihoodZoneSeasonsResponses(LzSeasonsResponses livelihoodZoneSeasonsResponses) {
        this.livelihoodZoneSeasonsResponses = livelihoodZoneSeasonsResponses;
    }

    public MarketTransactionObject getMarketTransactionObject() {
        return marketTransactionObject;
    }

    public void setMarketTransactionObject(MarketTransactionObject marketTransactionObject) {
        this.marketTransactionObject = marketTransactionObject;
    }

    public EthnicityResponseObject getEthnicityResponseObject() {
        return ethnicityResponseObject;
    }

    public void setEthnicityResponseObject(EthnicityResponseObject ethnicityResponseObject) {
        this.ethnicityResponseObject = ethnicityResponseObject;
    }

    public List<SubLocationEntity> getSubLocationsUnderTheLivelihoodZone() {
        return subLocationsUnderTheLivelihoodZone;
    }

    public void setSubLocationsUnderTheLivelihoodZone(List<SubLocationEntity> subLocationsUnderTheLivelihoodZone) {
        this.subLocationsUnderTheLivelihoodZone = subLocationsUnderTheLivelihoodZone;
    }
}
