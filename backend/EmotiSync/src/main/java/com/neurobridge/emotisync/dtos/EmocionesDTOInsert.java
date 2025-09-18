package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Crisis;

public class EmocionesDTOInsert {
    private int idEmociones;
    private String tipoEmocion;
    private int intensidad;
    private Crisis crisis;

    public int getIdEmociones() {
        return idEmociones;
    }

    public void setIdEmociones(int idEmociones) {
        this.idEmociones = idEmociones;
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

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }
}
