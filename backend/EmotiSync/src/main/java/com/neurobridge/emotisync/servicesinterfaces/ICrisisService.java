package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Crisis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ICrisisService {
    //CRUD
    public void insert(Crisis crisis);
    public List<Crisis> list();
    public void update(Crisis crisis);
    public void delete(int id);

    //extra
    public Crisis listId(int id);

    //queries
    public List<Crisis> buscarPorUsuario(int usuarioId);
    public List<Crisis> buscarPorUsuarioYRangoFechas(Integer usuarioId,LocalDate desde, LocalDate hasta);
    //public List<String[]> cantidadCrisisDelUsuario();

}
