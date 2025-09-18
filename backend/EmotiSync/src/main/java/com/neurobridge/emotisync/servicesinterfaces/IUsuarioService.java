package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> getUsuarios();
    public void insert(Usuario usuario);
    public Usuario listId(int id);
    public void update(Usuario usuario);
    public void delete(int id);
    public Usuario buscarFamiliarPorPacienteService(int id);
    public List<Usuario> buscarPacientesPorMedico(int especialistaId);

}
