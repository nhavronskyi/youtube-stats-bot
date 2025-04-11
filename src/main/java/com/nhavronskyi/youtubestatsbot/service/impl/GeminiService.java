package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nhavronskyi.youtubestatsbot.props.GoogleProps;
import com.nhavronskyi.youtubestatsbot.service.helpers.GoogleGeminiRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiService {
    private final GoogleProps googleProps;
    private final Gson gson;

    private static String getAnswerFromJson(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        JsonArray candidates = jsonObject.getAsJsonArray("candidates");
        JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
        JsonObject content = firstCandidate.getAsJsonObject("content");
        JsonArray parts = content.getAsJsonArray("parts");
        JsonObject firstPart = parts.get(0).getAsJsonObject();
        return firstPart.get("text").getAsString();
    }

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
        GoogleGeminiRequest googleGeminiRequest = new GoogleGeminiRequest(endpoint, prompt, gson);

        return getAnswerFromJson(googleGeminiRequest.execute().body());
    }
}
