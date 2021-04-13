package com.greenfoxacademy.backend.model.meme;

import com.greenfoxacademy.backend.model.comment.Comment;
import com.greenfoxacademy.backend.model.comment.CommentDTO;
import com.greenfoxacademy.backend.model.reaction.Reaction;
import com.greenfoxacademy.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemeDTO {


//    Array[ meme object: { memeId, timestamp, metada[funny, cringe, horny stb.] array[comments], img } ]
    private String caption;
    private String url;

}
