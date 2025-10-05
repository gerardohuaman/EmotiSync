package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Ejercicio;
import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;

public class UsuarioEjercicioInsertDTO {

    private int idUsuarioEjercicio;

    private Usuario usuario;

    private Ejercicio ejercicio;

    private LocalDate fechaRealizacion;

    private String resultado;

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
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
