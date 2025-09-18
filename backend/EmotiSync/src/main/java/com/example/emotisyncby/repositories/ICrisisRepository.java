package com.example.emotisyncby.repositories;

import com.example.emotisyncby.entities.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrisisRepository extends JpaRepository<Crisis, Integer> {

    //aqui va @Query()
    //public ...
}
