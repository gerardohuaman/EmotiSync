package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiarioRepository extends JpaRepository<Diario, Integer> {
    List<Diario> findByUsuario_Username(String username);
}
