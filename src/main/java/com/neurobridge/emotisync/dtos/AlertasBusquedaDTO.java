package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class AlertasBusquedaDTO {
    private String nombreUsuario;
    private int nivel_alerta;

    public AlertasBusquedaDTO(String nombreUsuario, int nivel_alerta) {
        this.nombreUsuario = nombreUsuario;
        this.nivel_alerta = nivel_alerta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getNivel_alerta() {
        return nivel_alerta;
    }

    public void setNivel_alerta(int nivel_alerta) {
        this.nivel_alerta = nivel_alerta;
    }
}
