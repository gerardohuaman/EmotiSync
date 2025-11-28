package com.neurobridge.emotisync.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="Diario")
public class Diario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDiario;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "contenido", nullable = false, length = 255)
    private String contenido;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmociones")
    private Emociones emociones;

    public Diario() {
    }

    public Diario(int idDiario, String titulo, String contenido, LocalDate fecha, Usuario usuario, Emociones emociones) {
        this.idDiario = idDiario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.emociones = emociones;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Emociones getEmociones() {
        return emociones;
    }

    public void setEmociones(Emociones emociones) {
        this.emociones = emociones;
    }
}
