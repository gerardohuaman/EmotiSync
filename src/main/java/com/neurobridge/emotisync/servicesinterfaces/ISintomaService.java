package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Sintoma;
import java.util.List;

public interface ISintomaService {
    List<Sintoma> list();
    void insert(Sintoma s);
    Sintoma listId(int id);
    void delete(int id);
    void update(Sintoma s);
    List<Sintoma> buscarPorNombre(String nombre);
    List<Sintoma> buscarPorDescripcion(String desc);
    long contarTotal();
    boolean existePorNombre(String nombre);

}
