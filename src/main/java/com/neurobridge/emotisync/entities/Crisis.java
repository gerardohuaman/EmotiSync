package com.neurobridge.emotisync.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "crisis")
public class Crisis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCrisis;

//    @Column(name = "idUsuario", nullable = false, length = 50)
//    private int idUsuario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tiempoRespuesta", nullable = false)
    private float tiempoRespuesta;

    @Column(name = "ritmo", nullable = false)
    private float ritmo;

    @Column(name = "articulacion", nullable = false)
    private float articulacion;

    @Column(name = "f0_promedio", nullable = false)
    private float f0_promedio;

    @Column(name = "formantes_detectados", nullable = false)
    private String formantesDetectados;

    //a tabla usuario
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Crisis() {
    }

    public Crisis(int idCrisis, LocalDate fecha, float tiempoRespuesta, float ritmo, float articulacion, float f0_promedio, String formantesDetectados, Usuario usuario) {
        this.idCrisis = idCrisis;
        this.fecha = fecha;
        this.tiempoRespuesta = tiempoRespuesta;
        this.ritmo = ritmo;
        this.articulacion = articulacion;
        this.f0_promedio = f0_promedio;
        this.formantesDetectados = formantesDetectados;
        this.usuario = usuario;
    }

    public int getIdCrisis() {
        return idCrisis;
    }

    public void setIdCrisis(int idCrisis) {
        this.idCrisis = idCrisis;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(float tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public float getRitmo() {
        return ritmo;
    }

    public void setRitmo(float ritmo) {
        this.ritmo = ritmo;
    }

    public float getArticulacion() {
        return articulacion;
    }

    public void setArticulacion(float articulacion) {
        this.articulacion = articulacion;
    }

    public float getF0_promedio() {
        return f0_promedio;
    }

    public void setF0_promedio(float f0_promedio) {
        this.f0_promedio = f0_promedio;
    }

    public String getFormantesDetectados() {
        return formantesDetectados;
    }

    public void setFormantesDetectados(String formantesDetectados) {
        this.formantesDetectados = formantesDetectados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

