package livelihoodzone.service.reports.zonal.seasonal_calendar;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.dto.reports.zonal.LzSeasonalCalendarDataSetObject;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.*;
import livelihoodzone.repository.questionnaire.calendar.MonthsRepositrory;
import livelihoodzone.repository.questionnaire.county.LzCropProductionResponsesRepository;
import livelihoodzone.repository.questionnaire.county.seasonal_calendar.*;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHazardsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHungerPatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
import livelihoodzone.service.retrofit.reports.zonelevel.seasonal_calendar.LzSeasonMonthsDataSetRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static livelihoodzone.common.Constants.*;
import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class LzSeasonalCalendarDataSetService {

    @Autowired
    LzSeasonMonthsRepository lzSeasonMonthsRepository;

    @Autowired
    LzSeasonsRepository lzSeasonsRepository;

    @Autowired
    MonthsRepositrory monthsRepositrory;

    @Autowired
    CropsRepository cropsRepository;

    @Autowired
    LzCropProductionResponsesRepository lzCropProductionResponsesRepository;

    @Autowired
    LivestockMigrationMonthsRepository livestockMigrationMonthsRepository;

    @Autowired
    LivestockMigrationTypesRepository livestockMigrationTypesRepository;

    @Autowired
    HighLowMediumScaleRepository highLowMediumScaleRepository;

    @Autowired
    LzMilkProductionRepository lzMilkProductionRepository;

    @Autowired
    LzCalvingRepository lzCalvingRepository;

    @Autowired
    LzKiddingRepository lzKiddingRepository;

    @Autowired
    LzFoodPricesRepository lzFoodPricesRepository;

    @Autowired
    LzLivestockPricesRepository lzLivestockPricesRepository;

    @Autowired
    LzAgricultureCasualLabourAvailabilityRepository lzAgricultureCasualLabourAvailabilityRepository;

    @Autowired
    LzNonAgricCasualLabourAvailabilityRepository lzNonAgricCasualLabourAvailabilityRepository;

    @Autowired
    LzCasualLabourWagesRepository lzCasualLabourWagesRepository;

    @Autowired
    LzRemittancesRepository lzRemittancesRepository;

    @Autowired
    LzFishingMonthsRepository lzFishingMonthsRepository;

    @Autowired
    LzMarketAccessMonthsRepository lzMarketAccessMonthsRepository;

    @Autowired
    LzDiseaseOutBreakMonthsRepository lzDiseaseOutBreakMonthsRepository;

    @Autowired
    LzWaterStressMonthsRepository lzWaterStressMonthsRepository;

    @Autowired
    LzConflictRisksMonthsRepository lzConflictRisksMonthsRepository;

    @Autowired
    LzCeremonyMonthsRepository lzCeremonyMonthsRepository;

    @Autowired
    LzLeanSeasonMonthsRepository lzLeanSeasonMonthsRepository;

    @Autowired
    LzFoodSecurityAssessmentsRepository lzFoodSecurityAssessmentsRepository;

    @Autowired
    LzLandPreparationMonthsRepository lzLandPreparationMonthsRepository;

    @Autowired
    LzPlantingMonthsRepository lzPlantingMonthsRepository;

    @Autowired
    LzHarvestingMonthsRepository lzHarvestingMonthsRepository;




    public List<LzSeasonMonthsDataSetRetrofitModel> fetchSeasonMonths() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzSeasonMonthsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelSeasonMonths();
        try {
            Response<List<LzSeasonMonthsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzSeasonalCalendarDataSetObject processSeasonalCalendar(List<LzSeasonMonthsDataSetRetrofitModel> list) {
        LzSeasonalCalendarDataSetObject lzSeasonalCalendarDataSetObject = new LzSeasonalCalendarDataSetObject();

        //Set season months
        lzSeasonalCalendarDataSetObject = processSeasonMonths(list, lzSeasonalCalendarDataSetObject);

        return lzSeasonalCalendarDataSetObject;
    }

    public LzSeasonalCalendarDataSetObject processSeasonMonths(List<LzSeasonMonthsDataSetRetrofitModel> list, LzSeasonalCalendarDataSetObject lzSeasonalCalendarDataSetObject) {

        lzSeasonalCalendarDataSetObject.setDrySeasonMonths(processListForAParticularSeason(list, SC_DRY_SEASON));
        lzSeasonalCalendarDataSetObject.setLongRainsMonths(processListForAParticularSeason(list, Constants.SC_LONG_RAINS));
        lzSeasonalCalendarDataSetObject.setShortRainsMonths(processListForAParticularSeason(list, Constants.SC_SHORT_RAINS));
        return lzSeasonalCalendarDataSetObject;
    }

    public List<String> processListForAParticularSeason(List<LzSeasonMonthsDataSetRetrofitModel> list, int seasonCode) {
        List<LzSeasonMonthsDataSetRetrofitModel> processedList = returnListForSpecificSeason(seasonCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (LzSeasonMonthsDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ")" + currentItem.getMonthName() + ",  ";
                counter++;
            } else {
                stringReportList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ")" + currentItem.getMonthName() + ",  ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }


    public List<LzSeasonMonthsDataSetRetrofitModel> clusterSameSeasonMonthsQuestionnaireItemsTogether(List<LzSeasonMonthsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzSeasonMonthsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzSeasonMonthsDataSetRetrofitModel> returnListForSpecificSeason(int seasonCode, List<LzSeasonMonthsDataSetRetrofitModel> allItems) {
        List<LzSeasonMonthsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (LzSeasonMonthsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getLzSeasonCode() == seasonCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameSeasonMonthsQuestionnaireItemsTogether(processedList);
    }


    public WgCropProductionResponseItem extractFullCropProfile(int cropId, int lzQuestionnaireSessionId) {
        WgCropProductionResponseItem wgCropProductionResponseItem = new WgCropProductionResponseItem(true, cropsRepository.findByCropId(cropId));

        //Land Preparation
        List<LzLandPreparationMonthsEntity> landPreparationPeriod = lzLandPreparationMonthsRepository.findByLzQuestionnaireSessionIdAndCropId(lzQuestionnaireSessionId,cropId);
        List<MonthsEntity> landPreparationMonths = new ArrayList<>();
        for (LzLandPreparationMonthsEntity lzLandPreparationMonthsEntity : landPreparationPeriod) {
            landPreparationMonths.add(monthsRepositrory.findByMonthId(lzLandPreparationMonthsEntity.getMonthId()));
        }
        wgCropProductionResponseItem.getLandPreparationPeriod().addAll(landPreparationMonths);


        //Planting
        List<LzPlantingMonthsEntity> plantingPeriod = lzPlantingMonthsRepository.findByLzQuestionnaireSessionIdAndCropId(lzQuestionnaireSessionId,cropId);
        List<MonthsEntity> plantingMonths = new ArrayList<>();
        for (LzPlantingMonthsEntity lzPlantingMonthsEntity : plantingPeriod) {
            plantingMonths.add(monthsRepositrory.findByMonthId(lzPlantingMonthsEntity.getMonthId()));
        }
        wgCropProductionResponseItem.getPlantingPeriod().addAll(plantingMonths);


        //Harvesting period
        List<LzHarvestingMonthsEntity> harvestingPeriod = lzHarvestingMonthsRepository.findByLzQuestionnaireSessionIdAndCropId(lzQuestionnaireSessionId,cropId);
        List<MonthsEntity> harvestingMonths = new ArrayList<>();
        for (LzHarvestingMonthsEntity lzHarvestingMonthsEntity : harvestingPeriod) {
            harvestingMonths.add(monthsRepositrory.findByMonthId(lzHarvestingMonthsEntity.getMonthId()));
        }
        wgCropProductionResponseItem.getHarvestingPeriod().addAll(harvestingMonths);
        return wgCropProductionResponseItem;
    }


    public List<Number> extractUniqueCropsFromCropResponses(List<LzCropProductionResponsesEntity> lzCropProductionResponsesEntityList) {
        List<Number> uniqueCropIds = new ArrayList<>();
        for (LzCropProductionResponsesEntity lzCropProductionResponsesEntity : lzCropProductionResponsesEntityList) {
            if (!doesIdAlreadyExist(uniqueCropIds, lzCropProductionResponsesEntity.getCropId())) {
                uniqueCropIds.add(lzCropProductionResponsesEntity.getCropId());
            }
        }
        return uniqueCropIds;
    }

    public boolean doesIdAlreadyExist(List<Number> uniqueCropIds, int currentId) {
        for (Number number : uniqueCropIds) {
            if (number.intValue() == currentId) {
                return true;
            }
        }
        return false;
    }

    public LzLivelihoodZoneDataObject processSeasonalCalendarChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = new LzSeasonsResponses(true);

        List<LzSeasonMonthsEntity> lzSeasonMonthsEntityList = lzSeasonMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzCropProductionResponsesEntity> lzCropProductionResponsesEntityList = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<Number> uniqueCropIds = extractUniqueCropsFromCropResponses(lzCropProductionResponsesEntityList);

        List<WgCropProductionResponseItem> wgCropProductionResponseItemList = new ArrayList<>();

        for (Number currentCropId : uniqueCropIds) {
            wgCropProductionResponseItemList.add(extractFullCropProfile(currentCropId.intValue(), lzQuestionnaireSessionId));
        }

        livelihoodZoneSeasonsResponses.getWgCropProductionResponseItemList().addAll(wgCropProductionResponseItemList);

        List<LivestockMigrationMonthsEntity> livestockMigrationMonthsEntityList = livestockMigrationMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzMilkProductionEntity> lzMilkProductionEntityList = lzMilkProductionRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzCalvingEntity> lzCalvingEntityList = lzCalvingRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzKiddingEntity> lzKiddingEntityList = lzKiddingRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzFoodPricesEntity> lzFoodPricesEntityList = lzFoodPricesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzLivestockPricesEntity> lzLivestockPricesEntityList = lzLivestockPricesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzAgricultureCasualLabourAvailabilityEntity> lzAgricultureCasualLabourAvailabilityEntities = lzAgricultureCasualLabourAvailabilityRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzNonAgricCasualLabourAvailabilityEntity> lzNonAgricCasualLabourAvailabilityEntities = lzNonAgricCasualLabourAvailabilityRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzCasualLabourWagesEntity> lzCasualLabourWagesEntities = lzCasualLabourWagesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzRemittancesEntity> lzRemittancesEntities = lzRemittancesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzFishingMonthsEntity> lzFishingMonthsEntities = lzFishingMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzMarketAccessMonthsEntity> lzMarketAccessMonthsEntities = lzMarketAccessMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzDiseaseOutBreakMonthsEntity> lzDiseaseOutBreakMonthsEntities = lzDiseaseOutBreakMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzWaterStressMonthsEntity> lzWaterStressMonthsEntities = lzWaterStressMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzConflictRisksMonthsEntity> lzConflictRisksMonthsEntities = lzConflictRisksMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzCeremonyMonthsEntity> lzCeremonyMonthsEntities = lzCeremonyMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzLeanSeasonMonthsEntity> lzLeanSeasonMonthsEntities = lzLeanSeasonMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        List<LzFoodSecurityAssessmentsEntity> lzFoodSecurityAssessmentsEntities = lzFoodSecurityAssessmentsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

        //Seasons
        for (LzSeasonMonthsEntity lzSeasonMonthsEntity : lzSeasonMonthsEntityList) {
            if (lzSeasonsRepository.findByLzSeasonId(lzSeasonMonthsEntity.getLzSeasonId()).getLzSeasonCode() == SC_DRY_SEASON) {
                livelihoodZoneSeasonsResponses.getDry().add(monthsRepositrory.findByMonthId(lzSeasonMonthsEntity.getMonthId()));
            }
            if (lzSeasonsRepository.findByLzSeasonId(lzSeasonMonthsEntity.getLzSeasonId()).getLzSeasonCode() == SC_LONG_RAINS) {
                livelihoodZoneSeasonsResponses.getLongRains().add(monthsRepositrory.findByMonthId(lzSeasonMonthsEntity.getMonthId()));
            }
            if (lzSeasonsRepository.findByLzSeasonId(lzSeasonMonthsEntity.getLzSeasonId()).getLzSeasonCode() == SC_SHORT_RAINS) {
                livelihoodZoneSeasonsResponses.getShortRains().add(monthsRepositrory.findByMonthId(lzSeasonMonthsEntity.getMonthId()));
            }
        }


        //Livestock Migration
        for (LivestockMigrationMonthsEntity livestockMigrationMonthsEntity : livestockMigrationMonthsEntityList) {
            if (livestockMigrationTypesRepository.findByLivestockMigrationTypeId(livestockMigrationMonthsEntity.getLivestockMigrationTypeId()).getLivestockMigrationTypeCode() == LM_IN_MIGRATION) {
                livelihoodZoneSeasonsResponses.getLivestockInMigration().add(monthsRepositrory.findByMonthId(livestockMigrationMonthsEntity.getMonthId()));
            }
            if (livestockMigrationTypesRepository.findByLivestockMigrationTypeId(livestockMigrationMonthsEntity.getLivestockMigrationTypeId()).getLivestockMigrationTypeCode() == LM_OUT_MIGRATION) {
                livelihoodZoneSeasonsResponses.getLivestockOutMigration().add(monthsRepositrory.findByMonthId(livestockMigrationMonthsEntity.getMonthId()));
            }
        }


        //Milk Production
        for (LzMilkProductionEntity lzMilkProductionEntity : lzMilkProductionEntityList) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzMilkProductionEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighMilkProduction().add(monthsRepositrory.findByMonthId(lzMilkProductionEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzMilkProductionEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowMilkProduction().add(monthsRepositrory.findByMonthId(lzMilkProductionEntity.getMonthId()));
            }
        }


        //Calving
        for (LzCalvingEntity lzCalvingEntity : lzCalvingEntityList) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzCalvingEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighCalving().add(monthsRepositrory.findByMonthId(lzCalvingEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzCalvingEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowCalving().add(monthsRepositrory.findByMonthId(lzCalvingEntity.getMonthId()));
            }
        }


        //Kidding
        for (LzKiddingEntity lzKiddingEntity : lzKiddingEntityList) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzKiddingEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighKidding().add(monthsRepositrory.findByMonthId(lzKiddingEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzKiddingEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowKidding().add(monthsRepositrory.findByMonthId(lzKiddingEntity.getMonthId()));
            }
        }


        //Food Prices
        for (LzFoodPricesEntity lzFoodPricesEntity : lzFoodPricesEntityList) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzFoodPricesEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighFoodPrices().add(monthsRepositrory.findByMonthId(lzFoodPricesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzFoodPricesEntity.getScaleMetricId()).getScaleMetricCode() == MEDIUM) {
                livelihoodZoneSeasonsResponses.getMediumFoodPrices().add(monthsRepositrory.findByMonthId(lzFoodPricesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzFoodPricesEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowFoodPrices().add(monthsRepositrory.findByMonthId(lzFoodPricesEntity.getMonthId()));
            }
        }


        //Livestock Prices
        for (LzLivestockPricesEntity lzLivestockPricesEntity : lzLivestockPricesEntityList) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzLivestockPricesEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighLivestockPrices().add(monthsRepositrory.findByMonthId(lzLivestockPricesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzLivestockPricesEntity.getScaleMetricId()).getScaleMetricCode() == MEDIUM) {
                livelihoodZoneSeasonsResponses.getMediumLivestockPrices().add(monthsRepositrory.findByMonthId(lzLivestockPricesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzLivestockPricesEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowLivestockPrices().add(monthsRepositrory.findByMonthId(lzLivestockPricesEntity.getMonthId()));
            }
        }


        //Agricultural casual labour availability
        for (LzAgricultureCasualLabourAvailabilityEntity lzAgricultureCasualLabourAvailabilityEntity : lzAgricultureCasualLabourAvailabilityEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzAgricultureCasualLabourAvailabilityEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighCasualLabourAvailability().add(monthsRepositrory.findByMonthId(lzAgricultureCasualLabourAvailabilityEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzAgricultureCasualLabourAvailabilityEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowCasualLabourAvailability().add(monthsRepositrory.findByMonthId(lzAgricultureCasualLabourAvailabilityEntity.getMonthId()));
            }
        }


        //Non-Agricultural casual labour availability
        for (LzNonAgricCasualLabourAvailabilityEntity lzNonAgricCasualLabourAvailabilityEntity : lzNonAgricCasualLabourAvailabilityEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzNonAgricCasualLabourAvailabilityEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getNonAgricHighCasualLabourAvailability().add(monthsRepositrory.findByMonthId(lzNonAgricCasualLabourAvailabilityEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzNonAgricCasualLabourAvailabilityEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getNonAgricLowCasualLabourAvailability().add(monthsRepositrory.findByMonthId(lzNonAgricCasualLabourAvailabilityEntity.getMonthId()));
            }
        }


        //Casual labour wages
        for (LzCasualLabourWagesEntity lzCasualLabourWagesEntity : lzCasualLabourWagesEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzCasualLabourWagesEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighCasualLabourWages().add(monthsRepositrory.findByMonthId(lzCasualLabourWagesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzCasualLabourWagesEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowCasualLabourWages().add(monthsRepositrory.findByMonthId(lzCasualLabourWagesEntity.getMonthId()));
            }
        }


        //Remittances
        for (LzRemittancesEntity lzRemittancesEntity : lzRemittancesEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzRemittancesEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighRemittances().add(monthsRepositrory.findByMonthId(lzRemittancesEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzRemittancesEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowRemittances().add(monthsRepositrory.findByMonthId(lzRemittancesEntity.getMonthId()));
            }
        }


        //Fishing
        for (LzFishingMonthsEntity lzFishingMonthsEntity : lzFishingMonthsEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzFishingMonthsEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighFish().add(monthsRepositrory.findByMonthId(lzFishingMonthsEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzFishingMonthsEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowFish().add(monthsRepositrory.findByMonthId(lzFishingMonthsEntity.getMonthId()));
            }
        }


        //Market access
        for (LzMarketAccessMonthsEntity lzMarketAccessMonthsEntity : lzMarketAccessMonthsEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzMarketAccessMonthsEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighMarketAccess().add(monthsRepositrory.findByMonthId(lzMarketAccessMonthsEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzMarketAccessMonthsEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowMarketAccess().add(monthsRepositrory.findByMonthId(lzMarketAccessMonthsEntity.getMonthId()));
            }
        }


        //Disease outbreak
        for (LzDiseaseOutBreakMonthsEntity lzDiseaseOutBreakMonthsEntity : lzDiseaseOutBreakMonthsEntities) {
            if (highLowMediumScaleRepository.findByScaleMetricId(lzDiseaseOutBreakMonthsEntity.getScaleMetricId()).getScaleMetricCode() == HIGH) {
                livelihoodZoneSeasonsResponses.getHighDiseaseOutbreak().add(monthsRepositrory.findByMonthId(lzDiseaseOutBreakMonthsEntity.getMonthId()));
            }
            if (highLowMediumScaleRepository.findByScaleMetricId(lzDiseaseOutBreakMonthsEntity.getScaleMetricId()).getScaleMetricCode() == LOW) {
                livelihoodZoneSeasonsResponses.getLowDiseaseOutbreak().add(monthsRepositrory.findByMonthId(lzDiseaseOutBreakMonthsEntity.getMonthId()));
            }
        }

        //Water Stress
        for (LzWaterStressMonthsEntity lzWaterStressMonthsEntity : lzWaterStressMonthsEntities) {
            livelihoodZoneSeasonsResponses.getWaterStress().add(monthsRepositrory.findByMonthId(lzWaterStressMonthsEntity.getMonthId()));
        }

        //Conflict risks
        for (LzConflictRisksMonthsEntity lzConflictRisksMonthsEntity : lzConflictRisksMonthsEntities) {
            livelihoodZoneSeasonsResponses.getConflictRisks().add(monthsRepositrory.findByMonthId(lzConflictRisksMonthsEntity.getMonthId()));
        }

        //Ceremonies
        for (LzCeremonyMonthsEntity lzCeremonyMonthsEntity : lzCeremonyMonthsEntities) {
            livelihoodZoneSeasonsResponses.getCeremonies().add(monthsRepositrory.findByMonthId(lzCeremonyMonthsEntity.getMonthId()));
        }


        //Lean seasons
        for (LzLeanSeasonMonthsEntity lzLeanSeasonMonthsEntity : lzLeanSeasonMonthsEntities) {
            livelihoodZoneSeasonsResponses.getLeanSeasons().add(monthsRepositrory.findByMonthId(lzLeanSeasonMonthsEntity.getMonthId()));
        }

        //Food security assessment
        for (LzFoodSecurityAssessmentsEntity lzFoodSecurityAssessmentsEntity : lzFoodSecurityAssessmentsEntities) {
            livelihoodZoneSeasonsResponses.getFoodSecurityAssessments().add(monthsRepositrory.findByMonthId(lzFoodSecurityAssessmentsEntity.getMonthId()));
        }


        lzLivelihoodZoneDataObject.setLivelihoodZoneSeasonsResponses(livelihoodZoneSeasonsResponses);
        return lzLivelihoodZoneDataObject;
    }
}
