package com.greenfoxacademy.backend.repository;

import com.greenfoxacademy.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findUserByUsername(String username);

  @Query(value = "SELECT username FROM user", nativeQuery = true)
  List<String> findAllUsername();
}
