package livelihoodzone.controller.questionnaire_responses;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.user_management.UserRepository;
import livelihoodzone.security.JwtTokenProvider;
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
@RequestMapping("/wealthgroup")
@Api(tags = "wealthgroup")
public class WealthGroupController {

    @Autowired
    WealthGroupService wealthGroupService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/responses")
    @ApiOperation(value = "${WealthGroupController.responses}", response = QuestionnaireResponseDto.class)
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
}
