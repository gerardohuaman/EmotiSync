package com.neurobridge.emotisync.dtos;

public class RendimientoPlanesDTO {
    private int idPlanesSuscripcion;
    private String nombrePlan;
    private float precio;
    private int suscriptoresActivos;
    private int totalHistorial;
    private float precioTotalEstimado;

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

    public int getSuscriptoresActivos() {
        return suscriptoresActivos;
    }

    public void setSuscriptoresActivos(int suscriptoresActivos) {
        this.suscriptoresActivos = suscriptoresActivos;
    }

    public int getTotalHistorial() {
        return totalHistorial;
    }

    public void setTotalHistorial(int totalHistorial) {
        this.totalHistorial = totalHistorial;
    }

    public float getPrecioTotalEstimado() {
        return precioTotalEstimado;
    }

    public void setPrecioTotalEstimado(float precioTotalEstimado) {
        this.precioTotalEstimado = precioTotalEstimado;
    }
}
