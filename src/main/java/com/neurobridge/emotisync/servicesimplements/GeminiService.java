package com.neurobridge.emotisync.servicesimplements;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String obtenerRespuesta(String pregunta){
        String url = apiUrl + "?key=" + apiKey;

        String promptSistema = "Eres EmotiBot, un asistente empático de la app de salud mental EmotiSync. " +
                "Responde de forma breve, cálida y útil a pacientes. " +
                "Si detectas riesgo grave, sugiere buscar ayuda profesional. " +
                "Pregunta del usuario: " + pregunta;

        Map<String, Object> part = new HashMap<>();
        part.put("text", promptSistema);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            Map<String, Object> body = response.getBody();
            if(body != null && body.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
                if(!candidates.isEmpty()) {
                    Map<String, Object> contentResp = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> partsResp = (List<Map<String, Object>>) contentResp.get("parts");
                    if(!partsResp.isEmpty()) {
                        return (String)  partsResp.get(0).get("text");
                    }
                }
            }
            return "Lo siento, no pude procesar tu respuesta.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al conectar con el asistente" + e.getMessage();
        }
    }
}
