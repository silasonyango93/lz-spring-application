package livelihoodzone.service.ingestor.tribe;

import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsEntity;
import livelihoodzone.repository.questionnaire.tribe.EthnicGroupsRepository;
import livelihoodzone.util.excel.crops.CropExcelHelper;
import livelihoodzone.util.excel.crops.CropExcelModel;
import livelihoodzone.util.excel.tribe.TribeExcelHelper;
import livelihoodzone.util.excel.tribe.TribeExcelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TribeExcelService {

    @Autowired
    EthnicGroupsRepository ethnicGroupsRepository;

    public void save(MultipartFile file) {
        try {
            List<TribeExcelModel> ingestedRows = TribeExcelHelper.excelToTutorials(file.getInputStream());

            List<EthnicGroupsEntity> tribeEntityList = new ArrayList<>();

            for (TribeExcelModel currentTribeExcelModel : ingestedRows) {
                tribeEntityList.add(new EthnicGroupsEntity(
                        currentTribeExcelModel.getTribeName(),
                        currentTribeExcelModel.getTribeId()
                ));
            }

            ethnicGroupsRepository.saveAll(tribeEntityList);

            System.out.println();



        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
