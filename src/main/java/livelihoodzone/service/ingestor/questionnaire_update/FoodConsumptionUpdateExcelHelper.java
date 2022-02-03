package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.FoodSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.FoodTypesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

public class FoodConsumptionUpdateExcelHelper {

    @Autowired
    FoodTypesRepository foodTypesRepository;

    @Autowired
    FoodSourcesRepository foodSourcesRepository;

    @Autowired
    WgFoodSourcesResponsesRepository wgFoodSourcesResponsesRepository;

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

            Sheet sheet = workbook.getSheet(FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME);

            //Maize and Posho
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(4).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(4).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MAIZE_AND_POSHO).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(4).getCell(4).getNumericCellValue()
            ));




            //Wheat Barley Rye
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(5).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(5).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHEAT_BARLEY_RYE).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(5).getCell(4).getNumericCellValue()
            ));





            //Sorghum Millet Products
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(6).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(6).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SORGHUM_MILLET_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(6).getCell(4).getNumericCellValue()
            ));




            //Rice And Products
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(7).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(7).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.RICE_AND_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(7).getCell(4).getNumericCellValue()
            ));




            //Beans
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(8).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(8).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.BEANS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(8).getCell(4).getNumericCellValue()
            ));




            //Other Pulses
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(9).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(9).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.OTHER_PULSES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(9).getCell(4).getNumericCellValue()
            ));





            //Vegetables
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(10).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(10).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.VEGETABLES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(10).getCell(4).getNumericCellValue()
            ));




            //Fruits And Berries
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(11).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(11).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(11).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FRUITS_AND_BERRIES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(11).getCell(4).getNumericCellValue()
            ));




            //White Root Tubers
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(12).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(12).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(12).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.WHITE_ROOTS_TUBERS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(12).getCell(4).getNumericCellValue()
            ));




            //Meat
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(13).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(13).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(13).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MEAT).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(13).getCell(4).getNumericCellValue()
            ));




            //Milk And Dairy Products
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(14).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(14).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(14).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.MILKS_AND_DAIRY_PRODUCTS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(14).getCell(4).getNumericCellValue()
            ));





            //Fish And Sea Food
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(15).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(15).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(15).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.FISH_AND_SEA_FOOD).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(15).getCell(4).getNumericCellValue()
            ));





            //Eggs
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(16).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(16).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(16).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.EGGS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(16).getCell(4).getNumericCellValue()
            ));




            //Cooking Fats And Oils
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(17).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(17).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(17).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.COOKING_FATS_AND_OILS).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(17).getCell(4).getNumericCellValue()
            ));




            //Spices and Condiments
            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.OWN_FARM_PRODUCTION).getFoodSourceId(),
                    sheet.getRow(18).getCell(1).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.MARKET_FOOD_PURCHASE).getFoodSourceId(),
                    sheet.getRow(18).getCell(2).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.HUNTING_GATHERING_FISHING_FOOD_SOURCE).getFoodSourceId(),
                    sheet.getRow(18).getCell(3).getNumericCellValue()
            ));

            wgFoodSourcesResponsesRepository.save(new WgFoodSourcesResponsesEntity(
                    wgQuestionnaireSessionId,
                    foodTypesRepository.findByFoodTypeCode(Constants.SPICES_CONDIMENTS_BEVERAGES).getFoodTypeId(),
                    foodSourcesRepository.findByFoodSourceCode(Constants.GIFTS_AND_FOOD_AID).getFoodSourceId(),
                    sheet.getRow(18).getCell(4).getNumericCellValue()
            ));

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
