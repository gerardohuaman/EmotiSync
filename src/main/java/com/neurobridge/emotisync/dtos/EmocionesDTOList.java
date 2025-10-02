package com.neurobridge.emotisync.dtos;

public class EmocionesDTOList {
    //mostrar
    private String tipoEmocion;
    private int intensidad;

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
