package com.neurobridge.emotisync.dtos;

public class UsuarioAlertaDTO {
    private int idUsuario;
    private String nombreUsuario;
    private int nivelMaximo;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    public UsuarioAlertaDTO(int idUsuario, String nombreUsuario, int nivelMaximo) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nivelMaximo = nivelMaximo;


    }
}
