package com.greenfoxacademy.backend.service;

import com.greenfoxacademy.backend.exception.reactionException.NoSuchReactionException;
import com.greenfoxacademy.backend.model.meme.Meme;
import com.greenfoxacademy.backend.model.reaction.Reaction;
import com.greenfoxacademy.backend.model.reaction.ReactionFactory;
import com.greenfoxacademy.backend.model.reaction.ReactionType;
import com.greenfoxacademy.backend.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

  private ReactionRepository reactionRepository;
  private ReactionFactory reactionFactory;

  @Autowired
  public ReactionService(ReactionRepository reactionRepository,
                         ReactionFactory reactionFactory) {
    this.reactionRepository = reactionRepository;
    this.reactionFactory = reactionFactory;
  }

  public void addReactionToNewMeme(Meme meme) {
    meme.addReaction(saveReactionWithMeme(ReactionType.ANGRY, meme));
    meme.addReaction(saveReactionWithMeme(ReactionType.CRINGE, meme));
    meme.addReaction(saveReactionWithMeme(ReactionType.FUNNY, meme));
    meme.addReaction(saveReactionWithMeme(ReactionType.HORNY, meme));
    meme.addReaction(saveReactionWithMeme(ReactionType.SAD, meme));
    meme.addReaction(saveReactionWithMeme(ReactionType.SCARY, meme));
  }

  public Reaction saveReactionWithMeme(ReactionType reactionType, Meme meme) {
    Reaction reaction = reactionFactory.createReaction(reactionType);
    reaction.setMeme(meme);
    return reaction;
  }


  protected List<Reaction> increaseReaction(List<Reaction> reactionList, String reaction, Integer value)
      throws NoSuchReactionException {
    int counter = 0;
    for (Reaction item : reactionList) {
      if (reaction.equalsIgnoreCase(item.getType())) {
        item.setValue(item.getValue() + value);
        counter++;
      }
    }
    if (counter > 1) {
      throw new NoSuchReactionException();
    }
    return reactionList;
  }
}
