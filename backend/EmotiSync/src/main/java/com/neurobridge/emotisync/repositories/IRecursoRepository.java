package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Recurso;

@Repository
public interface IRecursoRepository extends JpaRepository<Recurso, Integer> {
}
