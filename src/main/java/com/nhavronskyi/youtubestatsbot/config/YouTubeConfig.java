package com.nhavronskyi.youtubestatsbot.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YouTubeConfig {
    @Bean
    YouTube youTube() {
        return new YouTube.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequest -> {
                })
                .setApplicationName("youtube-stats-bot")
                .build();
    }
}
