package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Usuario;

public class AlertasDTOInsert {
    private int idAlerta;
    private String  tipo_alerta;
    private String  mensaje;
    private int  nivel_alerta;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNivel_alerta() {
        return nivel_alerta;
    }

    public void setNivel_alerta(int nivel_alerta) {
        this.nivel_alerta = nivel_alerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }
}
