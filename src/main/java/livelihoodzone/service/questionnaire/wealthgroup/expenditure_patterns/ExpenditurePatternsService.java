package livelihoodzone.service.questionnaire.wealthgroup.expenditure_patterns;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.wealthgroup.expenditurepatterns.ExpenditurePatternsResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenditurePatternsService {

    @Autowired
    WgExpenditureItemsRepository wgExpenditureItemsRepository;

    @Autowired
    WgExpenditurePercentagesRepository wgExpenditurePercentagesRepository;

    public void saveExpenditureService(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, WgQuestionnaireSessionEntity questionnaireSession) {
        ExpenditurePatternsResponses expenditurePatternsResponses = wealthGroupQuestionnaireRequestDto.getExpenditurePatternsResponses();

        //Maize and maize flour
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MAIZE_AND_MAIZE_FLOUR).getExpenditureItemId(),
                expenditurePatternsResponses.getMaizeAndMaizeFlour()
        ));

        //Other cereals
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OTHER_CEREALS).getExpenditureItemId(),
                expenditurePatternsResponses.getOtherCereals()
        ));

        //Pulses
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_PULSES).getExpenditureItemId(),
                expenditurePatternsResponses.getPulses()
        ));

        //Roots and tubers
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_ROOTS_AND_ROOT_TUBERS).getExpenditureItemId(),
                expenditurePatternsResponses.getRootsAndTubers()
        ));

        //Vegetables and fruits
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_VEGETABLES_AND_FRUITS).getExpenditureItemId(),
                expenditurePatternsResponses.getVegetablesAndFruits()
        ));

        //Fish and sea food
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_FISH_AND_SEA_FOOD).getExpenditureItemId(),
                expenditurePatternsResponses.getFishandSeaFood()
        ));

        //Meat
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MEAT).getExpenditureItemId(),
                expenditurePatternsResponses.getMeat()
        ));

        //Milk
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MILK).getExpenditureItemId(),
                expenditurePatternsResponses.getMilk()
        ));

        //Eggs
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_EGGS).getExpenditureItemId(),
                expenditurePatternsResponses.getEggs()
        ));

        //Oils and fats
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OILS_AND_FATS).getExpenditureItemId(),
                expenditurePatternsResponses.getOilsAndFats()
        ));

        //Other foods
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OTHER_FOODS).getExpenditureItemId(),
                expenditurePatternsResponses.getOtherFoods()
        ));

        //School fees
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SCHOOL_FEES).getExpenditureItemId(),
                expenditurePatternsResponses.getSchoolFees()
        ));

        //Drugs and medical care
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_DRUGS_AND_MEDICAL_CARE).getExpenditureItemId(),
                expenditurePatternsResponses.getDrugsAndMedicalCare()
        ));

        //Clothing and beauty products
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_CLOTHING_BEAUTY_PRODUCTS).getExpenditureItemId(),
                expenditurePatternsResponses.getClothingAndBeautyProducts()
        ));

        //House rent
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_HOUSE_RENT).getExpenditureItemId(),
                expenditurePatternsResponses.getHouseRent()
        ));

        //Communication expenses
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_COMMUNICATION_EXPENSES).getExpenditureItemId(),
                expenditurePatternsResponses.getCommunicationExpenses()
        ));

        //Farm inputs
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_FARM_INPUTS).getExpenditureItemId(),
                expenditurePatternsResponses.getFarmInputs()
        ));

        //Livestock drugs
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LIVESTOCK_DRUGS).getExpenditureItemId(),
                expenditurePatternsResponses.getLivestockDrugs()
        ));


        //Purchase of water
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_PURCHASE_OF_WATER).getExpenditureItemId(),
                expenditurePatternsResponses.getWaterPurchase()
        ));

        //Soaps and other detergents
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SOAPS_AND_OTHER_DETERGENTS).getExpenditureItemId(),
                expenditurePatternsResponses.getSoaps()
        ));


        //Hiring farm labour
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_HIRINNG_FARM_LABOUR).getExpenditureItemId(),
                expenditurePatternsResponses.getFarmLabour()
        ));


        //Travel expenses
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_TRAVEL_EXPENSES).getExpenditureItemId(),
                expenditurePatternsResponses.getTravelRelatedExpenses()
        ));

        //Leisure and entertainment
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LEISURE_AND_ENTERTAINMENT).getExpenditureItemId(),
                expenditurePatternsResponses.getLeisureAndEntertainment()
        ));

        //Electricity bills
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_ELECTRICITY_BILLS).getExpenditureItemId(),
                expenditurePatternsResponses.getElectricityBills()
        ));

        //Social obligation
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SOCIAL_OBLIGATION).getExpenditureItemId(),
                expenditurePatternsResponses.getSocialObligation()
        ));

        //Milling costs
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MILLING_COSTS).getExpenditureItemId(),
                expenditurePatternsResponses.getMillingCosts()
        ));

        //Cooking fuel
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_COOKING_FUEL).getExpenditureItemId(),
                expenditurePatternsResponses.getCookingFuel()
        ));

        //Saving and investments
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SAVING_AND_INVESTMENTS).getExpenditureItemId(),
                expenditurePatternsResponses.getSavingsAndInvestments()
        ));

        //Loan repayment
        wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                questionnaireSession.getWgQuestionnaireSessionId(),
                wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LOAN_REPAYMENTS).getExpenditureItemId(),
                expenditurePatternsResponses.getLoanRepayments()
        ));
    }
}
