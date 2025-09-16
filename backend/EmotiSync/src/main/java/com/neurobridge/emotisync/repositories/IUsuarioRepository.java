package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.familiar = :familiarId")
    public Usuario buscarFamiliarPorPaciente(@Param("familiarId") int familiarId );

}
