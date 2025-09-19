package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrisisRepository extends JpaRepository<Crisis, Integer> {

    //aqui va @Query()
    //public ...
}
