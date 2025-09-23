package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuario_suscripcionRepository extends JpaRepository<Usuario_suscripcion, Integer> {
    @Query("Select u from Usuario_suscripcion u where u.estado like %:nEstado%")
    public List<Usuario_suscripcion> buscar(@Param("nEstado") String nEstado);

    //1. primer query de búsqueda
    //Suscripciones activas con datos de usuario y plan
    @Query(value = """
            SELECT
                us.id_usuario_suscripcion AS suscripcion_id,
                u.id_usuario  AS usuario_id,
                u.email,
                p.id_planes_suscripcion  AS plan_id,
                p.nombre_plan,
                p.precio,
                us.estado
            FROM usuario_suscripcion us
            JOIN usuario u ON us.id_usuario = u.id_usuario
            JOIN planes_suscripcion p ON us.id_planes_suscripcion = p.id_planes_suscripcion
            WHERE us.estado = 'activo'
            ORDER BY us.fecha_inicio DESC;""", nativeQuery = true)
    public List<Object[]> buscarActivos();

    //2. segundo query de búsqueda
    //Historial de suscripciones de un usuario (por email o user_id)
    @Query(value = """
            SELECT
                p.nombre_plan,
                p.precio,
                us.estado
          FROM usuario_suscripcion us
          JOIN usuario u ON us.id_usuario_suscripcion = u.id_usuario
          JOIN planes_suscripcion p ON us.id_planes_suscripcion = p.id_planes_suscripcion
          WHERE u.email = 'ejemplo123@gmail.com' or u.id_usuario = 1
          ORDER BY us.fecha_inicio DESC;""", nativeQuery = true)
    public List<Object[]> buscarPorEmail(int id_usuario);

    //3. primer query toma de decision
    //Rendimiento de planes (nº de suscriptores activos y estimación de ingreso)
    @Query(value = """
            SELECT
              p.id_planes_suscripcion AS plan_id,
              p.nombre_plan,
              p.precio,
              COUNT(us.id_usuario_suscripcion) FILTER (WHERE us.estado = 'Activo') AS suscriptores_activos,
              COUNT(us.id_usuario_suscripcion) AS total_historial,
              (COUNT(us.id_usuario_suscripcion) FILTER (WHERE us.estado = 'Activo') * p.precio) AS ingreso_estimado_activos
            FROM planes_suscripcion p
            LEFT JOIN usuario_suscripcion us ON us.id_usuario_suscripcion = p.id_planes_suscripcion
            GROUP BY p.id_planes_suscripcion, p.nombre_plan, p.precio
            ORDER BY suscriptores_activos DESC; """, nativeQuery = true)
    public List<Object[]> buscarPorIdPlanesSuscripcion();
}
