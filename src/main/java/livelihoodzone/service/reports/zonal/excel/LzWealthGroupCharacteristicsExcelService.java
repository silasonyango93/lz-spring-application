package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WEALTH_GROUP_CHARACTERISTICS;

@Service
public class LzWealthGroupCharacteristicsExcelService {
    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(WEALTH_GROUP_CHARACTERISTICS);
        sheet.setColumnWidth(0,18000);
        sheet.setColumnWidth(1,18000);
        sheet.setColumnWidth(2,18000);
        sheet.setColumnWidth(3,18000);
        Row titleRow = sheet.createRow(rowNum);

        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        createCell(titleRow, 0, lzLivelihoodZoneDataObject.getLivelihoodZoneName().toUpperCase(), titleStyle);

        Row headerRow = sheet.createRow(rowNum + 2);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        font.setBold(true);
        style.setFont(font);

        createCell(headerRow, 0, "Very Poor Characteristics", style);
        createCell(headerRow, 1, "Poor Characteristics", style);
        createCell(headerRow, 2, "Medium Characteristics", style);
        createCell(headerRow, 3, "Better Off Characteristics", style);

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

        WealthGroupCharectaristicsResponses wealthGroupCharacteriticsResponses = lzLivelihoodZoneDataObject.getWealthGroupCharectariticsResponses();
        XSSFSheet sheet = workbook.getSheet(WEALTH_GROUP_CHARACTERISTICS);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);

        List<String> veryPoorCharacteristics = wealthGroupCharacteriticsResponses.getVeryPoorCharectaristics();
        List<String> poorCharacteristics = wealthGroupCharacteriticsResponses.getPoorCharectaristics();
        List<String> mediumCharacteristics = wealthGroupCharacteriticsResponses.getMediumCharectaristics();
        List<String> betterOffCharacteristics = wealthGroupCharacteriticsResponses.getBetterOffCharectaristics();

        int maxSize = veryPoorCharacteristics.size();

        if (poorCharacteristics.size() > maxSize) {
            maxSize = poorCharacteristics.size();
        }
        if (mediumCharacteristics.size() > maxSize) {
            maxSize = mediumCharacteristics.size();
        }
        if (betterOffCharacteristics.size() > maxSize) {
            maxSize = betterOffCharacteristics.size();
        }


        for (int i = 0; i <= maxSize; i++) {
            Row row = sheet.createRow(rowCount++);
            createCell(row, 0, returnACharacteristic(veryPoorCharacteristics, i), style);
            createCell(row, 1, returnACharacteristic(poorCharacteristics, i), style);
            createCell(row, 2, returnACharacteristic(mediumCharacteristics, i), style);
            createCell(row, 3, returnACharacteristic(betterOffCharacteristics, i), style);
        }


        return workbook;
    }


    public String returnACharacteristic(List<String> characteristics, int index) {
        if (characteristics.size() > index) {
            return characteristics.get(index);
        }
        return "";
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(WEALTH_GROUP_CHARACTERISTICS);
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,1);
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(sheet.getLastRowNum() + 10,lzLivelihoodZoneDataObject,workbook);
            workbook = writeDataLines(lzLivelihoodZoneDataObject, sheet.getLastRowNum() + 1 ,workbook);
        }
        return workbook;
    }
}
