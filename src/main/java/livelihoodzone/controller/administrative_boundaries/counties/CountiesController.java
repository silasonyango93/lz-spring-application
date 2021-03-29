package livelihoodzone.controller.administrative_boundaries.counties;

import io.swagger.annotations.*;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.user_management.Roles;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/counties")
@Api(tags = "counties")
public class CountiesController {

    @Autowired
    private CountiesRepository countiesRepository;

    @GetMapping(value = "/all-counties")
    @ApiOperation(value = "${CountiesController.all-counties}", response = CountiesEntity.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public List<CountiesEntity> getAllCounties() {
        return countiesRepository.findAll();
    }
}
