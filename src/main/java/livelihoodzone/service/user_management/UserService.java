package livelihoodzone.service.user_management;

import javax.servlet.http.HttpServletRequest;

import livelihoodzone.dao.administrative_boundaries.CountiesDao;
import livelihoodzone.dto.user_management.*;
import livelihoodzone.entity.user_management.AuthenticationStatus;
import livelihoodzone.entity.user_management.Roles;
import livelihoodzone.entity.user_management.UserRoles;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.administrative_boundaries.wards.WardsRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.calendar.MonthsRepositrory;
import livelihoodzone.repository.questionnaire.crops.CropsRepository;
import livelihoodzone.repository.questionnaire.tribe.EthnicGroupsRepository;
import livelihoodzone.repository.user_management.RolesRepository;
import livelihoodzone.repository.user_management.UserRolesRepository;
import livelihoodzone.service.livelihoodzones.LivelihoodZonesService;
import livelihoodzone.service.retrofit.RetrofitClientInstance;
import livelihoodzone.service.retrofit.livelihoodzones.CountySubLocationsLivelihoodZonesAssignmentRetrofitModel;
import livelihoodzone.service.retrofit.livelihoodzones.LivelihoodZonesRetrofitService;
import livelihoodzone.service.retrofit.user_management.UserRetrofitService;
import livelihoodzone.service.retrofit.user_management.UserRolesRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import livelihoodzone.exception.CustomException;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.user_management.UserRepository;
import livelihoodzone.security.JwtTokenProvider;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static livelihoodzone.configuration.EndPoints.NODE_SERVICE_BASE_URL;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

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
    CountiesDao countiesDao;

    @Autowired
    CropsRepository cropsRepository;

    @Autowired
    EthnicGroupsRepository ethnicGroupsRepository;

    @Autowired
    MonthsRepositrory monthsRepositrory;

    @Autowired
    LivelihoodZonesService livelihoodZonesService;

    public AuthenticationObject signin(String email, String attemtedPassword) {

        User user = userRepository.findByUserEmail(email);
        if (user == null) {
            return new AuthenticationObject(false, null, null, null, null, null, null, null, AuthenticationStatus.USER_DOES_NOT_EXIST, null);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(attemtedPassword, user.getEncryptedPassword())) {
            return new AuthenticationObject(true
                    , jwtTokenProvider.createToken(email, userRolesRepository.findByUserId(userRepository.findByUserEmail(email).getUserId()))
                    , user.getFirstName()
                    , user.getMiddleName()
                    , user.getSurname()
                    , user.getUserEmail()
                    , user.getOrganizationName()
                    , getAUserSimplifiedRoles(user.getUserId()),
                    AuthenticationStatus.SUCCESSFUL_AUTHENTICATION, processGeography(user.getCountyId()));
        } else {
            return new AuthenticationObject(false, null, null, null, null, null, null, null, AuthenticationStatus.WRONG_CREDENTIALS, null);
        }
    }

    public SignupStatusDto signup(User user, List<RoleAssignmentDto> rolesToBeAssigned) {
        if (!userRepository.existsByUserEmail(user.getUserEmail())) {
            user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
            User createdUser = userRepository.save(user);
            assignAUserRoles(createdUser.getUserId());
            designateSelectedRoles(createdUser.getUserId(), rolesToBeAssigned);
            return new SignupStatusDto(true, "User registration successful");
        } else {
            return new SignupStatusDto(false, "A user already exists by this email");
        }
    }

    public void assignAUserRoles(int userId) {
        List<Roles> roles = rolesRepository.findAll();
        for (Roles role : roles) {
            userRolesRepository.save(new UserRoles(
                    userId,
                    role.getRoleId(),
                    0
            ));
        }
    }

    public void designateSelectedRoles(int userId, List<RoleAssignmentDto> rolesToBeAssigned) {
        for (RoleAssignmentDto roleToBeAssigned : rolesToBeAssigned) {
            UserRoles userRole = fetchAUserSpecificRoleByRoleId(userId, roleToBeAssigned.getRoleId());
            userRole.setConfirmationStatus(1);
            userRolesRepository.save(userRole);
        }
    }

    public UserRoles fetchAUserSpecificRoleByRoleId(int userId, int roleId) {
        UserRetrofitService userRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(UserRetrofitService.class);
        Call<UserRolesRetrofitModel> callSync = userRetrofitService.fetchAUserSpecificRolesByUserIdAndRoleId(userId, roleId);
        try {
            Response<UserRolesRetrofitModel> response = callSync.execute();
            return userRolesRepository.findByUserRoleId(response.body().getUserRoleId());

        } catch (Exception ex) {
        }

        return null;
    }

    public void delete(String userEmail) {
        userRepository.deleteByUserEmail(userEmail);
    }

    public User search(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUserEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String userEmail) {
        return jwtTokenProvider.createToken(userEmail, userRolesRepository.findByUserId(userRepository.findByUserEmail(userEmail).getUserId()));
    }


    public List<SimplifiedUserRolesDto> getAUserSimplifiedRoles(int userId) {
        UserRetrofitService userRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(UserRetrofitService.class);
        Call<List<UserRolesRetrofitModel>> callSync = userRetrofitService.fetchAUserRolesByUserId(userId);
        try {
            Response<List<UserRolesRetrofitModel>> response = callSync.execute();
            List<SimplifiedUserRolesDto> simplifiedUserRolesDtoList = new ArrayList<>();

            for (UserRolesRetrofitModel userRolesRetrofitModel : response.body()) {
                simplifiedUserRolesDtoList.add(new SimplifiedUserRolesDto(userRolesRetrofitModel.getRoleDescription(), userRolesRetrofitModel.getConfirmationStatus() == 1));
            }

            return simplifiedUserRolesDtoList;

        } catch (Exception ex) {
        }

        return null;
    }


    public List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel> fetchACountySubLocationsLivelihoodZoneAssignment(int countyId) {
        LivelihoodZonesRetrofitService livelihoodZonesRetrofitService = RetrofitClientInstance.getRetrofitInstance(NODE_SERVICE_BASE_URL).create(LivelihoodZonesRetrofitService.class);
        Call<List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel>> callSync = livelihoodZonesRetrofitService.fetchACountySubLocationsLivelihoodZoneAssignment(countyId);
        try {
            Response<List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel>> response = callSync.execute();
            return response.body();

        } catch (Exception ex) {
            return null;
        }
    }


    public GeographyObjectDto processGeography(int countyId) {

        GeographyObjectDto geographyObjectDto = new GeographyObjectDto();
        geographyObjectDto.setLivelihoodZones(livelihoodZonesRepository.findAll());

        /*

        todo: The inner join fetches too many duplicates reason why iv commented this part out never to be needed(Replaceb by entity EAGER FETCH)

        List<CountiesEntity> countiesEntityList = countiesDao.fetchCountyComprehensively(countyId);

        if (countiesEntityList.size() > 0) {
            geographyObjectDto.setCounty(countiesEntityList.get(0));
        }

        */

        geographyObjectDto.setCounty(countiesRepository.findByCountyId(countyId));
        geographyObjectDto.setSubLocations(subLocationRepository.findAll());
        geographyObjectDto.setCrops(cropsRepository.findAll());
        geographyObjectDto.setEthnicGroups(ethnicGroupsRepository.findAll());
        geographyObjectDto.setMonths(monthsRepositrory.findAll());
        geographyObjectDto.setSubCounties(subCountiesRepository.findAll());
        geographyObjectDto.setSublocationsLivelihoodZoneAssignments(fetchACountySubLocationsLivelihoodZoneAssignment(countyId));
        geographyObjectDto.setCurrentUserAssignedCountyLivelihoodZones(livelihoodZonesService.fetchACountyLiveliHoodZones(countyId).getLivelihoodZones());

        return geographyObjectDto;
    }

}
