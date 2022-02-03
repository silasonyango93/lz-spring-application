package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesRepository;
import livelihoodzone.util.excel.crops.CropExcelModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Transactional
@Service
public class IncomeSourcesUpdateExcelHelper {

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    CashIncomeSourcesRepository cashIncomeSourcesRepository;

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

            Sheet sheet = workbook.getSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);


            //Livestock Production
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.LIVESTOCK_PRODUCTION).getCashIncomeSourceId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue()
            ));


            //Pasture/Fodder
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.PASTURE_FODDER_PRODUCTION).getCashIncomeSourceId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue()
            ));

            //Poultry Production
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.POULTRY_PRODUCTION).getCashIncomeSourceId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue()
            ));

            //Cash Crop Production
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASH_CROP_PRODUCTION).getCashIncomeSourceId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue()
            ));

            //Food Crop Production
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FOOD_CROP_PRODUCTION).getCashIncomeSourceId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue()
            ));

            //Casual Waged Labour
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.CASUAL_WAGED_LABOUR_INCOME).getCashIncomeSourceId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue()
            ));

            //Formal Waged Labour
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FORMAL_WAGED_LABOUR).getCashIncomeSourceId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue()
            ));

            //Fishing
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FISHING).getCashIncomeSourceId(),
                    sheet.getRow(11).getCell(1).getNumericCellValue()
            ));

            //Hunting and gathering
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.HUNTING_AND_GATHERING).getCashIncomeSourceId(),
                    sheet.getRow(12).getCell(1).getNumericCellValue()
            ));

            //Small Businesses
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SMALL_BUSINESSES).getCashIncomeSourceId(),
                    sheet.getRow(13).getCell(1).getNumericCellValue()
            ));

            //Firewood Collection
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.FIREWOOD_COLLECTION).getCashIncomeSourceId(),
                    sheet.getRow(14).getCell(1).getNumericCellValue()
            ));

            //Petty Trading
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.PETTY_TRADING).getCashIncomeSourceId(),
                    sheet.getRow(15).getCell(1).getNumericCellValue()
            ));

            //Remittance And Gifts
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.REMITTANCE_AND_GIFTS).getCashIncomeSourceId(),
                    sheet.getRow(16).getCell(1).getNumericCellValue()
            ));

            //Bodaboda Transport
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BODABODA_TRANSPORT).getCashIncomeSourceId(),
                    sheet.getRow(17).getCell(1).getNumericCellValue()
            ));

            //Bee Keeping
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.BEE_KEEPING).getCashIncomeSourceId(),
                    sheet.getRow(18).getCell(1).getNumericCellValue()
            ));

            //Sand Harvesting
            wgIncomeSourcesRepository.save(new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.SAND_HARVESTING).getCashIncomeSourceId(),
                    sheet.getRow(19).getCell(1).getNumericCellValue()
            ));


            //Other Income Sources
            WgIncomeSourcesEntity othersObject = new WgIncomeSourcesEntity(
                    wgQuestionnaireSessionId,
                    cashIncomeSourcesRepository.findByCashIncomeSourceCode(Constants.OTHERS_INCOME_SOURCES).getCashIncomeSourceId(),
                    sheet.getRow(20).getCell(1).getNumericCellValue()
            );
            wgIncomeSourcesRepository.save(othersObject);

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
