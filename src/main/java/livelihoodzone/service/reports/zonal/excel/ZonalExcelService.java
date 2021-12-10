package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionExcelExporterService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.*;

@Service
public class ZonalExcelService {

    @Autowired
    LzWealthGroupDistributionExcelExporterService lzWealthGroupDistributionExcelExporterService;

    @Autowired
    WaterSourcesExcelService waterSourcesExcelService;

    @Autowired
    MarketsExcelService marketsExcelService;

    @Autowired
    SocietyAndEthnicityExcelService societyAndEthnicityExcelService;

    @Autowired
    HungerPatternsExcelService hungerPatternsExcelService;

    @Autowired
    HazardExcelService hazardExcelService;

    @Autowired
    SeasonalCalendarExcelService seasonalCalendarExcelService;

    private XSSFWorkbook workbook;

    public void processData(int countyId) {
        workbook.createSheet(WEALTH_GROUP_POPULATION_DISTRIBUTION);
        workbook.createSheet(MAIN_WATER_SOURCES);
        workbook.createSheet(MARKETS_EXCEL_SHEET_NAME);
        workbook.createSheet(ETHNIC_GROUPS_EXCEL_SHEET_NAME);
        workbook.createSheet(HUNGER_PATTERNS);
        workbook.createSheet(LZ_HAZARDS);
        workbook.createSheet(SEASONAL_CALENDAR);

        workbook = lzWealthGroupDistributionExcelExporterService.processData(countyId,workbook);
        workbook = waterSourcesExcelService.processData(countyId,workbook);
        workbook = marketsExcelService.processData(countyId,workbook);
        workbook = societyAndEthnicityExcelService.processData(countyId,workbook);
        workbook = hungerPatternsExcelService.processData(countyId,workbook);
        workbook = hazardExcelService.processData(countyId,workbook);
        workbook = seasonalCalendarExcelService.processData(countyId,workbook);
    }

    public void export(HttpServletResponse response, int countyId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
