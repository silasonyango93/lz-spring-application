package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.WgCropContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution.WgCropContributionsRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.CROP_CONTRIBUTION;

@Service
public class CropContributionUpdateExcelService {

    @Autowired
    WgCropContributionsRepository wgCropContributionsRepository;

    @Autowired
    CropsRepository cropsRepository;

    public void processExcelQuestionnaire(MultipartFile file, int wgQuestionnaireSessionId) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheet(CROP_CONTRIBUTION);

            List<WgCropContributionsEntity> wgCropContributionsEntityList = new ArrayList<>();

            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 3;

            while (rows.hasNext()) {

                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 3) {
                    rowNumber++;
                    continue;
                }

                wgCropContributionsEntityList.add(new WgCropContributionsEntity(
                        wgQuestionnaireSessionId,
                        cropsRepository.findByCropName(currentRow.getCell(0).getStringCellValue()).getCropId(),
                        (int) currentRow.getCell(1).getNumericCellValue(),
                        currentRow.getCell(2).getNumericCellValue(),
                        (int) currentRow.getCell(3).getNumericCellValue(),
                        currentRow.getCell(4).getNumericCellValue()
                ));


            }


            wgCropContributionsRepository.saveAll(wgCropContributionsEntityList);


            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
