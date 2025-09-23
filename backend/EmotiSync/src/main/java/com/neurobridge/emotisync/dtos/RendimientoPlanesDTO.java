package com.neurobridge.emotisync.dtos;

public class RendimientoPlanesDTO {
    private int idPlanesSuscripcion;
    private String nombrePlan;
    private float precio;

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
