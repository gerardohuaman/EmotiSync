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
    @Query("SELECT a FROM Alertas a " +
            "WHERE a.usuario.idUsuario = :idUsuario " +
            "ORDER BY a.nivel_alerta DESC, a.idAlerta DESC")
    List<Alertas> buscarAlertasPorUsuario(@Param("idUsuario") int idUsuario);

    //Identifica qué usuarios han tenido crisis recurrentes en el último mes
    @Query("SELECT c.idUsuario " +
            "FROM Crisis c " +
            "WHERE c.fecha >= CURRENT_DATE - 30 " +
            "GROUP BY c.idUsuario " +
            "HAVING COUNT(c.idCrisis) > 3")
    List<Integer> usuariosConCrisisFrecuentes();

}
