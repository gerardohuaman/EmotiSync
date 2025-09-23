package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.familiar.idUsuario = :familiarId")
    public Usuario buscarFamiliarPorPaciente(@Param("familiarId") int familiarId );

    @Query("select p from Usuario p where p.especialista.idUsuario = :especialistaId")
    public List<Usuario> buscarPacientesPorMedico(@Param("especialistaId") int especialistaId );

    @Query("select p from Usuario p where p.rol = 'paciente'")
    public List<Usuario> buscarPacientes();

    @Query(value = "SELECT \n" +
            "    e.id_usuario as especialista_id,\n" +
            "    e.nombre as especialista_nombre,\n" +
            "    e.apellido as especialista_apellido,\n" +
            "    e.especialidad,\n" +
            "    COUNT(p.id_usuario) as cantidad_pacientes\n" +
            " FROM usuario e\n" +
            " LEFT JOIN usuario p ON p.especialista_id = e.id_usuario\n" +
            " WHERE e.rol = 'especialista'\n" +
            " GROUP BY e.id_usuario, e.nombre, e.apellido, e.especialidad\n" +
            " ORDER BY cantidad_pacientes DESC", nativeQuery = true)
    public List<String[]> cantidadDePacientesPorEspecialista();

    public Usuario findOneByUsername(String username);

}
