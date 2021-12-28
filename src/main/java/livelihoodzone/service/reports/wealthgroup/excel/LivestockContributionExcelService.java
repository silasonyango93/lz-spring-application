package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions.LivestockContributionResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_CONTRIBUTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_OWNERSHIP;

@Service
public class LivestockContributionExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,10000);
        sheet.setColumnWidth(4,10000);

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
        createCell(tableHeaderRow, 1, "Cash Rank", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Cash Contribution(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Food Rank", tableHeaderStyle);
        createCell(tableHeaderRow, 4, "Food Contribution(%)", tableHeaderStyle);
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
        LivestockContributionResponses livestockContributions = wgLivelihoodZoneDataObject.getLivestockContributions();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);

        //Local Cattle
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Local Cattle", style);
        createCell(livestockRow, 1, livestockContributions.getCattle().getIncomeRank().getActualValue(), style);
        createCell(livestockRow, 2, livestockContributions.getCattle().getIncomePercentage().getActualValue(), style);
        createCell(livestockRow, 3, livestockContributions.getCattle().getConsumptionRank().getActualValue(), style);
        createCell(livestockRow, 4, livestockContributions.getCattle().getConsumptionPercentage().getActualValue(), style);

        //Goats
        Row goatsRow = sheet.createRow(rowCount++);
        createCell(goatsRow, 0, "Goats", style);
        createCell(goatsRow, 1, livestockContributions.getGoats().getIncomeRank().getActualValue(), style);
        createCell(goatsRow, 2, livestockContributions.getGoats().getIncomePercentage().getActualValue(), style);
        createCell(goatsRow, 3, livestockContributions.getGoats().getConsumptionRank().getActualValue(), style);
        createCell(goatsRow, 4, livestockContributions.getGoats().getConsumptionPercentage().getActualValue(), style);

        //Sheep
        Row sheepRow = sheet.createRow(rowCount++);
        createCell(sheepRow, 0, "Sheep", style);
        createCell(sheepRow, 1, livestockContributions.getSheep().getIncomeRank().getActualValue(), style);
        createCell(sheepRow, 2, livestockContributions.getSheep().getIncomePercentage().getActualValue(), style);
        createCell(sheepRow, 3, livestockContributions.getSheep().getConsumptionRank().getActualValue(), style);
        createCell(sheepRow, 4, livestockContributions.getSheep().getConsumptionPercentage().getActualValue(), style);

        //Donkeys
        Row donkeyRow = sheet.createRow(rowCount++);
        createCell(donkeyRow, 0, "Donkeys", style);
        createCell(donkeyRow, 1, livestockContributions.getDonkeys().getIncomeRank().getActualValue(), style);
        createCell(donkeyRow, 2, livestockContributions.getDonkeys().getIncomePercentage().getActualValue(), style);
        createCell(donkeyRow, 3, livestockContributions.getDonkeys().getConsumptionRank().getActualValue(), style);
        createCell(donkeyRow, 4, livestockContributions.getDonkeys().getConsumptionPercentage().getActualValue(), style);

        //Camels
        Row camelRow = sheet.createRow(rowCount++);
        createCell(camelRow, 0, "Camels", style);
        createCell(camelRow, 1, livestockContributions.getCamels().getIncomeRank().getActualValue(), style);
        createCell(camelRow, 2, livestockContributions.getCamels().getIncomePercentage().getActualValue(), style);
        createCell(camelRow, 3, livestockContributions.getCamels().getConsumptionRank().getActualValue(), style);
        createCell(camelRow, 4, livestockContributions.getCamels().getConsumptionPercentage().getActualValue(), style);

        //Pigs
        Row pigsRow = sheet.createRow(rowCount++);
        createCell(pigsRow, 0, "Pigs", style);
        createCell(pigsRow, 1, livestockContributions.getPigs().getIncomeRank().getActualValue(), style);
        createCell(pigsRow, 2, livestockContributions.getPigs().getIncomePercentage().getActualValue(), style);
        createCell(pigsRow, 3, livestockContributions.getPigs().getConsumptionRank().getActualValue(), style);
        createCell(pigsRow, 4, livestockContributions.getPigs().getConsumptionPercentage().getActualValue(), style);

        //Chickens
        Row chickenRow = sheet.createRow(rowCount++);
        createCell(chickenRow, 0, "Chickens", style);
        createCell(chickenRow, 1, livestockContributions.getChicken().getIncomeRank().getActualValue(), style);
        createCell(chickenRow, 2, livestockContributions.getChicken().getIncomePercentage().getActualValue(), style);
        createCell(chickenRow, 3, livestockContributions.getChicken().getConsumptionRank().getActualValue(), style);
        createCell(chickenRow, 4, livestockContributions.getChicken().getConsumptionPercentage().getActualValue(), style);

        //Ducks
        Row ducksRow = sheet.createRow(rowCount++);
        createCell(ducksRow, 0, "Ducks", style);
        createCell(ducksRow, 1, livestockContributions.getDucks().getIncomeRank().getActualValue(), style);
        createCell(ducksRow, 2, livestockContributions.getDucks().getIncomePercentage().getActualValue(), style);
        createCell(ducksRow, 3, livestockContributions.getDucks().getConsumptionRank().getActualValue(), style);
        createCell(ducksRow, 4, livestockContributions.getDucks().getConsumptionPercentage().getActualValue(), style);

        //Beehives
        Row beeHivesRow = sheet.createRow(rowCount++);
        createCell(beeHivesRow, 0, "Bee hives", style);
        createCell(beeHivesRow, 1, livestockContributions.getBeeHives().getIncomeRank().getActualValue(), style);
        createCell(beeHivesRow, 2, livestockContributions.getBeeHives().getIncomePercentage().getActualValue(), style);
        createCell(beeHivesRow, 3, livestockContributions.getBeeHives().getConsumptionRank().getActualValue(), style);
        createCell(beeHivesRow, 4, livestockContributions.getBeeHives().getConsumptionPercentage().getActualValue(), style);

        //Fish Ponds
        Row fishPondsRow = sheet.createRow(rowCount++);
        createCell(fishPondsRow, 0, "Fish Ponds", style);
        createCell(fishPondsRow, 1, livestockContributions.getFishPonds().getIncomeRank().getActualValue(), style);
        createCell(fishPondsRow, 2, livestockContributions.getFishPonds().getIncomePercentage().getActualValue(), style);
        createCell(fishPondsRow, 3, livestockContributions.getFishPonds().getConsumptionRank().getActualValue(), style);
        createCell(fishPondsRow, 4, livestockContributions.getFishPonds().getConsumptionPercentage().getActualValue(), style);

        //Improved Cattle
        Row improvedCattleRow = sheet.createRow(rowCount++);
        createCell(improvedCattleRow, 0, "Improved Cattle", style);
        createCell(improvedCattleRow, 1, livestockContributions.getImprovedCattle().getIncomeRank().getActualValue(), style);
        createCell(improvedCattleRow, 2, livestockContributions.getImprovedCattle().getIncomePercentage().getActualValue(), style);
        createCell(improvedCattleRow, 3, livestockContributions.getImprovedCattle().getConsumptionRank().getActualValue(), style);
        createCell(improvedCattleRow, 4, livestockContributions.getImprovedCattle().getConsumptionPercentage().getActualValue(), style);

        //Improved Chicken
        Row improvedChickenRow = sheet.createRow(rowCount++);
        createCell(improvedChickenRow, 0, "Improved Chicken", style);
        createCell(improvedChickenRow, 1, livestockContributions.getImprovedChicken().getIncomeRank().getActualValue(), style);
        createCell(improvedChickenRow, 2, livestockContributions.getImprovedChicken().getIncomePercentage().getActualValue(), style);
        createCell(improvedChickenRow, 3, livestockContributions.getImprovedChicken().getConsumptionRank().getActualValue(), style);
        createCell(improvedChickenRow, 4, livestockContributions.getImprovedChicken().getConsumptionPercentage().getActualValue(), style);

        //Fish Cages
        Row fishCagesRow = sheet.createRow(rowCount++);
        createCell(fishCagesRow, 0, "Fish Cages", style);
        createCell(fishCagesRow, 1, livestockContributions.getFishCages().getIncomeRank().getActualValue(), style);
        createCell(fishCagesRow, 2, livestockContributions.getFishCages().getIncomePercentage().getActualValue(), style);
        createCell(fishCagesRow, 3, livestockContributions.getFishCages().getConsumptionRank().getActualValue(), style);
        createCell(fishCagesRow, 4, livestockContributions.getFishCages().getConsumptionPercentage().getActualValue(), style);


        //Dairy Cattle
        Row dairyCattleRow = sheet.createRow(rowCount++);
        createCell(dairyCattleRow, 0, "Dairy Cattle", style);
        createCell(dairyCattleRow, 1, livestockContributions.getDairyCattle().getIncomeRank().getActualValue(), style);
        createCell(dairyCattleRow, 2, livestockContributions.getDairyCattle().getIncomePercentage().getActualValue(), style);
        createCell(dairyCattleRow, 3, livestockContributions.getDairyCattle().getConsumptionRank().getActualValue(), style);
        createCell(dairyCattleRow, 4, livestockContributions.getDairyCattle().getConsumptionPercentage().getActualValue(), style);



        return workbook;
    }


    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook, String sectionName) {

        String sheetName = sectionName != null ? sectionName : LIVESTOCK_CONTRIBUTION;

        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,10);


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
