package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponsesDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME;

@Service
public class FoodConsumptionPercentageExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;


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

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,20000);
        sheet.setColumnWidth(1,8000);
        sheet.setColumnWidth(2,8000);
        sheet.setColumnWidth(3,8000);
        sheet.setColumnWidth(4,8000);

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
        createCell(tableHeaderRow, 0, "Type of food", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Own farm production(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Market food purchase(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Hunting, gathering, fishing(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 4, "Gifts and food aid(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 5, "Total", tableHeaderStyle);
        return workbook;
    }


    private XSSFWorkbook writeDataLines(WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, int rowCount, XSSFWorkbook workbook) {
        FoodConsumptionResponsesDto foodConsumptionResponsesDto = wgLivelihoodZoneDataObject.getFoodConsumptionPercentages();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(FOOD_CONSUMPTION_PERCENTAGES_EXCEL_SHEET_NAME);

        //Maize and posho
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Maize and posho", style);
        createCell(livestockRow, 1, foodConsumptionResponsesDto.getMaizeAndPosho().getOwnFarm(), style);
        createCell(livestockRow, 2, foodConsumptionResponsesDto.getMaizeAndPosho().getMarketFoodPurchase(), style);
        createCell(livestockRow, 3, foodConsumptionResponsesDto.getMaizeAndPosho().getHuntingGatheringFishing(), style);
        createCell(livestockRow, 4, foodConsumptionResponsesDto.getMaizeAndPosho().getGifts(), style);
        createCell(livestockRow, 5, "100%", style);

        //Wheat, barley, rye and bread
        Row wheatRow = sheet.createRow(rowCount++);
        createCell(wheatRow, 0, "Wheat, barley, rye and bread", style);
        createCell(wheatRow, 1, foodConsumptionResponsesDto.getWheatOrBarley().getOwnFarm(), style);
        createCell(wheatRow, 2, foodConsumptionResponsesDto.getWheatOrBarley().getMarketFoodPurchase(), style);
        createCell(wheatRow, 3, foodConsumptionResponsesDto.getWheatOrBarley().getHuntingGatheringFishing(), style);
        createCell(wheatRow, 4, foodConsumptionResponsesDto.getWheatOrBarley().getGifts(), style);
        createCell(wheatRow, 5, "100%", style);

        //Sorghum, millets and products
        Row sorghumRow = sheet.createRow(rowCount++);
        createCell(sorghumRow, 0, "Sorghum, millets and products", style);
        createCell(sorghumRow, 1, foodConsumptionResponsesDto.getSorghumOrMillet().getOwnFarm(), style);
        createCell(sorghumRow, 2, foodConsumptionResponsesDto.getSorghumOrMillet().getMarketFoodPurchase(), style);
        createCell(sorghumRow, 3, foodConsumptionResponsesDto.getSorghumOrMillet().getHuntingGatheringFishing(), style);
        createCell(sorghumRow, 4, foodConsumptionResponsesDto.getSorghumOrMillet().getGifts(), style);
        createCell(sorghumRow, 5, "100%", style);

        //Rice and products
        Row riceRow = sheet.createRow(rowCount++);
        createCell(riceRow, 0, "Rice and products", style);
        createCell(riceRow, 1, foodConsumptionResponsesDto.getRice().getOwnFarm(), style);
        createCell(riceRow, 2, foodConsumptionResponsesDto.getRice().getMarketFoodPurchase(), style);
        createCell(riceRow, 3, foodConsumptionResponsesDto.getRice().getHuntingGatheringFishing(), style);
        createCell(riceRow, 4, foodConsumptionResponsesDto.getRice().getGifts(), style);
        createCell(riceRow, 5, "100%", style);

        //Beans
        Row beansRow = sheet.createRow(rowCount++);
        createCell(beansRow, 0, "Beans", style);
        createCell(beansRow, 1, foodConsumptionResponsesDto.getBeans().getOwnFarm(), style);
        createCell(beansRow, 2, foodConsumptionResponsesDto.getBeans().getMarketFoodPurchase(), style);
        createCell(beansRow, 3, foodConsumptionResponsesDto.getBeans().getHuntingGatheringFishing(), style);
        createCell(beansRow, 4, foodConsumptionResponsesDto.getBeans().getGifts(), style);
        createCell(beansRow, 5, "100%", style);

        //Other Pulses, seeds and nuts (green grams, dried peas, cowpeas etc)
        Row pulsesRow = sheet.createRow(rowCount++);
        createCell(pulsesRow, 0, "Other Pulses, seeds and nuts (green grams, dried peas, cowpeas etc)", style);
        createCell(pulsesRow, 1, foodConsumptionResponsesDto.getPulses().getOwnFarm(), style);
        createCell(pulsesRow, 2, foodConsumptionResponsesDto.getPulses().getMarketFoodPurchase(), style);
        createCell(pulsesRow, 3, foodConsumptionResponsesDto.getPulses().getHuntingGatheringFishing(), style);
        createCell(pulsesRow, 4, foodConsumptionResponsesDto.getPulses().getGifts(), style);
        createCell(pulsesRow, 5, "100%", style);

        //Vegetables (including wild)
        Row vegetablesRow = sheet.createRow(rowCount++);
        createCell(vegetablesRow, 0, "Vegetables (including wild)", style);
        createCell(vegetablesRow, 1, foodConsumptionResponsesDto.getVegetables().getOwnFarm(), style);
        createCell(vegetablesRow, 2, foodConsumptionResponsesDto.getVegetables().getMarketFoodPurchase(), style);
        createCell(vegetablesRow, 3, foodConsumptionResponsesDto.getVegetables().getHuntingGatheringFishing(), style);
        createCell(vegetablesRow, 4, foodConsumptionResponsesDto.getVegetables().getGifts(), style);
        createCell(vegetablesRow, 5, "100%", style);

        //Fruits and berries (including wild
        Row friutsRow = sheet.createRow(rowCount++);
        createCell(friutsRow, 0, "Fruits and berries (including wild", style);
        createCell(friutsRow, 1, foodConsumptionResponsesDto.getFruits().getOwnFarm(), style);
        createCell(friutsRow, 2, foodConsumptionResponsesDto.getFruits().getMarketFoodPurchase(), style);
        createCell(friutsRow, 3, foodConsumptionResponsesDto.getFruits().getHuntingGatheringFishing(), style);
        createCell(friutsRow, 4, foodConsumptionResponsesDto.getFruits().getGifts(), style);
        createCell(friutsRow, 5, "100%", style);

        //White Roots, Tubers  and Plantain (including wild)  (irish and sweet potatoes, cassava, turnip, arrow root, cooked or roasted bananas etc)
        Row whiteRootsRow = sheet.createRow(rowCount++);
        createCell(whiteRootsRow, 0, "White Roots, Tubers  and Plantain (including wild)  (irish and sweet potatoes, cassava, turnip, arrow root, cooked or roasted bananas etc)", style);
        createCell(whiteRootsRow, 1, foodConsumptionResponsesDto.getWhiteRoots().getOwnFarm(), style);
        createCell(whiteRootsRow, 2, foodConsumptionResponsesDto.getWhiteRoots().getMarketFoodPurchase(), style);
        createCell(whiteRootsRow, 3, foodConsumptionResponsesDto.getWhiteRoots().getHuntingGatheringFishing(), style);
        createCell(whiteRootsRow, 4, foodConsumptionResponsesDto.getWhiteRoots().getGifts(), style);
        createCell(whiteRootsRow, 5, "100%", style);

        //Meat (including wild animals and poultry)
        Row meatRow = sheet.createRow(rowCount++);
        createCell(meatRow, 0, "Meat (including wild animals and poultry)", style);
        createCell(meatRow, 1, foodConsumptionResponsesDto.getMeat().getOwnFarm(), style);
        createCell(meatRow, 2, foodConsumptionResponsesDto.getMeat().getMarketFoodPurchase(), style);
        createCell(meatRow, 3, foodConsumptionResponsesDto.getMeat().getHuntingGatheringFishing(), style);
        createCell(meatRow, 4, foodConsumptionResponsesDto.getMeat().getGifts(), style);
        createCell(meatRow, 5, "100%", style);

        //Milk and dairy products
        Row milkRow = sheet.createRow(rowCount++);
        createCell(milkRow, 0, "Milk and dairy products", style);
        createCell(milkRow, 1, foodConsumptionResponsesDto.getMilk().getOwnFarm(), style);
        createCell(milkRow, 2, foodConsumptionResponsesDto.getMilk().getMarketFoodPurchase(), style);
        createCell(milkRow, 3, foodConsumptionResponsesDto.getMilk().getHuntingGatheringFishing(), style);
        createCell(milkRow, 4, foodConsumptionResponsesDto.getMilk().getGifts(), style);
        createCell(milkRow, 5, "100%", style);

        //Fish and sea food
        Row fishRow = sheet.createRow(rowCount++);
        createCell(fishRow, 0, "Fish and sea food", style);
        createCell(fishRow, 1, foodConsumptionResponsesDto.getFish().getOwnFarm(), style);
        createCell(fishRow, 2, foodConsumptionResponsesDto.getFish().getMarketFoodPurchase(), style);
        createCell(fishRow, 3, foodConsumptionResponsesDto.getFish().getHuntingGatheringFishing(), style);
        createCell(fishRow, 4, foodConsumptionResponsesDto.getFish().getGifts(), style);
        createCell(fishRow, 5, "100%", style);

        //Eggs
        Row eggsRow = sheet.createRow(rowCount++);
        createCell(eggsRow, 0, "Eggs", style);
        createCell(eggsRow, 1, foodConsumptionResponsesDto.getEggs().getOwnFarm(), style);
        createCell(eggsRow, 2, foodConsumptionResponsesDto.getEggs().getMarketFoodPurchase(), style);
        createCell(eggsRow, 3, foodConsumptionResponsesDto.getEggs().getHuntingGatheringFishing(), style);
        createCell(eggsRow, 4, foodConsumptionResponsesDto.getEggs().getGifts(), style);
        createCell(eggsRow, 5, "100%", style);

        //Cooking fats and oils
        Row fatsRow = sheet.createRow(rowCount++);
        createCell(fatsRow, 0, "Cooking fats and oils", style);
        createCell(fatsRow, 1, foodConsumptionResponsesDto.getCookingFats().getOwnFarm(), style);
        createCell(fatsRow, 2, foodConsumptionResponsesDto.getCookingFats().getMarketFoodPurchase(), style);
        createCell(fatsRow, 3, foodConsumptionResponsesDto.getCookingFats().getHuntingGatheringFishing(), style);
        createCell(fatsRow, 4, foodConsumptionResponsesDto.getCookingFats().getGifts(), style);
        createCell(fatsRow, 5, "100%", style);

        //Spices, condiments and beverages (chili, hot sauce, coffee, tea etc)
        Row spicesRow = sheet.createRow(rowCount++);
        createCell(spicesRow, 0, "Spices, condiments and beverages (chili, hot sauce, coffee, tea etc) ", style);
        createCell(spicesRow, 1, foodConsumptionResponsesDto.getSpices().getOwnFarm(), style);
        createCell(spicesRow, 2, foodConsumptionResponsesDto.getSpices().getMarketFoodPurchase(), style);
        createCell(spicesRow, 3, foodConsumptionResponsesDto.getSpices().getHuntingGatheringFishing(), style);
        createCell(spicesRow, 4, foodConsumptionResponsesDto.getSpices().getGifts(), style);
        createCell(spicesRow, 5, "100%", style);


        return workbook;
    }


    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook) {
        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,2);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 22;
        }
        return workbook;
    }
}
