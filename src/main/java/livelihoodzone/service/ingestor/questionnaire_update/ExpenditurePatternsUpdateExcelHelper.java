package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.EXPENDITURE_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
@Transactional
public class ExpenditurePatternsUpdateExcelHelper {

    @Autowired
    WgExpenditureItemsRepository wgExpenditureItemsRepository;

    @Autowired
    WgExpenditurePercentagesRepository wgExpenditurePercentagesRepository;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public void processExcelQuestionnaire(MultipartFile file, int wgQuestionnaireSessionId) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheet(EXPENDITURE_PATTERNS);


            //Maize and maize flour
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MAIZE_AND_MAIZE_FLOUR).getExpenditureItemId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue()
            ));

            //Other cereals
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OTHER_CEREALS).getExpenditureItemId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue()
            ));

            //Pulses
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_PULSES).getExpenditureItemId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue()
            ));

            //Roots and tubers
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_ROOTS_AND_ROOT_TUBERS).getExpenditureItemId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue()
            ));

            //Vegetables and fruits
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_VEGETABLES_AND_FRUITS).getExpenditureItemId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue()
            ));

            //Fish and sea food
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_FISH_AND_SEA_FOOD).getExpenditureItemId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue()
            ));

            //Meat
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MEAT).getExpenditureItemId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue()
            ));

            //Milk
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MILK).getExpenditureItemId(),
                    sheet.getRow(11).getCell(1).getNumericCellValue()
            ));

            //Eggs
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_EGGS).getExpenditureItemId(),
                    sheet.getRow(12).getCell(1).getNumericCellValue()
            ));

            //Oils and fats
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OILS_AND_FATS).getExpenditureItemId(),
                    sheet.getRow(13).getCell(1).getNumericCellValue()
            ));

            //Other foods
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_OTHER_FOODS).getExpenditureItemId(),
                    sheet.getRow(14).getCell(1).getNumericCellValue()
            ));

            //School fees
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SCHOOL_FEES).getExpenditureItemId(),
                    sheet.getRow(17).getCell(1).getNumericCellValue()
            ));

            //Drugs and medical care
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_DRUGS_AND_MEDICAL_CARE).getExpenditureItemId(),
                    sheet.getRow(18).getCell(1).getNumericCellValue()
            ));

            //Clothing and beauty products
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_CLOTHING_BEAUTY_PRODUCTS).getExpenditureItemId(),
                    sheet.getRow(19).getCell(1).getNumericCellValue()
            ));

            //House rent
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_HOUSE_RENT).getExpenditureItemId(),
                    sheet.getRow(20).getCell(1).getNumericCellValue()
            ));

            //Communication expenses
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_COMMUNICATION_EXPENSES).getExpenditureItemId(),
                    sheet.getRow(21).getCell(1).getNumericCellValue()
            ));

            //Farm inputs
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_FARM_INPUTS).getExpenditureItemId(),
                    sheet.getRow(22).getCell(1).getNumericCellValue()
            ));

            //Livestock drugs
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LIVESTOCK_DRUGS).getExpenditureItemId(),
                    sheet.getRow(31).getCell(1).getNumericCellValue()
            ));


            //Purchase of water
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_PURCHASE_OF_WATER).getExpenditureItemId(),
                    sheet.getRow(32).getCell(1).getNumericCellValue()
            ));

            //Soaps and other detergents
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SOAPS_AND_OTHER_DETERGENTS).getExpenditureItemId(),
                    sheet.getRow(33).getCell(1).getNumericCellValue()
            ));


            //Hiring farm labour
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_HIRINNG_FARM_LABOUR).getExpenditureItemId(),
                    sheet.getRow(34).getCell(1).getNumericCellValue()
            ));


            //Travel expenses
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_TRAVEL_EXPENSES).getExpenditureItemId(),
                    sheet.getRow(23).getCell(1).getNumericCellValue()
            ));

            //Leisure and entertainment
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LEISURE_AND_ENTERTAINMENT).getExpenditureItemId(),
                    sheet.getRow(24).getCell(1).getNumericCellValue()
            ));

            //Electricity bills
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_ELECTRICITY_BILLS).getExpenditureItemId(),
                    sheet.getRow(25).getCell(1).getNumericCellValue()
            ));

            //Social obligation
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SOCIAL_OBLIGATION).getExpenditureItemId(),
                    sheet.getRow(26).getCell(1).getNumericCellValue()
            ));

            //Milling costs
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_MILLING_COSTS).getExpenditureItemId(),
                    sheet.getRow(27).getCell(1).getNumericCellValue()
            ));

            //Cooking fuel
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_COOKING_FUEL).getExpenditureItemId(),
                    sheet.getRow(28).getCell(1).getNumericCellValue()
            ));

            //Saving and investments
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_SAVING_AND_INVESTMENTS).getExpenditureItemId(),
                    sheet.getRow(29).getCell(1).getNumericCellValue()
            ));

            //Loan repayment
            wgExpenditurePercentagesRepository.save(new WgExpenditurePercentagesEntity(
                    wgQuestionnaireSessionId,
                    wgExpenditureItemsRepository.findByExpenditureItemCode(Constants.EXP_LOAN_REPAYMENTS).getExpenditureItemId(),
                    sheet.getRow(30).getCell(1).getNumericCellValue()
            ));

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
