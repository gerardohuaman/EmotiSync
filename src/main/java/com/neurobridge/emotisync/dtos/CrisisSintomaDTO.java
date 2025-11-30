package com.neurobridge.emotisync.dtos;

import com.neurobridge.emotisync.entities.Crisis;
import com.neurobridge.emotisync.entities.Sintoma;

public class CrisisSintomaDTO {
    private Integer id;
    private Integer sever;
    private Integer observacion;
    private String notas;
    private Crisis crisis;
    private Sintoma sintoma;

    // ===== Getters & Setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSever() { return sever; }
    public void setSever(Integer sever) { this.sever = sever; }

    public Integer getObservacion() { return observacion; }
    public void setObservacion(Integer observacion) { this.observacion = observacion; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public Crisis getCrisis() { return crisis; }
    public void setCrisis(Crisis crisis) { this.crisis = crisis; }

    public Sintoma getSintoma() { return sintoma; }
    public void setSintoma(Sintoma sintoma) { this.sintoma = sintoma; }
}