package livelihoodzone.service.reports.wealthgroup.excel.wealth_group_comparison;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.service.reports.wealthgroup.excel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.*;

@Service
public class WealthGroupComparisonExcelService {

    private XSSFWorkbook workbook;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    LivestockOwnershipWealthGroupComparisonExcelService livestockOwnershipWealthGroupComparisonExcelService;

    @Autowired
    MainSourcesOfFoodAndIncomeWealthGroupComparisonExcelService mainSourcesOfFoodAndIncomeWealthGroupComparisonExcelService;

    @Autowired
    FoodConsumptionPercentageWealthGroupComparisonExcelService foodConsumptionPercentageWealthGroupComparisonExcelService;

    @Autowired
    WgCropProductionWealthGroupComparisonExcelService wgCropProductionWealthGroupComparisonExcelService;

    @Autowired
    LivestockContributionWealthGroupComparisonExcelService livestockContributionWealthGroupComparisonExcelService;

    @Autowired
    LabourPatternsWealthGroupComparisonExcelService labourPatternsWealthGroupComparisonExcelService;

    @Autowired
    ExpenditurePatternsWealthGroupComparisonExcelService expenditurePatternsWealthGroupComparisonExcelService;

    @Autowired
    MigrationPatternsWealthGroupComparisonExcelService migrationPatternsWealthGroupComparisonExcelService;

    @Autowired
    WgConstraintsWealthGroupComparisonExcelService wgConstraintsWealthGroupComparisonExcelService;

    public void processData(int countyId,int livelihoodZoneId, int questionnaireSectionCode) {


        if (questionnaireSectionCode == 1) {
            workbook.createSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);
            workbook = mainSourcesOfFoodAndIncomeWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 2) {
            workbook.createSheet(FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME);
            workbook = foodConsumptionPercentageWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 3) {
            workbook.createSheet(CROP_CONTRIBUTION);
            workbook = wgCropProductionWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 4) {
            workbook.createSheet(LIVESTOCK_OWNERSHIP);
            workbook = livestockOwnershipWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 5) {
            workbook.createSheet(LABOUR_PATTERNS);
            workbook = labourPatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 6) {
            workbook.createSheet(EXPENDITURE_PATTERNS);
            workbook = expenditurePatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 7) {
            workbook.createSheet(MIGRATION_PATTERNS);
            workbook = migrationPatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 8) {
            workbook.createSheet(WG_CONSTRAINTS);
            workbook = wgConstraintsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }
        if (questionnaireSectionCode == 10) {
            workbook.createSheet(LIVESTOCK_CONTRIBUTION);
            workbook = livestockContributionWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, null);
        }


    }


    public void export(HttpServletResponse response, int countyId, int livelihoodZoneId, int questionnaireSectionCode) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId,livelihoodZoneId, questionnaireSectionCode);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
