package livelihoodzone.service.reports.zonal.excel;

import livelihoodzone.service.reports.zonal.wealthgroup.LzWealthGroupDistributionExcelExporterService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static livelihoodzone.service.reports.wealthgroup.excel.ExcelSheetNamesConstants.WEALTH_GROUP_POPULATION_DISTRIBUTION;

@Service
public class ZonalExcelService {

    @Autowired
    LzWealthGroupDistributionExcelExporterService lzWealthGroupDistributionExcelExporterService;

    private XSSFWorkbook workbook;

    public void processData(int countyId) {
        workbook.createSheet(WEALTH_GROUP_POPULATION_DISTRIBUTION);

        workbook = lzWealthGroupDistributionExcelExporterService.processData(countyId,workbook);
    }

    public void export(HttpServletResponse response, int countyId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
