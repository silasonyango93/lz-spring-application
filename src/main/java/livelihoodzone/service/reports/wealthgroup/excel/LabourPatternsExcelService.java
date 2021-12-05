package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LABOUR_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LIVESTOCK_CONTRIBUTION;

@Service
public class LabourPatternsExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(LABOUR_PATTERNS);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,20000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,10000);

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
        createCell(tableHeaderRow, 0, "Activity", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Women", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Men", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Other Activities", tableHeaderStyle);
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
        LabourPatternResponses labourPatterns = wgLivelihoodZoneDataObject.getLabourPatterns();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(LABOUR_PATTERNS);

        //Labour on own farms (crop production)
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Labour on own farms (crop production)", style);
        createCell(livestockRow, 1, labourPatterns.getOwnFarmCropProduction().getWomen(), style);
        createCell(livestockRow, 2, labourPatterns.getOwnFarmCropProduction().getMen(), style);

        //Livestock husbandry
        Row husbandryRow = sheet.createRow(rowCount++);
        createCell(husbandryRow, 0, "Livestock husbandry", style);
        createCell(husbandryRow, 1, labourPatterns.getLivestockHusbandry().getWomen(), style);
        createCell(husbandryRow, 2, labourPatterns.getLivestockHusbandry().getMen(), style);

        //Waged labour on other farms
        Row wagedOtherFarmsRow = sheet.createRow(rowCount++);
        createCell(wagedOtherFarmsRow, 0, "Waged labour on other farms", style);
        createCell(wagedOtherFarmsRow, 1, labourPatterns.getWagedLabourOnFarms().getWomen(), style);
        createCell(wagedOtherFarmsRow, 2, labourPatterns.getWagedLabourOnFarms().getMen(), style);

        //Low-skilled non farm labour (including paid manual and domestic labour)
        Row lowSkilledRow = sheet.createRow(rowCount++);
        createCell(lowSkilledRow, 0, "Low-skilled non farm labour (including paid manual and domestic labour)", style);
        createCell(lowSkilledRow, 1, labourPatterns.getLowSkilledNonFarmLabour().getWomen(), style);
        createCell(lowSkilledRow, 2, labourPatterns.getLowSkilledNonFarmLabour().getMen(), style);

        //Skilled labor (carpentry, masonry, artisans, )
        Row skilledRow = sheet.createRow(rowCount++);
        createCell(skilledRow, 0, "Skilled labor (carpentry, masonry, artisans, )", style);
        createCell(skilledRow, 1, labourPatterns.getSkilledLabour().getWomen(), style);
        createCell(skilledRow, 2, labourPatterns.getSkilledLabour().getMen(), style);

        //Formal employment
        Row formalEmploymentRow = sheet.createRow(rowCount++);
        createCell(formalEmploymentRow, 0, "Formal employment ", style);
        createCell(formalEmploymentRow, 1, labourPatterns.getFormalEmployment().getWomen(), style);
        createCell(formalEmploymentRow, 2, labourPatterns.getFormalEmployment().getMen(), style);

        //Hunting and gathering
        Row huntingRow = sheet.createRow(rowCount++);
        createCell(huntingRow, 0, "Hunting and gathering", style);
        createCell(huntingRow, 1, labourPatterns.getHuntingAndGathering().getWomen(), style);
        createCell(huntingRow, 2, labourPatterns.getHuntingAndGathering().getMen(), style);

        //Fishing
        Row fishingRow = sheet.createRow(rowCount++);
        createCell(fishingRow, 0, "Fishing", style);
        createCell(fishingRow, 1, labourPatterns.getFishing().getWomen(), style);
        createCell(fishingRow, 2, labourPatterns.getFishing().getMen(), style);

        //Trading
        Row tradingRow = sheet.createRow(rowCount++);
        createCell(tradingRow, 0, "Trading", style);
        createCell(tradingRow, 1, labourPatterns.getTrading().getWomen(), style);
        createCell(tradingRow, 2, labourPatterns.getTrading().getMen(), style);

        //Domestic (unpaid) work including childcare
        Row domesticRow = sheet.createRow(rowCount++);
        createCell(domesticRow, 0, "Domestic (unpaid) work including childcare", style);
        createCell(domesticRow, 1, labourPatterns.getDomesticUnpaidWork().getWomen(), style);
        createCell(domesticRow, 2, labourPatterns.getDomesticUnpaidWork().getMen(), style);

        //Begging
        Row beggingRow = sheet.createRow(rowCount++);
        createCell(beggingRow, 0, "Begging", style);
        createCell(beggingRow, 1, labourPatterns.getBegging().getWomen(), style);
        createCell(beggingRow, 2, labourPatterns.getBegging().getMen(), style);

        //Commercial sex work
        Row sexRow = sheet.createRow(rowCount++);
        createCell(sexRow, 0, "Commercial sex work", style);
        createCell(sexRow, 1, labourPatterns.getCommercialSexWork().getWomen(), style);
        createCell(sexRow, 2, labourPatterns.getCommercialSexWork().getMen(), style);

        //Leisure, socializing and entertainment
        Row entertainmentRow = sheet.createRow(rowCount++);
        createCell(entertainmentRow, 0, "Leisure, socializing and entertainment", style);
        createCell(entertainmentRow, 1, labourPatterns.getLeisure().getWomen(), style);
        createCell(entertainmentRow, 2, labourPatterns.getLeisure().getMen(), style);

        //Transport services (bodaboda, matatus, donkey carts)
        Row bodabodaRow = sheet.createRow(rowCount++);
        createCell(bodabodaRow, 0, "Transport services (bodaboda, matatus, donkey carts)", style);
        createCell(bodabodaRow, 1, labourPatterns.getTransportServices().getWomen(), style);
        createCell(bodabodaRow, 2, labourPatterns.getTransportServices().getMen(), style);

        //Others
        Row othersRow = sheet.createRow(rowCount++);
        createCell(othersRow, 0, "Others", style);
        createCell(othersRow, 1, labourPatterns.getOthers().getWomen(), style);
        createCell(othersRow, 2, labourPatterns.getOthers().getMen(), style);
        createCell(othersRow, 3, labourPatterns.getOthers().getExtraDescription(), style);



        return workbook;
    }

    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook) {
        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,5);


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
