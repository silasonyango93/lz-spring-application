package livelihoodzone.service.reports.wealthgroup.excel.coutry_files;

import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

@Service
public class MainSourcesOfFoodAndIncomeCountryFileExcelService {

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row tableHeaderRow = sheet.createRow(rowNum);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,15000);
        sheet.setColumnWidth(2,12000);
        sheet.setColumnWidth(3,10000);


        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(tableHeaderRow, 0, "County", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Livelihood Zone", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Cash Income Source", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Percent Of Total Income", tableHeaderStyle);
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
        IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses = wgLivelihoodZoneDataObject.getIncomeAndFoodSourcesResponses();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);

        //Livestock Production
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(livestockRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(livestockRow, 2, "Livestock Production (including meat, milk, hides, skins, and by products/manure", style);
        createCell(livestockRow, 3, incomeAndFoodSourcesResponses.getLivestockProduction(), style);

        //Pasture/Fodder Production
        Row pastureRow = sheet.createRow(rowCount++);
        createCell(pastureRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(pastureRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(pastureRow, 2, "Pasture/Fodder Production", style);
        createCell(pastureRow, 3, incomeAndFoodSourcesResponses.getPastureFodderProduction(), style);

        //Poultry Production including meat and egg production
        Row poultryRow = sheet.createRow(rowCount++);
        createCell(poultryRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(poultryRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(poultryRow, 2, "Poultry Production including meat and egg production", style);
        createCell(poultryRow, 3, incomeAndFoodSourcesResponses.getPoultryProduction(), style);

        //Cash Crop Production
        Row cashCropRow = sheet.createRow(rowCount++);
        createCell(cashCropRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(cashCropRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(cashCropRow, 2, "Cash Crop Production", style);
        createCell(cashCropRow, 3, incomeAndFoodSourcesResponses.getCashCropProduction(), style);

        //Food Crop Production
        Row foodCropRow = sheet.createRow(rowCount++);
        createCell(foodCropRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(foodCropRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(foodCropRow, 2, "Food Crop Production", style);
        createCell(foodCropRow, 3, incomeAndFoodSourcesResponses.getFoodCropProduction(), style);

        //Casual /Waged-labour Income
        Row casualWagedLabourRow = sheet.createRow(rowCount++);
        createCell(casualWagedLabourRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(casualWagedLabourRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(casualWagedLabourRow, 2, "Casual /Waged-labour Income", style);
        createCell(casualWagedLabourRow, 3, incomeAndFoodSourcesResponses.getCasualOrWagedLabour(), style);

        //Formal Waged Labour including public and private sector employees
        Row formalWagedLabourRow = sheet.createRow(rowCount++);
        createCell(formalWagedLabourRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(formalWagedLabourRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(formalWagedLabourRow, 2, "Formal Waged Labour including public and private sector employees", style);
        createCell(formalWagedLabourRow, 3, incomeAndFoodSourcesResponses.getFormalWagedLabour(), style);

        //Fishing (marine or inland)
        Row fishingRow = sheet.createRow(rowCount++);
        createCell(fishingRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(fishingRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(fishingRow, 2, "Fishing (marine or inland)", style);
        createCell(fishingRow, 3, incomeAndFoodSourcesResponses.getFishing(), style);

        //Hunting and Gathering
        Row huntingRow = sheet.createRow(rowCount++);
        createCell(huntingRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(huntingRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(huntingRow, 2, "Hunting and Gathering", style);
        createCell(huntingRow, 3, incomeAndFoodSourcesResponses.getHuntingAndGathering(), style);

        //Small Businesses/own business including crafts, non- farm production/ Brokerage services/ middlemen, beer etc
        Row smallBusiinessRow = sheet.createRow(rowCount++);
        createCell(smallBusiinessRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(smallBusiinessRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(smallBusiinessRow, 2, "Small Businesses/own business including crafts, non- farm production/ Brokerage services/ middlemen, beer etc", style);
        createCell(smallBusiinessRow, 3, incomeAndFoodSourcesResponses.getSmallBusiness(), style);

        //Firewood collection/charcoal burning
        Row firewoodRow = sheet.createRow(rowCount++);
        createCell(firewoodRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(firewoodRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(firewoodRow, 2, "Firewood collection/charcoal burning", style);
        createCell(firewoodRow, 3, incomeAndFoodSourcesResponses.getFirewoodOrCharcoal(), style);

        //Petty Trading
        Row pettyTradingRow = sheet.createRow(rowCount++);
        createCell(pettyTradingRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(pettyTradingRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(pettyTradingRow, 2, "Petty Trading", style);
        createCell(pettyTradingRow, 3, incomeAndFoodSourcesResponses.getPettyTrading(), style);

        //Remittance and Gifts
        Row remittancesRow = sheet.createRow(rowCount++);
        createCell(remittancesRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(remittancesRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(remittancesRow, 2, "Remittance and Gifts", style);
        createCell(remittancesRow, 3, incomeAndFoodSourcesResponses.getRemittance(), style);

        //Boda boda transport
        Row bodabodaRow = sheet.createRow(rowCount++);
        createCell(bodabodaRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(bodabodaRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(bodabodaRow, 2, "Boda boda transport", style);
        createCell(bodabodaRow, 3, incomeAndFoodSourcesResponses.getBodaboda(), style);

        //Bee Keeping
        Row beeKeepingRow = sheet.createRow(rowCount++);
        createCell(beeKeepingRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(beeKeepingRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(beeKeepingRow, 2, "Bee Keeping", style);
        createCell(beeKeepingRow, 3, incomeAndFoodSourcesResponses.getBeeKeeping(), style);

        //Sand harvesting
        Row sandHarvestingRow = sheet.createRow(rowCount++);
        createCell(sandHarvestingRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(sandHarvestingRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(sandHarvestingRow, 2, "Sand harvesting", style);
        createCell(sandHarvestingRow, 3, incomeAndFoodSourcesResponses.getSandHarvesting(), style);

        //Other Specify
        Row othersRow = sheet.createRow(rowCount++);
        createCell(othersRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(othersRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(othersRow, 2, "Other Specify", style);
        createCell(othersRow, 3, incomeAndFoodSourcesResponses.getOther() != null ?  incomeAndFoodSourcesResponses.getOther().getValue() : "N/A", style);


        return workbook;
    }


    public XSSFWorkbook processData(int wealthGroupId, XSSFWorkbook workbook, String sectionName) {


        String sheetName = sectionName != null ? sectionName : MAIN_INCOME_SOURCES_EXCEL_SHEET_NAME;

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNum = 0;
        workbook = writeHeaderLine(rowNum,workbook, sheetName);
        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {

            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countiesEntity.getCountyId(),wealthGroupId,1);
            for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
                workbook = writeDataLines(wgLivelihoodZoneDataObject, sheet.getLastRowNum() + 1,workbook, sheetName);
            }

        }
        return workbook;
    }
}
