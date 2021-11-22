package livelihoodzone.service.reports.zonal.markets;

import livelihoodzone.dto.questionnaire.county.model.markets.MarketTransactionsItem;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.charts.MarketTransactionObject;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketTransactionsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.countries.CountriesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.questionnaire.county.LzMarketTransactionsRepository;
import livelihoodzone.repository.questionnaire.county.LzMarketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketsReportService {

    @Autowired
    LzMarketTransactionsRepository lzMarketTransactionsRepository;

    @Autowired
    LzMarketsRepository lzMarketsRepository;

    @Autowired
    SubCountiesRepository subCountiesRepository;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    public LzLivelihoodZoneDataObject processMarketsChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        MarketTransactionObject marketTransactionObject = new MarketTransactionObject(true);
        List<LzMarketTransactionsEntity> lzMarketTransactionsEntityList = lzMarketTransactionsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        for (LzMarketTransactionsEntity lzMarketTransactionsEntity : lzMarketTransactionsEntityList) {
            MarketTransactionsItem marketTransactionsItem = new MarketTransactionsItem();
            LzMarketsEntity currentMarketDefinition = lzMarketsRepository.findByMarketId(lzMarketTransactionsEntity.getMarketId());
            marketTransactionsItem.setMarketName(currentMarketDefinition.getMarketName());
            marketTransactionsItem.setNearestVillageOrTownName(currentMarketDefinition.getNearestVillageOrTown());
            if (currentMarketDefinition.getSubCountyId() != 0) {
                SubCountyEntity subCountyEntity = subCountiesRepository.findBySubCountyId(currentMarketDefinition.getSubCountyId());
                subCountyEntity.setWards(new ArrayList<>());
                marketTransactionsItem.setSubCounty(subCountyEntity);
            }
            if (currentMarketDefinition.getCountyId() != 0) {
                CountiesEntity countiesEntity = countiesRepository.findByCountyId(currentMarketDefinition.getCountyId());
                countiesEntity.setSubCounties(new ArrayList<>());
                marketTransactionsItem.setCounty(countiesEntity);
            }
            if (currentMarketDefinition.getCountryId() != 0) {
                marketTransactionsItem.setCountry(countriesRepository.findByCountryId(currentMarketDefinition.getCountryId()));
            }
            marketTransactionsItem.setLivestockTrade(lzMarketTransactionsEntity.getLivestockTrade() == 1);
            marketTransactionsItem.setPoultryTrade(lzMarketTransactionsEntity.getPoultryTrade() == 1);
            marketTransactionsItem.setFarmProduceTrade(lzMarketTransactionsEntity.getFarmProduceTrade() == 1);
            marketTransactionsItem.setRetailFarmInput(lzMarketTransactionsEntity.getRetailFarmInput() == 1);
            marketTransactionsItem.setLabourExchange(lzMarketTransactionsEntity.getLabourExchange() == 1);
            marketTransactionObject.getMarketTransactionItems().add(marketTransactionsItem);
        }
        lzLivelihoodZoneDataObject.setMarketTransactionObject(marketTransactionObject);
        return lzLivelihoodZoneDataObject;
    }
}
