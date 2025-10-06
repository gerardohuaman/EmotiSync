package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Planes_suscripcion;
import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;
public class Usuario_suscripcionDTO {
    private int idUsuarioSuscripcion;
    private Usuario usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Planes_suscripcion PlanesSuscripcion;

    public int getIdUsuarioSuscripcion() {
        return idUsuarioSuscripcion;
    }

    public void setIdUsuarioSuscripcion(int idUsuarioSuscripcion) {
        this.idUsuarioSuscripcion = idUsuarioSuscripcion;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

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

    public Planes_suscripcion getPlanesSuscripcion() {
        return PlanesSuscripcion;
    }

    public void setPlanesSuscripcion(Planes_suscripcion planesSuscripcion) {
        PlanesSuscripcion = planesSuscripcion;
    }
}
