package com.greenfoxacademy.backend.model.comment;

import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

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
  private String comment;

  public Comment() {
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
  }

  public Comment(String comment, User user, Meme meme) {
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
    this.comment = comment;
    this.user = user;
    this.meme=meme;
  }
}
