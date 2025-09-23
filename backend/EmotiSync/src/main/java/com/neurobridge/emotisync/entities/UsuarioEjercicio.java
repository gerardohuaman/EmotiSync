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
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEjercicio")
    private Ejercicio idEjercicio;

    @Column(name = "fechaRealizacion",  nullable = true)
    private LocalDate fechaRealizacion;

    @Column(name = "resultado", nullable = true, length = 255)
    private String resultado;

    public UsuarioEjercicio() {
    }

    public UsuarioEjercicio(int idUsuarioEjercicio, Usuario idUsuario, Ejercicio idEjercicio, LocalDate fechaRealizacion, String resultado) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
        this.idUsuario = idUsuario;
        this.idEjercicio = idEjercicio;
        this.fechaRealizacion = fechaRealizacion;
        this.resultado = resultado;
    }

    public int getIdUsuarioEjercicio() {
        return idUsuarioEjercicio;
    }

    public void setIdUsuarioEjercicio(int idUsuarioEjercicio) {
        this.idUsuarioEjercicio = idUsuarioEjercicio;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Ejercicio getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Ejercicio idEjercicio) {
        this.idEjercicio = idEjercicio;
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
