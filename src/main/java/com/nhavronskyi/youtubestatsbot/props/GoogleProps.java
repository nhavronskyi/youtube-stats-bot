package com.nhavronskyi.youtubestatsbot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.youtube")
public record GoogleProps(String apiKey, GoogleSecret secret, GoogleUrl url) {
    public record GoogleSecret(
            String clientId,
            String clientSecret,
            String refreshToken) {
    }

    public record GoogleUrl(
            String redirectUrl,
            String geminiUrl,
            String tokenUrl
    ) {
    }
}
