package livelihoodzone.service.reports.wealthgroup.excel.wealth_group_comparison;

import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.*;

@Service
public class WealthGroupComparisonPerZoneExcelService {

    private XSSFWorkbook workbook;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

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

        workbook.createSheet(determineZoneSheetName(livelihoodZoneId));
        if (questionnaireSectionCode == 1) {
            workbook = mainSourcesOfFoodAndIncomeWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 2) {
            workbook = foodConsumptionPercentageWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 3) {
            workbook = wgCropProductionWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 4) {
            workbook = livestockOwnershipWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 5) {
            workbook = labourPatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 6) {
            workbook = expenditurePatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 7) {
            workbook = migrationPatternsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 8) {
            workbook = wgConstraintsWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }
        if (questionnaireSectionCode == 10) {
            workbook = livestockContributionWealthGroupComparisonExcelService.processData(countyId, livelihoodZoneId, workbook, determineZoneSheetName(livelihoodZoneId));
        }


    }


    public String determineZoneSheetName(int livelihoodZoneId) {
        if (livelihoodZoneId == 15) {
            return PASTORAL_SHEET_NAME;
        }
        if (livelihoodZoneId == 16) {
            return AGRO_PASTORAL_SHEET_NAME;
        }
        if (livelihoodZoneId == 18) {
            return MIXED_FARMING_SHEET_NAME;
        }
        if (livelihoodZoneId == 19) {
            return MIXED_FOOD_CROPPING_SHEET_NAME;
        }
        if (livelihoodZoneId == 20) {
            return BUSINESS_TRADE_SHEET_NAME;
        }
        if (livelihoodZoneId == 21) {
            return FORMAL_UNSKILLED_SHEET_NAME;
        }
        if (livelihoodZoneId == 22) {
            return CASH_CROPPING_SHEET_NAME;
        }
        if (livelihoodZoneId == 23) {
            return MARGINAL_MIXED_SHEET_NAME;
        }
        if (livelihoodZoneId == 24) {
            return CASUAL_WAGED_SHEET_NAME;
        }
        if (livelihoodZoneId == 25) {
            return IRRIGATED_CROPPING_SHEET_NAME;
        }
        if (livelihoodZoneId == 26) {
            return MIXED_FARMING_HORTICULTURE_SHEET_NAME;
        }
        if (livelihoodZoneId == 27) {
            return LIVESTOCK_FARMING_EX_PASTORALISM_SHEET_NAME;
        }
        if (livelihoodZoneId == 28) {
            return FISHING_SHEET_NAME;
        }
        if (livelihoodZoneId == 29) {
            return MIXED_FARMING_DAIRY_SHEET_NAME;
        }
        return PROTECTED_AREAS_SHEET_NAME;
    }


    public void export(HttpServletResponse response, int countyId, int questionnaireSectionCode) throws IOException {
        this.workbook = new XSSFWorkbook();

        List<CountyLivelihoodZonesAssignmentEntity> protectedAreas = countyLivelihoodZonesAssignmentRepository.findByCountyIdAndLivelihoodZoneId(countyId,17);

        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);

        if (!protectedAreas.isEmpty()) {
            countyLivelihoodZonesAssignmentEntityList.remove(protectedAreas.get(0));
        }

        for (CountyLivelihoodZonesAssignmentEntity countyLivelihoodZonesAssignmentEntity : countyLivelihoodZonesAssignmentEntityList) {
            processData(countyId,countyLivelihoodZonesAssignmentEntity.getLivelihoodZoneId(), questionnaireSectionCode);
        }


        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
