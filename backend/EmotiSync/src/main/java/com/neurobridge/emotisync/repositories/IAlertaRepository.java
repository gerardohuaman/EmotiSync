package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Alertas;

@Repository
public interface IAlertaRepository extends JpaRepository<Alertas, Integer> {
}
