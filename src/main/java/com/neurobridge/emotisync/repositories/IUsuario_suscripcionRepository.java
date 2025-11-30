package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario_suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuario_suscripcionRepository extends JpaRepository<Usuario_suscripcion, Integer> {
    List<Usuario_suscripcion> findByUsuario_Username(String username);

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
            WHERE us.estado = 'Activo'
            ORDER BY us.fecha_inicio DESC;""", nativeQuery = true)
    public List<String[]> buscarActivos();

    //2. segundo query de búsqueda
    //Historial de suscripciones de un usuario (por email o user_id)
    @Query(value = """
            SELECT
              p.nombre_plan,
              p.precio,
              us.estado
            FROM usuario_suscripcion us
            JOIN usuario u ON us.id_usuario = u.id_usuario
            JOIN planes_suscripcion p ON us.id_planes_suscripcion = p.id_planes_suscripcion
            WHERE u.email = :email
            ORDER BY us.fecha_inicio DESC;""", nativeQuery = true)
    public List<String[]> buscarPorEmail(@Param("email") String email);

    //3. primer query toma de decision
    //Rendimiento de planes (nº de suscriptores activos y estimación de ingreso)
    @Query(value = """
            SELECT
              p.id_planes_suscripcion AS plan_id,
              p.nombre_plan,
              p.precio,
              COUNT(us.id_usuario_suscripcion) FILTER (WHERE us.estado = 'Activo') AS suscriptores_activos,
              COUNT(us.id_usuario_suscripcion) AS total_historial,
              (COUNT(us.id_usuario_suscripcion) FILTER (WHERE us.estado = 'Activo') * COALESCE(p.precio,0)) AS ingreso_estimado_activos
            FROM planes_suscripcion p
            LEFT JOIN usuario_suscripcion us ON us.id_planes_suscripcion = p.id_planes_suscripcion
            GROUP BY p.id_planes_suscripcion, p.nombre_plan, p.precio
            ORDER BY suscriptores_activos DESC; """, nativeQuery = true)
    public List<String[]> buscarPorIdPlanesSuscripcion();

    //4. cuarto query toma de decision
    //Listar los planes con menos suscriptores activos
    @Query(value = """
            SELECT
                p.id_planes_suscripcion AS plan_id,
                p.nombre_plan            AS nombre_plan,
                COALESCE(p.precio,0)     AS precio,
                COALESCE(COUNT(us.id_usuario_suscripcion) FILTER (WHERE LOWER(us.estado) = 'activo'), 0) AS suscriptores_activos,
                COALESCE(COUNT(us.id_usuario_suscripcion), 0) AS total_historial,
                CASE
                  WHEN COALESCE(COUNT(us.id_usuario_suscripcion),0) = 0 THEN 0
                  ELSE ROUND((COUNT(us.id_usuario_suscripcion) FILTER (WHERE LOWER(us.estado) = 'activo')::numeric / COUNT(us.id_usuario_suscripcion)::numeric) * 100, 2)
                END AS porcentaje_activos
              FROM planes_suscripcion p
              LEFT JOIN usuario_suscripcion us
                ON us.id_planes_suscripcion = p.id_planes_suscripcion
              GROUP BY p.id_planes_suscripcion, p.nombre_plan, p.precio
              ORDER BY suscriptores_activos ASC, porcentaje_activos ASC;""", nativeQuery = true)
    public List<String[]> buscarPlanesMenosSuscriptoresActivos();
}
