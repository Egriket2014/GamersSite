package com.gamerssite.gamerssite.repositories;

import com.gamerssite.gamerssite.entity.Memory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends CrudRepository<Memory, Long> {
}
