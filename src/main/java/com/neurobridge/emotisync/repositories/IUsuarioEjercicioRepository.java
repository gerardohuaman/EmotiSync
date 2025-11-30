package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.UsuarioEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioEjercicioRepository extends JpaRepository<UsuarioEjercicio, Integer> {

    List<UsuarioEjercicio> findByUsuario_Username(String username);

    @Query(value = "SELECT \n" +
            "    u.id_usuario as usuario_id,\n" +
            "    u.nombre,\n" +
            "    u.apellido,\n" +
            "    COUNT(ue.id_ejercicio) as ejercicios_completados\n" +
            " FROM usuario u\n" +
            " JOIN usuario_ejercicio ue ON u.id_usuario = ue.id_usuario\n" +
            " WHERE ue.fecha_realizacion IS NOT NULL\n" +
            " GROUP BY u.id_usuario, u.nombre, u.apellido\n" +
            " ORDER BY ejercicios_completados DESC", nativeQuery = true)
    public List<String[]> ejerciciosPorUsuario();
}
