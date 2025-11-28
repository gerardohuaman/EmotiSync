package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class RecursoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String enlace;
    private String tipo;
    private LocalDate fechaCr;
    private Integer creadorId;
    private Integer destinatarioId;
    private boolean esPublico;
    private String nombreCreador;

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

    public Integer getCreadorId() { return creadorId; }
    public void setCreadorId(Integer creadorId) { this.creadorId = creadorId; }

    public Integer getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Integer destinatarioId) { this.destinatarioId = destinatarioId; }

    public boolean isEsPublico() { return esPublico; }
    public void setEsPublico(boolean esPublico) { this.esPublico = esPublico; }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }
}
