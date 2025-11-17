package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;

public class PacienteListDTO {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private EspecialistaDTO especialista;
    private FamiliarDTO familiar;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EspecialistaDTO getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaDTO especialista) {
        this.especialista = especialista;
    }

    public FamiliarDTO getFamiliar() {
        return familiar;
    }

    public void setFamiliar(FamiliarDTO familiar) {
        this.familiar = familiar;
    }
}
