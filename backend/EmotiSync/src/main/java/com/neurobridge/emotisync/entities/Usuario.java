package com.neurobridge.emotisync.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre", nullable = false,  length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "institucion", nullable = true, length = 150)
    private String institucion;

    @Column(name = "nroColegiatura", nullable = true)
    private Integer nroColegiatura;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;

    @Column(name = "especialidad", nullable = true, length = 30)
    private String especialidad;

    @Column(name = "familiarDe", nullable = true)
    private Integer familiarDe;

    @Column(name = "pacienteDe", nullable = true)
    private Integer pacienteDe;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String apellido, String email, String password, String telefono, LocalDate fechaNacimiento, String institucion, Integer nroColegiatura, String rol, String especialidad, Integer familiarDe, Integer pacienteDe) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.institucion = institucion;
        this.nroColegiatura = nroColegiatura;
        this.rol = rol;
        this.especialidad = especialidad;
        this.familiarDe = familiarDe;
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

    public Integer getPacienteDe() {
        return pacienteDe;
    }

    public void setPacienteDe(Integer pacienteDe) {
        this.pacienteDe = pacienteDe;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
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
