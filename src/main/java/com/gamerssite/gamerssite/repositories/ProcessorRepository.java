package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.Processor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends CrudRepository<Processor, Long> {
}
