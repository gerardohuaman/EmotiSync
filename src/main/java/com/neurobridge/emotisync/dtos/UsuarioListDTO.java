package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioListDTO {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String institucion;
    private Integer nroColegiatura;
    private String rol;
    private String especialidad;
    private UsuarioListDTO familiar;
    private UsuarioListDTO especialista;

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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Integer getNroColegiatura() {
        return nroColegiatura;
    }

    public void setNroColegiatura(Integer nroColegiatura) {
        this.nroColegiatura = nroColegiatura;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public UsuarioListDTO getEspecialista() {
        return especialista;
    }

    public void setEspecialista(UsuarioListDTO especialista) {
        this.especialista = especialista;
    }

    public UsuarioListDTO getFamiliar() {
        return familiar;
    }

    public void setFamiliar(UsuarioListDTO familiar) {
        this.familiar = familiar;
    }
}
