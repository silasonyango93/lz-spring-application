package livelihoodzone.service.reports.wealthgroup.animal_ownership;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions.LivestockContributionResponses;
import livelihoodzone.dto.reports.wealthgroup.WgAnimalContributionDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.WgLivestockOwnershipDataSetObject;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.reports.wealthgroup.*;
import org.apache.tomcat.util.bcel.classfile.Constant;
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
public class AnimalOwnershipService {

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WgAnimalContributionsRepository wgAnimalContributionsRepository;

    public List<WgAnimalOwnershipRetrofitModel> fetchWealthGroupAnimalOwnership(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgAnimalOwnershipRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupLivestockOwnership(countyId, questionnaireTypeId);
        try {
            Response<List<WgAnimalOwnershipRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgLivestockOwnershipDataSetObject processLivestockOwnership(List<WgAnimalOwnershipRetrofitModel> list) {
        List<String> livestockOwnershipStringReportList = new ArrayList<>();
        List<WgAnimalOwnershipRetrofitModel> processedList = clusterSameAnimalOwnershipQuestionnaireItemsTogether(list);


        int currentQuestionnaireSessionId = processedList.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";
        int counter = 1;

        for (WgAnimalOwnershipRetrofitModel currentItem : processedList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + counter + ") " + currentItem.getAnimalName() + " -> " + currentItem.getAverageNumber() + ", ";
                counter++;
            } else {
                livestockOwnershipStringReportList.add(currentReportString);
                counter = 1;
                currentReportString = counter + ") " + currentItem.getAnimalName() + " -> " + currentItem.getAverageNumber() + ", ";
                counter++;
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        livestockOwnershipStringReportList.add(currentReportString);

        return new WgLivestockOwnershipDataSetObject(livestockOwnershipStringReportList);
    }

    public List<WgLivestockContributionRetrofitModel> returnListForSpecificAnimalType(int animalCode, List<WgLivestockContributionRetrofitModel> allItems) {
        List<WgLivestockContributionRetrofitModel> processedList = new ArrayList<>();

        for (WgLivestockContributionRetrofitModel currentItem : allItems) {
            if (currentItem.getAnimalCode() == animalCode) {
                processedList.add(currentItem);
            }
        }
        return clusterLivestockContributionSameAnimalOwnershipQuestionnaireItemsTogether(processedList);
    }

    public List<WgAnimalOwnershipRetrofitModel> clusterSameAnimalOwnershipQuestionnaireItemsTogether(List<WgAnimalOwnershipRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgAnimalOwnershipRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<WgLivestockContributionRetrofitModel> clusterLivestockContributionSameAnimalOwnershipQuestionnaireItemsTogether(List<WgLivestockContributionRetrofitModel> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(WgLivestockContributionRetrofitModel::getWgQuestionnaireSessionId))
                .collect(Collectors.toList());
    }


    public List<WgLivestockContributionRetrofitModel> fetchWealthGroupAnimalContribution(int countyId, int questionnaireTypeId) {
        WealthGroupReportRetrofitService wealthGroupReportRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(WealthGroupReportRetrofitService.class);
        Call<List<WgLivestockContributionRetrofitModel>> callSync = wealthGroupReportRetrofitService.fetchWealthGroupLivestockContribution(countyId, questionnaireTypeId);
        try {
            Response<List<WgLivestockContributionRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public WgAnimalContributionDataSetObject processLivestockContribution(List<WgLivestockContributionRetrofitModel> list) {
        List<String> localCattle = processAnimalTypeData(list, Constants.LOCAL_CATTLE);
        List<String> dairyCattle = processAnimalTypeData(list, Constants.DAIRY_CATTLE);
        List<String> improvedCattle = processAnimalTypeData(list, Constants.IMPROVED_CATTLE);
        List<String> goats = processAnimalTypeData(list, Constants.GOATS);
        List<String> sheep = processAnimalTypeData(list, Constants.SHEEP);
        List<String> donkeys = processAnimalTypeData(list, Constants.DONKEYS);
        List<String> camels = processAnimalTypeData(list, Constants.CAMELS);
        List<String> pigs = processAnimalTypeData(list, Constants.PIGS);
        List<String> chicken = processAnimalTypeData(list, Constants.LOCAL_CHICKEN);
        List<String> improvedChicken = processAnimalTypeData(list, Constants.IMPROVED_CHICKEN);
        List<String> ducks = processAnimalTypeData(list, Constants.DUCKS);
        List<String> beeHives = processAnimalTypeData(list, Constants.BEE_HIVES);
        List<String> fishPonds = processAnimalTypeData(list, Constants.FISH_PONDS);
        List<String> fishCages = processAnimalTypeData(list, Constants.FISH_CAGES);


        return new WgAnimalContributionDataSetObject(
                localCattle,
                dairyCattle,
                improvedCattle,
                goats,
                sheep,
                donkeys,
                camels,
                pigs,
                chicken,
                improvedChicken,
                ducks,
                beeHives,
                fishPonds,
                fishCages
        );
    }

    public List<String> processAnimalTypeData(List<WgLivestockContributionRetrofitModel> list, int animalCode) {
        List<WgLivestockContributionRetrofitModel> processedList = returnListForSpecificAnimalType(animalCode, list);
        List<String> reportString = new ArrayList<>();
        int currentQuestionnaireSessionId = processedList.get(0).getWgQuestionnaireSessionId();
        String currentReportString = "";

        for (WgLivestockContributionRetrofitModel currentItem : processedList) {
            if (currentItem.getWgQuestionnaireSessionId() == currentQuestionnaireSessionId) {
                currentReportString = currentReportString + "Income rank -> " + currentItem.getIncomeContributionRank() + "  Income approx percentage -> " + currentItem.getIncomeContributionApproxPercentage() + "  Consumption rank -> " + currentItem.getConsumptionContributionRank() + "  Consumption approx percentage -> " + currentItem.getConsumptionContributionApproxPercentage() + " ";
            } else {
                reportString.add(currentReportString);
                currentReportString = "Income rank -> " + currentItem.getIncomeContributionRank() + "  Income approx percentage -> " + currentItem.getIncomeContributionApproxPercentage() + "  Consumption rank -> " + currentItem.getConsumptionContributionRank() + "  Consumption approx percentage -> " + currentItem.getConsumptionContributionApproxPercentage() + " ";
                currentQuestionnaireSessionId = currentItem.getWgQuestionnaireSessionId();
            }
        }
        reportString.add(currentReportString);
        return reportString;
    }


    public WgLivelihoodZoneDataObject processLivestockOwnershipChart(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses = new LivestockPoultryOwnershipResponses();
        List<WgAveAnimalNoPerHouseholdEntity> wgAveAnimalNoPerHouseholdEntityList = wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgAveAnimalNoPerHouseholdEntity wgAveAnimalNoPerHouseholdEntity : wgAveAnimalNoPerHouseholdEntityList) {
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CATTLE) {
                livestockPoultryOwnershipResponses.setCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.GOATS) {
                livestockPoultryOwnershipResponses.setGoats(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.SHEEP) {
                livestockPoultryOwnershipResponses.setSheep(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DONKEYS) {
                livestockPoultryOwnershipResponses.setDonkeys(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.CAMELS) {
                livestockPoultryOwnershipResponses.setCamels(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.PIGS) {
                livestockPoultryOwnershipResponses.setPigs(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CHICKEN) {
                livestockPoultryOwnershipResponses.setChicken(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DUCKS) {
                livestockPoultryOwnershipResponses.setDucks(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.BEE_HIVES) {
                livestockPoultryOwnershipResponses.setBeeHives(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.FISH_PONDS) {
                livestockPoultryOwnershipResponses.setFishPonds(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CATTLE) {
                livestockPoultryOwnershipResponses.setImprovedCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CHICKEN) {
                livestockPoultryOwnershipResponses.setImprovedChicken(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.FISH_CAGES) {
                livestockPoultryOwnershipResponses.setFishCages(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DAIRY_CATTLE) {
                livestockPoultryOwnershipResponses.setDairyCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
        }

        wgLivelihoodZoneDataObject.setLivestockAndPoultryOwnership(livestockPoultryOwnershipResponses);
        return wgLivelihoodZoneDataObject;
    }


    public WgLivelihoodZoneDataObject processLivestockOwnershipByLivestock(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId, int livestockCode) {
        LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses = new LivestockPoultryOwnershipResponses();
        List<WgAveAnimalNoPerHouseholdEntity> wgAveAnimalNoPerHouseholdEntityList = wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgAveAnimalNoPerHouseholdEntity wgAveAnimalNoPerHouseholdEntity : wgAveAnimalNoPerHouseholdEntityList) {
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CATTLE && livestockCode == Constants.LOCAL_CATTLE) {
                livestockPoultryOwnershipResponses.setCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.GOATS && livestockCode == Constants.GOATS) {
                livestockPoultryOwnershipResponses.setGoats(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.SHEEP && livestockCode == Constants.SHEEP) {
                livestockPoultryOwnershipResponses.setSheep(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DONKEYS && livestockCode == Constants.DONKEYS) {
                livestockPoultryOwnershipResponses.setDonkeys(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.CAMELS && livestockCode == Constants.CAMELS) {
                livestockPoultryOwnershipResponses.setCamels(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.PIGS && livestockCode == Constants.PIGS) {
                livestockPoultryOwnershipResponses.setPigs(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CHICKEN && livestockCode == Constants.LOCAL_CHICKEN) {
                livestockPoultryOwnershipResponses.setChicken(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DUCKS && livestockCode == Constants.DUCKS) {
                livestockPoultryOwnershipResponses.setDucks(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.BEE_HIVES && livestockCode == Constants.BEE_HIVES) {
                livestockPoultryOwnershipResponses.setBeeHives(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.FISH_PONDS && livestockCode == Constants.FISH_PONDS) {
                livestockPoultryOwnershipResponses.setFishPonds(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CATTLE && livestockCode == Constants.IMPROVED_CATTLE) {
                livestockPoultryOwnershipResponses.setImprovedCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CHICKEN && livestockCode == Constants.IMPROVED_CHICKEN) {
                livestockPoultryOwnershipResponses.setImprovedChicken(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.FISH_CAGES && livestockCode == Constants.FISH_CAGES) {
                livestockPoultryOwnershipResponses.setFishCages(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
            if (animalsRepository.findByAnimalId(wgAveAnimalNoPerHouseholdEntity.getAnimalId()).getAnimalCode() == Constants.DAIRY_CATTLE && livestockCode == Constants.DAIRY_CATTLE) {
                livestockPoultryOwnershipResponses.setDairyCattle(wgAveAnimalNoPerHouseholdEntity.getAverageNumber());
            }
        }
        wgLivelihoodZoneDataObject.setLivestockAndPoultryOwnership(livestockPoultryOwnershipResponses);
        return wgLivelihoodZoneDataObject;
    }


    public WgLivelihoodZoneDataObject processLivestockContributionChart(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int questionnaireSessionId) {
        LivestockContributionResponses livestockContributionResponses = new LivestockContributionResponses(true);
        List<WgAnimalContributionsEntity> wgAnimalContributionsEntityList = wgAnimalContributionsRepository.findByWgQuestionnaireSessionId(questionnaireSessionId);

        for (WgAnimalContributionsEntity wgAnimalContributionsEntity : wgAnimalContributionsEntityList) {
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CATTLE) {
                livestockContributionResponses.getCattle().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getCattle().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getCattle().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getCattle().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.GOATS) {
                livestockContributionResponses.getGoats().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getGoats().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getGoats().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getGoats().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.SHEEP) {
                livestockContributionResponses.getSheep().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getSheep().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getSheep().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getSheep().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.DONKEYS) {
                livestockContributionResponses.getDonkeys().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getDonkeys().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getDonkeys().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getDonkeys().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.CAMELS) {
                livestockContributionResponses.getCamels().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getCamels().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getCamels().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getCamels().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.PIGS) {
                livestockContributionResponses.getPigs().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getPigs().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getPigs().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getPigs().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.LOCAL_CHICKEN) {
                livestockContributionResponses.getChicken().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getChicken().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getChicken().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getChicken().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.DUCKS) {
                livestockContributionResponses.getDucks().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getDucks().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getDucks().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getDucks().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.BEE_HIVES) {
                livestockContributionResponses.getBeeHives().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getBeeHives().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getBeeHives().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getBeeHives().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.FISH_PONDS) {
                livestockContributionResponses.getFishPonds().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getFishPonds().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getFishPonds().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getFishPonds().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CATTLE) {
                livestockContributionResponses.getImprovedCattle().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getImprovedCattle().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getImprovedCattle().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getImprovedCattle().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.IMPROVED_CHICKEN) {
                livestockContributionResponses.getImprovedChicken().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getImprovedChicken().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getImprovedChicken().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getImprovedChicken().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.FISH_CAGES) {
                livestockContributionResponses.getFishCages().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getFishCages().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getFishCages().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getFishCages().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
            if (animalsRepository.findByAnimalId(wgAnimalContributionsEntity.getAnimalId()).getAnimalCode() == Constants.DAIRY_CATTLE) {
                livestockContributionResponses.getDairyCattle().getIncomeRank().setActualValue(wgAnimalContributionsEntity.getIncomeContributionRank());
                livestockContributionResponses.getDairyCattle().getIncomePercentage().setActualValue(wgAnimalContributionsEntity.getIncomeContributionApproxPercentage());
                livestockContributionResponses.getDairyCattle().getConsumptionRank().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionRank());
                livestockContributionResponses.getDairyCattle().getConsumptionPercentage().setActualValue(wgAnimalContributionsEntity.getConsumptionContributionApproxPercentage());
            }
        }
        wgLivelihoodZoneDataObject.setLivestockContributions(livestockContributionResponses);
        return wgLivelihoodZoneDataObject;
    }
}
