package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEjercicioRepository extends JpaRepository<Ejercicio, Integer> {
}
