package livelihoodzone.controller.questionnaire_responses;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.user_management.UserRepository;
import livelihoodzone.security.JwtTokenProvider;
import livelihoodzone.service.questionnaire.CountyLevelService;
import livelihoodzone.service.questionnaire.WealthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}