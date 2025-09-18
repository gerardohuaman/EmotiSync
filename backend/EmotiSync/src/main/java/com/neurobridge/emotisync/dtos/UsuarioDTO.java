package com.neurobridge.emotisync.dtos;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
    private Integer familiarDe;
    private Integer pacienteDe;
    private String institucion;
    private Integer nroColegiatura;
    private String rol;
    private String especialidad;

    public Integer getPacienteDe() {
        return pacienteDe;
    }

    public void setPacienteDe(Integer pacienteDe) {
        this.pacienteDe = pacienteDe;
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

    public Integer getFamiliarDe() {
        return familiarDe;
    }

    public void setFamiliarDe(Integer familiarDe) {
        this.familiarDe = familiarDe;
    }


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
