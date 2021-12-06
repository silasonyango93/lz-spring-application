package livelihoodzone.service.reports.wealthgroup.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.*;

@Service
public class WealthGroupExcelService {

    @Autowired
    MainSourcesOfFoodAndIncomeExcelService mainSourcesOfFoodAndIncomeExcelService;

    @Autowired
    FoodConsumptionPercentageExcelService foodConsumptionPercentageExcelService;

    @Autowired
    WgCropProductionExcelService wgCropProductionExcelService;

    @Autowired
    LivestockOwnershipExcelService livestockOwnershipExcelService;

    @Autowired
    LivestockContributionExcelService livestockContributionExcelService;

    @Autowired
    LabourPatternsExcelService labourPatternsExcelService;

    @Autowired
    ExpenditurePatternsExcelService expenditurePatternsExcelService;

    @Autowired
    MigrationPatternsExcelService migrationPatternsExcelService;

    @Autowired
    WgConstraintsExcelService wgConstraintsExcelService;

    private XSSFWorkbook workbook;

    public void processData(int countyId, int wealthGroupId) {
        workbook.createSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);
        workbook.createSheet(FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME);
        workbook.createSheet(CROP_CONTRIBUTION);
        workbook.createSheet(LIVESTOCK_OWNERSHIP);
        workbook.createSheet(LIVESTOCK_CONTRIBUTION);
        workbook.createSheet(LABOUR_PATTERNS);
        workbook.createSheet(EXPENDITURE_PATTERNS);
        workbook.createSheet(MIGRATION_PATTERNS);
        workbook.createSheet(WG_CONSTRAINTS);

        workbook = mainSourcesOfFoodAndIncomeExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = foodConsumptionPercentageExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = wgCropProductionExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = livestockOwnershipExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = livestockContributionExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = labourPatternsExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = expenditurePatternsExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = migrationPatternsExcelService.processData(countyId,wealthGroupId,workbook);
        workbook = wgConstraintsExcelService.processData(countyId,wealthGroupId,workbook);
    }


    public void export(HttpServletResponse response, int countyId, int wealthGroupId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId,wealthGroupId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
