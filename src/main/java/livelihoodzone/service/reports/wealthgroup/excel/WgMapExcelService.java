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

    public void processData( int wealthGroupId) {

        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            workbook.createSheet(countiesEntity.getCountyName());
            workbook = livestockOwnershipExcelService.processData(countiesEntity.getCountyId(),wealthGroupId,workbook,countiesEntity.getCountyName());
        }
    }


    public void export(HttpServletResponse response, int wealthGroupId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(wealthGroupId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
