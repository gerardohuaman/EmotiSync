package com.example.emotisyncby.repositories;

import com.example.emotisyncby.entities.Emociones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmocionesRepository extends JpaRepository<Emociones, Integer> {
}
