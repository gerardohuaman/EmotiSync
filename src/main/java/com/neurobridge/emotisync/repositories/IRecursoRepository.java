package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Recurso;

import java.util.List;

@Repository
public interface IRecursoRepository extends JpaRepository<Recurso, Integer> {

    List<Recurso> findByCreador_Username(String username);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Recurso r " +
            "WHERE r.creador.idUsuario = :creadorId AND r.destinatario.idUsuario = :destinatarioId")
    boolean existeRelacionEntreUsuarios(int creadorId, int destinatarioId);


    @Query("SELECT r.creador.nombre, COUNT(r) * 1.0 / (SELECT COUNT(DISTINCT r2.creador.idUsuario) FROM Recurso r2) " +
            "FROM Recurso r " +
            "GROUP BY r.creador.nombre")
    List<Object[]> promedioRecursosPorCreador();

}
