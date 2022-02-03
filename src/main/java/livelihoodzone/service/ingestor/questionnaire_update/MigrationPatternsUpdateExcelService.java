package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.MigrationPatternsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternsPercentagesRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.EXPENDITURE_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;

@Service
@Transactional
public class MigrationPatternsUpdateExcelService {

    @Autowired
    MigrationPatternsRepository migrationPatternsRepository;

    @Autowired
    WgMigrationPatternsPercentagesRepository wgMigrationPatternsPercentagesRepository;

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

            Sheet sheet = workbook.getSheet(MIGRATION_PATTERNS);


            //Fully nomadic
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_FULLY_NOMADIC).getMigrationPatternId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue()
            ));

            //Semi nomadic
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_SEMI_NOMADIC).getMigrationPatternId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue()
            ));

            //Occasional nomadic
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_OCCASIONAL_NOMADIC).getMigrationPatternId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue()
            ));

            //Out-migrant labour
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_OUT_MIGRANT_LABOUR).getMigrationPatternId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue()
            ));

            //In-migrant labour
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_IN_MIGRANT_LABOUR).getMigrationPatternId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue()
            ));

            //Fully settled
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_FULLY_SETTLED).getMigrationPatternId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue()
            ));

            //Internally displaced
            wgMigrationPatternsPercentagesRepository.save(new WgMigrationPatternPercentagesEntity(
                    wgQuestionnaireSessionId,
                    migrationPatternsRepository.findByMigrationPatternCode(Constants.MGR_INTERNALLY_DISPLACED).getMigrationPatternId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue()
            ));
            

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
