package livelihoodzone.service.reports.wealthgroup.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class WealthGroupExcelService {

    @Autowired
    MainSourcesOfFoodAndIncomeExcelService mainSourcesOfFoodAndIncomeExcelService;

    private XSSFWorkbook workbook;

    public void processData(int countyId, int wealthGroupId) {
        XSSFSheet sheet = workbook.createSheet("Main Sources of Income and Food");
        workbook = mainSourcesOfFoodAndIncomeExcelService.processData(countyId,wealthGroupId,workbook);
    }


    public void export(HttpServletResponse response, int countyId, int wealthGroupId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId,wealthGroupId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
