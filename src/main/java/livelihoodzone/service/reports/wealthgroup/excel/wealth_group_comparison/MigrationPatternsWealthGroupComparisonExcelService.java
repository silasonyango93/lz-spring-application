package livelihoodzone.service.reports.wealthgroup.excel.wealth_group_comparison;

import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.service.reports.wealthgroup.WealthGroupChartsService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;

@Service
public class MigrationPatternsWealthGroupComparisonExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,20000);
        sheet.setColumnWidth(1,10000);

        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(titleRow, 0, wgLivelihoodZoneDataObject.getWealthGroupName().toUpperCase(), titleStyle);
        createCell(tableHeaderRow, 0, "Migration Pattern", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Percent", tableHeaderStyle);
        return workbook;
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        //sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private XSSFWorkbook writeDataLines(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int rowCount, XSSFWorkbook workbook, String sheetName) {
        MigrationPatternResponses settlementAndmigrationPatterns = wgLivelihoodZoneDataObject.getSettlementAndmigrationPatterns();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);

        //Fully Nomadic (no fixed abode, don’t settle)
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Fully Nomadic (no fixed abode, don’t settle)", style);
        createCell(livestockRow, 1, settlementAndmigrationPatterns.getFullyNomadic(), style);

        //Semi Nomadic (nomadic for part of year but have fixed abode)
        Row semiNomadicRow = sheet.createRow(rowCount++);
        createCell(semiNomadicRow, 0, "Semi Nomadic (nomadic for part of year but have fixed abode)", style);
        createCell(semiNomadicRow, 1, settlementAndmigrationPatterns.getSemiNomadic(), style);

        //Occasional Nomadic
        Row occasionalNomadicRow = sheet.createRow(rowCount++);
        createCell(occasionalNomadicRow, 0, "Occasional Nomadic", style);
        createCell(occasionalNomadicRow, 1, settlementAndmigrationPatterns.getOccasionalNomadic(), style);

        //Out-migrant labour (live in LZ but work elsewhere seasonally)
        Row outMigrantRow = sheet.createRow(rowCount++);
        createCell(outMigrantRow, 0, "Out-migrant labour (live in LZ but work elsewhere seasonally)", style);
        createCell(outMigrantRow, 1, settlementAndmigrationPatterns.getOutMigrantLabour(), style);

        //In-migrant Labour (live elsewhere but come to work in the LZ)
        Row inMigrantRow = sheet.createRow(rowCount++);
        createCell(inMigrantRow, 0, "In-migrant Labour (live elsewhere but come to work in the LZ)", style);
        createCell(inMigrantRow, 1, settlementAndmigrationPatterns.getInMigrantLabour(), style);

        //Fully Settled
        Row fullySettledRow = sheet.createRow(rowCount++);
        createCell(fullySettledRow, 0, "Fully Settled", style);
        createCell(fullySettledRow, 1, settlementAndmigrationPatterns.getFullysettled(), style);

        //Internally displaced (settled in temporary accommodation, cannot return to their usual homes)
        Row internallyDisplacedRow = sheet.createRow(rowCount++);
        createCell(internallyDisplacedRow, 0, "Internally displaced (settled in temporary accommodation, cannot return to their usual homes)", style);
        createCell(internallyDisplacedRow, 1, settlementAndmigrationPatterns.getInternallyDisplaced(), style);


        return workbook;
    }

    public XSSFWorkbook processData(int countyId,int livelihoodZoneId, XSSFWorkbook workbook, String sectionName) {

        String sheetName = sectionName != null ? sectionName : MIGRATION_PATTERNS;

        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChartByLivelihoodZone(countyId,livelihoodZoneId,7);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook,sheetName);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook,sheetName);
            rowNum = rowNum + 19;
        }
        return workbook;
    }

}
