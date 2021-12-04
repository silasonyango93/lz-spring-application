package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
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

@Service
public class MainSourcesOfFoodAndIncomeExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,18000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,20000);

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

        createCell(titleRow, 0, wgLivelihoodZoneDataObject.getLivelihoodZoneName().toUpperCase(), titleStyle);
        createCell(tableHeaderRow, 0, "Cash Income Source", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Percent Of Total Income", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Others Description", tableHeaderStyle);
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

    private XSSFWorkbook writeDataLines(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int rowCount, XSSFWorkbook workbook) {
        IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses = wgLivelihoodZoneDataObject.getIncomeAndFoodSourcesResponses();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME);

        //Livestock Production
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Livestock Production (including meat, milk, hides, skins, and by products/manure", style);
        createCell(livestockRow, 1, incomeAndFoodSourcesResponses.getLivestockProduction(), style);

        //Pasture/Fodder Production
        Row pastureRow = sheet.createRow(rowCount++);
        createCell(pastureRow, 0, "Pasture/Fodder Production", style);
        createCell(pastureRow, 1, incomeAndFoodSourcesResponses.getPastureFodderProduction(), style);

        //Poultry Production including meat and egg production
        Row poultryRow = sheet.createRow(rowCount++);
        createCell(poultryRow, 0, "Poultry Production including meat and egg production", style);
        createCell(poultryRow, 1, incomeAndFoodSourcesResponses.getPoultryProduction(), style);

        //Cash Crop Production
        Row cashCropRow = sheet.createRow(rowCount++);
        createCell(cashCropRow, 0, "Cash Crop Production", style);
        createCell(cashCropRow, 1, incomeAndFoodSourcesResponses.getCashCropProduction(), style);

        //Food Crop Production
        Row foodCropRow = sheet.createRow(rowCount++);
        createCell(foodCropRow, 0, "Food Crop Production", style);
        createCell(foodCropRow, 1, incomeAndFoodSourcesResponses.getFoodCropProduction(), style);

        //Casual /Waged-labour Income
        Row casualWagedLabourRow = sheet.createRow(rowCount++);
        createCell(casualWagedLabourRow, 0, "Casual /Waged-labour Income", style);
        createCell(casualWagedLabourRow, 1, incomeAndFoodSourcesResponses.getCasualOrWagedLabour(), style);

        //Formal Waged Labour including public and private sector employees
        Row formalWagedLabourRow = sheet.createRow(rowCount++);
        createCell(formalWagedLabourRow, 0, "Formal Waged Labour including public and private sector employees", style);
        createCell(formalWagedLabourRow, 1, incomeAndFoodSourcesResponses.getFormalWagedLabour(), style);

        //Fishing (marine or inland)
        Row fishingRow = sheet.createRow(rowCount++);
        createCell(fishingRow, 0, "Fishing (marine or inland)", style);
        createCell(fishingRow, 1, incomeAndFoodSourcesResponses.getFishing(), style);

        //Hunting and Gathering
        Row huntingRow = sheet.createRow(rowCount++);
        createCell(huntingRow, 0, "Hunting and Gathering", style);
        createCell(huntingRow, 1, incomeAndFoodSourcesResponses.getHuntingAndGathering(), style);

        //Small Businesses/own business including crafts, non- farm production/ Brokerage services/ middlemen, beer etc
        Row smallBusiinessRow = sheet.createRow(rowCount++);
        createCell(smallBusiinessRow, 0, "Small Businesses/own business including crafts, non- farm production/ Brokerage services/ middlemen, beer etc", style);
        createCell(smallBusiinessRow, 1, incomeAndFoodSourcesResponses.getSmallBusiness(), style);

        //Firewood collection/charcoal burning
        Row firewoodRow = sheet.createRow(rowCount++);
        createCell(firewoodRow, 0, "Firewood collection/charcoal burning", style);
        createCell(firewoodRow, 1, incomeAndFoodSourcesResponses.getFirewoodOrCharcoal(), style);

        //Petty Trading
        Row pettyTradingRow = sheet.createRow(rowCount++);
        createCell(pettyTradingRow, 0, "Petty Trading", style);
        createCell(pettyTradingRow, 1, incomeAndFoodSourcesResponses.getPettyTrading(), style);

        //Remittance and Gifts
        Row remittancesRow = sheet.createRow(rowCount++);
        createCell(remittancesRow, 0, "Remittance and Gifts", style);
        createCell(remittancesRow, 1, incomeAndFoodSourcesResponses.getRemittance(), style);

        //Boda boda transport
        Row bodabodaRow = sheet.createRow(rowCount++);
        createCell(bodabodaRow, 0, "Boda boda transport", style);
        createCell(bodabodaRow, 1, incomeAndFoodSourcesResponses.getBodaboda(), style);

        //Bee Keeping
        Row beeKeepingRow = sheet.createRow(rowCount++);
        createCell(beeKeepingRow, 0, "Bee Keeping", style);
        createCell(beeKeepingRow, 1, incomeAndFoodSourcesResponses.getBeeKeeping(), style);

        //Sand harvesting
        Row sandHarvestingRow = sheet.createRow(rowCount++);
        createCell(sandHarvestingRow, 0, "Sand harvesting", style);
        createCell(sandHarvestingRow, 1, incomeAndFoodSourcesResponses.getSandHarvesting(), style);

        //Other Specify
        Row othersRow = sheet.createRow(rowCount++);
        createCell(othersRow, 0, "Other Specify", style);
        createCell(othersRow, 1, incomeAndFoodSourcesResponses.getOther().getValue(), style);
        createCell(othersRow, 2, incomeAndFoodSourcesResponses.getOther().getDescription(), style);


        return workbook;
    }


    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook) {
        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,1);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 19;
        }
        return workbook;
    }
}
