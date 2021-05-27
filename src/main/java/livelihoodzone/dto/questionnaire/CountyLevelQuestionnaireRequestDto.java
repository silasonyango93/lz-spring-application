package livelihoodzone.dto.questionnaire;

import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.ethnicgroup.EthnicityResponseItem;
import livelihoodzone.dto.questionnaire.county.model.hazards.HazardResponses;
import livelihoodzone.dto.questionnaire.county.model.hunger.HungerPatternsResponses;
import livelihoodzone.dto.questionnaire.county.model.markets.MarketTransactionsItem;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;

import java.util.List;

public class CountyLevelQuestionnaireRequestDto {
    private String uniqueId;
    private LivelihoodZonesEntity selectedLivelihoodZone;
    private double latitude;
    private double longitude;
    private String questionnaireStartDate;
    private String questionnaireEndDate;
    private WealthGroupCharectaristicsResponses wealthGroupCharectariticsResponses;
    private WealthGroupPercentageResponse wealthGroupResponse;
    private LzCropProductionResponses lzCropProductionResponses;
    private WaterSourcesResponsesDto waterSourceResponses;
    private List<MarketTransactionsItem> marketTransactionItems;
    private List<EthnicityResponseItem> ethnicGroupResponseList;
    private HungerPatternsResponses hungerPatternsResponses;
    private HazardResponses hazardResponses;
    private LzSeasonsResponses livelihoodZoneSeasonsResponses;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public LivelihoodZonesEntity getSelectedLivelihoodZone() {
        return selectedLivelihoodZone;
    }

    public void setSelectedLivelihoodZone(LivelihoodZonesEntity selectedLivelihoodZone) {
        this.selectedLivelihoodZone = selectedLivelihoodZone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getQuestionnaireStartDate() {
        return questionnaireStartDate;
    }

    public void setQuestionnaireStartDate(String questionnaireStartDate) {
        this.questionnaireStartDate = questionnaireStartDate;
    }

    public String getQuestionnaireEndDate() {
        return questionnaireEndDate;
    }

    public void setQuestionnaireEndDate(String questionnaireEndDate) {
        this.questionnaireEndDate = questionnaireEndDate;
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

    public List<MarketTransactionsItem> getMarketTransactionItems() {
        return marketTransactionItems;
    }

    public void setMarketTransactionItems(List<MarketTransactionsItem> marketTransactionItems) {
        this.marketTransactionItems = marketTransactionItems;
    }

    public List<EthnicityResponseItem> getEthnicGroupResponseList() {
        return ethnicGroupResponseList;
    }

    public void setEthnicGroupResponseList(List<EthnicityResponseItem> ethnicGroupResponseList) {
        this.ethnicGroupResponseList = ethnicGroupResponseList;
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
}
