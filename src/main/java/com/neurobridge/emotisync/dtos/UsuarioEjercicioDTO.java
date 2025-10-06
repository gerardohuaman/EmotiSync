package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class UsuarioEjercicioDTO {

    private int idUsuarioEjercicio;

    private int usuarioIdUsuario;

    private int  ejercicioIdEjercicio;

    private LocalDate fechaRealizacion;

    private String resultado;

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getEjercicioIdEjercicio() {
        return ejercicioIdEjercicio;
    }

    public void setEjercicioIdEjercicio(int ejercicioIdEjercicio) {
        this.ejercicioIdEjercicio = ejercicioIdEjercicio;
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
