package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEjercicioRepository extends JpaRepository<Ejercicio, Integer> {

    @Query(value = "select e from Ejercicio e where e.nombre = :nombre")
    public List<Ejercicio> buscarEjercicioPorNombre(@Param("nombre") String nombre);

}
