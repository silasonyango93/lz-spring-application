package livelihoodzone.service.ingestor.crops;

import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.IngestedFileModel;
import livelihoodzone.util.excel.crops.CropExcelHelper;
import livelihoodzone.util.excel.crops.CropExcelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CropsExcellService {

    @Autowired
    CropsRepository cropsRepository;

    public void save(MultipartFile file) {
        try {
            System.out.println();
            List<CropExcelModel> ingestedRows = CropExcelHelper.excelToTutorials(file.getInputStream());

            List<CropsEntity> cropsEntityList = new ArrayList<>();

            for (CropExcelModel currentCropExcelModel : ingestedRows) {
                cropsEntityList.add(new CropsEntity(
                        currentCropExcelModel.getCropName(),
                        currentCropExcelModel.getCropId()
                ));
            }

            cropsRepository.saveAll(cropsEntityList);

            System.out.println();



        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
