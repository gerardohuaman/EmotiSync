package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Planes_suscripcion;
import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;
public class Usuario_suscripcionDTO {
    private int idUsuarioSuscripcion;
    private UsuarioListDTO usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Planes_suscripcion planesSuscripcion;

    public int getIdUsuarioSuscripcion() {
        return idUsuarioSuscripcion;
    }

    public void setIdUsuarioSuscripcion(int idUsuarioSuscripcion) {
        this.idUsuarioSuscripcion = idUsuarioSuscripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioListDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioListDTO usuario) {
        this.usuario = usuario;
    }

    public Planes_suscripcion getPlanesSuscripcion() {
        return planesSuscripcion;
    }

    public void setPlanesSuscripcion(Planes_suscripcion planesSuscripcion) {
        this.planesSuscripcion = planesSuscripcion;
    }
}
