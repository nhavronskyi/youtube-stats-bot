package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YouTubeAuthService {
    private final GoogleCredential googleCredential;

    @SneakyThrows
    public String accessToken() {
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }
}
