package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
