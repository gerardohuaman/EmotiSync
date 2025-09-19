package com.neurobridge.emotisync.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Ejercicio")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEjercicio;

    @Column(name= "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name= "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "tipoEjercicio", nullable = false, length = 50)
    private String tipoEjercicio;

    public Ejercicio() {
    }

    public Ejercicio(int idEjercicio, String nombre, String descripcion, String tipoEjercicio) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoEjercicio = tipoEjercicio;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }
}
