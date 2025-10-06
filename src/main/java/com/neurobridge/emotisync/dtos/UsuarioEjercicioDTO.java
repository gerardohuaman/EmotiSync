package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;

public class UsuarioEjercicioDTO {

    private int idUsuarioEjercicio;

    private UsuarioListDTO idUsuario;

    private EjercicioDTO idEjercicio;

    private LocalDate fechaRealizacion;

    private String resultado;

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }

    public UsuarioListDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioListDTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EjercicioDTO getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(EjercicioDTO idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
