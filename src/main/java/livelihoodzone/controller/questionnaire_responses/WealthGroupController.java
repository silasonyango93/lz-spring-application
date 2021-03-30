package livelihoodzone.controller.questionnaire_responses;

import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.service.user_management.questionnaire.WealthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wealthgroup")
@Api(tags = "wealthgroup")
public class WealthGroupController {

    @Autowired
    WealthGroupService wealthGroupService;

    @PostMapping("/responses")
    @ApiOperation(value = "${WealthGroupController.responses}", response = QuestionnaireResponseDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Duplicate questionnaire")})
    public void acceptResponses(@ApiParam("Accept Responses") @RequestBody WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto) {
        wealthGroupService.processQuestionnaire(wealthGroupQuestionnaireRequestDto);
    }
}
