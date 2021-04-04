package livelihoodzone.service.ingestor;

import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.IngestedFileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void save(MultipartFile file) {
        try {
            System.out.println();
            List<IngestedFileModel> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            //repository.saveAll(tutorials);
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
