package com.nhavronskyi.youtubestatsbot.config;

import com.nhavronskyi.youtubestatsbot.props.TelegramProps;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Bean
    TelegramBot telegramBot(TelegramProps telegramProps) {
        return new TelegramBot(telegramProps.token());
    }
}
