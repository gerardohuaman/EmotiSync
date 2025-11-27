package com.neurobridge.emotisync.entities;


import jakarta.persistence.*;
//Terrible
@Entity
@Table(name = "Planes_suscripcion")
public class Planes_suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlanesSuscripcion;

    @Column(name = "nombre_plan", nullable = false, length = 30)
    private String nombre_plan;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    public Planes_suscripcion() {

    }

    public Planes_suscripcion(int idPlanesSuscripcion, String nombre_plan, double precio, String descripcion) {
        this.idPlanesSuscripcion = idPlanesSuscripcion;
        this.nombre_plan = nombre_plan;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getIdPlanesSuscripcion() {
        return idPlanesSuscripcion;
    }

    public void setIdPlanesSuscripcion(int idPlanesSuscripcion) {
        this.idPlanesSuscripcion = idPlanesSuscripcion;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
