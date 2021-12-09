package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.hazards.HazardResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LZ_HAZARDS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;

@Service
public class HazardExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(LZ_HAZARDS);
        sheet.setColumnWidth(0,18000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,13000);
        sheet.setColumnWidth(3,13000);
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

        createCell(headerRow, 0, "Name of Hazard", tableHeaderStyle);
        createCell(headerRow, 1, "Rank of importance", tableHeaderStyle);
        createCell(headerRow, 2, "No of years experienced in last 10 years", tableHeaderStyle);
        createCell(headerRow, 3, "Others description", tableHeaderStyle);

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

        HazardResponses hazardResponses = lzLivelihoodZoneDataObject.getHazardResponses();
        XSSFSheet sheet = workbook.getSheet(LZ_HAZARDS);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        //Animal Rustling
        Row animalRustlingRow = sheet.createRow(rowCount++);
        createCell(animalRustlingRow, 0, "Animal Rustling", style);
        createCell(animalRustlingRow, 1, hazardResponses.getAnimalRustling().getImportanceRank(), style);
        createCell(animalRustlingRow, 2, hazardResponses.getAnimalRustling().getNoExperiencedYears(), style);

        //Banditry
        Row banditryRow = sheet.createRow(rowCount++);
        createCell(banditryRow, 0, "Banditry", style);
        createCell(banditryRow, 1, hazardResponses.getBanditry().getImportanceRank(), style);
        createCell(banditryRow, 2, hazardResponses.getBanditry().getNoExperiencedYears(), style);

        //Terrorism
        Row terrorismRow = sheet.createRow(rowCount++);
        createCell(terrorismRow, 0, "Terrorism", style);
        createCell(terrorismRow, 1, hazardResponses.getTerrorism().getImportanceRank(), style);
        createCell(terrorismRow, 2, hazardResponses.getTerrorism().getNoExperiencedYears(), style);

        //Ethnic Conflict
        Row ethnicConflictRow = sheet.createRow(rowCount++);
        createCell(ethnicConflictRow, 0, "Ethnic Conflict", style);
        createCell(ethnicConflictRow, 1, hazardResponses.getEthnicConflict().getImportanceRank(), style);
        createCell(ethnicConflictRow, 2, hazardResponses.getEthnicConflict().getNoExperiencedYears(), style);

        //Political Conflict/violence
        Row politicalRow = sheet.createRow(rowCount++);
        createCell(politicalRow, 0, "Political Conflict/violence", style);
        createCell(politicalRow, 1, hazardResponses.getPoliticalViolence().getImportanceRank(), style);
        createCell(politicalRow, 2, hazardResponses.getPoliticalViolence().getNoExperiencedYears(), style);

        //Drought
        Row droughtRow = sheet.createRow(rowCount++);
        createCell(droughtRow, 0, "Drought", style);
        createCell(droughtRow, 1, hazardResponses.getDrought().getImportanceRank(), style);
        createCell(droughtRow, 2, hazardResponses.getDrought().getNoExperiencedYears(), style);

        //Livestock Pests and Diseases
        Row livestockPestsRow = sheet.createRow(rowCount++);
        createCell(livestockPestsRow, 0, "Livestock Pests and Diseases", style);
        createCell(livestockPestsRow, 1, hazardResponses.getLivestockPestsAndDiseases().getImportanceRank(), style);
        createCell(livestockPestsRow, 2, hazardResponses.getLivestockPestsAndDiseases().getNoExperiencedYears(), style);

        //Hailstorms or frost
        Row hailStormRow = sheet.createRow(rowCount++);
        createCell(hailStormRow, 0, "Hailstorms or frost", style);
        createCell(hailStormRow, 1, hazardResponses.getHailstormsOrFrost().getImportanceRank(), style);
        createCell(hailStormRow, 2, hazardResponses.getHailstormsOrFrost().getNoExperiencedYears(), style);

        //Flooding
        Row floodingRow = sheet.createRow(rowCount++);
        createCell(floodingRow, 0, "Flooding", style);
        createCell(floodingRow, 1, hazardResponses.getFlooding().getImportanceRank(), style);
        createCell(floodingRow, 2, hazardResponses.getFlooding().getNoExperiencedYears(), style);

        //Landslides
        Row landSlidesRow = sheet.createRow(rowCount++);
        createCell(landSlidesRow, 0, "Landslides", style);
        createCell(landSlidesRow, 1, hazardResponses.getLandslides().getImportanceRank(), style);
        createCell(landSlidesRow, 2, hazardResponses.getLandslides().getNoExperiencedYears(), style);

        //High winds/cyclones
        Row highWindsRow = sheet.createRow(rowCount++);
        createCell(highWindsRow, 0, "High winds/cyclones", style);
        createCell(highWindsRow, 1, hazardResponses.getHighWindsOrCyclones().getImportanceRank(), style);
        createCell(highWindsRow, 2, hazardResponses.getHighWindsOrCyclones().getNoExperiencedYears(), style);

        //Bush Fires
        Row bushFiresRow = sheet.createRow(rowCount++);
        createCell(bushFiresRow, 0, "Bush Fires", style);
        createCell(bushFiresRow, 1, hazardResponses.getBushFires().getImportanceRank(), style);
        createCell(bushFiresRow, 2, hazardResponses.getBushFires().getNoExperiencedYears(), style);

        //Crop Pests
        Row cropPestsRow = sheet.createRow(rowCount++);
        createCell(cropPestsRow, 0, "Crop pests", style);
        createCell(cropPestsRow, 1, hazardResponses.getCropPests().getImportanceRank(), style);
        createCell(cropPestsRow, 2, hazardResponses.getCropPests().getNoExperiencedYears(), style);

        //Locust invasion
        Row locustRow = sheet.createRow(rowCount++);
        createCell(locustRow, 0, "Locust invasion", style);
        createCell(locustRow, 1, hazardResponses.getLocustInvasion().getImportanceRank(), style);
        createCell(locustRow, 2, hazardResponses.getLocustInvasion().getNoExperiencedYears(), style);

        //Crop Diseases
        Row cropDiseasesRow = sheet.createRow(rowCount++);
        createCell(cropDiseasesRow, 0, "Crop Diseases", style);
        createCell(cropDiseasesRow, 1, hazardResponses.getCropDiseases().getImportanceRank(), style);
        createCell(cropDiseasesRow, 2, hazardResponses.getCropDiseases().getNoExperiencedYears(), style);

        //Terminal illness e.g Cancer, HIV/AIDS
        Row terminalIllnessRow = sheet.createRow(rowCount++);
        createCell(terminalIllnessRow, 0, "Terminal illness e.g Cancer, HIV/AIDS", style);
        createCell(terminalIllnessRow, 1, hazardResponses.getTerminalIllnesses().getImportanceRank(), style);
        createCell(terminalIllnessRow, 2, hazardResponses.getTerminalIllnesses().getNoExperiencedYears(), style);

        //Significant Malaria Outbreak
        Row malariaRow = sheet.createRow(rowCount++);
        createCell(malariaRow, 0, "Significant Malaria Outbreak", style);
        createCell(malariaRow, 1, hazardResponses.getMalariaPowerOutBreak().getImportanceRank(), style);
        createCell(malariaRow, 2, hazardResponses.getMalariaPowerOutBreak().getNoExperiencedYears(), style);

        //Water Borne Disease Epidemics (cholera, D&V, dysentery etc)
        Row waterDiseasesRow = sheet.createRow(rowCount++);
        createCell(waterDiseasesRow, 0, "Water Borne Disease Epidemics (cholera, D&V, dysentery etc)", style);
        createCell(waterDiseasesRow, 1, hazardResponses.getWaterBornDiseases().getImportanceRank(), style);
        createCell(waterDiseasesRow, 2, hazardResponses.getWaterBornDiseases().getNoExperiencedYears(), style);

        //Human wildlife conflict
        Row wildlifeRow = sheet.createRow(rowCount++);
        createCell(wildlifeRow, 0, "Human wildlife conflict", style);
        createCell(wildlifeRow, 1, hazardResponses.getHumanWildlifeConflict().getImportanceRank(), style);
        createCell(wildlifeRow, 2, hazardResponses.getHumanWildlifeConflict().getNoExperiencedYears(), style);

        //High/variable food prices
        Row foodPriceRow = sheet.createRow(rowCount++);
        createCell(foodPriceRow, 0, "High/variable food prices", style);
        createCell(foodPriceRow, 1, hazardResponses.getHighFoodPrices().getImportanceRank(), style);
        createCell(foodPriceRow, 2, hazardResponses.getHighFoodPrices().getNoExperiencedYears(), style);

        //Shortages of food on the market
        Row foodShortageRow = sheet.createRow(rowCount++);
        createCell(foodShortageRow, 0, "Shortages of food on the market", style);
        createCell(foodShortageRow, 1, hazardResponses.getMarketFoodShortages().getImportanceRank(), style);
        createCell(foodShortageRow, 2, hazardResponses.getMarketFoodShortages().getNoExperiencedYears(), style);

        //Drinking water shortages
        Row drinkWaterShortageRow = sheet.createRow(rowCount++);
        createCell(drinkWaterShortageRow, 0, "Drinking water shortages", style);
        createCell(drinkWaterShortageRow, 1, hazardResponses.getDrinkingWaterShortages().getImportanceRank(), style);
        createCell(drinkWaterShortageRow, 2, hazardResponses.getDrinkingWaterShortages().getNoExperiencedYears(), style);

        //Invasive plant species
        Row invasivePlantRow = sheet.createRow(rowCount++);
        createCell(invasivePlantRow, 0, "Invasive plant species", style);
        createCell(invasivePlantRow, 1, hazardResponses.getInvasivePlants().getImportanceRank(), style);
        createCell(invasivePlantRow, 2, hazardResponses.getInvasivePlants().getNoExperiencedYears(), style);

        //Others
        Row otherRow = sheet.createRow(rowCount++);
        createCell(otherRow, 0, "Other", style);
        createCell(otherRow, 1, hazardResponses.getOthers().getImportanceRank(), style);
        createCell(otherRow, 2, hazardResponses.getOthers().getNoExperiencedYears(), style);
        createCell(otherRow, 3, hazardResponses.getOthers().getExtraDescription(), style);

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,8);
        int rowNum = 0;
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,lzLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(lzLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 33;
        }
        return workbook;
    }
}
