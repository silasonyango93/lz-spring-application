package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.hunger.HungerPatternsResponses;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.service.reports.zonal.ZoneLevelChartsService;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.HUNGER_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;

@Service
public class HungerPatternsExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(HUNGER_PATTERNS);
        sheet.setColumnWidth(0,18000);
        sheet.setColumnWidth(1,13000);
        Row titleRow = sheet.createRow(rowNum);

        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        createCell(titleRow, 0, lzLivelihoodZoneDataObject.getLivelihoodZoneName().toUpperCase(), titleStyle);

        Row headerRow = sheet.createRow(rowNum + 2);

        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(headerRow, 0, "Season", tableHeaderStyle);
        createCell(headerRow, 1, "Years of widespread hunger(Out of ten) ", tableHeaderStyle);

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

    private XSSFWorkbook writeDataLines(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int rowCount, XSSFWorkbook workbook) {

        HungerPatternsResponses hungerPatternsResponses = lzLivelihoodZoneDataObject.getHungerPatternsResponses();
        XSSFSheet sheet = workbook.getSheet(HUNGER_PATTERNS);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);

        //Long rains season(MAM)
        Row riversRow = sheet.createRow(rowCount++);
        createCell(riversRow, 0, "Long rains season(MAM)", style);
        createCell(riversRow, 1, hungerPatternsResponses.getLongRainsPeriod(), style);

        //Between the end of Long rains and begin of Short Rains Season
        Row endLongBeginShortRow = sheet.createRow(rowCount++);
        createCell(endLongBeginShortRow, 0, "Between the end of Long rains and begin of Short Rains Season", style);
        createCell(endLongBeginShortRow, 1, hungerPatternsResponses.getEndLongBeginShort(), style);

        //Short Rains Season (OND)
        Row shortRainsRow = sheet.createRow(rowCount++);
        createCell(shortRainsRow, 0, "Short Rains Season (OND)", style);
        createCell(shortRainsRow, 1, hungerPatternsResponses.getShortRainsPeriod(), style);

        //Between the end of short rains and beginning of long rains
        Row endShortBeginLongRow = sheet.createRow(rowCount++);
        createCell(endShortBeginLongRow, 0, "Short Rains Season (OND)", style);
        createCell(endShortBeginLongRow, 1, hungerPatternsResponses.getEndShortBeginLong(), style);

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,7);
        int rowNum = 0;
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,lzLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(lzLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 22;
        }
        return workbook;
    }
}
