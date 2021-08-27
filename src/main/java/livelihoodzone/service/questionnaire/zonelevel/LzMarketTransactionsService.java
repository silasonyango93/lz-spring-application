package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.model.markets.MarketTransactionsItem;
import livelihoodzone.entity.questionnaire.county.LzMarketTransactionsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.questionnaire.county.LzMarketTransactionsRepository;
import livelihoodzone.repository.questionnaire.county.LzMarketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LzMarketTransactionsService {

    @Autowired
    LzMarketsRepository lzMarketsRepository;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    SubCountiesRepository subCountiesRepository;

    @Autowired
    LzMarketTransactionsRepository lzMarketTransactionsRepository;

    public void saveMarketTransactions(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        List<MarketTransactionsItem> marketTransactionItems = countyLevelQuestionnaireRequestDto.getMarketTransactionItems();

        for (MarketTransactionsItem currentMarketItem : marketTransactionItems) {

            //Save the market
            LzMarketsEntity savedMarket = lzMarketsRepository.save(new LzMarketsEntity(
                    currentMarketItem.getCountry().getCountryId(),
                    currentMarketItem.getCounty() != null ? currentMarketItem.getCounty().getCountyId() : countiesRepository.findByCountyCode("-1").getCountyId(),
                    currentMarketItem.getSubCounty() != null ? currentMarketItem.getSubCounty().getSubCountyId() : subCountiesRepository.findBySubCountyCode(-1).getSubCountyId(),
                    currentMarketItem.getMarketName(),
                    currentMarketItem.getNearestVillageOrTownName()
            ));


            //Save market transactions
            lzMarketTransactionsRepository.save(new LzMarketTransactionsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    savedMarket.getMarketId(),
                    currentMarketItem.isLivestockTrade() ? 1 : 0,
                    currentMarketItem.isPoultryTrade() ? 1 : 0,
                    currentMarketItem.isFarmProduceTrade() ? 1 : 0,
                    currentMarketItem.isRetailFarmInput() ? 1 : 0,
                    currentMarketItem.isLabourExchange() ? 1 : 0
            ));
        }
    }
}
