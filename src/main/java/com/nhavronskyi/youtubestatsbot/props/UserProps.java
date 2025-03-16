package com.nhavronskyi.youtubestatsbot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram.users")
public record UserProps(String[] chatIds) {
}
