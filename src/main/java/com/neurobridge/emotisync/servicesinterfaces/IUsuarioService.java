package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> getUsuarios();
    public List<Usuario> buscarPacientesService();
    public void insert(Usuario usuario);
    public Usuario listId(int id);
    public void update(Usuario usuario);
    public void delete(int id);
    public List<Usuario> buscarPacientesPorMedico(String email);
    public List<String[]> cantidadDePacientesPorEspecialidad();
    public List<Usuario> buscarEspecialista();
    public List<Usuario> buscarFamiliares();
}
