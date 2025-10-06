package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Alertas;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alertas, Integer>{
    //Se obtiene todas las alertas activas de un usuario específico ordenadas por nivel de alerta
    @Query(value = "SELECT a FROM Alertas a " +
            "WHERE a.usuario.idUsuario = :idUsuario " +
            "ORDER BY a.nivel_alerta DESC, a.idAlerta DESC",
            nativeQuery = false)
    List<Alertas> buscarAlertasPorUsuario(@Param("idUsuario") int idUsuario);

    @Query("SELECT c.usuario.idUsuario " +
            "FROM Crisis c " +
            "WHERE c.tiempoRespuesta > 5 " +
            "GROUP BY c.usuario.idUsuario " +
            "HAVING COUNT(c.idCrisis) > 2")
    List<Integer> usuariosConRespuestaLenta();


}
