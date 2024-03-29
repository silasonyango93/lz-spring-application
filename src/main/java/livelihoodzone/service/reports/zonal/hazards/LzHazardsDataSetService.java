package livelihoodzone.service.reports.zonal.hazards;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.county.model.hazards.HazardResponses;
import livelihoodzone.dto.reports.zonal.LzHazardsDataSetObject;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.repository.questionnaire.county.LzHazardResponsesRepository;
import livelihoodzone.repository.questionnaire.county.LzHazardsRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHazardsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzHungerPatternsDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.LzWaterSourceDataSetRetrofitModel;
import livelihoodzone.service.retrofit.reports.zonelevel.ZoneLevelReportRetrofitService;
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
public class LzHazardsDataSetService {

    @Autowired
    LzHazardResponsesRepository lzHazardResponsesRepository;

    @Autowired
    LzHazardsRepository lzHazardsRepository;

    public List<LzHazardsDataSetRetrofitModel> fetchHazardsDataSet() {
        ZoneLevelReportRetrofitService zoneLevelReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(ZoneLevelReportRetrofitService.class);
        Call<List<LzHazardsDataSetRetrofitModel>> callSync = zoneLevelReportRetrofitService.fetchZoneLevelHazards();
        try {
            Response<List<LzHazardsDataSetRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public LzHazardsDataSetObject processHazards(List<LzHazardsDataSetRetrofitModel> list) {
        List<String> animalRustling = processReportsForAParticularHazard(Constants.HZ_ANIMAL_RUSTLING, list);
        List<String> banditry = processReportsForAParticularHazard(Constants.HZ_BANDITRY, list);
        List<String> terrorism = processReportsForAParticularHazard(Constants.HZ_BANDITRY, list);
        List<String> ethnicConflict = processReportsForAParticularHazard(Constants.HZ_TERRORISM, list);
        List<String> politicalConflict = processReportsForAParticularHazard(Constants.HZ_ETHNIC_CONFLICT, list);
        List<String> drought = processReportsForAParticularHazard(Constants.HZ_POLITICAL_CONFLICT, list);
        List<String> livestockPestsDiseases = processReportsForAParticularHazard(Constants.HZ_DROUGHT, list);
        List<String> hailStorms = processReportsForAParticularHazard(Constants.HZ_HAILSTORMS_OR_FROST, list);
        List<String> flooding = processReportsForAParticularHazard(Constants.HZ_FLOODING, list);
        List<String> landSlides = processReportsForAParticularHazard(Constants.HZ_LANDSLIDES, list);
        List<String> highWids = processReportsForAParticularHazard(Constants.HZ_HIGH_WINDS, list);
        List<String> bushFires = processReportsForAParticularHazard(Constants.HZ_BUSH_FIRES, list);
        List<String> cropPests = processReportsForAParticularHazard(Constants.HZ_CROP_PESTS, list);
        List<String> locustInvasion = processReportsForAParticularHazard(Constants.HZ_LOCUST_INVASION, list);
        List<String> cropDiseases = processReportsForAParticularHazard(Constants.HZ_CROP_DISEASES, list);
        List<String> terminalIllness = processReportsForAParticularHazard(Constants.HZ_TERMINAL_ILLNESS, list);
        List<String> malariaOutbreak = processReportsForAParticularHazard(Constants.HZ_MALARIA_OUTBREAK, list);
        List<String> waterBorneDiseases = processReportsForAParticularHazard(Constants.HZ_WATER_BORNE_DISEASE, list);
        List<String> humaWildlifeConflict = processReportsForAParticularHazard(Constants.HZ_HUMAN_WILDLIFE_CONFLICT, list);
        List<String> highFoodPrices = processReportsForAParticularHazard(Constants.HZ_HIGH_FOOD_PRICES, list);
        List<String> marketFoodShortage = processReportsForAParticularHazard(Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET, list);
        List<String> drinkingWaterShortages = processReportsForAParticularHazard(Constants.HZ_DRINKING_WATER_SHORTAGES, list);
        List<String> invasivePlantSpecies = processReportsForAParticularHazard(Constants.HZ_INVASIVE_PLANT_SPECIES, list);
        List<String> others = processReportsForAParticularHazard(Constants.HZ_OTHERS, list);

        return new LzHazardsDataSetObject(
                animalRustling,
                banditry,
                terrorism,
                ethnicConflict,
                politicalConflict,
                drought,
                livestockPestsDiseases,
                hailStorms,
                flooding,
                landSlides,
                highWids,
                bushFires,
                cropPests,
                locustInvasion,
                cropDiseases,
                terminalIllness,
                malariaOutbreak,
                waterBorneDiseases,
                humaWildlifeConflict,
                highFoodPrices,
                marketFoodShortage,
                drinkingWaterShortages,
                invasivePlantSpecies,
                others
        );
    }

    public List<String> processReportsForAParticularHazard(int hazardCode, List<LzHazardsDataSetRetrofitModel> list) {
        List<LzHazardsDataSetRetrofitModel> processedList = returnListForSpecificHazard(hazardCode, list);
        List<String> stringReportList = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getLzQuestionnaireSessionId();
        String currentReportString = "";

        for (LzHazardsDataSetRetrofitModel currentItem : processedList) {
            if (currentItem.getLzQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                if (hazardCode == Constants.HZ_OTHERS && !currentItem.getLzHazardDescription().isEmpty()) {
                    currentReportString = currentReportString + "Description -> " + currentItem.getLzHazardDescription() + "  Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                } else {
                    currentReportString = currentReportString + "Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                }
            } else {
                stringReportList.add(currentReportString);
                if (hazardCode == Constants.HZ_OTHERS && !currentItem.getLzHazardDescription().isEmpty()) {
                    currentReportString = "Description -> " + currentItem.getLzHazardDescription() + "  Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                } else {
                    currentReportString = "Rank -> " + currentItem.getRank() + "  No of years experienced -> " + currentItem.getYearsExperienced();
                }
                currentQuestionnaireSessionId = currentItem.getLzQuestionnaireSessionId();
            }
        }
        stringReportList.add(currentReportString);
        return stringReportList;
    }

    public List<LzHazardsDataSetRetrofitModel> clusterSameQuestionnaireItemsTogether(List<LzHazardsDataSetRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(LzHazardsDataSetRetrofitModel::getLzQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<LzHazardsDataSetRetrofitModel> returnListForSpecificHazard(int hazardCode, List<LzHazardsDataSetRetrofitModel> allItems) {
        List<LzHazardsDataSetRetrofitModel> processedList = new ArrayList<>();

        for (LzHazardsDataSetRetrofitModel currentItem : allItems) {
            if (currentItem.getLzHazardCode() == hazardCode) {
                processedList.add(currentItem);
            }
        }
        return clusterSameQuestionnaireItemsTogether(processedList);
    }


    public LzLivelihoodZoneDataObject processHazardsChart(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId) {
        HazardResponses hazardResponses = new HazardResponses(true);
        List<LzHazardResponsesEntity> lzHazardResponsesEntityList = lzHazardResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        for (LzHazardResponsesEntity lzHazardResponsesEntity : lzHazardResponsesEntityList) {
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_ANIMAL_RUSTLING) {
                hazardResponses.getAnimalRustling().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getAnimalRustling().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_BANDITRY) {
                hazardResponses.getBanditry().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getBanditry().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_TERRORISM) {
                hazardResponses.getTerrorism().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getTerrorism().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_ETHNIC_CONFLICT) {
                hazardResponses.getEthnicConflict().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getEthnicConflict().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_POLITICAL_CONFLICT) {
                hazardResponses.getPoliticalViolence().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getPoliticalViolence().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_DROUGHT) {
                hazardResponses.getDrought().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getDrought().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LIVESTOCK_PESTS_DISEASES) {
                hazardResponses.getLivestockPestsAndDiseases().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getLivestockPestsAndDiseases().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HAILSTORMS_OR_FROST) {
                hazardResponses.getHailstormsOrFrost().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getHailstormsOrFrost().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_FLOODING) {
                hazardResponses.getFlooding().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getFlooding().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LANDSLIDES) {
                hazardResponses.getLandslides().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getLandslides().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HIGH_WINDS) {
                hazardResponses.getHighWindsOrCyclones().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getHighWindsOrCyclones().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_BUSH_FIRES) {
                hazardResponses.getBushFires().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getBushFires().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_CROP_PESTS) {
                hazardResponses.getCropPests().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getCropPests().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LOCUST_INVASION) {
                hazardResponses.getLocustInvasion().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getLocustInvasion().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_CROP_DISEASES) {
                hazardResponses.getCropDiseases().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getCropDiseases().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_TERMINAL_ILLNESS) {
                hazardResponses.getTerminalIllnesses().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getTerminalIllnesses().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_MALARIA_OUTBREAK) {
                hazardResponses.getMalariaPowerOutBreak().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getMalariaPowerOutBreak().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_WATER_BORNE_DISEASE) {
                hazardResponses.getWaterBornDiseases().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getWaterBornDiseases().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HUMAN_WILDLIFE_CONFLICT) {
                hazardResponses.getHumanWildlifeConflict().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getHumanWildlifeConflict().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HIGH_FOOD_PRICES) {
                hazardResponses.getHighFoodPrices().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getHighFoodPrices().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET) {
                hazardResponses.getMarketFoodShortages().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getMarketFoodShortages().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_DRINKING_WATER_SHORTAGES) {
                hazardResponses.getDrinkingWaterShortages().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getDrinkingWaterShortages().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_INVASIVE_PLANT_SPECIES) {
                hazardResponses.getInvasivePlants().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getInvasivePlants().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_OTHERS) {
                hazardResponses.getOthers().setImportanceRank(lzHazardResponsesEntity.getRank());
                hazardResponses.getOthers().setNoExperiencedYears(lzHazardResponsesEntity.getYearsExperienced());
            }
        }
        lzLivelihoodZoneDataObject.setHazardResponses(hazardResponses);
        return lzLivelihoodZoneDataObject;
    }




    public LzLivelihoodZoneDataObject processHazardsChartByHazard(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int lzQuestionnaireSessionId, int hazardCode, int hazardAspectCode) {
        HazardResponses hazardResponses = new HazardResponses();
        List<LzHazardResponsesEntity> lzHazardResponsesEntityList = lzHazardResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        for (LzHazardResponsesEntity lzHazardResponsesEntity : lzHazardResponsesEntityList) {
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_ANIMAL_RUSTLING && hazardCode == Constants.HZ_ANIMAL_RUSTLING) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    //hazardResponses.setParameterValue(Math.pow(lzHazardResponsesEntity.getRank(), -1) * 10);
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_BANDITRY && hazardCode == Constants.HZ_BANDITRY) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_TERRORISM && hazardCode == Constants.HZ_TERRORISM) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_ETHNIC_CONFLICT && hazardCode == Constants.HZ_ETHNIC_CONFLICT) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_POLITICAL_CONFLICT && hazardCode == Constants.HZ_POLITICAL_CONFLICT) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_DROUGHT && hazardCode == Constants.HZ_DROUGHT) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LIVESTOCK_PESTS_DISEASES && hazardCode == Constants.HZ_LIVESTOCK_PESTS_DISEASES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HAILSTORMS_OR_FROST && hazardCode == Constants.HZ_HAILSTORMS_OR_FROST) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_FLOODING && hazardCode == Constants.HZ_FLOODING) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LANDSLIDES && hazardCode == Constants.HZ_LANDSLIDES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HIGH_WINDS && hazardCode == Constants.HZ_HIGH_WINDS) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_BUSH_FIRES && hazardCode == Constants.HZ_BUSH_FIRES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_CROP_PESTS && hazardCode == Constants.HZ_CROP_PESTS) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_LOCUST_INVASION && hazardCode == Constants.HZ_LOCUST_INVASION) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_CROP_DISEASES && hazardCode == Constants.HZ_CROP_DISEASES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_TERMINAL_ILLNESS && hazardCode == Constants.HZ_TERMINAL_ILLNESS) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_MALARIA_OUTBREAK && hazardCode == Constants.HZ_MALARIA_OUTBREAK) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_WATER_BORNE_DISEASE && hazardCode == Constants.HZ_WATER_BORNE_DISEASE) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HUMAN_WILDLIFE_CONFLICT && hazardCode == Constants.HZ_HUMAN_WILDLIFE_CONFLICT) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_HIGH_FOOD_PRICES && hazardCode == Constants.HZ_HIGH_FOOD_PRICES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET && hazardCode == Constants.HZ_SHORTAGE_OF_FOOD_ON_MARKET) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_DRINKING_WATER_SHORTAGES && hazardCode == Constants.HZ_DRINKING_WATER_SHORTAGES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_INVASIVE_PLANT_SPECIES && hazardCode == Constants.HZ_INVASIVE_PLANT_SPECIES) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
            if (lzHazardsRepository.findByLzHazardId(lzHazardResponsesEntity.getLzHazardId()).getLzHazardCode() == Constants.HZ_OTHERS && hazardCode == Constants.HZ_OTHERS) {
                if (hazardAspectCode == Constants.RANK_OF_IMPORTANCE.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getRank());
                }
                if (hazardAspectCode == Constants.NO_OF_YEARS.getHazardAspectCode()) {
                    hazardResponses.setParameterValue(lzHazardResponsesEntity.getYearsExperienced());
                }
            }
        }
        lzLivelihoodZoneDataObject.setHazardResponses(hazardResponses);
        return lzLivelihoodZoneDataObject;
    }
}
