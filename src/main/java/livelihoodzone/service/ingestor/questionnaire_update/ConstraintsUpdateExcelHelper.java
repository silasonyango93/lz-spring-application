package livelihoodzone.service.ingestor.questionnaire_update;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.wealthgroup.constraints.SmallEnterpriseIncomeConstraintsResponses;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.ConIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.IncomeConstraintsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WG_CONSTRAINTS;

@Service
public class ConstraintsUpdateExcelHelper {

    @Autowired
    IncomeConstraintsRepository incomeConstraintsRepository;

    @Autowired
    ConIncomeSourcesRepository conIncomeSourcesRepository;

    @Autowired
    WgIncomeConstraintRankRepository wgIncomeConstraintRankRepository;

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

            Sheet sheet = workbook.getSheet(WG_CONSTRAINTS);


            //Waged labour
            /******************************************************************************************************************/


            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_LOW_EDUCATION).getIncomeConstraintId(),
                    (int) sheet.getRow(4).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_POOR_HEALTH).getIncomeConstraintId(),
                    (int) sheet.getRow(5).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_TOO_FEW_JOBS).getIncomeConstraintId(),
                    (int) sheet.getRow(6).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_TOO_MUCH_FARM_TIME).getIncomeConstraintId(),
                    (int) sheet.getRow(7).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_WAGED_LABOUR).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_WL_LOW_AVERAGE_WAGE_RATES).getIncomeConstraintId(),
                    (int) sheet.getRow(8).getCell(2).getNumericCellValue()
            ));


            /******************************************************************************************************************/



            //Crop production
            /******************************************************************************************************************/

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_SMALL_LAND_HOLDINGS).getIncomeConstraintId(),
                    (int) sheet.getRow(11).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_CREDIT).getIncomeConstraintId(),
                    (int) sheet.getRow(12).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_HIGH_INPUT_COSTS).getIncomeConstraintId(),
                    (int) sheet.getRow(13).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_LAND_FERTILITY).getIncomeConstraintId(),
                    (int) sheet.getRow(14).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_RELIABLE_WATER).getIncomeConstraintId(),
                    (int) sheet.getRow(15).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_TECHNICAL_SKILLS).getIncomeConstraintId(),
                    (int) sheet.getRow(16).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LOW_QUALITY_SEED_STOCK).getIncomeConstraintId(),
                    (int) sheet.getRow(17).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_MARKET_ACCESS).getIncomeConstraintId(),
                    (int) sheet.getRow(18).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_ENDEMIC_CROP_PESTS_DISEASES).getIncomeConstraintId(),
                    (int) sheet.getRow(19).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_CROP_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_CP_LACK_OF_AGRICULTURAL_EXTENSION_SERVICES).getIncomeConstraintId(),
                    (int) sheet.getRow(20).getCell(2).getNumericCellValue()
            ));

            /******************************************************************************************************************/




            //Livestock production
            /******************************************************************************************************************/


            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_PASTURE).getIncomeConstraintId(),
                    (int) sheet.getRow(23).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_ANIMAL_DRINKING_WATER).getIncomeConstraintId(),
                    (int) sheet.getRow(24).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LOW_YIELDING_ANIMALS).getIncomeConstraintId(),
                    (int) sheet.getRow(25).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_HIGH_COST_VETERINARY_DRUGS).getIncomeConstraintId(),
                    (int) sheet.getRow(26).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_ENDEMIC_LIVESTOCK_PESTS_DISEASES).getIncomeConstraintId(),
                    (int) sheet.getRow(27).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_MARKET).getIncomeConstraintId(),
                    (int) sheet.getRow(28).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_INSECURITY).getIncomeConstraintId(),
                    (int) sheet.getRow(29).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LOW_TECHNICAL_SKILLS_KNOWLEDGE).getIncomeConstraintId(),
                    (int) sheet.getRow(30).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_UNFAVOURABLE_CLIMATE).getIncomeConstraintId(),
                    (int) sheet.getRow(31).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_LIVESTOCK_PRODUCTION).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_LP_LACK_OF_LIVESTOCK_EXTENSION_SERVICES).getIncomeConstraintId(),
                    (int) sheet.getRow(32).getCell(2).getNumericCellValue()
            ));

            /******************************************************************************************************************/




            //Fishing
            /******************************************************************************************************************/

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LOW_FISH_STOCKS).getIncomeConstraintId(),
                    (int) sheet.getRow(35).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LOW_FISH_PRICE).getIncomeConstraintId(),
                    (int) sheet.getRow(36).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LACK_OF_EQUIPMENT).getIncomeConstraintId(),
                    (int) sheet.getRow(37).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_TOO_MUCH_COMPETITION).getIncomeConstraintId(),
                    (int) sheet.getRow(38).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_LACK_OF_EXPERTISE).getIncomeConstraintId(),
                    (int) sheet.getRow(39).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_RESTRICTIONS_ON_FISHING_RIGHTS).getIncomeConstraintId(),
                    (int) sheet.getRow(40).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_FISHING).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_FI_INADEQUATE_COLD_STORAGE_FACILITIES).getIncomeConstraintId(),
                    (int) sheet.getRow(41).getCell(2).getNumericCellValue()
            ));


            /******************************************************************************************************************/




            //Natural resources
            /******************************************************************************************************************/

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_DECLINING_NATURAL_RESOURCES).getIncomeConstraintId(),
                    (int) sheet.getRow(44).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_TOO_MUCH_POPULATION_PRESSURE).getIncomeConstraintId(),
                    (int) sheet.getRow(45).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_RESTRICTIONS_RIGHTS_TO_EXPLOIT_NR).getIncomeConstraintId(),
                    (int) sheet.getRow(46).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_NATURAL_RESOURCES).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_NR_LOW_VALUE_NR_BASED_PRODUCTS).getIncomeConstraintId(),
                    (int) sheet.getRow(47).getCell(2).getNumericCellValue()
            ));

            /******************************************************************************************************************/




            //Small enterprise
            /******************************************************************************************************************/

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_CAPITAL).getIncomeConstraintId(),
                    (int) sheet.getRow(50).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_TOO_MUCH_RED_TAPE).getIncomeConstraintId(),
                    (int) sheet.getRow(51).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_TOO_MANY_TAXES).getIncomeConstraintId(),
                    (int) sheet.getRow(52).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_ACCESS_TO_MARKETS).getIncomeConstraintId(),
                    (int) sheet.getRow(53).getCell(2).getNumericCellValue()
            ));

            wgIncomeConstraintRankRepository.save(new WgIncomeConstraintRankEntity(
                    wgQuestionnaireSessionId,
                    conIncomeSourcesRepository.findByConIncomeSourceCode(Constants.CONIN_SMALL_ENTERPRISE).getConIncomeSourceId(),
                    incomeConstraintsRepository.findByIncomeConstraintCode(Constants.INCO_SE_LACK_OF_EXPERTISE).getIncomeConstraintId(),
                    (int) sheet.getRow(54).getCell(2).getNumericCellValue()
            ));

            /******************************************************************************************************************/


            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
