package com.neurobridge.emotisync.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recursos")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "enlace", columnDefinition = "TEXT")
    private String enlace;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "fecha_cr", nullable = false)
    private LocalDate fechaCr;

    // FK -> usuarios (creador)
    @ManyToOne
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuario creador;

    // FK -> usuarios (destinatario)
    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    @Column(name = "es_publico", nullable = false)
    private boolean esPublico;

    // ===== Getters y Setters =====
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

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public Usuario getDestinatario() { return destinatario; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }

    public boolean isEsPublico() { return esPublico; }
    public void setEsPublico(boolean esPublico) { this.esPublico = esPublico; }
}
