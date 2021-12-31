package livelihoodzone.service.reports.wealthgroup.excel.country_files;

import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;

@Service
public class MigrationPatternsCountryFileExcelService {

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row tableHeaderRow = sheet.createRow(rowNum);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,15000);
        sheet.setColumnWidth(2,20000);
        sheet.setColumnWidth(3,10000);


        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(tableHeaderRow, 0, "County", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Livelihood Zone", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Migration Pattern", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Percent", tableHeaderStyle);
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
        createCell(livestockRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(livestockRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(livestockRow, 2, "Fully Nomadic (no fixed abode, don’t settle)", style);
        createCell(livestockRow, 3, settlementAndmigrationPatterns.getFullyNomadic(), style);

        //Semi Nomadic (nomadic for part of year but have fixed abode)
        Row semiNomadicRow = sheet.createRow(rowCount++);
        createCell(semiNomadicRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(semiNomadicRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(semiNomadicRow, 2, "Semi Nomadic (nomadic for part of year but have fixed abode)", style);
        createCell(semiNomadicRow, 3, settlementAndmigrationPatterns.getSemiNomadic(), style);

        //Occasional Nomadic
        Row occasionalNomadicRow = sheet.createRow(rowCount++);
        createCell(occasionalNomadicRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(occasionalNomadicRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(occasionalNomadicRow, 2, "Occasional Nomadic", style);
        createCell(occasionalNomadicRow, 3, settlementAndmigrationPatterns.getOccasionalNomadic(), style);

        //Out-migrant labour (live in LZ but work elsewhere seasonally)
        Row outMigrantRow = sheet.createRow(rowCount++);
        createCell(outMigrantRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(outMigrantRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(outMigrantRow, 2, "Out-migrant labour (live in LZ but work elsewhere seasonally)", style);
        createCell(outMigrantRow, 3, settlementAndmigrationPatterns.getOutMigrantLabour(), style);

        //In-migrant Labour (live elsewhere but come to work in the LZ)
        Row inMigrantRow = sheet.createRow(rowCount++);
        createCell(inMigrantRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(inMigrantRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(inMigrantRow, 2, "In-migrant Labour (live elsewhere but come to work in the LZ)", style);
        createCell(inMigrantRow, 3, settlementAndmigrationPatterns.getInMigrantLabour(), style);

        //Fully Settled
        Row fullySettledRow = sheet.createRow(rowCount++);
        createCell(fullySettledRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(fullySettledRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(fullySettledRow, 2, "Fully Settled", style);
        createCell(fullySettledRow, 3, settlementAndmigrationPatterns.getFullysettled(), style);

        //Internally displaced (settled in temporary accommodation, cannot return to their usual homes)
        Row internallyDisplacedRow = sheet.createRow(rowCount++);
        createCell(internallyDisplacedRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(internallyDisplacedRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(internallyDisplacedRow, 2, "Internally displaced (settled in temporary accommodation, cannot return to their usual homes)", style);
        createCell(internallyDisplacedRow, 3, settlementAndmigrationPatterns.getInternallyDisplaced(), style);


        return workbook;
    }

    public XSSFWorkbook processData(int wealthGroupId, XSSFWorkbook workbook, String sectionName) {


        String sheetName = sectionName != null ? sectionName : MIGRATION_PATTERNS;

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNum = 0;
        workbook = writeHeaderLine(rowNum,workbook, sheetName);
        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {

            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countiesEntity.getCountyId(),wealthGroupId,7);
            for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
                workbook = writeDataLines(wgLivelihoodZoneDataObject, sheet.getLastRowNum() + 1,workbook, sheetName);
            }

        }
        return workbook;
    }
}
