package pe.edu.upc.emotisync.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.emotisync.entities.Alertas;
import pe.edu.upc.emotisync.repositories.IAlertaRepository;
import pe.edu.upc.emotisync.servicesinterfaces.IAlertaService;

import java.util.List;

@Service
public class AlertaServiceImplement implements IAlertaService {

    @Autowired
    private IAlertaRepository repository;

    @Override
    public List<Alertas> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Alertas alerta) { repository.save(alerta);    }
}
