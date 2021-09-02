package livelihoodzone.service.questionnaire.wealthgroup.constraints;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.constraints.ConstraintsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.constraints.SmallEnterpriseIncomeConstraintsResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.ConIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.IncomeConstraintsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeConstraintsService {

    @Autowired
    IncomeConstraintsRepository incomeConstraintsRepository;

    @Autowired
    ConIncomeSourcesRepository conIncomeSourcesRepository;

    @Autowired
    WgIncomeConstraintRankRepository wgIncomeConstraintRankRepository;

    public void saveIncomeConstraints(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        ConstraintsResponses constraintsResponses = wealthGroupQuestionnaireRequestDto.getConstraintsResponses();

        //Waged labour
        /******************************************************************************************************************/


        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_LOW_EDUCATION).getIncomeConstraintId(),
                constraintsResponses.getWagedLabourIncomeConstraintsResponses().getLowEducation()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_POOR_HEALTH).getIncomeConstraintId(),
                constraintsResponses.getWagedLabourIncomeConstraintsResponses().getPoorHealth()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_TOO_FEW_JOBS).getIncomeConstraintId(),
                constraintsResponses.getWagedLabourIncomeConstraintsResponses().getFewJobs()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_TOO_MUCH_FARM_TIME).getIncomeConstraintId(),
                constraintsResponses.getWagedLabourIncomeConstraintsResponses().getTooMuchFarmTime()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_LOW_AVERAGE_WAGE_RATES).getIncomeConstraintId(),
                constraintsResponses.getWagedLabourIncomeConstraintsResponses().getLowAverageWageRates()
        ));


        /******************************************************************************************************************/



        //Crop production
        /******************************************************************************************************************/

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_SMALL_LAND_HOLDINGS).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getSmallLandHoldings()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_CREDIT).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLackOfCredit()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_HIGH_INPUT_COSTS).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getHighInputCost()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_LAND_FERTILITY).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLowLandFertility()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_RELIABLE_WATER).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLackOfReliableWater()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_TECHNICAL_SKILLS).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLowTechnicalSkills()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_QUALITY_SEED_STOCK).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLowQualitySeed()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_MARKET_ACCESS).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLackOfMarketAccess()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_ENDEMIC_CROP_PESTS_DISEASES).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getEndemicCropPests()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_AGRICULTURAL_EXTENSION_SERVICES).getIncomeConstraintId(),
                constraintsResponses.getCropProductionIncomeConstraintsResponses().getLackOfAgricExtensions()
        ));

        /******************************************************************************************************************/




        //Livestock production
        /******************************************************************************************************************/


        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_PASTURE).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLackOfPasture()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_ANIMAL_DRINKING_WATER).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLackOfAnimalDrinkingWater()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LOW_YIELDING_ANIMALS).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLowYieldingAnimal()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_HIGH_COST_VETERINARY_DRUGS).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getCostlyVeterinaryDrugs()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_ENDEMIC_LIVESTOCK_PESTS_DISEASES).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLivestockPestsAndDiseases()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_MARKET).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLackofMarket()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_INSECURITY).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getInsecurity()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LOW_TECHNICAL_SKILLS_KNOWLEDGE).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLowTechnicalSkillsKnowledge()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_UNFAVOURABLE_CLIMATE).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getUnfavourableClimate()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_LIVESTOCK_EXTENSION_SERVICES).getIncomeConstraintId(),
                constraintsResponses.getLivestockProductionIncomeConstraintsResponses().getLackOfLivestockExtensionServices()
        ));

        /******************************************************************************************************************/




        //Fishing
        /******************************************************************************************************************/

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LOW_FISH_STOCKS).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getLowFishStocks()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LOW_FISH_PRICE).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getPoorMarket()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LACK_OF_EQUIPMENT).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getLackOfEquipment()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_TOO_MUCH_COMPETITION).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getExtremeCompetition()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LACK_OF_EXPERTISE).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getLackOfExpertise()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_RESTRICTIONS_ON_FISHING_RIGHTS).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getFishingRightsRestrictions()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_INADEQUATE_COLD_STORAGE_FACILITIES).getIncomeConstraintId(),
                constraintsResponses.getFishingIncomeConstraintsResponses().getInadequateColdStorageFacilities()
        ));


        /******************************************************************************************************************/




        //Natural resources
        /******************************************************************************************************************/

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_DECLINING_NATURAL_RESOURCES).getIncomeConstraintId(),
                constraintsResponses.getNaturalResourceIncomeConstraintsResponses().getDecliningNaturalResources()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_TOO_MUCH_POPULATION_PRESSURE).getIncomeConstraintId(),
                constraintsResponses.getNaturalResourceIncomeConstraintsResponses().getPopulationPressure()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_RESTRICTIONS_RIGHTS_TO_EXPLOIT_NR).getIncomeConstraintId(),
                constraintsResponses.getNaturalResourceIncomeConstraintsResponses().getNaturalresourceExploitationRights()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_LOW_VALUE_NR_BASED_PRODUCTS).getIncomeConstraintId(),
                constraintsResponses.getNaturalResourceIncomeConstraintsResponses().getLowValueNrBasedProducts()
        ));

        /******************************************************************************************************************/




        //Small enterprise
        /******************************************************************************************************************/
        SmallEnterpriseIncomeConstraintsResponses sm = constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses();
        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_CAPITAL).getIncomeConstraintId(),
                constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses().getLackOfCapital()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_TOO_MUCH_RED_TAPE).getIncomeConstraintId(),
                constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses().getTooMuchRedTape()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_TOO_MANY_TAXES).getIncomeConstraintId(),
                constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses().getTooManyTaxes()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_ACCESS_TO_MARKETS).getIncomeConstraintId(),
                constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses().getLackOfAccessToMarket()
        ));

        wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_EXPERTISE).getIncomeConstraintId(),
                constraintsResponses.getSmallEnterpriseIncomeConstraintsResponses().getLackOfExpertise()
        ));

        /******************************************************************************************************************/

    }
}
