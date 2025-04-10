package com.nhavronskyi.youtubestatsbot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.youtube")
public record YoutubeProps(String apiKey, YoutubeSecret secret) {
    public record YoutubeSecret(
            String clientId,
            String clientSecret,
            String redirectUri,
            String refreshToken,
            String token_uri) {
    }
}
