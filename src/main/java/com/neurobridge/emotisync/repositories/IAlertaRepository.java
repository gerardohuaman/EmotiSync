package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.dtos.AlertasBusquedaDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaCountDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaDTO;
import com.neurobridge.emotisync.dtos.UsuarioPromedioAlertasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.Alertas;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alertas, Integer>{
    List<Alertas> findByUsuario_Username(String username);

    @Query("SELECT new com.neurobridge.emotisync.dtos.UsuarioAlertaCountDTO(u.idUsuario, u.nombre, COUNT(a)) " +
            "FROM Alertas a JOIN a.usuario u " +
            "GROUP BY u.idUsuario, u.nombre")
    List<UsuarioAlertaCountDTO> contarAlertasPorUsuario();


    //Obtiene todas las alertas de un usuario específico, siempre y cuando ese usuario haya tenido alguna crisis dentro de un rango de fechas.
    @Query("SELECT new com.neurobridge.emotisync.dtos.AlertasBusquedaDTO(u.nombre, a.nivel_alerta) " +
            "FROM Alertas a JOIN a.usuario u " +
            "WHERE u.nombre LIKE CONCAT(:letra, '%')")
    List<AlertasBusquedaDTO> buscarAlertasPorNombreUsuario(@Param("letra") String letra);

    @Query("SELECT new com.neurobridge.emotisync.dtos.UsuarioPromedioAlertasDTO(a.usuario.nombre, AVG(a.nivel_alerta)) " +
            "FROM Alertas a " +
            "GROUP BY a.usuario.nombre " +
            "HAVING AVG(a.nivel_alerta) >= :nivelCritico")
    List<UsuarioPromedioAlertasDTO> usuariosConPromedioAlertasAltas(@Param("nivelCritico") double nivelCritico);

    // En tu IAlertasRepository (o como se llame tu interfaz de repositorio)

    @Query("SELECT new com.neurobridge.emotisync.dtos.UsuarioAlertaDTO(u.idUsuario, u.nombre, MAX(a.nivel_alerta)) " +
            "FROM Alertas a JOIN a.usuario u " +
            "WHERE a.nivel_alerta >= :nivel " +
            "GROUP BY u.idUsuario, u.nombre")
    List<UsuarioAlertaDTO> obtenerUsuariosConAlertasCriticas(@Param("nivel") int nivel);
}
