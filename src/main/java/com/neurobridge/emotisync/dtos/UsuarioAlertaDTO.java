package com.neurobridge.emotisync.dtos;

public class UsuarioAlertaDTO {
    private int idUsuario;
    private int nivelMaximo;

    public UsuarioAlertaDTO(int idUsuario, int nivelMaximo) {
        this.idUsuario = idUsuario;
        this.nivelMaximo = nivelMaximo;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }
}
