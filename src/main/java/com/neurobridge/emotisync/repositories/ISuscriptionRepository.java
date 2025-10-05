package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Susbcription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISuscriptionRepository extends JpaRepository<Susbcription, Integer> {
    @Query("Select s from Susbcription s where s.nombre_plan like %:nSuscripcion%")
    public List<Susbcription> buscar(@Param("nSuscripcion") String nSuscripcion);
}
