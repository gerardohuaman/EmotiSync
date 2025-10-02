package com.neurobridge.emotisync.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "crisis_sintomas")
public class CrisisSintoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FK -> crisis
    @ManyToOne
    @JoinColumn(name = "crisis", nullable = false)
    private Crisis crisis;

    // FK -> sintomas
    @ManyToOne
    @JoinColumn(name = "sinto", nullable = false)
    private Sintoma sintoma;

    @Column(name = "sever", nullable = false)
    private int sever;

    @Column(name = "observacion", nullable = false)
    private int observacion;

    @Column(name = "notas", length = 255)
    private String notas;

    // ===== Getters & Setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Crisis getCrisis() { return crisis; }
    public void setCrisis(Crisis crisis) { this.crisis = crisis; }

    public Sintoma getSintoma() { return sintoma; }
    public void setSintoma(Sintoma sintoma) { this.sintoma = sintoma; }

    public int getSever() { return sever; }
    public void setSever(int sever) { this.sever = sever; }

    public int getObservacion() { return observacion; }
    public void setObservacion(int observacion) { this.observacion = observacion; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}
