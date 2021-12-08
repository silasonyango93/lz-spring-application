package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.dto.questionnaire.wealthgroup.constraints.*;
import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
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

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.MIGRATION_PATTERNS;
import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WG_CONSTRAINTS;

@Service
public class WgConstraintsExcelService {

    @Autowired
    WealthGroupChartsService wealthGroupChartsService;

    private XSSFWorkbook writeHeaderLine(int rowNum, WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(WG_CONSTRAINTS);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0,20000);
        sheet.setColumnWidth(1,20000);
        sheet.setColumnWidth(2,10000);

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
        createCell(tableHeaderRow, 0, "Source of Income", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Constraint", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Rank", tableHeaderStyle);
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
        ConstraintsResponses constraintsToMainEconomicActivities = wgLivelihoodZoneDataObject.getConstraintsToMainEconomicActivities();

        WagedLabourIncomeConstraintsResponses wagedLabourIncomeConstraintsResponses = constraintsToMainEconomicActivities.getWagedLabourIncomeConstraintsResponses();
        CropProductionIncomeConstraintsResponses cropProductionIncomeConstraintsResponses = constraintsToMainEconomicActivities.getCropProductionIncomeConstraintsResponses();
        LivestockProductionIncomeConstraintsResponses livestockProductionIncomeConstraintsResponses = constraintsToMainEconomicActivities.getLivestockProductionIncomeConstraintsResponses();
        FishingIncomeConstraintsResponses fishingIncomeConstraintsResponses = constraintsToMainEconomicActivities.getFishingIncomeConstraintsResponses();
        NaturalResourceIncomeConstraintsResponses naturalResourceIncomeConstraintsResponses = constraintsToMainEconomicActivities.getNaturalResourceIncomeConstraintsResponses();
        SmallEnterpriseIncomeConstraintsResponses smallEnterpriseIncomeConstraintsResponses = constraintsToMainEconomicActivities.getSmallEnterpriseIncomeConstraintsResponses();

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);


        XSSFSheet sheet = workbook.getSheet(WG_CONSTRAINTS);

        //Low educational attainment/low skills
        Row livestockRow = sheet.createRow(rowCount++);
        createCell(livestockRow, 0, "Income from waged labour", style);
        createCell(livestockRow, 1, "Low educational attainment/low skills", style);
        createCell(livestockRow, 2, wagedLabourIncomeConstraintsResponses.getLowEducation(), style);

        //Poor health, frequent Illness
        Row poorHealthRow = sheet.createRow(rowCount++);
        createCell(poorHealthRow, 0, "", style);
        createCell(poorHealthRow, 1, "Poor health, frequent Illness", style);
        createCell(poorHealthRow, 2, wagedLabourIncomeConstraintsResponses.getPoorHealth(), style);

        //Too few jobs, lack of demand for labour
        Row fewJobsRow = sheet.createRow(rowCount++);
        createCell(fewJobsRow, 0, "", style);
        createCell(fewJobsRow, 1, "Too few jobs, lack of demand for labour", style);
        createCell(fewJobsRow, 2, wagedLabourIncomeConstraintsResponses.getFewJobs(), style);

        //Too much time spent on farm
        Row farmTimeRow = sheet.createRow(rowCount++);
        createCell(farmTimeRow, 0, "", style);
        createCell(farmTimeRow, 1, "Too much time spent on farm", style);
        createCell(farmTimeRow, 2, wagedLabourIncomeConstraintsResponses.getTooMuchFarmTime(), style);

        //Low average wage rates
        Row wageRatesRow = sheet.createRow(rowCount++);
        createCell(wageRatesRow, 0, "", style);
        createCell(wageRatesRow, 1, "Low average wage rates", style);
        createCell(wageRatesRow, 2, wagedLabourIncomeConstraintsResponses.getLowAverageWageRates(), style);


        Row blank1 = sheet.createRow(rowCount++);
        Row blank2 = sheet.createRow(rowCount++);



        //Small land holdings
        Row landHoldingsRow = sheet.createRow(rowCount++);
        createCell(landHoldingsRow, 0, "Income/Consumption from Crop Production", style);
        createCell(landHoldingsRow, 1, "Small land holdings", style);
        createCell(landHoldingsRow, 2, cropProductionIncomeConstraintsResponses.getSmallLandHoldings(), style);

        //Lack of credit
        Row lackOfCreditRow = sheet.createRow(rowCount++);
        createCell(lackOfCreditRow, 0, "", style);
        createCell(lackOfCreditRow, 1, "Lack of credit", style);
        createCell(lackOfCreditRow, 2, cropProductionIncomeConstraintsResponses.getLackOfCredit(), style);

        //High input costs
        Row highInputcostsRow = sheet.createRow(rowCount++);
        createCell(highInputcostsRow, 0, "", style);
        createCell(highInputcostsRow, 1, "High input costs", style);
        createCell(highInputcostsRow, 2, cropProductionIncomeConstraintsResponses.getHighInputCost(), style);

        //Low land fertility
        Row landFertilityRow = sheet.createRow(rowCount++);
        createCell(landFertilityRow, 0, "", style);
        createCell(landFertilityRow, 1, "Low land fertility", style);
        createCell(landFertilityRow, 2, cropProductionIncomeConstraintsResponses.getLowLandFertility(), style);

        //Lack of reliable water, unfavourable climate
        Row reliableWaterRow = sheet.createRow(rowCount++);
        createCell(reliableWaterRow, 0, "", style);
        createCell(reliableWaterRow, 1, "Lack of reliable water, unfavourable climate", style);
        createCell(reliableWaterRow, 2, cropProductionIncomeConstraintsResponses.getLackOfReliableWater(), style);

        //Low technical skills, knowledge
        Row lowTechSkillsRow = sheet.createRow(rowCount++);
        createCell(lowTechSkillsRow, 0, "", style);
        createCell(lowTechSkillsRow, 1, "Low technical skills, knowledge", style);
        createCell(lowTechSkillsRow, 2, cropProductionIncomeConstraintsResponses.getLowTechnicalSkills(), style);

        //Low quality seed stock and planting materials
        Row lowQualitySeedRow = sheet.createRow(rowCount++);
        createCell(lowQualitySeedRow, 0, "", style);
        createCell(lowQualitySeedRow, 1, "Low quality seed stock and planting materials", style);
        createCell(lowQualitySeedRow, 2, cropProductionIncomeConstraintsResponses.getLowQualitySeed(), style);

        //Lack of access to markets, low producer prices
        Row cpMarketAccessRow = sheet.createRow(rowCount++);
        createCell(cpMarketAccessRow, 0, "", style);
        createCell(cpMarketAccessRow, 1, "Lack of access to markets, low producer prices", style);
        createCell(cpMarketAccessRow, 2, cropProductionIncomeConstraintsResponses.getLackOfMarketAccess(), style);

        //Endemic crop pests or diseases
        Row cpCropPestsRow = sheet.createRow(rowCount++);
        createCell(cpCropPestsRow, 0, "", style);
        createCell(cpCropPestsRow, 1, "Endemic crop pests or diseases", style);
        createCell(cpCropPestsRow, 2, cropProductionIncomeConstraintsResponses.getEndemicCropPests(), style);

        //Lack of agricultural extension service
        Row cpAgricExtensionsRow = sheet.createRow(rowCount++);
        createCell(cpAgricExtensionsRow, 0, "", style);
        createCell(cpAgricExtensionsRow, 1, "Lack of agricultural extension service", style);
        createCell(cpAgricExtensionsRow, 2, cropProductionIncomeConstraintsResponses.getLackOfAgricExtensions(), style);


        Row blank3 = sheet.createRow(rowCount++);
        Row blank4 = sheet.createRow(rowCount++);


        //Lack of pasture and browse
        Row lackPastureRow = sheet.createRow(rowCount++);
        createCell(lackPastureRow, 0, "Livestock Production", style);
        createCell(lackPastureRow, 1, "Lack of pasture and browse", style);
        createCell(lackPastureRow, 2, livestockProductionIncomeConstraintsResponses.getLackOfPasture(), style);

        //Lack of animal drinking water
        Row llackDrinkingRow = sheet.createRow(rowCount++);
        createCell(llackDrinkingRow, 0, "", style);
        createCell(llackDrinkingRow, 1, "Lack of animal drinking water", style);
        createCell(llackDrinkingRow, 2, livestockProductionIncomeConstraintsResponses.getLackOfAnimalDrinkingWater(), style);

        //Poor/low yielding animal genetic stock
        Row llowYieldAnimalsRow = sheet.createRow(rowCount++);
        createCell(llowYieldAnimalsRow, 0, "", style);
        createCell(llowYieldAnimalsRow, 1, "Poor/low yielding animal genetic stock", style);
        createCell(llowYieldAnimalsRow, 2, livestockProductionIncomeConstraintsResponses.getLowYieldingAnimal(), style);

        //Poor/low yielding animal genetic stock
        Row lveterinaryDrugsRow = sheet.createRow(rowCount++);
        createCell(lveterinaryDrugsRow, 0, "", style);
        createCell(lveterinaryDrugsRow, 1, "High costs/restricted supplies of veterinary drugs", style);
        createCell(lveterinaryDrugsRow, 2, livestockProductionIncomeConstraintsResponses.getCostlyVeterinaryDrugs(), style);

        //Endemic livestock pests and diseases
        Row lpestsAndDiseasesRow = sheet.createRow(rowCount++);
        createCell(lpestsAndDiseasesRow, 0, "", style);
        createCell(lpestsAndDiseasesRow, 1, "Endemic livestock pests and diseases", style);
        createCell(lpestsAndDiseasesRow, 2, livestockProductionIncomeConstraintsResponses.getLivestockPestsAndDiseases(), style);

        //Lack of market, low prices for animals
        Row llackMarketRow = sheet.createRow(rowCount++);
        createCell(llackMarketRow, 0, "", style);
        createCell(llackMarketRow, 1, "Lack of market, low prices for animals", style);
        createCell(llackMarketRow, 2, livestockProductionIncomeConstraintsResponses.getLackofMarket(), style);


        //Insecurity/raid risk of holding animal stock
        Row linsecurityRow = sheet.createRow(rowCount++);
        createCell(linsecurityRow, 0, "", style);
        createCell(linsecurityRow, 1, "Insecurity/raid risk of holding animal stock", style);
        createCell(linsecurityRow, 2, livestockProductionIncomeConstraintsResponses.getInsecurity(), style);

        //Low technical skills, knowledge
        Row llowTechSkillsRow = sheet.createRow(rowCount++);
        createCell(llowTechSkillsRow, 0, "", style);
        createCell(llowTechSkillsRow, 1, "Low technical skills, knowledge", style);
        createCell(llowTechSkillsRow, 2, livestockProductionIncomeConstraintsResponses.getLowTechnicalSkillsKnowledge(), style);

        //Unfavourable climate
        Row lunfavourableClimateRow = sheet.createRow(rowCount++);
        createCell(lunfavourableClimateRow, 0, "", style);
        createCell(lunfavourableClimateRow, 1, "Unfavourable climate", style);
        createCell(lunfavourableClimateRow, 2, livestockProductionIncomeConstraintsResponses.getUnfavourableClimate(), style);

        //Lack of livestock extension service
        Row lextensionServicesRow = sheet.createRow(rowCount++);
        createCell(lextensionServicesRow, 0, "", style);
        createCell(lextensionServicesRow, 1, "Lack of livestock extension service", style);
        createCell(lextensionServicesRow, 2, livestockProductionIncomeConstraintsResponses.getLackOfLivestockExtensionServices(), style);



        Row blank5 = sheet.createRow(rowCount++);
        Row blank6 = sheet.createRow(rowCount++);


        //Low fish stocks
        Row lowFishStockRow = sheet.createRow(rowCount++);
        createCell(lowFishStockRow, 0, "Fishing (coastal or inland)", style);
        createCell(lowFishStockRow, 1, "Low fish stocks", style);
        createCell(lowFishStockRow, 2, fishingIncomeConstraintsResponses.getLowFishStocks(), style);

        //Poor market/low prices for fish
        Row fishPoorMarketRow = sheet.createRow(rowCount++);
        createCell(fishPoorMarketRow, 0, "", style);
        createCell(fishPoorMarketRow, 1, "Poor market/low prices for fish", style);
        createCell(fishPoorMarketRow, 2, fishingIncomeConstraintsResponses.getPoorMarket(), style);

        //Lack of equipment, high cost of equipment
        Row fishLackEquipmentRow = sheet.createRow(rowCount++);
        createCell(fishLackEquipmentRow, 0, "", style);
        createCell(fishLackEquipmentRow, 1, "Lack of equipment, high cost of equipment", style);
        createCell(fishLackEquipmentRow, 2, fishingIncomeConstraintsResponses.getLackOfEquipment(), style);

        //Too much competition
        Row fishTooMuchCompetitionRow = sheet.createRow(rowCount++);
        createCell(fishTooMuchCompetitionRow, 0, "", style);
        createCell(fishTooMuchCompetitionRow, 1, "Too much competition", style);
        createCell(fishTooMuchCompetitionRow, 2, fishingIncomeConstraintsResponses.getExtremeCompetition(), style);

        //Lack of expertise
        Row fishLackExpertiseRow = sheet.createRow(rowCount++);
        createCell(fishLackExpertiseRow, 0, "", style);
        createCell(fishLackExpertiseRow, 1, "Lack of expertise", style);
        createCell(fishLackExpertiseRow, 2, fishingIncomeConstraintsResponses.getLackOfExpertise(), style);

        //Restrictions on fishing rights
        Row fishFishingRightsRow = sheet.createRow(rowCount++);
        createCell(fishFishingRightsRow, 0, "", style);
        createCell(fishFishingRightsRow, 1, "Restrictions on fishing rights", style);
        createCell(fishFishingRightsRow, 2, fishingIncomeConstraintsResponses.getFishingRightsRestrictions(), style);

        //Inadequate cold storage facilities
        Row fishColdStorageFacilitiesRow = sheet.createRow(rowCount++);
        createCell(fishColdStorageFacilitiesRow, 0, "", style);
        createCell(fishColdStorageFacilitiesRow, 1, "Inadequate cold storage facilities", style);
        createCell(fishColdStorageFacilitiesRow, 2, fishingIncomeConstraintsResponses.getInadequateColdStorageFacilities(), style);

        Row blank9 = sheet.createRow(rowCount++);
        Row blank10 = sheet.createRow(rowCount++);


        //Low/declining natural resources
        Row declineNaturalResourceRow = sheet.createRow(rowCount++);
        createCell(declineNaturalResourceRow, 0, "Natural resource based)", style);
        createCell(declineNaturalResourceRow, 1, "Low/declining natural resources", style);
        createCell(declineNaturalResourceRow, 2, naturalResourceIncomeConstraintsResponses.getDecliningNaturalResources(), style);

        //Too much population pressure on NR
        Row populationPressureRow = sheet.createRow(rowCount++);
        createCell(populationPressureRow, 0, "", style);
        createCell(populationPressureRow, 1, "Too much population pressure on NR", style);
        createCell(populationPressureRow, 2, naturalResourceIncomeConstraintsResponses.getPopulationPressure(), style);

        //Restrictions on rights to exploit natural resources
        Row rightNaturalResourcesRow = sheet.createRow(rowCount++);
        createCell(rightNaturalResourcesRow, 0, "", style);
        createCell(rightNaturalResourcesRow, 1, "Restrictions on rights to exploit natural resources", style);
        createCell(rightNaturalResourcesRow, 2, naturalResourceIncomeConstraintsResponses.getNaturalresourceExploitationRights(), style);

        //Low value of NR-based products
        Row lowValueNRRow = sheet.createRow(rowCount++);
        createCell(lowValueNRRow, 0, "", style);
        createCell(lowValueNRRow, 1, "Low value of NR-based products", style);
        createCell(lowValueNRRow, 2, naturalResourceIncomeConstraintsResponses.getLowValueNrBasedProducts(), style);



        Row blank7 = sheet.createRow(rowCount++);
        Row blank8 = sheet.createRow(rowCount++);


        //Lack of capital, weak financial services
        Row lackCapitalRow = sheet.createRow(rowCount++);
        createCell(lackCapitalRow, 0, "Small enterprises", style);
        createCell(lackCapitalRow, 1, "Lack of capital, weak financial services", style);
        createCell(lackCapitalRow, 2, smallEnterpriseIncomeConstraintsResponses.getLackOfCapital(), style);

        //Too much red tape
        Row redTapeRow = sheet.createRow(rowCount++);
        createCell(redTapeRow, 0, "", style);
        createCell(redTapeRow, 1, "Too much red tape", style);
        createCell(redTapeRow, 2, smallEnterpriseIncomeConstraintsResponses.getTooMuchRedTape(), style);

        //Too many taxes, tax rates too high
        Row taxesRow = sheet.createRow(rowCount++);
        createCell(taxesRow, 0, "", style);
        createCell(taxesRow, 1, "Too many taxes, tax rates too high", style);
        createCell(taxesRow, 2, smallEnterpriseIncomeConstraintsResponses.getTooManyTaxes(), style);

        //Lack of expertise
        Row smeExpertiseRow = sheet.createRow(rowCount++);
        createCell(smeExpertiseRow, 0, "", style);
        createCell(smeExpertiseRow, 1, "Lack of expertise", style);
        createCell(smeExpertiseRow, 2, smallEnterpriseIncomeConstraintsResponses.getLackOfExpertise(), style);


        return workbook;
    }

    public XSSFWorkbook processData(int countyId,int wealthGroupId, XSSFWorkbook workbook) {
        List<WgLivelihoodZoneDataObject> wgLivelihoodZoneDataObjectList = wealthGroupChartsService.prepareWealthGroupChart(countyId,wealthGroupId,8);


        int rowNum = 0;
        for (WgLivelihoodZoneDataObject wgLivelihoodZoneDataObject : wgLivelihoodZoneDataObjectList) {
            workbook = writeHeaderLine(rowNum,wgLivelihoodZoneDataObject,workbook);
            rowNum = rowNum + 4;
            workbook = writeDataLines(wgLivelihoodZoneDataObject, rowNum,workbook);
            rowNum = rowNum + 55;
        }
        return workbook;
    }
}
