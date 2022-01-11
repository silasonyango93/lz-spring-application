package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.reports.wealthgroup.charts.WgCropContributionChartObject;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.CROP_CONTRIBUTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;

@Service
public class LivestockOwnershipExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,10000);
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

        createCell(titleRow, 0, wgLivelihoodZoneDataObject.getLivelihoodZoneName().toUpperCase(), titleStyle);
        createCell(tableHeaderRow, 0, "Type of Animal", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Ave. No Per HouseHold", tableHeaderStyle);
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
        LivestockPoultryOwnershipResponses livestockAndPoultryOwnership = wgLivelihoodZoneDataObject.getLivestockAndPoultryOwnership();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);

        //Local Cattle
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Local Cattle", style);
        createCell(livestockRow, 1, livestockAndPoultryOwnership.getCattle(), style);

        //Goats
        Row goatsRow = sheet.createRow(rowCount++);
        createCell(goatsRow, 0, "Goats", style);
        createCell(goatsRow, 1, livestockAndPoultryOwnership.getGoats(), style);

        //Sheep
        Row sheepRow = sheet.createRow(rowCount++);
        createCell(sheepRow, 0, "Sheep", style);
        createCell(sheepRow, 1, livestockAndPoultryOwnership.getSheep(), style);

        //Donkeys
        Row donkeyRow = sheet.createRow(rowCount++);
        createCell(donkeyRow, 0, "Donkeys", style);
        createCell(donkeyRow, 1, livestockAndPoultryOwnership.getDonkeys(), style);

        //Camels
        Row camelRow = sheet.createRow(rowCount++);
        createCell(camelRow, 0, "Camels", style);
        createCell(camelRow, 1, livestockAndPoultryOwnership.getCamels(), style);

        //Pigs
        Row pigsRow = sheet.createRow(rowCount++);
        createCell(pigsRow, 0, "Pigs", style);
        createCell(pigsRow, 1, livestockAndPoultryOwnership.getPigs(), style);

        //Chickens
        Row chickenRow = sheet.createRow(rowCount++);
        createCell(chickenRow, 0, "Chickens", style);
        createCell(chickenRow, 1, livestockAndPoultryOwnership.getChicken(), style);

        //Ducks
        Row ducksRow = sheet.createRow(rowCount++);
        createCell(ducksRow, 0, "Ducks", style);
        createCell(ducksRow, 1, livestockAndPoultryOwnership.getDucks(), style);

        //Beehives
        Row beeHivesRow = sheet.createRow(rowCount++);
        createCell(beeHivesRow, 0, "Bee hives", style);
        createCell(beeHivesRow, 1, livestockAndPoultryOwnership.getBeeHives(), style);

        //Fish Ponds
        Row fishPondsRow = sheet.createRow(rowCount++);
        createCell(fishPondsRow, 0, "Fish Ponds", style);
        createCell(fishPondsRow, 1, livestockAndPoultryOwnership.getFishPonds(), style);

        //Improved Cattle
        Row improvedCattleRow = sheet.createRow(rowCount++);
        createCell(improvedCattleRow, 0, "Improved Cattle", style);
        createCell(improvedCattleRow, 1, livestockAndPoultryOwnership.getImprovedCattle(), style);

        //Improved Chicken
        Row improvedChickenRow = sheet.createRow(rowCount++);
        createCell(improvedChickenRow, 0, "Improved Chicken", style);
        createCell(improvedChickenRow, 1, livestockAndPoultryOwnership.getImprovedChicken(), style);

        //Fish Cages
        Row fishCagesRow = sheet.createRow(rowCount++);
        createCell(fishCagesRow, 0, "Fish Cages", style);
        createCell(fishCagesRow, 1, livestockAndPoultryOwnership.getFishCages(), style);

        //Dairy Cattle
        Row dairyCattleRow = sheet.createRow(rowCount++);
        createCell(dairyCattleRow, 0, "Dairy Cattle", style);
        createCell(dairyCattleRow, 1, livestockAndPoultryOwnership.getDairyCattle(), style);

        //TLU
        Row tluRow = sheet.createRow(rowCount++);
        createCell(tluRow, 0, "TLU", style);
        createCell(tluRow, 1, livestockAndPoultryOwnership.getTlu(), style);



        return workbook;
    }


    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook, String sectionName) {

        String sheetName = sectionName != null ? sectionName : LIVESTOCK_OWNERSHIP;

        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,4);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook, sheetName);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook, sheetName);
            rowNum = rowNum + 19;
        }
        return workbook;
    }

}
