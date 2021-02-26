package livelihoodzone.service.user_management;

import javax.servlet.http.HttpServletRequest;

import livelihoodzone.dto.user_management.AuthenticationObject;
import livelihoodzone.repository.user_management.UserRolesRepository;
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
              ,user.getOrganizationName());
    } else {
      return new AuthenticationObject(false, null,null,null);
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

}
