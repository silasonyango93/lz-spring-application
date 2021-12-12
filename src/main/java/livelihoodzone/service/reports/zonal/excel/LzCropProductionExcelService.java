package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LZ_CROP_PRODUCTION;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.LZ_HAZARDS;

@Service
public class LzCropProductionExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(LZ_CROP_PRODUCTION);
        sheet.setColumnWidth(0,13000);
        sheet.setColumnWidth(1,15000);
        sheet.setColumnWidth(2,15000);
        sheet.setColumnWidth(3,15000);
        sheet.setColumnWidth(4,15000);
        sheet.setColumnWidth(5,15000);
        sheet.setColumnWidth(6,15000);
        sheet.setColumnWidth(7,15000);
        sheet.setColumnWidth(8,15000);
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

        createCell(headerRow, 0, "Name of Crop", tableHeaderStyle);
        createCell(headerRow, 1, "Long rains rainfed cultivated area(%)", tableHeaderStyle);
        createCell(headerRow, 2, "Long rains rainfed average yield(Kg/Ha)", tableHeaderStyle);
        createCell(headerRow, 3, "Long rains irrigated cultivated area(%)", tableHeaderStyle);
        createCell(headerRow, 4, "Long rains irrigated average yield(Kg/Ha)", tableHeaderStyle);

        createCell(headerRow, 5, "Short rains rainfed cultivated area(%)", tableHeaderStyle);
        createCell(headerRow, 6, "Short rains rainfed average yield(Kg/Ha)", tableHeaderStyle);
        createCell(headerRow, 7, "Short rains irrigated cultivated area(%)", tableHeaderStyle);
        createCell(headerRow, 8, "Short rains irrigated average yield(Kg/Ha)", tableHeaderStyle);

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

        LzCropProductionResponses lzCropProductionResponses = lzLivelihoodZoneDataObject.getLzCropProductionResponses();
        XSSFSheet sheet = workbook.getSheet(LZ_CROP_PRODUCTION);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        List<WgCropProductionResponseItem> cropProductionResponses = lzCropProductionResponses.getCropProductionResponses();

        if (cropProductionResponses != null && !cropProductionResponses.isEmpty()) {

            for (WgCropProductionResponseItem wgCropProductionResponseItem : cropProductionResponses) {
                Row cropRow = sheet.createRow(rowCount++);
                createCell(cropRow, 0, wgCropProductionResponseItem.getCrop().getCropName(), style);
                createCell(cropRow, 1, wgCropProductionResponseItem.getLongRainsSeason().getRainfedCultivatedAreaPercentage().getValue(), style);
                createCell(cropRow, 2, wgCropProductionResponseItem.getLongRainsSeason().getRainfedAverageYieldPerHa().getValue(), style);
                createCell(cropRow, 3, wgCropProductionResponseItem.getLongRainsSeason().getIrrigatedCultivatedArea().getValue(), style);
                createCell(cropRow, 4, wgCropProductionResponseItem.getLongRainsSeason().getIrrigatedAverageYieldPerHa().getValue(), style);

                createCell(cropRow, 5, wgCropProductionResponseItem.getShortRainsSeason().getRainfedCultivatedAreaPercentage().getValue(), style);
                createCell(cropRow, 6, wgCropProductionResponseItem.getShortRainsSeason().getRainfedAverageYieldPerHa().getValue(), style);
                createCell(cropRow, 7, wgCropProductionResponseItem.getShortRainsSeason().getIrrigatedCultivatedArea().getValue(), style);
                createCell(cropRow, 8, wgCropProductionResponseItem.getShortRainsSeason().getIrrigatedAverageYieldPerHa().getValue(), style);
            }
        }

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,3);
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
