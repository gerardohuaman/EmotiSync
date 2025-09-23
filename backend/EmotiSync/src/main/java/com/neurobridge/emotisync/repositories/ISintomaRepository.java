package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Sintoma;

@Repository
public interface ISintomaRepository extends JpaRepository<Sintoma, Integer> {
    // Aquí puedes agregar consultas personalizadas si lo necesitas
}
