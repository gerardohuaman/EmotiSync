package com.example.emotisyncby.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Emociones")
public class Emociones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmociones;

    @Column(name = "tipoEmocion", nullable = false, length = 50)
    private String tipoEmocion;

    @Column(name = "intensidad", nullable = false)
    private int intensidad;

    @ManyToOne
    @JoinColumn(name = "idCrisis")
    private Crisis crisis;

    public Emociones() {
    }

    public Emociones(int idEmociones, String tipoEmocion, int intensidad, Crisis crisis) {
        this.idEmociones = idEmociones;
        this.tipoEmocion = tipoEmocion;
        this.intensidad = intensidad;
        this.crisis = crisis;
    }

    public int getIdEmociones() {
        return idEmociones;
    }

    public void setIdEmociones(int idEmociones) {
        this.idEmociones = idEmociones;
    }

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }

    public String getTipoEmocion() {
        return tipoEmocion;
    }

    public void setTipoEmocion(String tipoEmocion) {
        this.tipoEmocion = tipoEmocion;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
