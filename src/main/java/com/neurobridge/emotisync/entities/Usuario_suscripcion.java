package com.neurobridge.emotisync.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Usuario_suscripcion")
public class Usuario_suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioSuscripcion;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idPlanes_Suscripcion")
    private Planes_suscripcion planesSuscripcion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Usuario_suscripcion(){

    }

    public Usuario_suscripcion(int idUsuarioSuscripcion, LocalDate fechaInicio, LocalDate fechaFin, String estado, Planes_suscripcion planesSuscripcion, Usuario usuario) {
        this.idUsuarioSuscripcion = idUsuarioSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.planesSuscripcion = planesSuscripcion;
        this.usuario = usuario;
    }

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

    public Planes_suscripcion getPlanesSuscripcion() {
        return planesSuscripcion;
    }

    public void setPlanesSuscripcion(Planes_suscripcion planesSuscripcion) {
        this.planesSuscripcion = planesSuscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}