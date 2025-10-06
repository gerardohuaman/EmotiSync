package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.dtos.AlertasBusquedaDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaCountDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaDTO;
import com.neurobridge.emotisync.dtos.UsuarioPromedioAlertasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.repositories.IAlertaRepository;
import com.neurobridge.emotisync.servicesinterfaces.IAlertaService;

import java.time.LocalDate;
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
    public void insert(Alertas alerta) { repository.save(alerta);  }

    @Override
    public Alertas listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Alertas alerta) {
        repository.save(alerta);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<UsuarioAlertaCountDTO> contarAlertasPorUsuario() {
        return repository.contarAlertasPorUsuario();
    }

    @Override
    public List<AlertasBusquedaDTO> buscarAlertasPorNombreUsuario(String letra) {
        return repository.buscarAlertasPorNombreUsuario(letra);
    }


    @Override
    public List<UsuarioPromedioAlertasDTO> usuariosConPromedioAlertasAltas(double nivelCritico) {
        return repository.usuariosConPromedioAlertasAltas(nivelCritico);
    }


    @Override
    public List<UsuarioAlertaDTO> obtenerUsuariosConAlertasCriticas(int nivelCritico) {
        return repository.findUsuariosConAlertasCriticas(nivelCritico);
    }

}



