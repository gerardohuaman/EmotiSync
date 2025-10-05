package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uS;

    @Override
    public List<Usuario> getUsuarios() {
        return uS.findAll();
    }

    @Override
    public List<Usuario> buscarPacientesService() {
        return uS.buscarPacientes();
    }

    @Override
    public void insert(Usuario usuario) {
        uS.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return uS.findById(id).orElse(null);
    }

    @Override
    public void update(Usuario usuario) {
        uS.save(usuario);
    }

    @Override
    public void delete(int id) {
        uS.deleteById(id);
    }

    @Override
    public List<Usuario> buscarPacientesPorMedico(String email) {
        return uS.buscarPacientesPorMedico(email);
    }

    @Override
    public List<String[]> cantidadDePacientesPorEspecialista() {
        return uS.cantidadDePacientesPorEspecialista();
    }


}
