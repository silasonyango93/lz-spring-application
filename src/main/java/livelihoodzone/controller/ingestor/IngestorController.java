package livelihoodzone.controller.ingestor;

import livelihoodzone.service.ingestor.ExcelService;
import livelihoodzone.service.ingestor.crops.CropsExcellService;
import livelihoodzone.service.ingestor.questionnaire_update.IncomeSourcesUpdateExcelHelper;
import livelihoodzone.service.ingestor.questionnaire_update.QuestionnaireUpdateService;
import livelihoodzone.service.ingestor.tribe.TribeExcelService;
import livelihoodzone.service.ingestor.users.UsersExcelService;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.crops.CropExcelHelper;
import livelihoodzone.util.excel.tribe.TribeExcelHelper;
import livelihoodzone.util.excel.users.UsersExcelHelper;
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
    QuestionnaireUpdateService questionnaireUpdateService;

    @Autowired
    ExcelService fileService;

    @Autowired
    CropsExcellService cropsExcellService;

    @Autowired
    TribeExcelService tribeExcelService;

    @Autowired
    UsersExcelService usersExcelService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);

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


    @PostMapping("/users")
    public ResponseEntity<ResponseMessage> uploadUsers(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (UsersExcelHelper.hasExcelFormat(file)) {
            try {
                usersExcelService.signUpIngestedUsers(file);

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


    @PostMapping("/update-questionnaire")
    public ResponseEntity<ResponseMessage> updateQuestionnaire(@RequestParam("file") MultipartFile file, @RequestParam("wgQuestionnaireSessionId") int wgQuestionnaireSessionId) {
        String message = "";

        if (UsersExcelHelper.hasExcelFormat(file)) {
            try {
                questionnaireUpdateService.processQuestionnaireUpdate(file,wgQuestionnaireSessionId);

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
}
