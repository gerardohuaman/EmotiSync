package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class UsuarioEjercicioDTO {

    private int idUsuarioEjercicio;

    private UsuarioListDTO usuario;

    private EjercicioDTO  ejercicio;

    private LocalDate fechaRealizacion;

    private String resultado;

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }


    public UsuarioListDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioListDTO usuario) {
        this.usuario = usuario;
    }

    public EjercicioDTO getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioDTO ejercicio) {
        this.ejercicio = ejercicio;
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
