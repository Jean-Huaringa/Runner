package com.cibertec.runner.dto.response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

public class JsonResponse {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeError(HttpServletResponse response, int status, String errorMessage) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        Map<String, String> errorBody = new HashMap<String, String>();
        errorBody.put("error", errorMessage);

        String jsonResponse = mapper.writeValueAsString(errorBody);
        response.getWriter().write(jsonResponse);
    }

    public static ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
    
}
