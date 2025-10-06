package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select p.pacientes from Usuario p where p.email = :email")
    public List<Usuario> buscarPacientesPorMedico(@Param("email") String email );

    @Query("select p from Usuario p where upper(p.rol) = 'PACIENTE'")
    public List<Usuario> buscarPacientes();

    @Query("select e from Usuario e where upper(e.rol) = 'ESPECIALISTA'")
    public List<Usuario> buscarEspecialista();

    @Query("select f from Usuario f where upper(f.rol) = 'FAMILIAR'")
    public List<Usuario> buscarFamiliares();

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
