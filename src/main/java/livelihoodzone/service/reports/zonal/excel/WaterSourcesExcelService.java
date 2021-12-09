package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MAIN_WATER_SOURCES;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WEALTH_GROUP_POPULATION_DISTRIBUTION;

@Service
public class WaterSourcesExcelService {

    @Autowired
    ZoneLevelChartsService zoneLevelChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(MAIN_WATER_SOURCES);
        sheet.setColumnWidth(0,10000);
        sheet.setColumnWidth(1,10000);
        sheet.setColumnWidth(2,10000);
        sheet.setColumnWidth(3,10000);
        Row titleRow = sheet.createRow(rowNum);

        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        createCell(titleRow, 0, lzLivelihoodZoneDataObject.getLivelihoodZoneName().toUpperCase(), titleStyle);

        Row headerRow = sheet.createRow(rowNum + 2);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);

        createCell(headerRow, 0, "Water Source", style);
        createCell(headerRow, 1, "Wet Season", style);
        createCell(headerRow, 2, "Dry Season", style);
        createCell(headerRow, 3, "Others Description", style);

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

        WaterSourcesResponsesDto waterSourceResponses = lzLivelihoodZoneDataObject.getWaterSourceResponses();
        XSSFSheet sheet = workbook.getSheet(MAIN_WATER_SOURCES);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        //Rivers
        Row riversRow = sheet.createRow(rowCount++);
        createCell(riversRow, 0, "Rivers", style);
        createCell(riversRow, 1, waterSourceResponses.getRivers().getWetSeasonPopulation(), style);
        createCell(riversRow, 2, waterSourceResponses.getRivers().getDrySeasonPopulationResponse(), style);

        //Traditional river wells
        Row traditionalRiversRow = sheet.createRow(rowCount++);
        createCell(traditionalRiversRow, 0, "Traditional river wells", style);
        createCell(traditionalRiversRow, 1, waterSourceResponses.getTraditionalRiversWells().getWetSeasonPopulation(), style);
        createCell(traditionalRiversRow, 2, waterSourceResponses.getTraditionalRiversWells().getDrySeasonPopulationResponse(), style);

        //Natural ponds
        Row pondsRow = sheet.createRow(rowCount++);
        createCell(pondsRow, 0, "Natural ponds", style);
        createCell(pondsRow, 1, waterSourceResponses.getNaturalPonds().getWetSeasonPopulation(), style);
        createCell(pondsRow, 2, waterSourceResponses.getNaturalPonds().getDrySeasonPopulationResponse(), style);

        //Pans and dams
        Row damsRow = sheet.createRow(rowCount++);
        createCell(damsRow, 0, "Pans and dams", style);
        createCell(damsRow, 1, waterSourceResponses.getPansAndDams().getWetSeasonPopulation(), style);
        createCell(damsRow, 2, waterSourceResponses.getPansAndDams().getDrySeasonPopulationResponse(), style);

        //Shallow wells
        Row wellsRow = sheet.createRow(rowCount++);
        createCell(wellsRow, 0, "Shallow wells", style);
        createCell(wellsRow, 1, waterSourceResponses.getShallowWells().getWetSeasonPopulation(), style);
        createCell(wellsRow, 2, waterSourceResponses.getShallowWells().getDrySeasonPopulationResponse(), style);

        //Boreholes
        Row boreHolesRow = sheet.createRow(rowCount++);
        createCell(boreHolesRow, 0, "Boreholes", style);
        createCell(boreHolesRow, 1, waterSourceResponses.getBoreholes().getWetSeasonPopulation(), style);
        createCell(boreHolesRow, 2, waterSourceResponses.getBoreholes().getDrySeasonPopulationResponse(), style);

        //Springs
        Row springsRow = sheet.createRow(rowCount++);
        createCell(springsRow, 0, "Springs", style);
        createCell(springsRow, 1, waterSourceResponses.getSprings().getWetSeasonPopulation(), style);
        createCell(springsRow, 2, waterSourceResponses.getSprings().getDrySeasonPopulationResponse(), style);

        //Lakes
        Row lakesRow = sheet.createRow(rowCount++);
        createCell(lakesRow, 0, "Lakes", style);
        createCell(lakesRow, 1, waterSourceResponses.getLakes().getWetSeasonPopulation(), style);
        createCell(lakesRow, 2, waterSourceResponses.getLakes().getDrySeasonPopulationResponse(), style);

        //Rock catchments
        Row rockCatchmentsRow = sheet.createRow(rowCount++);
        createCell(rockCatchmentsRow, 0, "Rock catchments", style);
        createCell(rockCatchmentsRow, 1, waterSourceResponses.getRockCatchments().getWetSeasonPopulation(), style);
        createCell(rockCatchmentsRow, 2, waterSourceResponses.getRockCatchments().getDrySeasonPopulationResponse(), style);

        //Piped water systems
        Row pipedRow = sheet.createRow(rowCount++);
        createCell(pipedRow, 0, "Piped water systems", style);
        createCell(pipedRow, 1, waterSourceResponses.getPipedWater().getWetSeasonPopulation(), style);
        createCell(pipedRow, 2, waterSourceResponses.getPipedWater().getDrySeasonPopulationResponse(), style);

        //Water trucking
        Row truckingRow = sheet.createRow(rowCount++);
        createCell(truckingRow, 0, "Water trucking", style);
        createCell(truckingRow, 1, waterSourceResponses.getWaterTrucking().getWetSeasonPopulation(), style);
        createCell(truckingRow, 2, waterSourceResponses.getWaterTrucking().getDrySeasonPopulationResponse(), style);

        //Roof catchments
        Row roofCatchmentRow = sheet.createRow(rowCount++);
        createCell(roofCatchmentRow, 0, "Roof catchments", style);
        createCell(roofCatchmentRow, 1, waterSourceResponses.getRoofCatchments().getWetSeasonPopulation(), style);
        createCell(roofCatchmentRow, 2, waterSourceResponses.getRoofCatchments().getDrySeasonPopulationResponse(), style);

        //Others
        Row othersRow = sheet.createRow(rowCount++);
        createCell(othersRow, 0, "Others", style);
        createCell(othersRow, 1, waterSourceResponses.getOthers().getWetSeasonPopulation(), style);
        createCell(othersRow, 2, waterSourceResponses.getOthers().getDrySeasonPopulationResponse(), style);
        createCell(othersRow, 3, waterSourceResponses.getOthers().getExtraDescription(), style);

        return workbook;
    }

    public XSSFWorkbook processData(int countyId, XSSFWorkbook workbook) {
        List<LzLivelihoodZoneDataObject> lzLivelihoodZoneDataObjectList = zoneLevelChartsService.prepareZoneLevelChart(countyId,4);
        int rowNum = 0;
        for (LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject : lzLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,lzLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(lzLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 22;
        }
        return workbook;
    }
}
