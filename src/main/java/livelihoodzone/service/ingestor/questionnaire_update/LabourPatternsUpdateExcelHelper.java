package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LABOUR_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
@Transactional
public class LabourPatternsUpdateExcelHelper {

    @Autowired
    LivelihoodActivitiesRepository livelihoodActivitiesRepository;

    @Autowired
    WgGenderLivelihoodActivitiesRepository wgGenderLivelihoodActivitiesRepository;

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

            Sheet sheet = workbook.getSheet(LABOUR_PATTERNS);


            //Labour on own farms (crop production)
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LABOUR_OWN_FARM).getLivelihoodActivityId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue(),
                    sheet.getRow(4).getCell(2).getNumericCellValue()
            ));

            //Livestock husbandry
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LIVESTOCK_HUSBANDRY).getLivelihoodActivityId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue(),
                    sheet.getRow(5).getCell(2).getNumericCellValue()
            ));


            //Transport services
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.TRANSPORT_SERVICES).getLivelihoodActivityId(),
                    sheet.getRow(17).getCell(1).getNumericCellValue(),
                    sheet.getRow(17).getCell(2).getNumericCellValue()
            ));


            //Waged labour on other farms
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.WAGED_LABOUR).getLivelihoodActivityId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue(),
                    sheet.getRow(6).getCell(2).getNumericCellValue()
            ));

            //Low-skilled non farm labour (including paid manual.
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LOW_SKILLED_NON_FARM_LABOUR).getLivelihoodActivityId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue(),
                    sheet.getRow(7).getCell(2).getNumericCellValue()
            ));

            //Skilled labor (carpentry, masonry, artisans, )
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.SKILLED_LABOUR).getLivelihoodActivityId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue(),
                    sheet.getRow(8).getCell(2).getNumericCellValue()
            ));

            //Formal employment
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.FORMAL_EMPLOYMENT).getLivelihoodActivityId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue(),
                    sheet.getRow(9).getCell(2).getNumericCellValue()
            ));

            //Hunting and gathering
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.HUNTING_AND_GATHERING_LZ_ACTIVITY).getLivelihoodActivityId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue(),
                    sheet.getRow(10).getCell(2).getNumericCellValue()
            ));

            //Fishing
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.FISHING_LZ_ACTIVITY).getLivelihoodActivityId(),
                    sheet.getRow(11).getCell(1).getNumericCellValue(),
                    sheet.getRow(11).getCell(2).getNumericCellValue()
            ));

            //Trading
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.TRADING_LZ_ACTIVITY).getLivelihoodActivityId(),
                    sheet.getRow(12).getCell(1).getNumericCellValue(),
                    sheet.getRow(12).getCell(2).getNumericCellValue()
            ));

            //Domestic (unpaid) work including childcare
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.DOMESTIC_UNPAID_WORK).getLivelihoodActivityId(),
                    sheet.getRow(13).getCell(1).getNumericCellValue(),
                    sheet.getRow(13).getCell(2).getNumericCellValue()
            ));

            //Begging
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.BEGGING_LZ_ACTIVITY).getLivelihoodActivityId(),
                    sheet.getRow(14).getCell(1).getNumericCellValue(),
                    sheet.getRow(14).getCell(2).getNumericCellValue()
            ));

            //Commercial sex work
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.COMMERCIAL_SEX_WORK).getLivelihoodActivityId(),
                    sheet.getRow(15).getCell(1).getNumericCellValue(),
                    sheet.getRow(15).getCell(2).getNumericCellValue()
            ));

            //Leisure, socializing and entertainment
            wgGenderLivelihoodActivitiesRepository.save(new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.LEISURE_SOCIALIZING_ENTERTAINMENT).getLivelihoodActivityId(),
                    sheet.getRow(16).getCell(1).getNumericCellValue(),
                    sheet.getRow(16).getCell(2).getNumericCellValue()
            ));

            //Others
            WgGenderLivelihoodActivitiesEntity othersObject = new WgGenderLivelihoodActivitiesEntity(
                    wgQuestionnaireSessionId,
                    livelihoodActivitiesRepository.findByLivelihoodActivityCode(Constants.OTHER_LIVELIHOOD_ACTIVITIES).getLivelihoodActivityId(),
                    sheet.getRow(18).getCell(1).getNumericCellValue(),
                    sheet.getRow(18).getCell(2).getNumericCellValue()
            );
            wgGenderLivelihoodActivitiesRepository.save(othersObject);


            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
