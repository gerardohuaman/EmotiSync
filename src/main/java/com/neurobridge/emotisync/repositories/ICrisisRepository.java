package com.neurobridge.emotisync.repositories;

import com.neurobridge.emotisync.entities.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICrisisRepository extends JpaRepository<Crisis, Integer> {
    //queries
    @Query("select c from Crisis c where c.ritmo = :ritmo")
    public List<Crisis> buscarPorRitmo(@Param("ritmo") float ritmo);

    @Query("select c from Crisis c where c.usuario.idUsuario = :usuarioId\n" +
            "and c.fecha between :desde and :hasta order by  c.fecha desc")
    public List<Crisis> buscarPorUsuarioYRangoFechas(@Param("usuarioId") Integer usuarioId,
                                                     @Param("desde") LocalDate desde,
                                                     @Param("hasta") LocalDate hasta);

    @Query(value="select c.id_usuario, count(u.id_usuario)\n" +
            "from crisis c inner join usuario u on c.id_usuario=u.id_usuario\n" +
            "group by c.id_usuario", nativeQuery = true)
    public List<String[]> cantidadCrisisDelUsuario();

    //@Query(value="select ")
}
