package com.neurobridge.emotisync.dtos;

public class UsuarioPromedioAlertasDTO {
    private String nombreUsuario;
    private double promedio_alertas;

    public UsuarioPromedioAlertasDTO(String nombreUsuario, double promedio_alertas) {
        this.nombreUsuario = nombreUsuario;
        this.promedio_alertas = promedio_alertas;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public double getPromedio_alertas() {
        return promedio_alertas;
    }

    public void setPromedio_alertas(double promedio_alertas) {
        this.promedio_alertas = promedio_alertas;
    }
}
