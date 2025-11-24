package com.neurobridge.emotisync.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Column(name = "especialidad", nullable = true, length = 30)
    private String especialidad;

    @Column(length = 30, unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialistaId")
    @JsonIgnore
    private Usuario especialista;

    @OneToMany(mappedBy = "especialista", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> pacientes; // <-- Esto ya lo tenías (¡bien!)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familiarId")
    @JsonIgnore  // <--- ¡AÑADE ESTA LÍNEA!
    private Usuario familiar;

    @OneToMany(mappedBy = "familiar", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> dependientes;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Diario> diarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Crisis> crisis;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String apellido, String email, String password, String telefono, LocalDate fechaNacimiento, String institucion, Integer nroColegiatura, String especialidad, String username, Usuario especialista, List<Usuario> pacientes, Usuario familiar, List<Usuario> dependientes, Boolean enabled, List<Rol> roles, List<Diario> diarios, List<Crisis> crisis) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.institucion = institucion;
        this.nroColegiatura = nroColegiatura;
        this.especialidad = especialidad;
        this.username = username;
        this.especialista = especialista;
        this.pacientes = pacientes;
        this.familiar = familiar;
        this.dependientes = dependientes;
        this.enabled = enabled;
        this.roles = roles;
        this.diarios = diarios;
        this.crisis = crisis;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Usuario getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Usuario especialista) {
        this.especialista = especialista;
    }

    public List<Usuario> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Usuario> pacientes) {
        this.pacientes = pacientes;
    }

    public Usuario getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Usuario familiar) {
        this.familiar = familiar;
    }

    public List<Usuario> getDependientes() {
        return dependientes;
    }

    public void setDependientes(List<Usuario> dependientes) {
        this.dependientes = dependientes;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Diario> getDiarios() {
        return diarios;
    }

    public void setDiarios(List<Diario> diarios) {
        this.diarios = diarios;
    }

    public List<Crisis> getCrisis() {
        return crisis;
    }

    public void setCrisis(List<Crisis> crisis) {
        this.crisis = crisis;
    }
}
