package com.neurobridge.emotisync.dtos;

public class UsuarioAlertaCountDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private Long total_alertas;

    public UsuarioAlertaCountDTO(Integer idUsuario, String nombreUsuario, Long total_alertas) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.total_alertas = total_alertas;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getTotal_alertas() {
        return total_alertas;
    }

    public void setTotal_alertas(Long total_alertas) {
        this.total_alertas = total_alertas;
    }
}
