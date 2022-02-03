package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.AnimalsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
public class LivestockOwnershipUpdateExcelHelper {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

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

            Sheet sheet = workbook.getSheet(LIVESTOCK_OWNERSHIP);

            //Cattle
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.LOCAL_CATTLE).getAnimalId(),
                    sheet.getRow(4).getCell(1).getNumericCellValue()
            ));

            //Goats
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.GOATS).getAnimalId(),
                    sheet.getRow(5).getCell(1).getNumericCellValue()
            ));

            //Sheep
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.SHEEP).getAnimalId(),
                    sheet.getRow(6).getCell(1).getNumericCellValue()
            ));

            //Donkeys
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DONKEYS).getAnimalId(),
                    sheet.getRow(7).getCell(1).getNumericCellValue()
            ));

            //Camels
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.CAMELS).getAnimalId(),
                    sheet.getRow(8).getCell(1).getNumericCellValue()
            ));

            //Pigs
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.PIGS).getAnimalId(),
                    sheet.getRow(9).getCell(1).getNumericCellValue()
            ));

            //Chicken
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.LOCAL_CHICKEN).getAnimalId(),
                    sheet.getRow(10).getCell(1).getNumericCellValue()
            ));

            //Ducks
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DUCKS).getAnimalId(),
                    sheet.getRow(11).getCell(1).getNumericCellValue()
            ));

            //BeeHives
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.BEE_HIVES).getAnimalId(),
                    sheet.getRow(12).getCell(1).getNumericCellValue()
            ));

            //Fish Ponds
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.FISH_PONDS).getAnimalId(),
                    sheet.getRow(13).getCell(1).getNumericCellValue()
            ));

            //Improved Cattle
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.IMPROVED_CATTLE).getAnimalId(),
                    sheet.getRow(14).getCell(1).getNumericCellValue()
            ));

            //Improved Chicken
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.IMPROVED_CHICKEN).getAnimalId(),
                    sheet.getRow(15).getCell(1).getNumericCellValue()
            ));

            //Fish Cages
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.FISH_CAGES).getAnimalId(),
                    sheet.getRow(16).getCell(1).getNumericCellValue()
            ));

            //Dairy Cattle
            wgAveAnimalNoPerHouseholdRepository.save(new WgAveAnimalNoPerHouseholdEntity(
                    wgQuestionnaireSessionId,
                    animalsRepository.findByAnimalCode(Constants.DAIRY_CATTLE).getAnimalId(),
                    sheet.getRow(17).getCell(1).getNumericCellValue()
            ));

            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
