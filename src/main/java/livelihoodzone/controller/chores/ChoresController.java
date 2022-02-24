package livelihoodzone.controller.chores;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import livelihoodzone.dto.GenericResponse;
import livelihoodzone.dto.IdsDto;
import livelihoodzone.dto.reports.wealthgroup.charts.WgLivelihoodZoneDataObject;
import livelihoodzone.dto.user_management.UserUpdateRequestDto;
import livelihoodzone.entity.questionnaire.WgQuestionnaireSectionsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.service.chores.ChoresService;
import livelihoodzone.service.chores.PercentageValidationService;
import livelihoodzone.service.chores.WgDuplicateQuestionnairesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chores")
public class ChoresController {

    @Autowired
    ChoresService choresService;

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    PercentageValidationService percentageValidationService;

    @Autowired
    WgDuplicateQuestionnairesService wgDuplicateQuestionnairesService;

    @PostMapping("/reset-fish-cages")
    @ApiOperation(value = "${Chores.reset-fish-cages}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<GenericResponse> resetFishCages(@RequestParam("countyId") int countyId) {

        try {
            choresService.resetCountyFishCagesValue(countyId);
            return new ResponseEntity<GenericResponse>(new GenericResponse(true,"Reset succesful"), HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(new GenericResponse(false,"Reset failed"), HttpStatus.valueOf(500));
        }

    }



    @PostMapping("/update-fish-cages-value")
    @ApiOperation(value = "${Chores.update-fish-cages-value}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<GenericResponse> updateFishCagesValue(@RequestParam("wgQuestionnaireSessionId") int wgQuestionnaireSessionId, @RequestParam("fishCagesValue") double fishCagesValue) {

        try {
            choresService.updateFishCagesValue(wgQuestionnaireSessionId,fishCagesValue);
            return new ResponseEntity<GenericResponse>(new GenericResponse(true,"Reset succesful"), HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(new GenericResponse(false,"Reset failed"), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/wealth-group/search-questionnaire")
    @ApiOperation(value = "${Chores.search-crop}", response = WgQuestionnaireSessionEntity.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<WgQuestionnaireSessionEntity>> searchCrops(@RequestParam("questionnaireName") String questionnaireName) {

        try {
            List<WgQuestionnaireSessionEntity> returnedQuestionnaires = wgQuestionnaireSessionRepository.findByQuestionnaireSessionDescriptionContainingIgnoreCaseAndWgQuestionnaireTypeId(questionnaireName, 1);
            return new ResponseEntity<List<WgQuestionnaireSessionEntity>>(returnedQuestionnaires, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<WgQuestionnaireSessionEntity>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping("/excel/county-sampled-sub-locations")
    public void exportCountySampledSubLocations(HttpServletResponse response, @RequestParam("countyId") int countyId) throws IOException {

        try {

            String fileName = countiesRepository.findByCountyId(countyId).getCountyName() + " COUNTY SAMPLED SUB_LOCATIONS";
            response.setContentType("application/octet-stream");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            choresService.downloadCountySampledSubLocationsExcelFile(response, countyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/wealthgroup-percentage-validation-issues")
    @ApiOperation(value = "${Chores.wealthgroup-percentage-validation-issues}", response = String.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable data")})
    public ResponseEntity<List<String>> getAllWealthGroupPercentageValidationIssues(@RequestBody IdsDto idsDto) {

        try {
            List<String> affectedQuestionnaires = percentageValidationService.getQuestionnairesWithPercentageValidationIssues(idsDto.getIds());
            return new ResponseEntity<List<String>>(affectedQuestionnaires, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping("/country-wide-duplicate-wealthgroup-questionnaires")
    public ResponseEntity<List<String>> getCountrywideDuplicateWealthGroupQuestionnaires() {

        try {
            List<String> incompleteQuestionnaireIds = wgDuplicateQuestionnairesService.fetchCountrywideDuplicateWealthGroupQuestionnaires();
            return new ResponseEntity<List<String>>(incompleteQuestionnaireIds, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }
    }


    @GetMapping("/country-wide-duplicate-zone-level-questionnaires")
    public ResponseEntity<List<String>> getCountrywideDuplicateZoneLevelQuestionnaires() {

        try {
            List<String> incompleteQuestionnaireIds = wgDuplicateQuestionnairesService.fetchCountrywideDuplicateZoneLevelQuestionnaires();
            return new ResponseEntity<List<String>>(incompleteQuestionnaireIds, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }
    }


}
