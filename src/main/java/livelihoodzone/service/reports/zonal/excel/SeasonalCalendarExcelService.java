package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
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

    @Autowired
    CropsRepository cropsRepository;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(SEASONAL_CALENDAR);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,18000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,4000);
        sheet.setColumnWidth(4,4000);
        sheet.setColumnWidth(5,4000);
        sheet.setColumnWidth(6,4000);
        sheet.setColumnWidth(7,4000);
        sheet.setColumnWidth(8,4000);
        sheet.setColumnWidth(9,4000);
        sheet.setColumnWidth(10,4000);
        sheet.setColumnWidth(11,4000);
        sheet.setColumnWidth(12,4000);
        sheet.setColumnWidth(13,4000);
        sheet.setColumnWidth(14,4000);
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

        //Livestock in-migration
        Row livestockInMigrationRow = sheet.createRow(rowCount++);
        createCell(livestockInMigrationRow, 0, "Livestock Migration", style);
        createCell(livestockInMigrationRow, 1, "Livestock in-migration", style);
        processRow(livestockInMigrationRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLivestockInMigration()), cellPaintStyle);

        //Livestock out-migration
        Row livestockOutMigrationRow = sheet.createRow(rowCount++);
        createCell(livestockOutMigrationRow, 0, "", style);
        createCell(livestockOutMigrationRow, 1, "Livestock out-migration", style);
        processRow(livestockOutMigrationRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLivestockOutMigration()), cellPaintStyle);

        //High Milk Production
        Row highMilkProductionRow = sheet.createRow(rowCount++);
        createCell(highMilkProductionRow, 0, "Milk Production", style);
        createCell(highMilkProductionRow, 1, "High Milk Production", style);
        processRow(highMilkProductionRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighMilkProduction()), cellPaintStyle);

        //Low Milk Production
        Row lowMilkProductionRow = sheet.createRow(rowCount++);
        createCell(lowMilkProductionRow, 0, "", style);
        createCell(lowMilkProductionRow, 1, "Low Milk Production", style);
        processRow(lowMilkProductionRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowMilkProduction()), cellPaintStyle);

        //High Calving
        Row highCalvingRow = sheet.createRow(rowCount++);
        createCell(highCalvingRow, 0, "Calving", style);
        createCell(highCalvingRow, 1, "High calving", style);
        processRow(highCalvingRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighCalving()), cellPaintStyle);

        //Low Calving
        Row lowCalvingRow = sheet.createRow(rowCount++);
        createCell(lowCalvingRow, 0, "", style);
        createCell(lowCalvingRow, 1, "Low calving", style);
        processRow(lowCalvingRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowCalving()), cellPaintStyle);

        //High kidding
        Row highKiddingRow = sheet.createRow(rowCount++);
        createCell(highKiddingRow, 0, "Kidding", style);
        createCell(highKiddingRow, 1, "High kidding", style);
        processRow(highKiddingRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighKidding()), cellPaintStyle);

        //Low kidding
        Row lowKiddingRow = sheet.createRow(rowCount++);
        createCell(lowKiddingRow, 0, "", style);
        createCell(lowKiddingRow, 1, "Low kidding", style);
        processRow(lowKiddingRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowKidding()), cellPaintStyle);

        //High Food prices
        Row highFoodPricesRow = sheet.createRow(rowCount++);
        createCell(highFoodPricesRow, 0, "Food prices", style);
        createCell(highFoodPricesRow, 1, "High food prices", style);
        processRow(highFoodPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighFoodPrices()), cellPaintStyle);

        //Medium Food prices
        Row mediumFoodPricesRow = sheet.createRow(rowCount++);
        createCell(mediumFoodPricesRow, 0, "", style);
        createCell(mediumFoodPricesRow, 1, "Medium food prices", style);
        processRow(mediumFoodPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getMediumFoodPrices()), cellPaintStyle);

        //Low Food prices
        Row lowFoodPricesRow = sheet.createRow(rowCount++);
        createCell(lowFoodPricesRow, 0, "", style);
        createCell(lowFoodPricesRow, 1, "High food prices", style);
        processRow(lowFoodPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowFoodPrices()), cellPaintStyle);

        //High livestock prices
        Row highLivestockPricesRow = sheet.createRow(rowCount++);
        createCell(highLivestockPricesRow, 0, "Livestock prices", style);
        createCell(highLivestockPricesRow, 1, "High livestock prices", style);
        processRow(highLivestockPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighLivestockPrices()), cellPaintStyle);

        //Medium livestock prices
        Row mediumLivestockPricesRow = sheet.createRow(rowCount++);
        createCell(mediumLivestockPricesRow, 0, "", style);
        createCell(mediumLivestockPricesRow, 1, "Medium livestock prices", style);
        processRow(mediumLivestockPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getMediumLivestockPrices()), cellPaintStyle);

        //Low livestock prices
        Row lowLivestockPricesRow = sheet.createRow(rowCount++);
        createCell(lowLivestockPricesRow, 0, "", style);
        createCell(lowLivestockPricesRow, 1, "Low livestock prices", style);
        processRow(lowLivestockPricesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowLivestockPrices()), cellPaintStyle);

        //High casual labour
        Row highCasualLabourRow = sheet.createRow(rowCount++);
        createCell(highCasualLabourRow, 0, "Agricultural Casual labour availability", style);
        createCell(highCasualLabourRow, 1, "High agricultural casual labour", style);
        processRow(highCasualLabourRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighCasualLabourAvailability()), cellPaintStyle);

        //Low casual labour
        Row lowCasualLabourRow = sheet.createRow(rowCount++);
        createCell(lowCasualLabourRow, 0, "", style);
        createCell(lowCasualLabourRow, 1, "Low agricultural casual labour", style);
        processRow(lowCasualLabourRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowCasualLabourAvailability()), cellPaintStyle);

        //Non-agric High casual labour
        Row highNonAgricRow = sheet.createRow(rowCount++);
        createCell(highNonAgricRow, 0, "Non-agricultural Casual labour availability", style);
        createCell(highNonAgricRow, 1, "High Non-agricultural Casual labour availability", style);
        processRow(highNonAgricRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getNonAgricHighCasualLabourAvailability()), cellPaintStyle);

        //Non-agric Low casual labour
        Row lowNonAgricRow = sheet.createRow(rowCount++);
        createCell(lowNonAgricRow, 0, "", style);
        createCell(lowNonAgricRow, 1, "Low Non-agricultural Casual labour availability", style);
        processRow(lowNonAgricRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getNonAgricLowCasualLabourAvailability()), cellPaintStyle);

        //High casual labour wages
        Row highWagesRow = sheet.createRow(rowCount++);
        createCell(highWagesRow, 0, "Casual labour wages", style);
        createCell(highWagesRow, 1, "High casual labour wages", style);
        processRow(highWagesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighCasualLabourWages()), cellPaintStyle);

        //Low casual labour wages
        Row lowWagesRow = sheet.createRow(rowCount++);
        createCell(lowWagesRow, 0, "", style);
        createCell(lowWagesRow, 1, "Low casual labour wages", style);
        processRow(lowWagesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowCasualLabourWages()), cellPaintStyle);

        //High remittances
        Row highRemittancesRow = sheet.createRow(rowCount++);
        createCell(highRemittancesRow, 0, "Remittances", style);
        createCell(highRemittancesRow, 1, "High remittances", style);
        processRow(highRemittancesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighRemittances()), cellPaintStyle);

        //Low remittances
        Row lowRemittancesRow = sheet.createRow(rowCount++);
        createCell(lowRemittancesRow, 0, "", style);
        createCell(lowRemittancesRow, 1, "Low remittances", style);
        processRow(lowRemittancesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowRemittances()), cellPaintStyle);

        //High fishing
        Row fishingRow = sheet.createRow(rowCount++);
        createCell(fishingRow, 0, "Fishing/fish sales", style);
        createCell(fishingRow, 1, "High fishing/fish sales", style);
        processRow(fishingRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighFish()), cellPaintStyle);

        //Low fishing
        Row lowFishRow = sheet.createRow(rowCount++);
        createCell(lowFishRow, 0, "", style);
        createCell(lowFishRow, 1, "Low fishing/fish sales", style);
        processRow(lowFishRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowFish()), cellPaintStyle);

        //High market access
        Row highMarketAccessRow = sheet.createRow(rowCount++);
        createCell(highMarketAccessRow, 0, "Market access", style);
        createCell(highMarketAccessRow, 1, "High market access", style);
        processRow(highMarketAccessRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighMarketAccess()), cellPaintStyle);

        //Low market access
        Row lowMarketAccessRow = sheet.createRow(rowCount++);
        createCell(lowMarketAccessRow, 0, "", style);
        createCell(lowMarketAccessRow, 1, "Low market access", style);
        processRow(lowMarketAccessRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighMarketAccess()), cellPaintStyle);

        //High disease
        Row highDiseaseRow = sheet.createRow(rowCount++);
        createCell(highDiseaseRow, 0, "Disease outbreak", style);
        createCell(highDiseaseRow, 1, "High disease outbreak", style);
        processRow(highDiseaseRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getHighDiseaseOutbreak()), cellPaintStyle);

        //Low disease
        Row lowDiseaseRow = sheet.createRow(rowCount++);
        createCell(lowDiseaseRow, 0, "", style);
        createCell(lowDiseaseRow, 1, "Low disease outbreak", style);
        processRow(lowDiseaseRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLowDiseaseOutbreak()), cellPaintStyle);

        //Water stress
        Row waterStressRow = sheet.createRow(rowCount++);
        createCell(waterStressRow, 0, "Others", style);
        createCell(waterStressRow, 1, "Water stress", style);
        processRow(waterStressRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getWaterStress()), cellPaintStyle);

        //Conflict risk
        Row conflictRiskRow = sheet.createRow(rowCount++);
        createCell(conflictRiskRow, 0, "", style);
        createCell(conflictRiskRow, 1, "Conflict risk e.g cattle rustling", style);
        processRow(conflictRiskRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getConflictRisks()), cellPaintStyle);

        //Ceremonies
        Row ceremoniesRow = sheet.createRow(rowCount++);
        createCell(ceremoniesRow, 0, "", style);
        createCell(ceremoniesRow, 1, "Ceremonies e.g marriages, weddings, circumcision", style);
        processRow(ceremoniesRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getCeremonies()), cellPaintStyle);

        //Lean seasons
        Row leanSeasonsRow = sheet.createRow(rowCount++);
        createCell(leanSeasonsRow, 0, "", style);
        createCell(leanSeasonsRow, 1, "Lean seasons(peak)", style);
        processRow(leanSeasonsRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getLeanSeasons()), cellPaintStyle);

        //Food security
        Row foodSecurityRow = sheet.createRow(rowCount++);
        createCell(foodSecurityRow, 0, "", style);
        createCell(foodSecurityRow, 1, "Food security assessment(ASALs)", style);
        processRow(foodSecurityRow, retrieveColumnsToBePainted(livelihoodZoneSeasonsResponses.getFoodSecurityAssessments()), cellPaintStyle);

        List<WgCropProductionResponseItem> wgCropProductionResponseItemList = livelihoodZoneSeasonsResponses.getWgCropProductionResponseItemList();

        for (WgCropProductionResponseItem wgCropProductionResponseItem : wgCropProductionResponseItemList) {
            //Land Preparation
            Row landPreparationRow = sheet.createRow(rowCount++);
            createCell(landPreparationRow, 0, wgCropProductionResponseItem.getCrop().getCropName(), style);
            createCell(landPreparationRow, 1, "Land Preparation", style);
            processRow(landPreparationRow, retrieveColumnsToBePainted(wgCropProductionResponseItem.getLandPreparationPeriod()), cellPaintStyle);

            //Planting
            Row plantingRow = sheet.createRow(rowCount++);
            createCell(plantingRow, 0, wgCropProductionResponseItem.getCrop().getCropName(), style);
            createCell(plantingRow, 1, "Planting", style);
            processRow(plantingRow, retrieveColumnsToBePainted(wgCropProductionResponseItem.getPlantingPeriod()), cellPaintStyle);

            //Harvesting
            Row harvestingRow = sheet.createRow(rowCount++);
            createCell(harvestingRow, 0, wgCropProductionResponseItem.getCrop().getCropName(), style);
            createCell(harvestingRow, 1, "Harvesting", style);
            processRow(harvestingRow, retrieveColumnsToBePainted(wgCropProductionResponseItem.getHarvestingPeriod()), cellPaintStyle);
        }



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
            rowNum = rowNum + 70;
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
                columnIndices.add(5);
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
