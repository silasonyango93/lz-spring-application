package livelihoodzone.controller.user_management;

import javax.servlet.http.HttpServletRequest;

import livelihoodzone.dto.GenericResponse;
import livelihoodzone.dto.user_management.*;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.entity.user_management.AuthenticationStatus;
import livelihoodzone.entity.user_management.Roles;
import livelihoodzone.repository.user_management.RolesRepository;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.user_management.UserRetrofitModel;
import livelihoodzone.service.retrofit.user_management.UserRetrofitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.service.user_management.UserService;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}", response = AuthenticationObject.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "A user does not exist by this email"), //
            @ApiResponse(code = 200, message = "Succesful authentication"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public ResponseEntity<AuthenticationObject> login(@ApiParam("Login User") @RequestBody UserLoginDTO userLoginDTO) {
        AuthenticationObject authenticationObject = userService.signin(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (authenticationObject.isAuthenticationSuccessful()) {
            return new ResponseEntity<AuthenticationObject>(authenticationObject, HttpStatus.valueOf(200));
        } else if (authenticationObject.getAuthenticationStatus() == AuthenticationStatus.WRONG_CREDENTIALS){
            return new ResponseEntity<AuthenticationObject>(authenticationObject, HttpStatus.valueOf(401));
        } else if(authenticationObject.getAuthenticationStatus() == AuthenticationStatus.USER_DOES_NOT_EXIST) {
            return new ResponseEntity<AuthenticationObject>(authenticationObject, HttpStatus.valueOf(422));
        } else {
            return new ResponseEntity<AuthenticationObject>(authenticationObject, HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}", response = SignupStatusDto.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Email is already in use")})
    public ResponseEntity<SignupStatusDto> signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        SignupStatusDto signupStatusDto = userService.signup(new User(
                user.getCountyId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getSurname(),
                user.getUserEmail(),
                user.getOrganizationName(),
                user.getPassword()
        ), user.getRolesToBeAssigned());

        if (signupStatusDto.getIsSignupSuccessful()) {
            return new ResponseEntity<SignupStatusDto>(signupStatusDto, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<SignupStatusDto>(signupStatusDto, HttpStatus.valueOf(422));
        }
    }

    @DeleteMapping(value = "/{username}")
    @ApiOperation(value = "${UserController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }


    @GetMapping(value = "/all-users")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.all-users}", response = UserResponseDTO.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public List<UserResponseDTO> getAllUsers() {
        UserRetrofitService userRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(UserRetrofitService.class);
        Call<List<UserRetrofitModel>> callSync = userRetrofitService.fetchAllUsers();
        try {
            Response<List<UserRetrofitModel>> response = callSync.execute();
            List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
            for (UserRetrofitModel userRetrofitModel : response.body()) {
                userResponseDTOList.add(new UserResponseDTO(
                        userRetrofitModel.getUserId(),
                        userRetrofitModel.getCountyName(),
                        userRetrofitModel.getUserFirstName(),
                        userRetrofitModel.getUserMiddleName(),
                        userRetrofitModel.getUserSurname(),
                        userRetrofitModel.getUserEmail(),
                        userRetrofitModel.getOrganizationName()
                ));
            }

            return userResponseDTOList;

        } catch (Exception ex) {}

        return null;
    }


    @GetMapping(value = "/all-roles")
    @ApiOperation(value = "${UserController.all-roles}", response = Roles.class, responseContainer = "List" ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

//    @GetMapping(value = "/{username}")
//    //@PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = {@Authorization(value = "apiKey")})
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 404, message = "The user doesn't exist"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
//        return modelMapper.map(userService.search(username), UserResponseDTO.class);
//    }
//
//    @GetMapping(value = "/me")
//    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = {@Authorization(value = "apiKey")})
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//    public boolean whoami(HttpServletRequest req) {
//        //return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
//        return true;
//    }

    @GetMapping("/refresh")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }




    @PostMapping("/new-roles")
    @ApiOperation(value = "${UserController.new-roles}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Internal issue")})
    public ResponseEntity<GenericResponse> newRoles() {
        GenericResponse genericResponse = userService.addNewUserRoles();

        if (genericResponse.isSuccess()) {
            return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.valueOf(500));
    }


    @GetMapping(value = "/user-request-by-email/{userEmail}")
    @ApiOperation(value = "${UserController.user-request-by-email}", response = UserResponseDTO.class ,authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad Request"), //
            @ApiResponse(code = 403, message = "Access denied - invalid token")})
    public ResponseEntity<UserResponseDTO> fetchUserByEmail(@ApiParam("userEmail") @PathVariable String userEmail) {

        UserResponseDTO userResponseDTO = userService.fetchAUserDetailsByEmail(userEmail);
        if (userResponseDTO == null) {
            return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.valueOf(422));
        }
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.valueOf(200));
    }



    @PostMapping("/update-user-details")
    @ApiOperation(value = "${UserController.update-user-details}", response = GenericResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Bad request"), //
            @ApiResponse(code = 422, message = "Internal issue")})
    public ResponseEntity<GenericResponse> updateAUserDetails(@ApiParam("User updated details") @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        GenericResponse genericResponse = userService.updateAUserDetails(userUpdateRequestDto);

        if (genericResponse.isSuccess()) {
            return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.valueOf(422));
    }

}
