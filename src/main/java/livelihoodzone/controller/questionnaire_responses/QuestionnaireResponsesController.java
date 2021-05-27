package livelihoodzone.controller.questionnaire_responses;

import com.google.gson.Gson;
import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.user_management.UserRepository;
import livelihoodzone.security.JwtTokenProvider;
import livelihoodzone.service.questionnaire.CountyLevelService;
import livelihoodzone.service.questionnaire.WealthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/responses")
@Api(tags = "responses")
public class QuestionnaireResponsesController {

    @Autowired
    WealthGroupService wealthGroupService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountyLevelService countyLevelService;

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    @PostMapping("/wealthgroup")
    @ApiOperation(value = "${QuestionnaireResponsesController.wealthgroup}", response = QuestionnaireResponseDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Duplicate questionnaire")})
    public ResponseEntity<QuestionnaireResponseDto> acceptResponses(@ApiParam("Accept Responses") @RequestBody WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, HttpServletRequest httpServletRequest) {
        String accessToken = jwtTokenProvider.resolveToken(httpServletRequest);
        String email = jwtTokenProvider.getUsername(accessToken);
        User dataCollector = userRepository.findByUserEmail(email);
        QuestionnaireResponseDto questionnaireResponseDto = wealthGroupService.processQuestionnaire(wealthGroupQuestionnaireRequestDto,dataCollector);

        if (questionnaireResponseDto.getQuestionnaireResponseStatus() == QuestionnaireResponseStatus.DUPLICATE) {
            return new ResponseEntity<QuestionnaireResponseDto>(questionnaireResponseDto, HttpStatus.valueOf(422));
        }

        return new ResponseEntity<QuestionnaireResponseDto>(questionnaireResponseDto, HttpStatus.valueOf(200));
    }


    @PostMapping("/county")
    @ApiOperation(value = "${QuestionnaireResponsesController.zone-level}", response = QuestionnaireResponseDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Duplicate questionnaire")})
    public ResponseEntity<QuestionnaireResponseDto> submitCountyLevelQuestionnaire(@ApiParam("Accept Responses") @RequestBody CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, HttpServletRequest httpServletRequest) {
        String accessToken = jwtTokenProvider.resolveToken(httpServletRequest);
        String email = jwtTokenProvider.getUsername(accessToken);
        User dataCollector = userRepository.findByUserEmail(email);
        QuestionnaireResponseDto questionnaireResponseDto = countyLevelService.submitCountyLevelQuestionnaire(countyLevelQuestionnaireRequestDto,dataCollector);

        if (questionnaireResponseDto.getQuestionnaireResponseStatus() == QuestionnaireResponseStatus.DUPLICATE) {
            return new ResponseEntity<QuestionnaireResponseDto>(questionnaireResponseDto, HttpStatus.valueOf(422));
        }

        return new ResponseEntity<QuestionnaireResponseDto>(questionnaireResponseDto, HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/wealthgroup/retrieve/{questionnaireUniqueId}")
    @ApiOperation(value = "${QuestionnaireResponsesController.retrieve-wealthgroup}", response = WealthGroupQuestionnaireRequestDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "The provided questionnaire unique id does not exist")})
    public ResponseEntity<WealthGroupQuestionnaireRequestDto> retrieveAWealthGroupQuestionnaireResponses(@ApiParam("Provide with a valid questionnaire unique id") @PathVariable String questionnaireUniqueId) {

        Gson gson = new Gson();

        List<WgQuestionnaireSessionEntity> existingQuestionnaires = wgQuestionnaireSessionRepository.findByQuestionnaireUniqueId(questionnaireUniqueId);

        if (existingQuestionnaires.isEmpty()) {
            return new ResponseEntity<WealthGroupQuestionnaireRequestDto>(new WealthGroupQuestionnaireRequestDto(), HttpStatus.valueOf(422));
        }

        WealthGroupQuestionnaireRequestDto questionnaireResponseObject = gson.fromJson(existingQuestionnaires.get(0).getQuestionnaireJsonString(), WealthGroupQuestionnaireRequestDto.class);

        return new ResponseEntity<WealthGroupQuestionnaireRequestDto>(questionnaireResponseObject, HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/county/retrieve/{questionnaireUniqueId}")
    @ApiOperation(value = "${QuestionnaireResponsesController.retrieve-zone-level}", response = CountyLevelQuestionnaireRequestDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "The provided questionnaire unique id does not exist")})
    public ResponseEntity<CountyLevelQuestionnaireRequestDto> retrieveAZoneLevelQuestionnaireResponses(@ApiParam("Provide with a valid questionnaire unique id") @PathVariable String questionnaireUniqueId) {

        Gson gson = new Gson();

        List<LzQuestionnaireSessionEntity> existingQuestionnaires = lzQuestionnaireSessionRepository.findByLzQuestionnaireUniqueId(questionnaireUniqueId);

        if (existingQuestionnaires.isEmpty()) {
            return new ResponseEntity<CountyLevelQuestionnaireRequestDto>(new CountyLevelQuestionnaireRequestDto(), HttpStatus.valueOf(422));
        }

        CountyLevelQuestionnaireRequestDto questionnaireResponseObject = gson.fromJson(existingQuestionnaires.get(0).getQuestionnaireJsonString(), CountyLevelQuestionnaireRequestDto.class);

        return new ResponseEntity<CountyLevelQuestionnaireRequestDto>(questionnaireResponseObject, HttpStatus.valueOf(200));
    }
}
