package livelihoodzone.service.reports.wealthgroup.migration_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
import livelihoodzone.dto.reports.wealthgroup.WgMigrationPatternsDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.MigrationPatternsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternsPercentagesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.WealthGroupReportRetrofitService;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgExpenditurePatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.wealthgroup.WgMigrationPatternsDataSetRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class MigrationPatternsDataSetService {

    @Autowired
    WgMigrationPatternsPercentagesRepository wgMigrationPatternsPercentagesRepository;

    @Autowired
    MigrationPatternsRepository migrationPatternsRepository;

    public List<WgMigrationPatternsDataSetRetrofitModel> fetchWealthGroupMigrationPatterns(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgMigrationPatternsDataSetRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupMigrationPatterns(countyId, questionnaireTypeId);
        try {
            Response<List<WgMigrationPatternsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgMigrationPatternsDataSetObject processMigrationPatterns(List<WgMigrationPatternsDataSetRetrofitModel> list) {
        List<Number> fullyNomadic = processAMigrationPattern(Constants.MGR_FULLY_NOMADIC, list);
        List<Number> semiNomadic = processAMigrationPattern(Constants.MGR_SEMI_NOMADIC, list);
        List<Number> occasionalNomadic =  processAMigrationPattern(Constants.MGR_OCCASIONAL_NOMADIC, list);
        List<Number> outMigrantLabour =  processAMigrationPattern(Constants.MGR_OUT_MIGRANT_LABOUR, list);
        List<Number> inMigrantLabour =  processAMigrationPattern(Constants.MGR_IN_MIGRANT_LABOUR, list);
        List<Number> fullySettled =  processAMigrationPattern(Constants.MGR_FULLY_SETTLED, list);
        List<Number> internallyDisplaced =  processAMigrationPattern(Constants.MGR_INTERNALLY_DISPLACED, list);

        return new WgMigrationPatternsDataSetObject(
                fullyNomadic,
                semiNomadic,
                occasionalNomadic,
                outMigrantLabour,
                inMigrantLabour,
                fullySettled,
                internallyDisplaced
        );
    }

    public List<WgMigrationPatternsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<WgMigrationPatternsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgMigrationPatternsDataSetRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }

    public List<WgMigrationPatternsDataSetRetrofitModel> returnListForSpecificMigrationPattern(int migrationPatternCode, List<WgMigrationPatternsDataSetRetrofitModel> allItems) {
        List<WgMigrationPatternsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (WgMigrationPatternsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getMigrationPatternCode() == migrationPatternCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }


    public List<Number> processAMigrationPattern(int migrationPatternCode, List<WgMigrationPatternsDataSetRetrofitModel> list) {
        List<WgMigrationPatternsDataSetRetrofitModel> processedList = returnListForSpecificMigrationPattern(migrationPatternCode, list);
        List<Number> specificMigrationPatternReportList = new ArrayList<>();
        for (WgMigrationPatternsDataSetRetrofitModel currentItem : processedList) {
            specificMigrationPatternReportList.add(currentItem.getPercentage());
        }
        return specificMigrationPatternReportList;
    }

    public WgLivelihoodZoneDataObject processMigrationPatternsChart(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        MigrationPatternResponses migrationPatternResponses = new MigrationPatternResponses();
        List<WgMigrationPatternPercentagesEntity> wgMigrationPatternPercentagesEntityList = wgMigrationPatternsPercentagesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgMigrationPatternPercentagesEntity wgMigrationPatternPercentagesEntity : wgMigrationPatternPercentagesEntityList) {
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_FULLY_NOMADIC) {
                migrationPatternResponses.setFullyNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_SEMI_NOMADIC) {
                migrationPatternResponses.setSemiNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_OCCASIONAL_NOMADIC) {
                migrationPatternResponses.setOccasionalNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_OUT_MIGRANT_LABOUR) {
                migrationPatternResponses.setOutMigrantLabour(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_FULLY_SETTLED) {
                migrationPatternResponses.setFullysettled(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_INTERNALLY_DISPLACED) {
                migrationPatternResponses.setInternallyDisplaced(wgMigrationPatternPercentagesEntity.getPercentage());
            }
        }
        wgLivelihoodZoneDataObject.setSettlementAndmigrationPatterns(migrationPatternResponses);
        return wgLivelihoodZoneDataObject;
    }


    public WgLivelihoodZoneDataObject processMigrationPatternsByPattern(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId, int migrationPatternCode) {
        MigrationPatternResponses migrationPatternResponses = new MigrationPatternResponses();
        List<WgMigrationPatternPercentagesEntity> wgMigrationPatternPercentagesEntityList = wgMigrationPatternsPercentagesRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgMigrationPatternPercentagesEntity wgMigrationPatternPercentagesEntity : wgMigrationPatternPercentagesEntityList) {
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_FULLY_NOMADIC && migrationPatternCode == Constants.MGR_FULLY_NOMADIC) {
                migrationPatternResponses.setFullyNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_SEMI_NOMADIC && migrationPatternCode == Constants.MGR_SEMI_NOMADIC) {
                migrationPatternResponses.setSemiNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_OCCASIONAL_NOMADIC && migrationPatternCode == Constants.MGR_OCCASIONAL_NOMADIC) {
                migrationPatternResponses.setOccasionalNomadic(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_OUT_MIGRANT_LABOUR && migrationPatternCode == Constants.MGR_OUT_MIGRANT_LABOUR) {
                migrationPatternResponses.setOutMigrantLabour(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_FULLY_SETTLED && migrationPatternCode == Constants.MGR_FULLY_SETTLED) {
                migrationPatternResponses.setFullysettled(wgMigrationPatternPercentagesEntity.getPercentage());
            }
            if (migrationPatternsRepository.findByMigrationPatternId(wgMigrationPatternPercentagesEntity.getMigrationPatternId()).getMigrationPatternCode() == Constants.MGR_INTERNALLY_DISPLACED && migrationPatternCode == Constants.MGR_INTERNALLY_DISPLACED) {
                migrationPatternResponses.setInternallyDisplaced(wgMigrationPatternPercentagesEntity.getPercentage());
            }
        }
        wgLivelihoodZoneDataObject.setSettlementAndmigrationPatterns(migrationPatternResponses);
        return wgLivelihoodZoneDataObject;
    }

}
