package com.neurobridge.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neurobridge.emotisync.entities.CrisisSintoma;

@Repository
public interface ICrisisSintomaRepository extends JpaRepository<CrisisSintoma, Integer> {
}
