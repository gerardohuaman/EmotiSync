package com.neurobridge.emotisync.dtos;

public class CrisisSintomaDTO {
    private Integer id;
    private Integer crisisId;   // FK -> Crisis
    private Integer sintomaId;  // FK -> Sintoma
    private Integer sever;
    private Integer observacion;
    private String notas;

    // ===== Getters & Setters =====
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrisisId() {
        return crisisId;
    }
    public void setCrisisId(Integer crisisId) {
        this.crisisId = crisisId;
    }

    public Integer getSintomaId() {
        return sintomaId;
    }
    public void setSintomaId(Integer sintomaId) {
        this.sintomaId = sintomaId;
    }

    public Integer getSever() {
        return sever;
    }
    public void setSever(Integer sever) {
        this.sever = sever;
    }

    public Integer getObservacion() {
        return observacion;
    }
    public void setObservacion(Integer observacion) {
        this.observacion = observacion;
    }

    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }
}
