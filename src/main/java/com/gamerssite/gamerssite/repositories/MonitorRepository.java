package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.Monitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor, Long> {
}
