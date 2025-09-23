package com.neurobridge.emotisync.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sintomas")
public class Sintoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // SERIAL
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    // ===== Getters & Setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
