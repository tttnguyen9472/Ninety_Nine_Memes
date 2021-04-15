package com.greenfoxacademy.backend.repository;

import com.greenfoxacademy.backend.model.reaction.Reaction;
import org.springframework.data.repository.CrudRepository;

public interface ReactionRepository extends CrudRepository<Reaction, Long> {
}
