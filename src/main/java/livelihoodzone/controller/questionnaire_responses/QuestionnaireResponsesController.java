package livelihoodzone.controller.questionnaire_responses;

import com.google.gson.Gson;
import io.swagger.annotations.*;
import livelihoodzone.dto.questionnaire.CountyDataCollectionProgressReport;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.CountyRequestDto;
import livelihoodzone.dto.questionnaire.county.ZoneLevelQuestionnaireSessionResponseDto;
import livelihoodzone.dto.questionnaire.wealthgroup.WealthGroupQuestionnaireSessionResponseDto;
import livelihoodzone.dto.user_management.UserResponseDTO;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.WgQuestionnaireSectionsEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.administrative_boundaries.wards.WardsRepository;
import livelihoodzone.repository.questionnaire.WgQuestionnaireSectionsRepository;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.repository.user_management.UserRepository;
import livelihoodzone.security.JwtTokenProvider;
import livelihoodzone.service.questionnaire.CountyLevelService;
import livelihoodzone.service.questionnaire.WealthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @Autowired
    CountiesRepository countiesRepository;

    @Autowired
    SubCountiesRepository subCountiesRepository;

    @Autowired
    WardsRepository wardsRepository;

    @Autowired
    SubLocationRepository subLocationRepository;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    WgQuestionnaireTypesRepository wgQuestionnaireTypesRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    @Autowired
    WgQuestionnaireSectionsRepository wgQuestionnaireSectionsRepository;


    @PostMapping("/wealthgroup")
    @ApiOperation(value = "${QuestionnaireResponsesController.wealthgroup}", response = QuestionnaireResponseDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Duplicate questionnaire")})
    public ResponseEntity<QuestionnaireResponseDto> acceptResponses(@ApiParam("Accept Responses") @RequestBody WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, HttpServletRequest httpServletRequest) {
        String accessToken = jwtTokenProvider.resolveToken(httpServletRequest);
        String email = jwtTokenProvider.getUsername(accessToken);
        User dataCollector = userRepository.findByUserEmail(email);
        QuestionnaireResponseDto questionnaireResponseDto = null;
        try {
            questionnaireResponseDto = wealthGroupService.processQuestionnaire(wealthGroupQuestionnaireRequestDto, dataCollector);
        } catch (Exception e) {
            e.printStackTrace();
        }



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
        QuestionnaireResponseDto questionnaireResponseDto = countyLevelService.submitCountyLevelQuestionnaire(countyLevelQuestionnaireRequestDto, dataCollector);

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

    @GetMapping(value = "/all-zone-level-questionnaire-sessions")
    @ApiOperation(value = "${QuestionnaireResponsesController.all-zone-level-questionnaire-sessions}", response = ZoneLevelQuestionnaireSessionResponseDto.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public ResponseEntity<List<ZoneLevelQuestionnaireSessionResponseDto>> getAllZoneLevelQuestionnaireSessions() {

        List<ZoneLevelQuestionnaireSessionResponseDto> questionnairesResponseList = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> zoneLevelQuestionnaires = lzQuestionnaireSessionRepository.findAll();

        for (LzQuestionnaireSessionEntity currentEntity : zoneLevelQuestionnaires) {
            User user = userRepository.findByUserId(currentEntity.getUserId());
            questionnairesResponseList.add(new ZoneLevelQuestionnaireSessionResponseDto(
                    currentEntity.getLzQuestionnaireSessionId(),
                    currentEntity.getUserId(),
                    currentEntity.getCountyId(),
                    currentEntity.getLivelihoodZoneId(),
                    currentEntity.getQuestionnaireSessionDescription(),
                    currentEntity.getLatitude(),
                    currentEntity.getLongitude(),
                    currentEntity.getSessionStartDate(),
                    currentEntity.getSessionEndDate(),
                    currentEntity.getLzQuestionnaireUniqueId(),
                    countiesRepository.findByCountyId(currentEntity.getCountyId()).getCountyName(),
                    livelihoodZonesRepository.findByLivelihoodZoneId(currentEntity.getLivelihoodZoneId()).getLivelihoodZoneName(),
                    new UserResponseDTO(
                            user.getUserId(),
                            countiesRepository.findByCountyId(user.getCountyId()).getCountyName(),
                            user.getFirstName(),
                            user.getMiddleName(),
                            user.getSurname(),
                            user.getUserEmail(),
                            user.getOrganizationName()
                    )
            ));
        }

        return new ResponseEntity<List<ZoneLevelQuestionnaireSessionResponseDto>>(questionnairesResponseList, HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/all-wealth-group-questionnaire-sessions")
    @ApiOperation(value = "${QuestionnaireResponsesController.all-wealth-group-questionnaire-sessions}", response = WealthGroupQuestionnaireSessionResponseDto.class, responseContainer = "List", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public ResponseEntity<List<WealthGroupQuestionnaireSessionResponseDto>> getAllWealthGroupQuestionnaireSessions() {

        List<WealthGroupQuestionnaireSessionResponseDto> questionnairesResponseList = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wealthGroupQuestionnaires = wgQuestionnaireSessionRepository.findAll();

        for (WgQuestionnaireSessionEntity currentEntity : wealthGroupQuestionnaires) {
            User user = userRepository.findByUserId(currentEntity.getUserId());
            questionnairesResponseList.add(new WealthGroupQuestionnaireSessionResponseDto(
                    currentEntity.getWgQuestionnaireSessionId(),
                    currentEntity.getUserId(),
                    currentEntity.getWgQuestionnaireTypeId(),
                    currentEntity.getWealthGroupId(),
                    currentEntity.getCountyId(),
                    currentEntity.getSubCountyId(),
                    currentEntity.getWardId(),
                    currentEntity.getSubLocationId(),
                    currentEntity.getLivelihoodZoneId(),
                    currentEntity.getQuestionnaireSessionDescription(),
                    currentEntity.getLatitude(),
                    currentEntity.getLongitude(),
                    currentEntity.getSessionStartDate(),
                    currentEntity.getSessionEndDate(),
                    currentEntity.getQuestionnaireUniqueId(),
                    countiesRepository.findByCountyId(currentEntity.getCountyId()).getCountyName(),
                    subCountiesRepository.findBySubCountyId(currentEntity.getSubCountyId()).getSubCountyName(),
                    wardsRepository.findByWardId(currentEntity.getWardId()).getWardName(),
                    subLocationRepository.findBySubLocationId(currentEntity.getSubLocationId()).getSubLocationName(),
                    livelihoodZonesRepository.findByLivelihoodZoneId(currentEntity.getLivelihoodZoneId()).getLivelihoodZoneName(),
                    wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeId(currentEntity.getWgQuestionnaireTypeId()).getWgQuestionnaireTypeDescription(),
                    new UserResponseDTO(
                            user.getUserId(),
                            countiesRepository.findByCountyId(user.getCountyId()).getCountyName(),
                            user.getFirstName(),
                            user.getMiddleName(),
                            user.getSurname(),
                            user.getUserEmail(),
                            user.getOrganizationName()
                    ),
                    wealthGroupRepository.findByWealthGroupId(currentEntity.getWealthGroupId()).getWealthGroupDescription()
            ));
        }

        return new ResponseEntity<List<WealthGroupQuestionnaireSessionResponseDto>>(questionnairesResponseList, HttpStatus.valueOf(200));
    }


    @PostMapping("/data-collection-progress-report")
    @ApiOperation(value = "${QuestionnaireResponsesController.zone-level}", response = CountyDataCollectionProgressReport.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "County does not exist")})
    public ResponseEntity<CountyDataCollectionProgressReport> countyDataCollectionProgressReport(@ApiParam("County Data Collection Progress Report") @RequestBody CountyRequestDto countyRequestDto) {

        try {
            CountyDataCollectionProgressReport countyDataCollectionProgressReport = countyLevelService.countyDataCollectionProgressReport(countyRequestDto.getCountyId());
            return new ResponseEntity<CountyDataCollectionProgressReport>(countyDataCollectionProgressReport, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CountyDataCollectionProgressReport>(new CountyDataCollectionProgressReport(), HttpStatus.valueOf(500));
        }

    }


    @GetMapping(value = "/wealth-group-questionnaire-sections")
    @ApiOperation(value = "${QuestionnaireResponsesController.wealth-group-questionnaire-sections}", response = WgQuestionnaireSectionsEntity.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Unprocessable")})
    public ResponseEntity<List<WgQuestionnaireSectionsEntity>> fetchWealthGroupQuestionnaireSections() {

        List<WgQuestionnaireSectionsEntity> wgQuestionnaireSectionsEntityList = wgQuestionnaireSectionsRepository.findAll();

        return new ResponseEntity<List<WgQuestionnaireSectionsEntity>>(wgQuestionnaireSectionsEntityList, HttpStatus.valueOf(200));
    }

}
