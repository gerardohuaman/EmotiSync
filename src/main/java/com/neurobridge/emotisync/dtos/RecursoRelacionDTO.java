package com.neurobridge.emotisync.dtos;

public class RecursoRelacionDTO {
    private int creadorId;
    private int destinatarioId;
    private boolean existeRelacion;

    public int getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(int creadorId) {
        this.creadorId = creadorId;
    }

    public int getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(int destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public boolean isExisteRelacion() {
        return existeRelacion;
    }

    public void setExisteRelacion(boolean existeRelacion) {
        this.existeRelacion = existeRelacion;
    }
}
