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
}
