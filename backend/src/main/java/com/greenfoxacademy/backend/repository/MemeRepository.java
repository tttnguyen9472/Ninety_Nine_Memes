package com.greenfoxacademy.backend.repository;

import com.greenfoxacademy.backend.model.meme.Meme;
import org.springframework.data.repository.CrudRepository;

public interface MemeRepository extends CrudRepository<Meme, Long> {
}
