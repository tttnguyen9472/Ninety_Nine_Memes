package com.greenfoxacademy.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.reaction.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String username;
  @JsonIgnore
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Meme> meme;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Reaction> reaction;

  public User(String username) {
    this.username = username;
  }

  public User(String username,  String password) {
    this.username = username;
    this.password = password;
  }
}
