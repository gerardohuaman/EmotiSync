package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;

public class CrisisDTO {
    private int idCrisis;
    //private int idUsuario;
    private Usuario usuario;
    private LocalDate fecha;
    private float tiempoRespuesta;
    private float ritmo;
    private float articulacion;
    private float f0_promedio;
    private String formantesDetectados;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
}
