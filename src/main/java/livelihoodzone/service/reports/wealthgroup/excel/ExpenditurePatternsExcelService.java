package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.expenditurepatterns.ExpenditurePatternsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.EXPENDITURE_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LABOUR_PATTERNS;

@Service
public class ExpenditurePatternsExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(EXPENDITURE_PATTERNS);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,20000);
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
        createCell(tableHeaderRow, 0, "Expenditure Item", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Percent Expenditure", tableHeaderStyle);
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
        ExpenditurePatternsResponses expenditurePatterns = wgLivelihoodZoneDataObject.getExpenditurePatterns();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(EXPENDITURE_PATTERNS);

        //Maize and maize flour
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Maize and maize flour", style);
        createCell(livestockRow, 1, expenditurePatterns.getMaizeAndMaizeFlour(), style);

        //Other cereals
        Row otherCerealsRow = sheet.createRow(rowCount++);
        createCell(otherCerealsRow, 0, "Other cereals", style);
        createCell(otherCerealsRow, 1, expenditurePatterns.getOtherCereals(), style);

        //Pulses
        Row pulsesRow = sheet.createRow(rowCount++);
        createCell(pulsesRow, 0, "Pulses", style);
        createCell(pulsesRow, 1, expenditurePatterns.getPulses(), style);

        //Roots and tubers
        Row rootsRow = sheet.createRow(rowCount++);
        createCell(rootsRow, 0, "Roots and tubers", style);
        createCell(rootsRow, 1, expenditurePatterns.getRootsAndTubers(), style);

        //Vegetables and fruits
        Row vegetablesRow = sheet.createRow(rowCount++);
        createCell(vegetablesRow, 0, "Vegetables and fruits", style);
        createCell(vegetablesRow, 1, expenditurePatterns.getVegetablesAndFruits(), style);

        //Fish and sea food
        Row fishRow = sheet.createRow(rowCount++);
        createCell(fishRow, 0, "Fish and sea food", style);
        createCell(fishRow, 1, expenditurePatterns.getFishandSeaFood(), style);

        //Meat
        Row meatRow = sheet.createRow(rowCount++);
        createCell(meatRow, 0, "Meat", style);
        createCell(meatRow, 1, expenditurePatterns.getMeat(), style);

        //Milk
        Row milkRow = sheet.createRow(rowCount++);
        createCell(milkRow, 0, "Milk", style);
        createCell(milkRow, 1, expenditurePatterns.getMilk(), style);

        //Eggs
        Row eggRow = sheet.createRow(rowCount++);
        createCell(eggRow, 0, "Eggs", style);
        createCell(eggRow, 1, expenditurePatterns.getEggs(), style);

        //Oils and fats
        Row oilsRow = sheet.createRow(rowCount++);
        createCell(oilsRow, 0, "Oils and fats", style);
        createCell(oilsRow, 1, expenditurePatterns.getOilsAndFats(), style);

        //Other Foods (e.g matoke and plantains)
        Row otherFoodsRow = sheet.createRow(rowCount++);
        createCell(otherFoodsRow, 0, "Other Foods (e.g matoke and plantains)", style);
        createCell(otherFoodsRow, 1, expenditurePatterns.getOtherFoods(), style);


        //Blank
        Row blank1 = sheet.createRow(rowCount++);

        //Blank
        Row blank2 = sheet.createRow(rowCount++);


        //School fees
        Row feesRow = sheet.createRow(rowCount++);
        createCell(feesRow, 0, "School fees", style);
        createCell(feesRow, 1, expenditurePatterns.getSchoolFees(), style);

        //Drugs and medical care
        Row drugsRow = sheet.createRow(rowCount++);
        createCell(drugsRow, 0, "Drugs and medical care", style);
        createCell(drugsRow, 1, expenditurePatterns.getDrugsAndMedicalCare(), style);

        //Clothing and beauty products
        Row clothsRow = sheet.createRow(rowCount++);
        createCell(clothsRow, 0, "Clothing and beauty products", style);
        createCell(clothsRow, 1, expenditurePatterns.getClothingAndBeautyProducts(), style);

        //House rent
        Row rentRow = sheet.createRow(rowCount++);
        createCell(rentRow, 0, "House rent", style);
        createCell(rentRow, 1, expenditurePatterns.getHouseRent(), style);

        //Communication expenses
        Row commRow = sheet.createRow(rowCount++);
        createCell(commRow, 0, "Communication expenses", style);
        createCell(commRow, 1, expenditurePatterns.getCommunicationExpenses(), style);

        //Farm inputs
        Row farmInputsRow = sheet.createRow(rowCount++);
        createCell(farmInputsRow, 0, "Farm inputs", style);
        createCell(farmInputsRow, 1, expenditurePatterns.getFarmInputs(), style);

        //Travel related expenses
        Row travelRow = sheet.createRow(rowCount++);
        createCell(travelRow, 0, "Travel related expenses", style);
        createCell(travelRow, 1, expenditurePatterns.getTravelRelatedExpenses(), style);

        //Leisure and entertainment
        Row leisureRow = sheet.createRow(rowCount++);
        createCell(leisureRow, 0, "Leisure and entertainment", style);
        createCell(leisureRow, 1, expenditurePatterns.getLeisureAndEntertainment(), style);

        //Electricity bills
        Row elecRow = sheet.createRow(rowCount++);
        createCell(elecRow, 0, "Electricity bills", style);
        createCell(elecRow, 1, expenditurePatterns.getElectricityBills(), style);

        //Social obligation
        Row socialObligationRow = sheet.createRow(rowCount++);
        createCell(socialObligationRow, 0, "Social obligation", style);
        createCell(socialObligationRow, 1, expenditurePatterns.getSocialObligation(), style);

        //Milling costs
        Row millingRow = sheet.createRow(rowCount++);
        createCell(millingRow, 0, "Milling costs", style);
        createCell(millingRow, 1, expenditurePatterns.getMillingCosts(), style);

        //Cooking fuel
        Row cookingRow = sheet.createRow(rowCount++);
        createCell(cookingRow, 0, "Cooking fuel", style);
        createCell(cookingRow, 1, expenditurePatterns.getCookingFuel(), style);

        //Saving and investments
        Row savingsRow = sheet.createRow(rowCount++);
        createCell(savingsRow, 0, "Saving and investments", style);
        createCell(savingsRow, 1, expenditurePatterns.getSavingsAndInvestments(), style);

        //Loan repayments
        Row loanRow = sheet.createRow(rowCount++);
        createCell(loanRow, 0, "Loan repayments", style);
        createCell(loanRow, 1, expenditurePatterns.getLoanRepayments(), style);

        //Livestock Drugs/Vaccinations/Veterinary Services
        Row livestockDrugsRow = sheet.createRow(rowCount++);
        createCell(livestockDrugsRow, 0, "Livestock Drugs/Vaccinations/Veterinary Services", style);
        createCell(livestockDrugsRow, 1, expenditurePatterns.getLivestockDrugs(), style);

        //Purchase of Water/Water Bills
        Row waterRow = sheet.createRow(rowCount++);
        createCell(waterRow, 0, "Purchase of Water/Water Bills", style);
        createCell(waterRow, 1, expenditurePatterns.getWaterPurchase(), style);

        //Soaps and other detergents
        Row soapsRow = sheet.createRow(rowCount++);
        createCell(soapsRow, 0, "Soaps and other detergents", style);
        createCell(soapsRow, 1, expenditurePatterns.getSoaps(), style);

        //Hiring of labour for farm/herding
        Row farmLabourRow = sheet.createRow(rowCount++);
        createCell(farmLabourRow, 0, "Hiring of labour for farm/herding", style);
        createCell(farmLabourRow, 1, expenditurePatterns.getFarmLabour(), style);


        return workbook;
    }


    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook) {
        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,6);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 34;
        }
        return workbook;
    }
}
