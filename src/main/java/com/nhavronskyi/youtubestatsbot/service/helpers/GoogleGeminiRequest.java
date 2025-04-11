package com.nhavronskyi.youtubestatsbot.service.helpers;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GoogleGeminiRequest {

    private final HttpRequest request;
    private final Gson gson;
    private final String prompt;

    public GoogleGeminiRequest(String endpoint, String prompt, Gson gson) {
        this.gson = gson;
        this.prompt = getRequestBody(prompt);
        this.request = getHttpRequest(endpoint);
    }

    private HttpRequest getHttpRequest(String endpoint) {
        return HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(this.prompt))
                .build();
    }

    public HttpResponse<String> execute() throws IOException, InterruptedException {
        return HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String getRequestBody(String promptText) {
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", promptText);
        Map<String, Object> contents = new HashMap<>();
        contents.put("parts", List.of(part));
        payload.put("contents", List.of(contents));

        return gson.toJson(payload);
    }
}
