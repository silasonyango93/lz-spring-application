package livelihoodzone.security;

import livelihoodzone.repository.user_management.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.user_management.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRolesRepository userRolesRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = userRepository.findByUserEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(email)//
        .password(user.getEncryptedPassword())//
        //.authorities(user.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
