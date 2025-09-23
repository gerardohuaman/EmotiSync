package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Emociones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmocionesRepository extends JpaRepository<Emociones, Integer> {
    @Query("select e from Emociones e where e.intensidad=5")
    public List<Emociones> buscarEmocionesIntensidad5();

    @Query("select e from Emociones e where e.tipoEmocion= :nombreEmocion and e.intensidad=:numero")
    public List<Emociones> buscarEmocionesIntensidad(@Param("nombreEmocion") String nombre, @Param("numero") float numero);
}
