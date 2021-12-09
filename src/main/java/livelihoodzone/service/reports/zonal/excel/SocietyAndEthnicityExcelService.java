package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.ethnicgroup.EthnicityResponseItem;
import livelihoodzone.dto.reports.zonal.charts.EthnicityResponseObject;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.ETHNIC_GROUPS_EXCEL_SHEET_NAME;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;

@Service
public class SocietyAndEthnicityExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(ETHNIC_GROUPS_EXCEL_SHEET_NAME);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,10000);
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

        createCell(headerRow, 0, "Ethnic Group", tableHeaderStyle);
        createCell(headerRow, 1, "Percentage", tableHeaderStyle);

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

        EthnicityResponseObject ethnicityResponseObject = lzLivelihoodZoneDataObject.getEthnicityResponseObject();
        XSSFSheet sheet = workbook.getSheet(ETHNIC_GROUPS_EXCEL_SHEET_NAME);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        List<EthnicityResponseItem> ethnicGroupResponseList = ethnicityResponseObject.getEthnicGroupResponseList();

        if (ethnicGroupResponseList != null && !ethnicGroupResponseList.isEmpty()) {
            for (EthnicityResponseItem ethnicityResponseItem : ethnicGroupResponseList) {
                Row riversRow = sheet.createRow(rowCount++);
                createCell(riversRow, 0, ethnicityResponseItem.getEthnicGroupModel().getEthnicGroupName(), style);
                createCell(riversRow, 1, ethnicityResponseItem.getPopulationPercentage(), style);
            }
        }

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,6);
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
