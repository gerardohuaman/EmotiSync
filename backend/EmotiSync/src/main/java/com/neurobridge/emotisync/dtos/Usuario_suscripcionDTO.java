package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Susbcription;
import java.time.LocalDate;
public class Usuario_suscripcionDTO {
    private int idUsuarioSuscripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Susbcription PlanesSuscripcion;

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

    public Susbcription getPlanesSuscripcion() {
        return PlanesSuscripcion;
    }

    public void setPlanesSuscripcion(Susbcription planesSuscripcion) {
        PlanesSuscripcion = planesSuscripcion;
    }
}
