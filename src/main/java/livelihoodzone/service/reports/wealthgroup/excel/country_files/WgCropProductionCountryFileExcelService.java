package livelihoodzone.service.reports.wealthgroup.excel.country_files;

import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;
import livelihoodzone.dto.reports.wealthgroup.charts.WgCropContributionChartObject;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.CROP_CONTRIBUTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;

@Service
public class WgCropProductionCountryFileExcelService {

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row tableHeaderRow = sheet.createRow(rowNum);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,15000);
        sheet.setColumnWidth(2,18000);
        sheet.setColumnWidth(3,10000);
        sheet.setColumnWidth(4,10000);
        sheet.setColumnWidth(5,10000);
        sheet.setColumnWidth(6,10000);


        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(tableHeaderRow, 0, "County", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Livelihood Zone", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Crop", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Income Rank", tableHeaderStyle);
        createCell(tableHeaderRow, 4, "Income Contribution(%)", tableHeaderStyle);
        createCell(tableHeaderRow, 5, "Food Rank", tableHeaderStyle);
        createCell(tableHeaderRow, 6, "Food Contribution(%)", tableHeaderStyle);
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
        WgCropContributionChartObject wgCropContributionChartObject = wgLivelihoodZoneDataObject.getCropProduction();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);

        List<WgCropContributionResponseItem> cropContributionResponseItems = wgCropContributionChartObject.getCropContributionResponseItems();

        if (cropContributionResponseItems != null && !cropContributionResponseItems.isEmpty()) {
            for (WgCropContributionResponseItem wgCropContributionResponseItem : cropContributionResponseItems) {
                Row currentRow = sheet.createRow(rowCount++);
                createCell(currentRow, 0, wgLivelihoodZoneDataObject.getCountyName(), style);
                createCell(currentRow, 1, wgLivelihoodZoneDataObject.getLivelihoodZoneName(), style);
                createCell(currentRow, 2, wgCropContributionResponseItem.getCropModel().getCropName(), style);
                createCell(currentRow, 3, wgCropContributionResponseItem.getCashIncomeRank().getActualValue(), style);
                createCell(currentRow, 4, wgCropContributionResponseItem.getCashIncomeApproxPercentage().getActualValue(), style);
                createCell(currentRow, 5, wgCropContributionResponseItem.getFoodConsumptionRank().getActualValue(), style);
                createCell(currentRow, 6, wgCropContributionResponseItem.getFoodConsumptionApproxPercentage().getActualValue(), style);
            }
        }



        return workbook;
    }


    public XSSFWorkbook processData(int wealthGroupId, XSSFWorkbook workbook, String sectionName) {


        String sheetName = sectionName != null ? sectionName : CROP_CONTRIBUTION;

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNum = 0;
        workbook = writeHeaderLine(rowNum,workbook, sheetName);
        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {

            List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countiesEntity.getCountyId(),wealthGroupId,3);
            for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
                workbook = writeDataLines(wgLivelihoodZoneDataObject, sheet.getLastRowNum() + 1,workbook, sheetName);
            }

        }
        return workbook;
    }
}
