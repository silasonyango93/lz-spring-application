package livelihoodzone.controller.livelihoodzones;

import io.swagger.annotations.*;
import livelihoodzone.dto.GenericResponse;
import livelihoodzone.dto.livelihoodzones.*;
import livelihoodzone.dto.user_management.AuthenticationObject;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentStatus;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.service.livelihoodzones.LivelihoodZonesService;
import livelihoodzone.service.retrofit.livelihoodzones.SubLocationSearchRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @PostMapping("/assign_a_sublocation_a_livelihoodzone")
    @ApiOperation(value = "${LivelihoodZonesController.assign-a-sublocation-a-livelihoodzone}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 422, message = "Bad Request"),
            @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<GenericResponse> assignASubLocationALivelihoodZone(@ApiParam("Sublocation - livelihood zone id pair") @RequestBody SubLocationLivelihoodZoneAssignmentDto subLocationLivelihoodZoneAssignmentDto) {
        Boolean isASuccess = livelihoodZonesService.subLocationLivelihoodZoneAssignment(subLocationLivelihoodZoneAssignmentDto.getSublocationId(), subLocationLivelihoodZoneAssignmentDto.getLivelihoodzoneId());

        if (isASuccess) {
            return new ResponseEntity<GenericResponse>(new GenericResponse(true, "Succesful assignment"), HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<GenericResponse>(new GenericResponse(false, "Duplicate assignment"), HttpStatus.valueOf(422));
        }
    }



    @PostMapping("/search_sublocation_by_name_and_county_id")
    @ApiOperation(value = "${LivelihoodZonesController.search-sublocation-by-name-and-county-id}", response = SubLocationSearchRetrofitModel.class, responseContainer = "List")
    @ApiResponses(value = {//
            @ApiResponse(code = 422, message = "Bad Request"),
            @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<List<SubLocationSearchRetrofitModel>> searchSubLocationByNameFromSpecificCounty(@ApiParam("Sublocation search") @RequestBody SubLocationSearchRequestDto subLocationSearchRequestDto) {
        List<SubLocationSearchRetrofitModel> subLocationSearchRetrofitModelList = livelihoodZonesService.searchSubLocationByNameFromSpecificCounty(subLocationSearchRequestDto.getSubLocationName(), subLocationSearchRequestDto.getCountyId());

        if (subLocationSearchRetrofitModelList == null) {
            return new ResponseEntity<List<SubLocationSearchRetrofitModel>>(new ArrayList<>(), HttpStatus.valueOf(500));
        }
        return new ResponseEntity<List<SubLocationSearchRetrofitModel>>(subLocationSearchRetrofitModelList, HttpStatus.valueOf(200));
    }


    @PostMapping("/update_a_sublocation_livelihood_zone")
    @ApiOperation(value = "${LivelihoodZonesController.update-a-sublocation-livelihood-zone}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<GenericResponse> updateASubLocationLivelihoodZone(@ApiParam("Update a sub-location livelihood zone") @RequestBody SubLocationLivelihoodZoneUpdateRequestDto subLocationLivelihoodZoneUpdateRequestDto) {

        try {
            livelihoodZonesService.updateASubLocationLivelihoodZone(subLocationLivelihoodZoneUpdateRequestDto.getSubLocationId(), subLocationLivelihoodZoneUpdateRequestDto.getLivelihoodZoneId(), subLocationLivelihoodZoneUpdateRequestDto.getCountyId());
            return new ResponseEntity<GenericResponse>(new GenericResponse(true,"Update was successful"), HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(new GenericResponse(false,"Update failed - internal server error"), HttpStatus.valueOf(500));
        }

    }


    @PutMapping("/update_county_livelihood_zone_assignments")
    @ApiOperation(value = "${LivelihoodZonesController.update-county-livelihood-zone-assignments}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<GenericResponse> updateCountyLivelihoodZones() {

        try {
            livelihoodZonesService.adjustCountyLivelihoodZoneAssignments();
            return new ResponseEntity<GenericResponse>(new GenericResponse(true,"Update was successful"), HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(new GenericResponse(false,"Update failed - internal server error"), HttpStatus.valueOf(500));
        }

    }

}
