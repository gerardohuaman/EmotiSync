package com.neurobridge.emotisync.dtos;

public class TotalPacienteDTO {
    private String especialidad;
    private int cantidadPacientes;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getCantidadPacientes() {
        return cantidadPacientes;
    }

    public void setCantidadPacientes(int cantidadPacientes) {
        this.cantidadPacientes = cantidadPacientes;
    }
}
