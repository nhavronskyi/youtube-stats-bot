package com.nhavronskyi.youtubestatsbot.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.nhavronskyi.youtubestatsbot.props.YoutubeProps;
import com.nhavronskyi.youtubestatsbot.service.impl.YouTubeAuthService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YouTubeConfig {
    @Bean
    GoogleCredential googleCredential(GsonFactory gsonFactory, YoutubeProps props, HttpTransport transport) {
        return new GoogleCredential().toBuilder()
                .setTransport(transport)
                .setJsonFactory(gsonFactory)
                .setClientSecrets(props.secret().clientId(), props.secret().clientSecret())
                .build()
                .setRefreshToken(props.secret().refreshToken());
    }

    @Bean
    YouTube youTube(GsonFactory gsonFactory, YouTubeAuthService authService) {
        return new YouTube.Builder(
                new NetHttpTransport(),
                gsonFactory,
                httpRequest -> httpRequest.getHeaders().setAuthorization("Bearer " + authService.accessToken()))
                .setApplicationName("youtube-stats-bot")
                .build();
    }

    @Bean
    GsonFactory jsonFactory() {
        return GsonFactory.getDefaultInstance();
    }

    @SneakyThrows
    @Bean
    HttpTransport httpTransport() {
        return GoogleNetHttpTransport.newTrustedTransport();
    }
}
