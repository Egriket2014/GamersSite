package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.GraphicCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicCardRepository extends CrudRepository<GraphicCard, Long> {
}
