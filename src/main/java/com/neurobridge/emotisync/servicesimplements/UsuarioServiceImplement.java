package com.neurobridge.emotisync.servicesimplements;

import com.neurobridge.emotisync.entities.Usuario;
import com.neurobridge.emotisync.repositories.IUsuarioRepository;
import com.neurobridge.emotisync.servicesinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uS;

    @Autowired
    private PasswordEncoder pE;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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
        String passwordEncriptada = pE.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
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
        Usuario usuario = uS.findById(id).orElse(null);

        uS.desvincularPacientesDeEspecialista(id);
        uS.desvincularPacientesDeFamiliar(id);
        uS.deleteById(id);
    }

    @Override
    public List<Usuario> buscarPacientesPorMedico(String email) {
        return uS.buscarPacientesPorMedico(email);
    }

    @Override
    public List<String[]> cantidadDePacientesPorEspecialidad() {
        return uS.cantidadDePacientesPorEspecialidad();
    }

    @Override
    public List<Usuario> buscarEspecialista() {
        return uS.buscarEspecialista();
    }

    @Override
    public List<Usuario> buscarFamiliares() {
        return uS.buscarFamiliares();
    }


}
