package livelihoodzone.controller.livelihoodzones;

import io.swagger.annotations.*;
import livelihoodzone.dto.GenericResponse;
import livelihoodzone.dto.livelihoodzones.CountyLivelihoodZonesAssignmentDto;
import livelihoodzone.dto.livelihoodzones.CountyLivelihoodZonesUpdateDetailsDto;
import livelihoodzone.dto.user_management.AuthenticationObject;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentStatus;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.service.livelihoodzones.LivelihoodZonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livelihoodzones")
@Api(tags = "livelihoodzones")
public class LivelihoodZonesController {

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    LivelihoodZonesService livelihoodZonesService;

    @GetMapping(value = "/all-livelihoodzones")
    @ApiOperation(value = "${LivelihoodZonesController.all-livelihoodzones}", response = LivelihoodZonesEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<List<LivelihoodZonesEntity>> getAllLivelivelihoodZones() {
        return new ResponseEntity<List<LivelihoodZonesEntity>>(livelihoodZonesRepository.findAll(), HttpStatus.valueOf(200));
    }


    @GetMapping(value = "/county-livelihoodzones/{countyId}")
    @ApiOperation(value = "${LivelihoodZonesController.a-county-livelihoodzones}", response = CountyLivelihoodZonesAssignmentDto.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token"),
            @ApiResponse(code = 422, message = "No county exists by this county id"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<CountyLivelihoodZonesAssignmentDto> getACountylivelihoodZones(@ApiParam("countyId") @PathVariable int countyId) {
        CountyLivelihoodZonesAssignmentDto countyLivelihoodZonesAssignmentDto = livelihoodZonesService.fetchACountyLiveliHoodZones(countyId);
        if (countyLivelihoodZonesAssignmentDto == null) {
            return new ResponseEntity<CountyLivelihoodZonesAssignmentDto>(countyLivelihoodZonesAssignmentDto, HttpStatus.valueOf(500));
        }

        if (countyLivelihoodZonesAssignmentDto.getCountyLivelihoodZonesAssignmentStatus() == CountyLivelihoodZonesAssignmentStatus.SUCCESS_EMPTY_LIST) {
            return new ResponseEntity<CountyLivelihoodZonesAssignmentDto>(countyLivelihoodZonesAssignmentDto, HttpStatus.valueOf(200));
        }

        return new ResponseEntity<CountyLivelihoodZonesAssignmentDto>(countyLivelihoodZonesAssignmentDto, HttpStatus.valueOf(200));
    }

    @PutMapping(value = "/update-county-livelihoodzones")
    @ApiOperation(value = "${LivelihoodZonesController.update-county-livelihoodzones}",response = GenericResponse.class , authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - Invalid token"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<GenericResponse> updateACountyLivelihoodones(@ApiParam("County livelihoodzone update details") @RequestBody CountyLivelihoodZonesUpdateDetailsDto countyLivelihoodZonesUpdateDetailsDto) {
        if (livelihoodZonesService.updateACountyLivelihoodZones(countyLivelihoodZonesUpdateDetailsDto)) {
            return new ResponseEntity<GenericResponse>(new GenericResponse(true,"Update succesful"), HttpStatus.valueOf(200));
        }

        return new ResponseEntity<GenericResponse>(new GenericResponse(false,"Internal server error"), HttpStatus.valueOf(500));
    }
}