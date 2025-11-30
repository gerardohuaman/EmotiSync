package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class DiarioDTOInsert {
    private int idDiario;
    private String titulo;
    private String contenido;
    private LocalDate fecha;
    private int usuario;
    private int emociones;

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

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEmociones() {
        return emociones;
    }

    public void setEmociones(int emociones) {
        this.emociones = emociones;
    }
}
