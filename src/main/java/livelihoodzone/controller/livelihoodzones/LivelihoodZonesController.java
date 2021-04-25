package livelihoodzone.controller.livelihoodzones;

import io.swagger.annotations.*;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livelihoodzones")
@Api(tags = "livelihoodzones")
public class LivelihoodZonesController {

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @GetMapping(value = "/all-livelihoodzones")
    @ApiOperation(value = "${LivelihoodZonesController.all-livelihoodzones}", response = LivelihoodZonesEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public List<LivelihoodZonesEntity> getAllLivelihoodZones() {
        return livelihoodZonesRepository.findAll();
    }
}
