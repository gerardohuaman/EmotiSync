package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Planes_suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPlanes_suscripcionRepository extends JpaRepository<Planes_suscripcion, Integer> {
    @Query("Select s from Planes_suscripcion s where s.nombre_plan like %:nSuscripcion%")
    public List<Planes_suscripcion> buscar(@Param("nSuscripcion") String nSuscripcion);
}
