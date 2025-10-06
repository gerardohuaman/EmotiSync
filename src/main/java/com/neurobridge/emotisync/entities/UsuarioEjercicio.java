package com.neurobridge.emotisync.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "UsuarioEjercicio")
public class UsuarioEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioEjercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEjercicio", nullable = false)
    private Ejercicio ejercicio;

    @Column(name = "fechaRealizacion",  nullable = true)
    private LocalDate fechaRealizacion;

    @Column(name = "resultado", nullable = true, length = 255)
    private String resultado;

    public UsuarioEjercicio() {
    }

    public UsuarioEjercicio(int idUsuarioEjercicio, Usuario usuario, Ejercicio ejercicio, LocalDate fechaRealizacion, String resultado) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
        this.usuario = usuario;
        this.ejercicio = ejercicio;
        this.fechaRealizacion = fechaRealizacion;
        this.resultado = resultado;
    }

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
