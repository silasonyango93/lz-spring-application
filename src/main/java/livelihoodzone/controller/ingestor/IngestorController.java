package livelihoodzone.controller.ingestor;

import livelihoodzone.common.CustomRunTimeStore;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.service.ingestor.ExcelService;
import livelihoodzone.service.ingestor.crops.CropsExcellService;
import livelihoodzone.service.ingestor.tribe.TribeExcelService;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.IngestedFileModel;
import livelihoodzone.util.excel.crops.CropExcelHelper;
import livelihoodzone.util.excel.tribe.TribeExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

@Controller
@RequestMapping("/excel")
public class IngestorController {

    @Autowired
    ExcelService fileService;

    @Autowired
    CropsExcellService cropsExcellService;

    @Autowired
    TribeExcelService tribeExcelService;

    @Autowired
    CountiesRepository countiesRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<IngestedFileModel> rows = CustomRunTimeStore.getInstance().getIngestedRows();

                if (rows.isEmpty()) {
                    fileService.save(file);
                } else {
                    saveCounties(rows);
                }


                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    public void saveCounties(List<IngestedFileModel> ingestedRows) {
        String currentCountyName = ingestedRows.get(0).getCounty();
        countiesRepository.save(new CountiesEntity(
                currentCountyName,
                ""
        ));

        for (IngestedFileModel currentRow : ingestedRows) {
            if (!currentRow.getCounty().equals(currentCountyName)) {
                countiesRepository.save(new CountiesEntity(
                        currentRow.getCounty(),
                        ""
                ));
                currentCountyName = currentRow.getCounty();
            }
        }
    }


    @PostMapping("/crops")
    public ResponseEntity<ResponseMessage> uploadCrops(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CropExcelHelper.hasExcelFormat(file)) {
            try {
                cropsExcellService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping("/tribe")
    public ResponseEntity<ResponseMessage> uploadTribes(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (TribeExcelHelper.hasExcelFormat(file)) {
            try {
                tribeExcelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
