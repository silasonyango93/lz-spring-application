package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_CONTRIBUTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;

@Service
@Transactional
public class LivestockContributionUpdateExcelHelper {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WgAnimalContributionsRepository wgAnimalContributionsRepository;

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

            Sheet sheet = workbook.getSheet(LIVESTOCK_CONTRIBUTION);

            //Cattle
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.LOCAL_CATTLE).getAnimalId(),
                    (int) sheet.getRow(4).getCell(1).getNumericCellValue(),
                    sheet.getRow(4).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(4).getCell(3).getNumericCellValue(),
                    sheet.getRow(4).getCell(4).getNumericCellValue()

            ));


            //Goats
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.GOATS).getAnimalId(),
                    (int) sheet.getRow(5).getCell(1).getNumericCellValue(),
                    sheet.getRow(5).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(5).getCell(3).getNumericCellValue(),
                    sheet.getRow(5).getCell(4).getNumericCellValue()

            ));


            //Sheep
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.SHEEP).getAnimalId(),
                    (int) sheet.getRow(6).getCell(1).getNumericCellValue(),
                    sheet.getRow(6).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(6).getCell(3).getNumericCellValue(),
                    sheet.getRow(6).getCell(4).getNumericCellValue()

            ));

            //Donkeys
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DONKEYS).getAnimalId(),
                    (int) sheet.getRow(7).getCell(1).getNumericCellValue(),
                    sheet.getRow(7).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(7).getCell(3).getNumericCellValue(),
                    sheet.getRow(7).getCell(4).getNumericCellValue()

            ));

            //Camels
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.CAMELS).getAnimalId(),
                    (int) sheet.getRow(8).getCell(1).getNumericCellValue(),
                    sheet.getRow(8).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(8).getCell(3).getNumericCellValue(),
                    sheet.getRow(8).getCell(4).getNumericCellValue()

            ));

            //Pigs
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.PIGS).getAnimalId(),
                    (int) sheet.getRow(9).getCell(1).getNumericCellValue(),
                    sheet.getRow(9).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(9).getCell(3).getNumericCellValue(),
                    sheet.getRow(9).getCell(4).getNumericCellValue()

            ));

            //Chicken
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.LOCAL_CHICKEN).getAnimalId(),
                    (int) sheet.getRow(10).getCell(1).getNumericCellValue(),
                    sheet.getRow(10).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(10).getCell(3).getNumericCellValue(),
                    sheet.getRow(10).getCell(4).getNumericCellValue()

            ));

            //Improved Chicken
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.IMPROVED_CHICKEN).getAnimalId(),
                    (int) sheet.getRow(15).getCell(1).getNumericCellValue(),
                    sheet.getRow(15).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(15).getCell(3).getNumericCellValue(),
                    sheet.getRow(15).getCell(4).getNumericCellValue()

            ));

            //Ducks
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DUCKS).getAnimalId(),
                    (int) sheet.getRow(11).getCell(1).getNumericCellValue(),
                    sheet.getRow(11).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(11).getCell(3).getNumericCellValue(),
                    sheet.getRow(11).getCell(4).getNumericCellValue()

            ));

            //Bee Hives
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.BEE_HIVES).getAnimalId(),
                    (int) sheet.getRow(12).getCell(1).getNumericCellValue(),
                    sheet.getRow(12).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(12).getCell(3).getNumericCellValue(),
                    sheet.getRow(12).getCell(4).getNumericCellValue()

            ));

            //Fish Ponds
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.FISH_PONDS).getAnimalId(),
                    (int) sheet.getRow(13).getCell(1).getNumericCellValue(),
                    sheet.getRow(13).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(13).getCell(3).getNumericCellValue(),
                    sheet.getRow(13).getCell(4).getNumericCellValue()

            ));


            //Fish Cages
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId(),
                    (int) sheet.getRow(16).getCell(1).getNumericCellValue(),
                    sheet.getRow(16).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(16).getCell(3).getNumericCellValue(),
                    sheet.getRow(16).getCell(4).getNumericCellValue()

            ));


            //Dairy Cattle
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DAIRY_CATTLE).getAnimalId(),
                    (int) sheet.getRow(17).getCell(1).getNumericCellValue(),
                    sheet.getRow(17).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(17).getCell(3).getNumericCellValue(),
                    sheet.getRow(17).getCell(4).getNumericCellValue()

            ));

            //Improved Cattle
            wgAnimalContributionsRepository.save(new WgAnimalContributionsEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.IMPROVED_CATTLE).getAnimalId(),
                    (int) sheet.getRow(14).getCell(1).getNumericCellValue(),
                    sheet.getRow(14).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(14).getCell(3).getNumericCellValue(),
                    sheet.getRow(14).getCell(4).getNumericCellValue()

            ));

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
