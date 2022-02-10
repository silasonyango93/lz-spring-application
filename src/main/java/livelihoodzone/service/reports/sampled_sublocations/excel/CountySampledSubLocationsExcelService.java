package livelihoodzone.service.reports.sampled_sublocations.excel;

import livelihoodzone.dto.CountySampledSubLocationsObject;
import livelihoodzone.dto.LivelihoodZoneSampledSubLocationsObject;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.administrative_boundaries.wards.WardsRepository;
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
public class CountySampledSubLocationsExcelService {

    @Autowired
    WardsRepository wardsRepository;

    @Autowired
    SubCountiesRepository subCountiesRepository;

    private XSSFWorkbook writeHeaderLine(int rowNum, String livelihoodZoneName, XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,15000);
        sheet.setColumnWidth(1,15000);
        sheet.setColumnWidth(2,15000);

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

        createCell(titleRow, 0, livelihoodZoneName.toUpperCase(), titleStyle);
        createCell(tableHeaderRow, 0, "SubLocation", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Ward", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Sub-County", tableHeaderStyle);
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


    private XSSFWorkbook writeDataLines(List<SubLocationEntity> subLocationEntityList, int rowCount, XSSFWorkbook workbook, String sheetName) {

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(sheetName);


        for (SubLocationEntity subLocationEntity : subLocationEntityList) {
            Row subLocationRow = sheet.createRow(rowCount++);
            createCell(subLocationRow, 0, subLocationEntity.getSubLocationName(), style);
            createCell(subLocationRow, 1, getWard(subLocationEntity).getWardName(), style);
            createCell(subLocationRow, 2, getSubCounty(getWard(subLocationEntity)).getSubCountyName(), style);
        }



        return workbook;
    }

    public XSSFWorkbook processData(CountySampledSubLocationsObject countySampledSubLocationsObject, XSSFWorkbook workbook) {


        String sheetName = "Sampled Sub-Locations";

        XSSFSheet sheet = workbook.getSheet(sheetName);

        List<LivelihoodZoneSampledSubLocationsObject> livelihoodZoneSampledSubLocationsObjectList = countySampledSubLocationsObject.getLivelihoodZoneSampledSubLocationsObjectList();

        for (LivelihoodZoneSampledSubLocationsObject livelihoodZoneSampledSubLocationsObject : livelihoodZoneSampledSubLocationsObjectList) {
            workbook = writeHeaderLine(sheet.getLastRowNum() + 4,livelihoodZoneSampledSubLocationsObject.getLivelihoodZoneName(),workbook, sheetName);
            workbook = writeDataLines(livelihoodZoneSampledSubLocationsObject.getSampledSubLocations(), sheet.getLastRowNum() + 1,workbook, sheetName);
        }
        return workbook;
    }

    public WardEntity getWard(SubLocationEntity subLocationEntity) {
        return wardsRepository.findByWardId(subLocationEntity.getWardId());
    }

    public SubCountyEntity getSubCounty(WardEntity wardEntity) {
        return subCountiesRepository.findBySubCountyId(wardEntity.getSubCountyId());
    }
}
