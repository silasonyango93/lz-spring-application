package livelihoodzone.service.reports.wealthgroup.excel;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class WgMapExcelService {

    private XSSFWorkbook workbook;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    LivestockOwnershipExcelService livestockOwnershipExcelService;

    @Autowired
    MainSourcesOfFoodAndIncomeExcelService mainSourcesOfFoodAndIncomeExcelService;

    @Autowired
    FoodConsumptionPercentageExcelService foodConsumptionPercentageExcelService;

    @Autowired
    WgCropProductionExcelService wgCropProductionExcelService;

    @Autowired
    LivestockContributionExcelService livestockContributionExcelService;

    @Autowired
    LabourPatternsExcelService labourPatternsExcelService;

    @Autowired
    ExpenditurePatternsExcelService expenditurePatternsExcelService;

    @Autowired
    MigrationPatternsExcelService migrationPatternsExcelService;

    @Autowired
    WgConstraintsExcelService wgConstraintsExcelService;

    public void processData( int wealthGroupId, int questionnaireSectionCode) {

        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            workbook.createSheet(countiesEntity.getCountyName());

            if (questionnaireSectionCode == 1) {
                workbook = mainSourcesOfFoodAndIncomeExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 2) {
                workbook = foodConsumptionPercentageExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 3) {
                workbook = wgCropProductionExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 4) {
                workbook = livestockOwnershipExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 5) {
                workbook = labourPatternsExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 6) {
                workbook = expenditurePatternsExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 7) {
                workbook = migrationPatternsExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 8) {
                workbook = wgConstraintsExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }
            if (questionnaireSectionCode == 10) {
                workbook = livestockContributionExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
            }


        }
    }


    public void export(HttpServletResponse response, int wealthGroupId, int questionnaireSectionCode) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(wealthGroupId,questionnaireSectionCode);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
