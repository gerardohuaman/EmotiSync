package pe.edu.upc.emotisync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.emotisync.entities.Alertas;

@Repository
public interface IAlertaRepository extends JpaRepository<Alertas, Integer> {
}
