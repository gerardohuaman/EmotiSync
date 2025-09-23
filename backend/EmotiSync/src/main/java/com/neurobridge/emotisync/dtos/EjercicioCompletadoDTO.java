package com.neurobridge.emotisync.dtos;

public class EjercicioCompletadoDTO {
    int usuarioId;
    String nombre;
    String apellido;
    int ejerciciosCompletados;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEjerciciosCompletados() {
        return ejerciciosCompletados;
    }

    public void setEjerciciosCompletados(int ejerciciosCompletados) {
        this.ejerciciosCompletados = ejerciciosCompletados;
    }
}
