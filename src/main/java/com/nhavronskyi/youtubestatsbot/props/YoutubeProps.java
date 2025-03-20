package com.nhavronskyi.youtubestatsbot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.youtube")
public record YoutubeProps(String apiKey) {
}
