package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class RecursoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String enlace;
    private String tipo;
    private LocalDate fechaCr;
    private UsuarioListDTO creador; // <-- LÍNEA NUEVA
    private UsuarioListDTO destinatario; // <-- LÍNEA NUEVA
    private boolean esPublico;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEnlace() { return enlace; }
    public void setEnlace(String enlace) { this.enlace = enlace; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFechaCr() { return fechaCr; }
    public void setFechaCr(LocalDate fechaCr) { this.fechaCr = fechaCr; }

    public UsuarioListDTO getCreador() {
        return creador;
    }

    public void setCreador(UsuarioListDTO creador) {
        this.creador = creador;
    }

    public UsuarioListDTO getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioListDTO destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isEsPublico() { return esPublico; }
    public void setEsPublico(boolean esPublico) { this.esPublico = esPublico; }
}
