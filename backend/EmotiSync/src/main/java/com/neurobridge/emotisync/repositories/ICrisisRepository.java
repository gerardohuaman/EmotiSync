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
    //aqui va @Query()
    //public...

    @Query("select c from Crisis c where c.usuario.idUsuario = :usuarioId")
    public List<Crisis> buscarPorUsuario(@Param("usuarioId") int usuarioId);

    @Query("select c from Crisis c where c.usuario.idUsuario = :usuarioId and c.fecha between :desde and :hasta order by  c.fecha desc")
    public List<Crisis> buscarPorUsuarioYRangoFechas(@Param("usuarioId") Integer usuarioId, @Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

//    @Query(value="select c.usuarioId, count(u.usuarioId) from crisis c inner join usuarios u on c.usuarioId=u.usuarioId goup by u.usuarioId", nativeQuery = true)
//    public List<String[]> cantidadCrisisDelUsuario();

}
