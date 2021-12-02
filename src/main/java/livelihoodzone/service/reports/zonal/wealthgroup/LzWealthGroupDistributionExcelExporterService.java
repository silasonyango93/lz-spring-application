package livelihoodzone.service.reports.zonal.wealthgroup;

import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class LzWealthGroupDistributionExcelExporterService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList;

    public LzWealthGroupDistributionExcelExporterService(List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList) {
        this.workbook = new XSSFWorkbook();
        this.lzLivelihoodZoneDataObjectList = lzLivelihoodZoneDataObjectList;
        this.sheet = workbook.createSheet("Wealth Group Population Distribution");
    }

    private void writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject) {

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
        style.setFont(font);

        createCell(headerRow, 0, "VERY POOR RESPONSE", style);
        createCell(headerRow, 1, "Poor Response", style);
        createCell(headerRow, 2, "Medium Response", style);
        createCell(headerRow, 3, "Better Off Response", style);
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

    private void writeDataLines(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int rowCount) {

        WealthGroupPercentageResponse wealthGroupResponse = lzLivelihoodZoneDataObject.getWealthGroupResponse();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);



        Row row = sheet.createRow(rowCount);
        int columnCount = 0;
        createCell(row, columnCount++, wealthGroupResponse.getVerPoorResponse(), style);
        createCell(row, columnCount++, wealthGroupResponse.getPoorResponse(), style);
        createCell(row, columnCount++, wealthGroupResponse.getMediumResponse(), style);
        createCell(row, columnCount++, wealthGroupResponse.getBetterOfResponse(), style);

    }

    public void export(HttpServletResponse response) throws IOException {

        processData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

    public void processData() {
        int rowNum = 0;
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            writeHeaderLine(rowNum,lzLivelihoodZoneDataObject);
            rowNum = rowNum + 4;
            writeDataLines(lzLivelihoodZoneDataObject, rowNum);
            rowNum = rowNum + 10;
        }
    }
}
