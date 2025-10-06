package com.neurobridge.emotisync.dtos;

public class RecursoPromedioDTO {
    private String nombreCreador;
    private double promedioRecursos;

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public double getPromedioRecursos() {
        return promedioRecursos;
    }

    public void setPromedioRecursos(double promedioRecursos) {
        this.promedioRecursos = promedioRecursos;
    }
}
