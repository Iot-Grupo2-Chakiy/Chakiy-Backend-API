package com.iot.error404.chakiy.iam.infrastructure.authorization.sfs.services;

import com.iot.error404.chakiy.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.iot.error404.chakiy.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * This method is responsible for loading the user details from the database.
   * @param userId The username.
   * @return The UserDetails object.
   * @throws UsernameNotFoundException If the user is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Long userIdLong = Long.valueOf(userId);
    var user = userRepository.findById(userIdLong)
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with username: " + userId));
    return UserDetailsImpl.build(user);
  }
}
