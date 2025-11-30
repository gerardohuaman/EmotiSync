package com.neurobridge.emotisync.dtos;

public class ChatDTO {
    private String mensaje;
    private String respuesta;

    public ChatDTO() {}

    public ChatDTO(String mensaje, String respuesta) {
        this.mensaje = mensaje;
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
