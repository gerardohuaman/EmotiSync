package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class DiarioListDTO {
    private int idDiario;
    private String titulo;
    private String contenido;
    private LocalDate fecha;
    private int usuarioId;
    private int emocionesId;

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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getEmocionesId() {
        return emocionesId;
    }

    public void setEmocionesId(int emocionesId) {
        this.emocionesId = emocionesId;
    }
}
