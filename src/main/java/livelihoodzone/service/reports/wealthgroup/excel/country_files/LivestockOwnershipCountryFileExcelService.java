package livelihoodzone.service.reports.wealthgroup.excel.country_files;

import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
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

@Service
public class LivestockOwnershipCountryFileExcelService {

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
        createCell(tableHeaderRow, 2, "Type of Animal", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Ave No per household", tableHeaderStyle);
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
        createCell(livestockRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(livestockRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(livestockRow, 2, "Local Cattle", style);
        createCell(livestockRow, 3, livestockAndPoultryOwnership.getCattle(), style);

        //Goats
        Row goatsRow = sheet.createRow(rowCount++);
        createCell(goatsRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(goatsRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(goatsRow, 2, "Goats", style);
        createCell(goatsRow, 3, livestockAndPoultryOwnership.getGoats(), style);

        //Sheep
        Row sheepRow = sheet.createRow(rowCount++);
        createCell(sheepRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(sheepRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(sheepRow, 2, "Sheep", style);
        createCell(sheepRow, 3, livestockAndPoultryOwnership.getSheep(), style);

        //Donkeys
        Row donkeyRow = sheet.createRow(rowCount++);
        createCell(donkeyRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(donkeyRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(donkeyRow, 2, "Donkeys", style);
        createCell(donkeyRow, 3, livestockAndPoultryOwnership.getDonkeys(), style);

        //Camels
        Row camelRow = sheet.createRow(rowCount++);
        createCell(camelRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(camelRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(camelRow, 2, "Camels", style);
        createCell(camelRow, 3, livestockAndPoultryOwnership.getCamels(), style);

        //Pigs
        Row pigsRow = sheet.createRow(rowCount++);
        createCell(pigsRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(pigsRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(pigsRow, 2, "Pigs", style);
        createCell(pigsRow, 3, livestockAndPoultryOwnership.getPigs(), style);

        //Chickens
        Row chickenRow = sheet.createRow(rowCount++);
        createCell(chickenRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(chickenRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(chickenRow, 2, "Chickens", style);
        createCell(chickenRow, 3, livestockAndPoultryOwnership.getChicken(), style);

        //Ducks
        Row ducksRow = sheet.createRow(rowCount++);
        createCell(ducksRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(ducksRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(ducksRow, 2, "Ducks", style);
        createCell(ducksRow, 3, livestockAndPoultryOwnership.getDucks(), style);

        //Beehives
        Row beeHivesRow = sheet.createRow(rowCount++);
        createCell(beeHivesRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(beeHivesRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(beeHivesRow, 2, "Bee hives", style);
        createCell(beeHivesRow, 3, livestockAndPoultryOwnership.getBeeHives(), style);

        //Fish Ponds
        Row fishPondsRow = sheet.createRow(rowCount++);
        createCell(fishPondsRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(fishPondsRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(fishPondsRow, 2, "Fish Ponds", style);
        createCell(fishPondsRow, 3, livestockAndPoultryOwnership.getFishPonds(), style);

        //Improved Cattle
        Row improvedCattleRow = sheet.createRow(rowCount++);
        createCell(improvedCattleRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(improvedCattleRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(improvedCattleRow, 2, "Improved Cattle", style);
        createCell(improvedCattleRow, 3, livestockAndPoultryOwnership.getImprovedCattle(), style);

        //Improved Chicken
        Row improvedChickenRow = sheet.createRow(rowCount++);
        createCell(improvedChickenRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(improvedChickenRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(improvedChickenRow, 2, "Improved Chicken", style);
        createCell(improvedChickenRow, 3, livestockAndPoultryOwnership.getImprovedChicken(), style);

        //Fish Cages
        Row fishCagesRow = sheet.createRow(rowCount++);
        createCell(fishCagesRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(fishCagesRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(fishCagesRow, 2, "Fish Cages", style);
        createCell(fishCagesRow, 3, livestockAndPoultryOwnership.getFishCages(), style);

        //Dairy Cattle
        Row dairyCattleRow = sheet.createRow(rowCount++);
        createCell(dairyCattleRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
        createCell(dairyCattleRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
        createCell(dairyCattleRow, 2, "Dairy Cattle", style);
        createCell(dairyCattleRow, 3, livestockAndPoultryOwnership.getDairyCattle(), style);



        return workbook;
    }


    public XSSFWorkbook processData(int wealthGroupId, XSSFWorkbook workbook, String sectionName) {


        String sheetName = sectionName != null ? sectionName : LIVESTOCK_OWNERSHIP;

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNum = 0;
        workbook = writeHeaderLine(rowNum,workbook, sheetName);
        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {

            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countiesEntity.getCountyId(),wealthGroupId,4);
            for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
                workbook = writeDataLines(wgLivelihoodZoneDataObject, sheet.getLastRowNum() + 1,workbook, sheetName);
            }

        }
        return workbook;
    }
}
