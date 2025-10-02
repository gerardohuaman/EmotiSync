package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISintomaRepository extends JpaRepository<Sintoma, Integer> {

    // -------- BUSQUEDA --------
    @Query("SELECT s FROM Sintoma s WHERE LOWER(s.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Sintoma> buscarPorNombre(String nombre);

    @Query("SELECT s FROM Sintoma s WHERE LOWER(s.descripcion) LIKE LOWER(CONCAT('%', :desc, '%'))")
    List<Sintoma> buscarPorDescripcion(String desc);

    // -------- DECISIÓN / AGREGACIÓN --------
    @Query("SELECT COUNT(s) FROM Sintoma s")
    long contarTotal();

    @Query("SELECT CASE WHEN COUNT(s)>0 THEN TRUE ELSE FALSE END FROM Sintoma s WHERE s.nombre = :nombre")
    boolean existePorNombre(String nombre);
}
