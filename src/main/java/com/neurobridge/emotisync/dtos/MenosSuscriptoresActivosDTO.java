package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Planes_suscripcion;

public class MenosSuscriptoresActivosDTO {
    private int planId;
    private String nombrePlan;
    private double precio;
    private int suscriptoresActivos;
    private int totalHistorial;
    private double porcentajeActivo;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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

    public double getPorcentajeActivo() {
        return porcentajeActivo;
    }

    public void setPorcentajeActivo(double porcentajeActivo) {
        this.porcentajeActivo = porcentajeActivo;
    }
}
