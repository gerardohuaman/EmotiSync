package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select p.pacientes from Usuario p where p.email = :email")
    public List<Usuario> buscarPacientesPorMedico(@Param("email") String email );

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE UPPER(r.rol) = 'PACIENTE'")
    public List<Usuario> buscarPacientes();

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE UPPER(r.rol) = 'ESPECIALISTA'")
    public List<Usuario> buscarEspecialista();

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE UPPER(r.rol) = 'FAMILIAR'")
    public List<Usuario> buscarFamiliares();

    @Query(value = "SELECT \n" +
            "    e.especialidad as especialidad,\n" +
            "    COUNT(DISTINCT p.id_usuario) as cantidad_pacientes\n" +
            "FROM usuario e\n" +
            "INNER JOIN usuario_roles er ON e.id_usuario = er.id_usuario\n" +
            "INNER JOIN roles r ON er.id_rol = r.id_rol\n" +
            "LEFT JOIN usuario p ON p.especialista_id = e.id_usuario\n" +
            "WHERE r.rol = 'ESPECIALISTA'\n" +
            "GROUP BY e.especialidad\n" +
            "ORDER BY cantidad_pacientes DESC", nativeQuery = true)
    public List<String[]> cantidadDePacientesPorEspecialidad();

    @Modifying
    @Transactional
    @Query("update Usuario u set u.especialista = null where u.especialista.idUsuario = :idEspecialista")
    public int desvincularPacientesDeEspecialista(@Param("idEspecialista") Integer idEspecialista);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.familiar = null where u.familiar.idUsuario = :idFamiliar")
    public int desvincularPacientesDeFamiliar(@Param("idFamiliar") Integer idFamiliar);

    public Usuario findOneByUsername(String username);

}
