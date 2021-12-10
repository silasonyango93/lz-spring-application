package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.service.reports.zonal.ZoneLevelChartsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.SEASONAL_CALENDAR;

@Service
public class SeasonalCalendarExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(SEASONAL_CALENDAR);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,10000);
        sheet.setColumnWidth(4,10000);
        sheet.setColumnWidth(5,10000);
        sheet.setColumnWidth(6,10000);
        sheet.setColumnWidth(7,10000);
        sheet.setColumnWidth(8,10000);
        sheet.setColumnWidth(9,10000);
        sheet.setColumnWidth(10,10000);
        sheet.setColumnWidth(11,10000);
        sheet.setColumnWidth(12,10000);
        sheet.setColumnWidth(13,10000);
        sheet.setColumnWidth(14,10000);
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

        createCell(headerRow, 0, "Season", tableHeaderStyle);
        createCell(headerRow, 1, "Activity", tableHeaderStyle);
        createCell(headerRow, 2, "January", tableHeaderStyle);
        createCell(headerRow, 3, "February", tableHeaderStyle);
        createCell(headerRow, 4, "March", tableHeaderStyle);
        createCell(headerRow, 5, "April", tableHeaderStyle);
        createCell(headerRow, 6, "May", tableHeaderStyle);
        createCell(headerRow, 7, "June", tableHeaderStyle);
        createCell(headerRow, 8, "July", tableHeaderStyle);
        createCell(headerRow, 9, "August", tableHeaderStyle);
        createCell(headerRow, 10, "September", tableHeaderStyle);
        createCell(headerRow, 11, "October", tableHeaderStyle);
        createCell(headerRow, 12, "November", tableHeaderStyle);
        createCell(headerRow, 13, "December", tableHeaderStyle);
        createCell(headerRow, 14, "Not Applicable", tableHeaderStyle);

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

        LzSeasonsResponses livelihoodZoneSeasonsResponses = lzLivelihoodZoneDataObject.getLivelihoodZoneSeasonsResponses();
        XSSFSheet sheet = workbook.getSheet(SEASONAL_CALENDAR);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        CellStyle cellPaintStyle = workbook.createCellStyle();
        cellPaintStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
        cellPaintStyle.setFillPattern(FillPatternType.BIG_SPOTS);


        //Dry season
        Row drySeasonRow = sheet.createRow(rowCount++);
        createCell(drySeasonRow, 0, "Seasons", style);
        createCell(drySeasonRow, 1, "Dry season", style);
        processRow(drySeasonRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getDry()), cellPaintStyle);

        //Long rains
        Row longRainsRow = sheet.createRow(rowCount++);
        createCell(longRainsRow, 0, "", style);
        createCell(longRainsRow, 1, "Long rains", style);
        processRow(longRainsRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLongRains()), cellPaintStyle);

        //Short rains
        Row shortRainsRow = sheet.createRow(rowCount++);
        createCell(shortRainsRow, 0, "", style);
        createCell(shortRainsRow, 1, "Short rains", style);
        processRow(shortRainsRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getShortRains()), cellPaintStyle);

        return workbook;
    }


    private void paintCell(Row row, int columnCount, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        cell.setCellStyle(style);
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,9);
        int rowNum = 0;
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,lzLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(lzLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 22;
        }
        return workbook;
    }

    private List<Number> retrieveColumnsToBePainted(List<MonthsEntity> monthsEntityList) {
        List<Number> columnIndices = new ArrayList<>();
        for (MonthsEntity monthsEntity : monthsEntityList) {
            if (monthsEntity.getMonthCode() == 1) {
                columnIndices.add(2);
            }
            if (monthsEntity.getMonthCode() == 2) {
                columnIndices.add(3);
            }
            if (monthsEntity.getMonthCode() == 3) {
                columnIndices.add(4);
            }
            if (monthsEntity.getMonthCode() == 4) {
                columnIndices.add(4);
            }
            if (monthsEntity.getMonthCode() == 5) {
                columnIndices.add(6);
            }
            if (monthsEntity.getMonthCode() == 6) {
                columnIndices.add(7);
            }
            if (monthsEntity.getMonthCode() == 7) {
                columnIndices.add(8);
            }
            if (monthsEntity.getMonthCode() == 8) {
                columnIndices.add(9);
            }
            if (monthsEntity.getMonthCode() == 9) {
                columnIndices.add(10);
            }
            if (monthsEntity.getMonthCode() == 10) {
                columnIndices.add(11);
            }
            if (monthsEntity.getMonthCode() == 11) {
                columnIndices.add(12);
            }
            if (monthsEntity.getMonthCode() == 12) {
                columnIndices.add(13);
            }
            if (monthsEntity.getMonthCode() == 0) {
                columnIndices.add(14);
            }
        }
        return columnIndices;
    }


    private void processRow(Row row, List<Number> columnIndices, CellStyle style) {
        for (Number currentColumnIndex : columnIndices) {
            paintCell(row, currentColumnIndex.intValue(), style);
        }
    }
}
