package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Emociones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmocionesRepository extends JpaRepository<Emociones, Integer> {
}
