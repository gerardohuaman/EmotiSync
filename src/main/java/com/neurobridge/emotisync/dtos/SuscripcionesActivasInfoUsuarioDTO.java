package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class SuscripcionesActivasInfoUsuarioDTO {
    private int idUsuarioSuscripcion;
    private int idUsuario;
    private String email;
    private int idPlanesSuscripcion;
    private String nombrePlan;
    private float precio;

    public int getIdUsuarioSuscripcion() {
        return idUsuarioSuscripcion;
    }

    public void setIdUsuarioSuscripcion(int idUsuarioSuscripcion) {
        this.idUsuarioSuscripcion = idUsuarioSuscripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPlanesSuscripcion() {
        return idPlanesSuscripcion;
    }

    public void setIdPlanesSuscripcion(int idPlanesSuscripcion) {
        this.idPlanesSuscripcion = idPlanesSuscripcion;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
