package com.nhavronskyi.youtubestatsbot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram.bot")
public record TelegramProps(String token, String username) {
}
