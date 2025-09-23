package com.neurobridge.emotisync.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Usuario_suscripcion")
public class Usuario_suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioSuscripcion;

    //Falta asociar a la tabla de Usuario

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idPlanes_Suscripcion")
    private Susbcription PlanesSuscripcion;

    public Usuario_suscripcion(){

    }

    public Usuario_suscripcion(int idUsuarioSuscripcion, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, String estado, Susbcription planesSuscripcion) {
        this.idUsuarioSuscripcion = idUsuarioSuscripcion;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        PlanesSuscripcion = planesSuscripcion;
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

    public Susbcription getPlanesSuscripcion() {
        return PlanesSuscripcion;
    }

    public void setPlanesSuscripcion(Susbcription planesSuscripcion) {
        PlanesSuscripcion = planesSuscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

