package com.greenfoxacademy.backend.model;

import com.greenfoxacademy.backend.model.reaction.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(targetEntity = Meme.class)
  @JoinColumn(name = "meme_id")
  private Meme meme;
  private Timestamp timestamp;

  public Comment() {
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
  }
}