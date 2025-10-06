package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.dtos.AlertasBusquedaDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaCountDTO;
import com.neurobridge.emotisync.dtos.UsuarioAlertaDTO;
import com.neurobridge.emotisync.dtos.UsuarioPromedioAlertasDTO;
import com.neurobridge.emotisync.entities.Alertas;
import com.neurobridge.emotisync.entities.Ejercicio;

import java.time.LocalDate;
import java.util.List;

public interface IAlertaService {
    public List<Alertas> list();
    public void insert(Alertas alerta);
    public Alertas listId(int id);
    public void update(Alertas alerta);
    public void delete(int id);
    List<UsuarioAlertaCountDTO> contarAlertasPorUsuario();
    List<AlertasBusquedaDTO> buscarAlertasPorNombreUsuario(String letra);
    List<UsuarioPromedioAlertasDTO> usuariosConPromedioAlertasAltas(double nivelCritico);
    List<UsuarioAlertaDTO> obtenerUsuariosConAlertasCriticas(int nivelCritico);
}
