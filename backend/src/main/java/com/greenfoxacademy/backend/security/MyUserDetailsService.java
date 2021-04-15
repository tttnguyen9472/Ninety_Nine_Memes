package com.greenfoxacademy.backend.security;


import com.greenfoxacademy.backend.model.user.User;
import com.greenfoxacademy.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {

    Optional<User> user = userRepository.findUserByUsername(username);

    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

    return user.map(com.greenfoxacademy.backend.security.MyUserDetails::new).get();
  }


}
