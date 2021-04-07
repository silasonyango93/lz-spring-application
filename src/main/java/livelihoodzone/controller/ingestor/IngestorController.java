package livelihoodzone.controller.ingestor;

import livelihoodzone.service.ingestor.ExcelService;
import livelihoodzone.service.ingestor.crops.CropsExcellService;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.crops.CropExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

@Controller
@RequestMapping("/excel")
public class IngestorController {

    @Autowired
    ExcelService fileService;

    @Autowired
    CropsExcellService cropsExcellService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);

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
}
