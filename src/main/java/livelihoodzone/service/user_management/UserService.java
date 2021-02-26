package livelihoodzone.service.user_management;

import javax.servlet.http.HttpServletRequest;

import livelihoodzone.dto.user_management.AuthenticationObject;
import livelihoodzone.dto.user_management.SimplifiedUserRolesDto;
import livelihoodzone.repository.user_management.UserRolesRepository;
import livelihoodzone.service.user_management.retrofit.RetrofitClientInstance;
import livelihoodzone.service.user_management.retrofit.user_management.UserRetrofitService;
import livelihoodzone.service.user_management.retrofit.user_management.UserRolesRetrofitModel;
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
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthenticationObject signin(String email, String attemtedPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    User user = userRepository.findByUserEmail(email);
    if (passwordEncoder.matches(attemtedPassword, user.getEncryptedPassword())) {
      return new AuthenticationObject(true
              , jwtTokenProvider.createToken(email, userRolesRepository.findByUserId(userRepository.findByUserEmail(email).getUserId()))
              ,user.getUserEmail()
              ,user.getOrganizationName()
      ,getAUserSimplifiedRoles(user.getUserId()));
    } else {
      return new AuthenticationObject(false, null,null,null,null);
    }
  }

  public String signup(User user) {
    if (!userRepository.existsByUserEmail(user.getUserEmail())) {
      user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUserEmail(), userRolesRepository.findByUserId(userRepository.findByUserEmail(user.getUserEmail()).getUserId()));
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
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
        simplifiedUserRolesDtoList.add(new SimplifiedUserRolesDto(userRolesRetrofitModel.getRoleDescription(),userRolesRetrofitModel.getConfirmationStatus() == 1));
      }

      return simplifiedUserRolesDtoList;

    } catch (Exception ex) {}

    return null;
  }

}
