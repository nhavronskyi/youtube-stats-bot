package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.gson.Gson;
import com.nhavronskyi.youtubestatsbot.props.GoogleProps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {
    private final GoogleProps googleProps;
    private final Gson gson;

    @SneakyThrows
    public String getSummaryAboutSubscribers(List<String> channelNames) {
        String promptText = String.format("what do you think about person who watch these channels? %s", String.join(", ", channelNames));

        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", promptText);
        Map<String, Object> contents = new HashMap<>();
        contents.put("parts", List.of(part));
        payload.put("contents", List.of(contents));

        String jsonPayload = gson.toJson(payload);

        String endpoint = googleProps.url().geminiUrl() + googleProps.apiKey();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());


        return response.body();
    }

}
