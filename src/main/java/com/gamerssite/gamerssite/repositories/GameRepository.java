package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByLink(String link);
}
