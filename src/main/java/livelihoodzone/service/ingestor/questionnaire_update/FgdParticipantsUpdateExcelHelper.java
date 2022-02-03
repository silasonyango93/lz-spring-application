package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.WgCropContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.fgd_participants.FgdParticipantsEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants.FgdParticipantsRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.CROP_CONTRIBUTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WG_FGD_PARTICIPANTS;

@Service
@Transactional
public class FgdParticipantsUpdateExcelHelper {

    @Autowired
    FgdParticipantsRepository fgdParticipantsRepository;

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

            Sheet sheet = workbook.getSheet(WG_FGD_PARTICIPANTS);

            List<FgdParticipantsEntity> fgdParticipantsEntityList = new ArrayList<>();

            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 3;

            while (rows.hasNext()) {

                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 3) {
                    rowNumber++;
                    continue;
                }

                fgdParticipantsEntityList.add(new FgdParticipantsEntity(
                        wgQuestionnaireSessionId,
                        currentRow.getCell(0).getStringCellValue(),
                        determineAgeBand(currentRow.getCell(1).getStringCellValue()),
                        currentRow.getCell(2).getNumericCellValue() == 1 ? 2 : 1,
                        (int) currentRow.getCell(3).getNumericCellValue(),
                        (int) currentRow.getCell(4).getNumericCellValue(),
                        (int) currentRow.getCell(5).getNumericCellValue()
                ));


            }


            fgdParticipantsRepository.saveAll(fgdParticipantsEntityList);


            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public int determineAgeBand(String bandString) {
        if (bandString.trim().equals("A")) {
            return 1;
        } else if (bandString.trim().equals("B")) {
            return 2;
        } else if (bandString.trim().equals("C")) {
            return 3;
        } else if (bandString.trim().equals("D")) {
            return 4;
        } else return 5;

    }

}
