package livelihoodzone.service.reports.wealthgroup.excel.coutry_files;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
public class WgCountryFileExcelService {

    @Autowired
    LivestockOwnershipCountryFileExcelService livestockOwnershipCountryFileExcelService;

    @Autowired
    MainSourcesOfFoodAndIncomeCountryFileExcelService mainSourcesOfFoodAndIncomeCountryFileExcelService;

    private XSSFWorkbook workbook;

    public void processData( int wealthGroupId, int questionnaireSectionCode) {
        if (questionnaireSectionCode == 4) {
            workbook.createSheet(LIVESTOCK_OWNERSHIP);
            workbook = livestockOwnershipCountryFileExcelService.processData(wealthGroupId,workbook,null);
        }
        if (questionnaireSectionCode == 1) {
            workbook.createSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);
            workbook = mainSourcesOfFoodAndIncomeCountryFileExcelService.processData(wealthGroupId,workbook,null);
        }
    }

    public void export(HttpServletResponse response, int wealthGroupId, int questionnaireSectionCode) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(wealthGroupId,questionnaireSectionCode);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
