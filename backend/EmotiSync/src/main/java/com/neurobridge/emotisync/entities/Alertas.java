package com.neurobridge.emotisync.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Alertas")
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlerta;

    @Column(name = "tipo_alerta", length = 50)
    private String  tipo_alerta;

    @Column(name = "mensaje", length = 255)
    private String  mensaje;

    @Column(name = "nivel_alerta")
    private int  nivel_alerta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public  Alertas(){
    }

    public Alertas(int idAlerta, String tipo_alerta, String mensaje, int nivel_alerta, Usuario usuario){
        this.idAlerta = idAlerta;
        this.tipo_alerta = tipo_alerta;
        this.mensaje = mensaje;
        this.nivel_alerta = nivel_alerta;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNivel_alerta() {
        return nivel_alerta;
    }

    public void setNivel_alerta(int nivel_alerta) {
        this.nivel_alerta = nivel_alerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }
}
