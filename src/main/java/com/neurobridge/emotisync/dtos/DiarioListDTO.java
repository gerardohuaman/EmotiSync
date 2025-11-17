package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class DiarioListDTO {
    private int idDiario;
    private String titulo;
    private String contenido;
    private LocalDate fecha;
    private UsuarioListDTO usuario;
    private EmocionesDTOList emociones;

    public int getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(int idDiario) {
        this.idDiario = idDiario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EmocionesDTOList getEmociones() {
        return emociones;
    }

    public void setEmociones(EmocionesDTOList emociones) {
        this.emociones = emociones;
    }

    public UsuarioListDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioListDTO usuario) {
        this.usuario = usuario;
    }
}
