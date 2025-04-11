package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        String endpoint = googleProps.url().geminiUrl() + googleProps.apiKey();
        String prompt = """
                як ти можеш охарактеризувати людину яка підписана на усі ці канали?
                
                ~~~
                %s
                ~~~
                тобі не потрібно охарактеризовувати кожен канал, тільки загальний підсумок.
                convert a message to markdown format.
                """.formatted(String.join("\n", channelNames));
        String jsonPayload = getRequestBody(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());


        return getAnswerFromJson(response.body());
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

    private String getAnswerFromJson(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        JsonArray candidates = jsonObject.getAsJsonArray("candidates");
        JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
        JsonObject content = firstCandidate.getAsJsonObject("content");
        JsonArray parts = content.getAsJsonArray("parts");
        JsonObject firstPart = parts.get(0).getAsJsonObject();
        return firstPart.get("text").getAsString();
    }

}
