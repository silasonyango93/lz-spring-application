package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.markets.MarketTransactionsItem;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.charts.MarketTransactionObject;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MARKETS_EXCEL_SHEET_NAME;

@Service
public class MarketsExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

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

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(MARKETS_EXCEL_SHEET_NAME);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,10000);
        sheet.setColumnWidth(4,10000);
        sheet.setColumnWidth(5,10000);
        sheet.setColumnWidth(6,10000);
        sheet.setColumnWidth(7,10000);
        sheet.setColumnWidth(8,10000);
        sheet.setColumnWidth(9,10000);
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



        createCell(headerRow, 0, "Market Name", tableHeaderStyle);
        createCell(headerRow, 1, "Nearest village/town", tableHeaderStyle);
        createCell(headerRow, 2, "Sub-county", tableHeaderStyle);
        createCell(headerRow, 3, "County", tableHeaderStyle);
        createCell(headerRow, 4, "Country", tableHeaderStyle);
        createCell(headerRow, 5, "Livestock trade", tableHeaderStyle);
        createCell(headerRow, 6, "Poultry Trade", tableHeaderStyle);
        createCell(headerRow, 7, "Farm produce trade", tableHeaderStyle);
        createCell(headerRow, 8, "Retail farm inputs", tableHeaderStyle);
        createCell(headerRow, 9, "Labour exchange", tableHeaderStyle);

        return workbook;
    }



    private XSSFWorkbook writeDataLines(LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, int rowCount, XSSFWorkbook workbook) {

        MarketTransactionObject marketTransactionObject = lzLivelihoodZoneDataObject.getMarketTransactionObject();
        XSSFSheet sheet = workbook.getSheet(MARKETS_EXCEL_SHEET_NAME);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        List<MarketTransactionsItem> marketTransactionItems = marketTransactionObject.getMarketTransactionItems();
        if (marketTransactionItems != null && !marketTransactionItems.isEmpty()) {
            for (MarketTransactionsItem marketTransactionsItem : marketTransactionItems) {
                Row riversRow = sheet.createRow(rowCount++);
                createCell(riversRow, 0, marketTransactionsItem.getMarketName(), style);
                createCell(riversRow, 1, marketTransactionsItem.getNearestVillageOrTownName(), style);
                createCell(riversRow, 2, marketTransactionsItem.getSubCounty().getSubCountyName(), style);
                createCell(riversRow, 3, marketTransactionsItem.getCounty().getCountyName(), style);
                createCell(riversRow, 4, marketTransactionsItem.getCountry().getCountryName(), style);
                createCell(riversRow, 5, marketTransactionsItem.isLivestockTrade() ? "Yes" : "No", style);
                createCell(riversRow, 6, marketTransactionsItem.isPoultryTrade() ? "Yes" : "No", style);
                createCell(riversRow, 7, marketTransactionsItem.isFarmProduceTrade() ? "Yes" : "No", style);
                createCell(riversRow, 8, marketTransactionsItem.isRetailFarmInput() ? "Yes" : "No", style);
                createCell(riversRow, 9, marketTransactionsItem.isLabourExchange() ? "Yes" : "No", style);
            }
        }

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,5);
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
