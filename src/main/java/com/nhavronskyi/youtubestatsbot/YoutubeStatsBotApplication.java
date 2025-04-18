package com.nhavronskyi.youtubestatsbot;

import com.nhavronskyi.youtubestatsbot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class YoutubeStatsBotApplication {
    private final BotService botService;

    @Bean
    public Supplier<String> sendMessageToUsers() {
        botService.sendMsgToAllUsers();
        return () -> "Message sent to all users";
    }

    public static void main(String[] args) {
        SpringApplication.run(YoutubeStatsBotApplication.class, args);
    }
}
